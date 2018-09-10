package org.altervista.lightdestory.sms.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.customs.CustomTableModel;
import org.altervista.lightdestory.sms.localization.Localization;

public class Database_Handler {
    
    private static Database_Handler instance = new Database_Handler();
    private String datapath = "jdbc:sqlite:";
    private final String enable_FK = "PRAGMA foreign_keys = ON;";
    //SMS QUERIES
    private final String SMS_createTable = "CREATE TABLE SMS (id integer PRIMARY KEY, message text NOT NULL, day real not null);";
    private final String SMS_insertTable = "INSERT INTO SMS (message, day) values (?,julianday('now'));";
    private final String SMS_selectTable = "SELECT id, message, date(day) from SMS;";
    private final String SMS_selectTableByText = "SELECT id from SMS where message = (?) ORDER by id DESC;";
    //CONTACTS QUERIES
    private final String CONTACTS_createTable = "CREATE TABLE Contacts (phonenumber char(10) PRIMARY KEY, firstname text NOT NULL, lastname text not null);";
    private final String CONTACTS_insertTable = "INSERT INTO Contacts (firstname, lastname, phonenumber) values (?,?,?);";
    private final String CONTACTS_updateTable = "UPDATE CONTACTS SET firstname = (?), lastname = (?) where phonenumber = (?);";
    private final String CONTACTS_selectAllTable = "SELECT firstname, lastname, phonenumber from Contacts;";
    private final String CONTACTS_selectDetailsTable = "SELECT firstname, lastname from Contacts where phonenumber = (?);";
    private final String CONTACTS_deleteTable = "DELETE FROM CONTACTS where phonenumber = (?);";
    private final String CONTACTS_deleteAllTable = "DELETE FROM CONTACTS;";
    //RECEIVE QUERIES
    private final String RECEIVE_createTable = "CREATE TABLE Receive (id_sms integer not null, id_contact integer NOT NULL, PRIMARY KEY(id_sms,id_contact), FOREIGN KEY(id_sms) references SMS(id) ON DELETE CASCADE, FOREIGN KEY(id_contact) references Contacts(phonenumber) ON DELETE CASCADE);";
    private final String RECEIVE_insertTable = "INSERT INTO Receive (id_sms, id_contact) values (?,?);";
    //TEMPLATES QUERIES
    private final String TEMPLATE_createTable = "CREATE TABLE TEMPLATE (id integer PRIMARY KEY, message text not null UNIQUE);";
    private final String TEMPLATE_insertTable = "INSERT INTO TEMPLATE (message) values (?);";
    private final String TEMPLATE_selectAllTable = "SELECT id, message from TEMPLATE;";
    private final String TEMPLATE_deleteTable = "DELETE FROM TEMPLATE where id = (?);";
    private final String TEMPLATE_deleteAllTable = "DELETE FROM TEMPLATE;";
    //JOIN-SELECT QUERY
    private final String JOIN_selectBySMS = "SELECT firstname, lastname, phonenumber from CONTACTS C, RECEIVE R where R.id_contact = C.phonenumber and R.id_sms = (?);";
    private final File database;
    private Connection con;
    private CustomTableModel tabledata;
    private String result;
    private PreparedStatement dynamic_query;
    
    private Database_Handler()
    {
        database = new File("sms.db");
        datapath = datapath + database.getName();
        tabledata = new CustomTableModel(null, null);
    }
    
    public static Database_Handler getInstance()
    {
        return instance;
    }
    
    public boolean exists()
    {
        return database.exists();
    }
    
    public void createDB()
    {
        try
        {
            if(connect()!=null)
            {
                Statement query = con.createStatement();
                query.execute(SMS_createTable);
                query.execute(CONTACTS_createTable);
                query.execute(RECEIVE_createTable);
                query.execute(TEMPLATE_createTable);
                query.close();
                con.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Connection connect()
    {
        try{
            con = DriverManager.getConnection(datapath);
            dynamic_query = con.prepareStatement(enable_FK);
            dynamic_query.execute();
            dynamic_query.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            con = null;
        }
        return con;
    }
    
    public void insertTemplate(String text)
    {
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(TEMPLATE_insertTable);
                dynamic_query.setString(1, text);
                dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
            }
            catch (SQLException ex) {
                if(ex.getErrorCode()==19)
                {
                    JOptionPane.showMessageDialog(null, Localization.getInstance().getText(Localization.JOP_DATABASE_TEMPLATE_UNIQUE_ERR_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    public void deleteTemplate(int... id)
    {
        if(connect()!=null)
        {
            try {
                if(id.length>0)
                {
                    
                    dynamic_query = con.prepareStatement(TEMPLATE_deleteTable);
                    dynamic_query.setInt(1, id[0]);
                }
                else
                {
                    dynamic_query = con.prepareStatement(TEMPLATE_deleteAllTable);
                }
                dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertSMS(String text)
    {
       if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(SMS_insertTable);
                dynamic_query.setString(1, text);
                dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    public int selectSMSByText(String text)
    {
        int id = -1;
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(SMS_selectTableByText);
                dynamic_query.setString(1, text);
                ResultSet res = dynamic_query.executeQuery();
                id = res.getInt(1);
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return id;
    }
    
    public ArrayList<Contact> selectContacts()
    {
        ArrayList<Contact> tmp = new ArrayList<>();
        if(connect()!=null)
        {
            try
            {
                dynamic_query = con.prepareStatement(CONTACTS_selectAllTable);
                ResultSet res = dynamic_query.executeQuery();
                while(res.next())
                {
                    tmp.add(new Contact(res.getString(1), res.getString(2), res.getString(3)));
                }
            }catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tmp;
    }
    
    public ArrayList<Contact> selectContactsBySMS(int ID)
    {
        ArrayList<Contact> tmp = new ArrayList<>();
        if(connect()!=null)
        {
            try
            {
                dynamic_query = con.prepareStatement(JOIN_selectBySMS);
                dynamic_query.setInt(1, ID);
                ResultSet res = dynamic_query.executeQuery();
                while(res.next())
                {
                    tmp.add(new Contact(res.getString(1), res.getString(2), res.getString(3)));
                }
            }catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tmp;
    }
    
    public void insertContact(Contact contact)
    {
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(CONTACTS_insertTable);
                dynamic_query.setString(1, contact.getFirstName());
                dynamic_query.setString(2, contact.getLastName());
                dynamic_query.setString(3, contact.getNumber());
                dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
                result = String.format(Localization.getInstance().getText(Localization.DATABASE_ADD_CONTACT_ID), contact.getFirstName(), contact.getLastName());
            } catch (SQLException ex) {
                if(ex.getErrorCode()==19)
                {
                    result = OverrideContact(contact);
                }
            }
        }
    }
    
    public void deleteContacts(String... num)
    {
        if(connect()!=null)
        {
            try {
                if(num.length>0)
                {
                    
                    dynamic_query = con.prepareStatement(CONTACTS_deleteTable);
                    dynamic_query.setString(1, num[0]);
                }
                else
                {
                    dynamic_query = con.prepareStatement(CONTACTS_deleteAllTable);
                }
                int arow = dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
                if(arow == 0 && num.length>0)
                {
                    result = Localization.getInstance().getText(Localization.JOP_DATABASE_NO_CTDEL_FOUND_ID);
                }
                else if(arow == 1 && num.length>0)
                {
                    result = String.format(Localization.getInstance().getText(Localization.JOP_DATABASE_CT_DEL_ID), num);
                }
                else
                {
                    result = Localization.getInstance().getText(Localization.JOP_DATABASE_CT_ALL_DEL_ID);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String OverrideContact(Contact contact)
    {
        try {
                dynamic_query = con.prepareStatement(CONTACTS_selectDetailsTable);
                dynamic_query.setString(1, contact.getNumber());
                ResultSet res = dynamic_query.executeQuery();
                String name = res.getString(1), surname = res.getString(2);
                dynamic_query.close();
                if(!(name.equals(contact.getFirstName()) && surname.equals(contact.getLastName())))
                {    
                    if(JOptionPane.showConfirmDialog(null, String.format(String.format(Localization.getInstance().getText(Localization.JOP_DATABASE_OVERRIDE_REQUEST_ID), contact.getNumber(), name, surname, contact.getFirstName(), contact.getLastName())), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        dynamic_query = con.prepareStatement(CONTACTS_updateTable);
                        dynamic_query.setString(1, contact.getFirstName());
                        dynamic_query.setString(2, contact.getLastName());
                        dynamic_query.setString(3, contact.getNumber());
                        dynamic_query.executeUpdate();
                        dynamic_query.close();
                        result = String.format(Localization.getInstance().getText(Localization.DATABASE_OVERRIDE_CONTACT_ID), name, surname, contact.getFirstName(), contact.getLastName()); 
                    }
                    else
                    {
                        result = String.format(Localization.getInstance().getText(Localization.DATABASE_NO_OVERRIDE_CONTACT_ID), contact.getFirstName(), contact.getLastName()); 
                    }
                }
                else
                {
                    result = "No overrride";
                }
            con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        return result;
    }
    
    public void insertReceive(int id, String phone)
    {
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(RECEIVE_insertTable);
                dynamic_query.setInt(1, id);
                dynamic_query.setString(2, phone);
                dynamic_query.executeUpdate();
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    public CustomTableModel getTableModel(){
        return tabledata;
    }
    
    public void UpdateTableTemplateMode()
    {
        tabledata.removeAllRows();
        tabledata.TemplateMode();
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(TEMPLATE_selectAllTable); 
                ResultSet res = dynamic_query.executeQuery();
                while(res.next())
                {
                    tabledata.addRow(new Object[] {res.getInt(1), res.getString(2)});
                }
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void UpdateTableSMSLogMode()
    {
        tabledata.removeAllRows();
        tabledata.SMSLogMode();
        if(connect()!=null)
        {
            try {
                dynamic_query = con.prepareStatement(SMS_selectTable); 
                ResultSet res = dynamic_query.executeQuery();
                while(res.next())
                {
                    tabledata.addRow(new Object[] {res.getInt(1), res.getString(2), res.getString(3)});
                }
                dynamic_query.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getResult()
    {
        return result;
    }
}

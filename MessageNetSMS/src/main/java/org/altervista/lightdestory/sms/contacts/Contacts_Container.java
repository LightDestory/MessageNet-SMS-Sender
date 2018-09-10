package org.altervista.lightdestory.sms.contacts;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.csv.CSV_Exporter;
import org.altervista.lightdestory.sms.csv.CSV_Importer;
import org.altervista.lightdestory.sms.customs.CustomTableModel;
import org.altervista.lightdestory.sms.database.Database_Handler;
import org.altervista.lightdestory.sms.localization.Localization;

public class Contacts_Container{
    
    private CustomTableModel tabledata;
    private ArrayList<Contact> library;
    private static Contacts_Container instance = new Contacts_Container();
    private String result;
    private int name_maxlength = 0;
    
    private Contacts_Container()
    {
        tabledata = new CustomTableModel(null, new Object[] {"First Name", "Last Name", "Phone Number"});
        library = new ArrayList<>();
    }
    
    public static Contacts_Container getInstance()
    {
        return instance;
    }
    
    public void AddContact(String firstName,String lastName,String number){
        if(!isAlreadyIn(number))
        {
            library.add(new Contact(firstName, lastName, number));
            result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADD_ID), firstName, lastName);
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            Contact c = getContactByNumber(number);
            if(!(firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) && 
                    JOptionPane.showConfirmDialog(null, String.format(String.format(Localization.getInstance().getText(Localization.JOP_DATABASE_OVERRIDE_REQUEST_ID), c.getNumber(), c.getFirstName(), c.getLastName(), firstName, lastName)), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                c.setFirstName(firstName);
                c.setLastName(lastName);
                result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADD_ID), firstName, lastName);
                JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
            }
        }
        UpdateTable();
        FindMaxLength();
    }
    
    public void AddContactFromLogger(int ID){
        String excluded = "";
        int count = 0;
        ArrayList<Contact> tmp = Database_Handler.getInstance().selectContactsBySMS(ID);
        if(tmp!=null)
        {
            for(Contact c : tmp)
            {
                if(!isAlreadyIn(c.getNumber()))
                {
                    library.add(c);
                    count++;
                }
                else
                {
                    excluded = String.format("%s%s %s\n", excluded, c.getFirstName(), c.getLastName());
                }
            }
            UpdateTable();
            FindMaxLength();
            if(!excluded.equals(""))
            {
                JOptionPane.showMessageDialog(null, String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_EXISTS_ID), excluded), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
            result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_ID), count);
        }
    }
    
    public void AddContactsFromCSV(String path, String type) {
        String excluded = "";
        int count = 0;
        ArrayList<Contact> tmp = CSV_Importer.getInstance().read(path, type);
        if(tmp!=null)
        {
            for(Contact c : tmp)
            {
                if(!isAlreadyIn(c.getNumber()))
                {
                    library.add(c);
                    count++;
                }
                else
                {
                    excluded = String.format("%s%s %s\n", excluded, c.getFirstName(), c.getLastName());
                }
            }
            UpdateTable();
            FindMaxLength();
            if(!excluded.equals(""))
            {
                JOptionPane.showMessageDialog(null, String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_EXISTS_ID), excluded), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
            result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_ID), count);
        }
    }
    
    public void AddContactsFromDatabase() {
        String excluded = "";
        int count = 0;
        ArrayList<Contact> tmp = Database_Handler.getInstance().selectContacts();
        if(tmp!=null)
        {
            for(Contact c : tmp)
            {
                if(!isAlreadyIn(c.getNumber()))
                {
                    library.add(c);
                    count++;
                }
                else
                {
                    excluded = String.format("%s%s %s\n", excluded, c.getFirstName(), c.getLastName());
                }
            }
            UpdateTable();
            FindMaxLength();
            if(!excluded.equals(""))
            {
                JOptionPane.showMessageDialog(null, String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_EXISTS_ID), excluded), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
            result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_ADDS_ID), count);
        }
    }
    
    public void RemoveContact(String num){
        for(Contact c : library)
        {
            if(c.getNumber().equals(num))
            {
                library.remove(c);
                result = String.format(Localization.getInstance().getText(Localization.JOP_CTC_DEL_ID), c.getFirstName(), c.getLastName(), c.getNumber());
                break;
            }
        }
        UpdateTable();
        FindMaxLength();
        JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void RemoveAll(){
        library.clear();
        UpdateTable();
        result = Localization.getInstance().getText(Localization.JOP_CTC_DELALL_ID);
        JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        name_maxlength = 0;
    }
    
    public void SwapContactName(String num){
        for(Contact c : library)
        {
            if(c.getNumber().equals(num))
            {
                c.SwapNames();
                result = Localization.getInstance().getText(Localization.JOP_CTC_SWAP_ID);
                break;
            }
        }
        UpdateTable();
        FindMaxLength();
        JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void SwapAllNames(){
        for(Contact c : library)
        {
            c.SwapNames();
        }
        UpdateTable();
        FindMaxLength();
        result = Localization.getInstance().getText(Localization.JOP_CTC_SWAP_ID);
        JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String getResult(){
        return result;
    }
    
    public CustomTableModel getTableModel(){
        return tabledata;
    }
    
    public int getNameMaxLength()
    {
        return name_maxlength;
    }
    
    public void SaveToCSV(String path, String type){
        CSV_Exporter.getInstance().writeToFile(library, path, type);
        result = CSV_Exporter.getInstance().getResult();
    }
    
    private void UpdateTable(){
        tabledata.removeAllRows();
        for(Contact c : library)
        {
            tabledata.addRow(c.getTableData());
        }
    }
    
    private void FindMaxLength()
    {
        name_maxlength = 0;
        for(Contact c : library)
        {
            int x = c.getFirstName().length();
            if(x>name_maxlength)
            {
                name_maxlength = x-2;
            }
        }
    }
    
    private boolean isAlreadyIn(String number){
        for(Contact c : library)
        {
            if(c.getNumber().equals(number))
            {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Contact> getContacts()
    {
        return library;
    }
    
    public Contact getContactByNumber(String number)
    {
        for(Contact c : library)
        {
            if(c.getNumber().equals(number))
            {
                return c;
            }
        }
        return null;
    }
    
    public String getAllPhoneNumbers()
    {
       String tmp = "";
       for(Contact c : library)
       {
           tmp = String.format("%s;%s", c.getNumber(), tmp);
       }
       tmp = tmp.substring(0, tmp.length()-1);
       return tmp;
    }
}

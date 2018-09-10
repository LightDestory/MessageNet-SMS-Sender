package org.altervista.lightdestory.sms.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.config.Configuration;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.contacts.Contacts_Container;
import org.altervista.lightdestory.sms.database.Database_Handler;
import org.altervista.lightdestory.sms.localization.Localization;

public class Sender_Thread extends Thread{
    
    private JLabel status_p;
    private HttpsURLConnection con;
    private DataOutputStream output;
    private BufferedReader input;
    private BufferedWriter logger;
    private final String User_Agent = "javaclient";
    private boolean status = false;;
    private String text, type;
    private boolean active = false;
    
    public Sender_Thread(String name, JLabel status_p)
    {
        super(name);
        this.status_p = status_p;
    }
    
    private void connectToAPI() throws IOException
    {
        URL API = Network_Helper.getInstance().getAPI(Configuration.getInstance().getAPI());
        con = (HttpsURLConnection) API.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", User_Agent);
        con.setDoOutput(true);
        output = new DataOutputStream(con.getOutputStream());
    }
    
    public void createJob(String text, String type)
    {
        this.text = text;
        this.type = type;
    }
    
    @Override
    public void run(){
        active = true;
        if(type.equals("single"))
        {
           sendSingle(); 
        }
        else
        {
           sendGroup(); 
        }
    }
    
    public boolean isActive()
    {
        return active;
    }
    
    private void sendGroup()
    {
        try {
            connectToAPI();
            String pars = Network_Helper.getInstance().getPOSTParam(text);
            System.out.println("\n" + pars);
            output.writeBytes(pars);
            output.flush();
            output.close();
            if(con.getResponseCode()==HttpsURLConnection.HTTP_OK)
            {
                RegisterOnDBGroup(); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Sender_Thread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, Localization.getInstance().getText(Localization.JOP_SETTING_TAB_INVALID_API_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
        } finally{
        die(closeConnection());
        }
    }
    
    private void sendSingle()
    {
        ArrayList<Contact> contacts = Contacts_Container.getInstance().getContacts();
        int i = 1, max = contacts.size(), codeJob = 500, sms_id = -1;
        boolean first = true;
        try {
            for(Contact c : contacts)
            {
                connectToAPI();
                status_p.setText(String.format(Localization.getInstance().getText(Localization.SMS_TAB_SENDING_ID), String.format("%d/%d", i, max)));
                String pars = Network_Helper.getInstance().getPOSTParam(text.replace("%n", c.getFirstName()), c.getNumber());
                System.out.println("\n" + pars);
                output.writeBytes(pars);
                output.flush();
                output.close();
                codeJob = closeConnection();
                if(codeJob!= HttpsURLConnection.HTTP_OK)
                {
                    break;
                }
                if(first)
                {
                    Database_Handler.getInstance().insertSMS(text);
                    sms_id = Database_Handler.getInstance().selectSMSByText(text);
                    first = false;
                }
                RegisterOnDBSingle(sms_id, c);
                i++;
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
                Logger.getLogger(Sender_Thread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sender_Thread.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            die(codeJob);
        }
    }
    
    private void RegisterOnDBGroup()
    {
        Database_Handler.getInstance().insertSMS(text);
        int sms_id = Database_Handler.getInstance().selectSMSByText(text);
        for(Contact c : Contacts_Container.getInstance().getContacts())
            {
                    Database_Handler.getInstance().insertContact(c);
                    Database_Handler.getInstance().insertReceive(sms_id, c.getNumber());
            }
    }
    
    private void RegisterOnDBSingle(int id, Contact c)
    {
        Database_Handler.getInstance().insertContact(c);
        Database_Handler.getInstance().insertReceive(id, c.getNumber());
    }
    
    private int closeConnection()
    {
        int codeJob = HttpsURLConnection.HTTP_INTERNAL_ERROR;
        try {
            codeJob = con.getResponseCode();
            System.out.println("CODE: "+ codeJob);
            if(codeJob == HttpsURLConnection.HTTP_OK)
            {
                input = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String tmp = "", name = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime()) + ".log";
                logger = new BufferedWriter(new FileWriter(name));
                while((tmp = input.readLine()) != null)
                {
                    logger.write(tmp);
                    logger.newLine();
                }
               logger.flush();
               input.close();
               logger.close();
            }
            con.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Sender_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeJob;
    }
    
    private void die(int code)
    {
        Database_Handler.getInstance().UpdateTableSMSLogMode();
        status_p.setText(Network_Helper.getInstance().checkJob(code));
        active = false;
    }
}

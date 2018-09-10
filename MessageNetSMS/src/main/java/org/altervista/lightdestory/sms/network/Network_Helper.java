package org.altervista.lightdestory.sms.network;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.config.Configuration;
import org.altervista.lightdestory.sms.contacts.Contacts_Container;
import org.altervista.lightdestory.sms.localization.Localization;

public class Network_Helper {
    
    private static Network_Helper instance = new Network_Helper();
    
    public String checkJob(int code)
    {
        String result = "";
        switch (code)
            {
                case HttpsURLConnection.HTTP_OK:
                    result = Localization.getInstance().getText(Localization.JOP_SMS_TAB_CODE200_ID);
                    break;
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    result = Localization.getInstance().getText(Localization.JOP_SMS_TAB_CODE401_ID);
                    break;
                case HttpsURLConnection.HTTP_PAYMENT_REQUIRED:
                    result = Localization.getInstance().getText(Localization.JOP_SMS_TAB_CODE402_ID);
                    break;
                default: //HTTP_INTERNAL_ERROR 500
                    result = Localization.getInstance().getText(Localization.JOP_SMS_TAB_CODE500_ID);
                    break;
            }
        if(code==HttpsURLConnection.HTTP_OK)
        {
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    
    public static Network_Helper getInstance()
    {
        return instance;
    }
    
    public String getPOSTParam(String text, String... nums)
    {
        String username, password, sender, format, report, number;
        try
        {
            username = URLEncoder.encode(Configuration.getInstance().getUsername(), "UTF-8");
            password = URLEncoder.encode(Configuration.getInstance().getPassword(), "UTF-8");
            sender = URLEncoder.encode(Configuration.getInstance().getSender(), "UTF-8");
            format = URLEncoder.encode(Configuration.getInstance().getFormat(), "UTF-8");
            report = URLEncoder.encode(Configuration.getInstance().isReport() ? "1" : "0", "UTF-8");
            if(nums.length>0)
            {
                number = String.format("%s", nums[0]);
            }
            else
            {
                number = Contacts_Container.getInstance().getAllPhoneNumbers();
            }
            number = URLEncoder.encode(number, "UTF-8");
            return String.format("auth_userid=%s&auth_password=%s&destination=%s&text=%s&sender=%s&report=%s&format=%s", username, password, number,
                text, sender, report, format);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Network_Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public URL getAPI(String url)
    {
        try{
            URL API = new URL(url);
            return API;
        } catch (MalformedURLException ex) {
            return null;
        }
    }
}

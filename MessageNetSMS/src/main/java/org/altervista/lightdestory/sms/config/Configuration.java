package org.altervista.lightdestory.sms.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.localization.Localization;
import org.altervista.lightdestory.sms.network.Network_Helper;

public class Configuration {
    
    private static Configuration instance = new Configuration();
    private String result;
    private Properties config;
    private final File file;
    private HashMap<String,String> prop;
    
    private Configuration()
    {
        config = new Properties();
        file  = new File("app.properties");
        prop = new HashMap<String,String>()
        {{
            put("username","");
            put("password", "");
            put("sender", "web");
            put("format", "json");
            put("api","https://api.messagenet.com/api/send_sms/");
            put("over-limit", "0");
            put("group-up", "1");
            put("report", "1");
            put("language", "english");
            put("sms-price","0");
        }};
    }
    
    public boolean ImportConfiguration()
    {
        String invalidprop = "";
        config.clear();
        try
        {
            config.load(new FileInputStream(file));
            for(HashMap.Entry<String,String> entry : prop.entrySet())
            {
                String tmp = entry.getKey();
                switch (tmp)
                {
                    case "report":
                        if(config.getProperty(tmp).equals("1") || config.getProperty(tmp).equals("0"))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else{
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "sender":
                        if(config.getProperty(tmp).length()==10 || config.getProperty(tmp).equals("web"))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else
                        {
                            invalidprop = String.format("%s\n%s", invalidprop, tmp);
                        }
                        break;
                    case "format":
                        if(config.getProperty(tmp).equals("json") || config.getProperty(tmp).equals("plist") || config.getProperty(tmp).equals("xml"))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else{
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "api":
                        if(Network_Helper.getInstance().getAPI(config.getProperty(tmp))!=null)
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else
                        {
                            invalidprop = String.format("%s\n%s", invalidprop, tmp);
                        }
                        break;
                    case "over-limit":
                        if(config.getProperty(tmp).equals("1") || config.getProperty(tmp).equals("0"))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else{
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "group-up":
                        if(config.getProperty(tmp).equals("1") || config.getProperty(tmp).equals("0"))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        else{
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "language":
                        if(Localization.getInstance().isLocale(config.getProperty(tmp)))
                        {
                            entry.setValue(config.getProperty(tmp).toLowerCase());
                        }
                        else{
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "sms-price":
                        try{
                            double price = Double.parseDouble(config.getProperty(tmp).replace(",", "."));
                            entry.setValue(config.getProperty(tmp));
                        }
                        catch(NullPointerException | NumberFormatException ex)
                        {
                            invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        }
                        break;
                    case "username":
                    case "password":
                        if(!config.getProperty(tmp).equals(""))
                        {
                            entry.setValue(config.getProperty(tmp));
                        }
                        break;
                    default:
                        invalidprop = String.format("%s\n%s", invalidprop, tmp); 
                        break; 
                }
            }
            if(!invalidprop.equals(""))
            {
                JOptionPane.showMessageDialog(null, String.format(Localization.getInstance().getText(Localization.JOP_CONF_IMPORT_INVALID_ID), invalidprop), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
            result = Localization.getInstance().getText(Localization.JOP_CONF_IMPORT_ID);
            return true;
        }
        catch(FileNotFoundException ex)
        {
            result = Localization.getInstance().getText(Localization.JOP_CONF_IMPORT_FNFEX_ID);
            return false;
        } catch (IOException ex)
        {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void ExportConfiguration()
    {
        config.clear();
        try {
            config.putAll(prop);
            config.store(new FileOutputStream(file), null);
            result = Localization.getInstance().getText(Localization.JOP_CONF_EXPORT_ID);
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex )
        {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            result = Localization.getInstance().getText(Localization.JOP_CONF_EXPORT_IOEX_ID);
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Configuration getInstance()
    {
        return instance;
    }
    
    public String getUsername()
    {
        return prop.get("username");
    }
    
    public String getPassword()
    {
        return prop.get("password");
    }
    
    public String getSender()
    {
        return prop.get("sender");
    }
    
    public String getFormat()
    {
        return prop.get("format");
    }
    
    public String getAPI()
    {
        return prop.get("api");
    }
    
    public double getPrice()
    {
        return Double.parseDouble(prop.get("sms-price").replace(",", "."));
    }
    
    public void setAccount(String username, String password)
    {
        prop.put("username", username);
        prop.put("password", password);
    }
    
    public void setSender(String sender)
    {
        prop.put("sender", sender);
    }
    
    public void setFormat(String format)
    {
        prop.put("format", format);
    }
    
    public void setAPI(String url)
    {
        prop.put("api", url);
    }
    
    public String getLanguage()
    {
        return prop.get("language");
    }
    
    public void setLanguage(String language)
    {
        prop.put("language", language.toLowerCase());
    }
    
    public boolean isOverLimit()
    {
        return prop.get("over-limit").equals("1");
    }
    
    public boolean isGroupUp()
    {
        return prop.get("group-up").equals("1");
    }
    
    public boolean isReport()
    {
        return prop.get("report").equals("1");
    }
    
    public void setOverLimit(boolean overlimit)
    {
        prop.put("over-limit", (overlimit == true ? "1" : "0"));
    }
    
    public void setGroupUp(boolean group)
    {
        prop.put("group-up", (group == true ? "1" : "0"));
    }
    
    public void setReport(boolean report)
    {
        prop.put("report", (report == true ? "1" : "0"));
    }
    
    public void setPrice(String price)
    {
        prop.put("sms-price", price);
    }
    
    public String getResult()
    {
        return result;
    }
}

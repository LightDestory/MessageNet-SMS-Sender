package org.altervista.lightdestory.sms.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.csv.format.CSV_Formatter;
import org.altervista.lightdestory.sms.localization.Localization;

public class CSV_Exporter {
    
    private static CSV_Exporter instance = new CSV_Exporter();
    private BufferedWriter writer;
    private String result;
    
    public static CSV_Exporter getInstance()
    {
        return instance;
    }
    
    public void writeToFile(ArrayList<Contact> list, String path, String type)
    {
        try {
            if(!path.endsWith(".csv"))
            {
                path = String.format("%s.csv", path);
            }
            writer = new BufferedWriter(new FileWriter(path));
            for(Contact c : list)
            {
                writer.write(CSV_Formatter.getInstance().ContactToString(c, type));
            }
            writer.close();
            result = Localization.getInstance().getText(Localization.JOP_CSV_EXPORT_ID);
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            result = Localization.getInstance().getText(Localization.JOP_CSV_EXPORT_IOEX_ID);
            JOptionPane.showMessageDialog(null, String.format("%s\n%s",result, ex.toString()), Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getResult()
    {
        return result;
    }
    
}

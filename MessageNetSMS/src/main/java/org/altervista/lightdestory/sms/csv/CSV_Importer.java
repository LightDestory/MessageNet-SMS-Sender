package org.altervista.lightdestory.sms.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.csv.format.CSV_Formatter;
import org.altervista.lightdestory.sms.localization.Localization;

public class CSV_Importer {
    
    private static CSV_Importer instance = new CSV_Importer();
    private ArrayList<Contact> imports = new ArrayList<>();
    private BufferedReader reader;
    private String result;
    
    public static CSV_Importer getInstance()
    {
        return instance;
    }
    
    public ArrayList<Contact> read(String path, String type)
    {
        imports.clear();
        int line_count = 1;
        String line;
        try {
            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null)
            {
                imports.add(CSV_Formatter.getInstance().StringToContact(line, type));
                line_count++;
            }
            reader.close();
            return imports;
        } catch (FileNotFoundException ex) {
            result = Localization.getInstance().getText(Localization.JOP_CSV_IMPORT_FNFEX_ID);
            JOptionPane.showMessageDialog(null, result, Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IOException | ArrayIndexOutOfBoundsException ex2) {
            result = Localization.getInstance().getText(Localization.JOP_CSV_IMPORT_IOEX_ID);
            JOptionPane.showMessageDialog(null, String.format(result, line_count), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String getResult()
    {
        return result;
    }
}

package org.altervista.lightdestory.sms.csv.format;

import org.altervista.lightdestory.sms.contacts.Contact;

public class CSV_Formatter {
    
    private final String FORMATS[];
    private static CSV_Formatter instance = new CSV_Formatter();
    
    private CSV_Formatter()
    {
        FORMATS  = new String[]
        {
            "FirstName-LastName-PhoneNumber", "LatName-FirstName-PhoneNumber",
            "PhoneNumber-FirstName-LastName", "PhoneNumber-LatName-FirstName",
            "PhoneNumber-FullName", "FullName-PhoneNumber"
        };
    }
    
    public static CSV_Formatter getInstance()
    {
        return instance;
    }
    
    public String[] getFormats()
    {
        return FORMATS;
    }
    
    public String ContactToString(Contact c, String type)
    {
        switch(type)
        {
            case "FirstName-LastName-PhoneNumber":
                return String.format("%s;%s;%s\n", c.getFirstName(), c.getLastName(), c.getNumber());
            case "LatName-FirstName-PhoneNumber":
                return String.format("%s;%s;%s\n", c.getLastName(), c.getFirstName(), c.getNumber());
            case "PhoneNumber-FirstName-LastName":
                return String.format("%s;%s;%s\n", c.getNumber(), c.getFirstName(), c.getLastName());
            case "PhoneNumber-LatName-FirstName":
                return String.format("%s;%s;%s\n", c.getNumber(), c.getLastName(), c.getFirstName());
            case "PhoneNumber-FullName":
                return String.format("%s;%s %s\n", c.getNumber(), c.getFirstName(), c.getLastName());
            default: //FullName-PhoneNumber
                return String.format("%s %s;%s\n", c.getFirstName(), c.getLastName(), c.getNumber());
        }
    }
    
    public Contact StringToContact(String data, String type) throws ArrayIndexOutOfBoundsException
    {
        String lastName, rawdata[];
        data = data.replace("\"", "").replace(",", ";");
        switch(type)
        {
            case "FirstName-LastName-PhoneNumber":
                rawdata = data.split(";");
                return new Contact(rawdata[0], rawdata[1], rawdata[2]);
            case "LatName-FirstName-PhoneNumber":
                rawdata = data.split(";");
                return new Contact(rawdata[1], rawdata[0], rawdata[2]);
            case "PhoneNumber-FirstName-LastName":
                rawdata = data.split(";");
                return new Contact(rawdata[1], rawdata[2], rawdata[0]);
            case "PhoneNumber-LatName-FirstName":
                rawdata = data.split(";");
                return new Contact(rawdata[2], rawdata[1], rawdata[0]);
            case "PhoneNumber-FullName":
                rawdata = data.split(";");
                lastName = rawdata[1].substring(rawdata[1].indexOf(" ")+1, rawdata[1].length());
                rawdata[1] = rawdata[1].substring(0, rawdata[1].indexOf(" "));
                return new Contact(rawdata[1], lastName, rawdata[0]);
            default: //FullName-PhoneNumber
                rawdata = data.split(";");
                lastName = rawdata[0].substring(rawdata[0].indexOf(" ")+1, rawdata[0].length());
                rawdata[0] = rawdata[0].substring(0, rawdata[0].indexOf(" "));
                return new Contact(rawdata[0], lastName, rawdata[1]);
        }
    }
}

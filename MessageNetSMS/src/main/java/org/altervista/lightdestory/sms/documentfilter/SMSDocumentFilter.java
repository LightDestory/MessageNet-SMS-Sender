package org.altervista.lightdestory.sms.documentfilter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.altervista.lightdestory.sms.api.MessageNet;
import org.altervista.lightdestory.sms.config.Configuration;
import org.altervista.lightdestory.sms.contacts.Contacts_Container;
import org.altervista.lightdestory.sms.localization.Localization;

public class SMSDocumentFilter extends PlainDocument {
    
    private final JTextArea Component;
    private static final String GSM = "@£$¥èéùìòÇØøÅåΔ_ΦΓΛΩΠΨΣΘΞ^{}\\[~]|€ÆæßÉ !\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";
    private char Illegal;
    
    public SMSDocumentFilter(JTextArea Component) {
        this.Component = Component;
    }
    
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(isValidGSM(str.toCharArray()) == false){
            JOptionPane.showMessageDialog(null, String.format(Localization.getInstance().getText(Localization.JOP_ILLEGAL_CHAR_ID), Illegal), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if((str.length()>(Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength())
                || (Component.getText()+str).length()>(Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength()))
                && Configuration.getInstance().isOverLimit() == false)
        {
                JOptionPane.showMessageDialog(null, Localization.getInstance().getText(Localization.JOP_SINGLE_SMS_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
                return;
        }
            super.insertString(offs, str, a);
    }
    
    private boolean isValidGSM(char[] chars)
    {
        for(char x : chars)
        {
            if(GSM.indexOf(x) == -1)
            {
                Illegal = x;
                return false;
            }
        }
        return true;
    }
}

package org.altervista.lightdestory.sms;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.UIManager;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.contacts.Contacts_Container;
import org.altervista.lightdestory.sms.customs.CustomTableModel;
import org.altervista.lightdestory.sms.database.Database_Handler;
import org.altervista.lightdestory.sms.localization.Localization;

public class Viewer extends javax.swing.JDialog {
    private CustomTableModel tabledata;
    private Font Verdana = new Font("Verdana", Font.PLAIN, 11);
    private int ID;
    public Viewer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabledata = new CustomTableModel(null, new Object[] {"First Name", "Last Name", "Phone Number"});
        SMSContactLogger.setModel(tabledata);
        UIManager.put("Table.font", Verdana);
        UIManager.put("Label.font", Verdana);
        this.setTitle(Localization.getInstance().getText(Localization.VIEWER_TITLE_ID));
    }

    public void changeLanguage()
    {
        this.setTitle(Localization.getInstance().getText(Localization.VIEWER_TITLE_ID));
        text_info.setText(Localization.getInstance().getText(Localization.VIEWER_SMS_TEXT_ID));
        SMSContactLogger.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_NAME_ID));
        SMSContactLogger.getTableHeader().getColumnModel().getColumn(1).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_SURNAME_ID));
        SMSContactLogger.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_PHONE_ID));
    }
    
    public void load(int ID, String text)
    {
        this.ID = ID;
        sms_text.setText(String.format("<html>%s</html>", text));
        tabledata.removeAllRows();
        for(Contact c : Database_Handler.getInstance().selectContactsBySMS(ID))
        {
            tabledata.addRow(c.getTableData());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        SMSContactLogger = new javax.swing.JTable();
        text_info = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        sms_text = new javax.swing.JLabel();
        LogToLibrary = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        SMSContactLogger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(SMSContactLogger);

        text_info.setText("Message:");

        LogToLibrary.setText("Load contacts into library");
        LogToLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogToLibraryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sms_text, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addComponent(LogToLibrary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_info)
                    .addComponent(sms_text, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LogToLibrary)
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LogToLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogToLibraryActionPerformed
        Contacts_Container.getInstance().AddContactFromLogger(ID);
    }//GEN-LAST:event_LogToLibraryActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Viewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Viewer dialog = new Viewer(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogToLibrary;
    private javax.swing.JTable SMSContactLogger;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel sms_text;
    private javax.swing.JLabel text_info;
    // End of variables declaration//GEN-END:variables
}

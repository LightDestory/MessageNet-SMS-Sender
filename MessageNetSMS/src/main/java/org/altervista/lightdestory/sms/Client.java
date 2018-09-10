package org.altervista.lightdestory.sms;

import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.altervista.lightdestory.sms.contacts.Contacts_Container;
import org.altervista.lightdestory.sms.documentfilter.SMSDocumentFilter;
import org.altervista.lightdestory.sms.api.MessageNet;
import org.altervista.lightdestory.sms.config.Configuration;
import org.altervista.lightdestory.sms.contacts.Contact;
import org.altervista.lightdestory.sms.database.Database_Handler;
import org.altervista.lightdestory.sms.localization.Localization;
import org.altervista.lightdestory.sms.network.Network_Helper;
import org.altervista.lightdestory.sms.network.Sender_Thread;

public class Client extends javax.swing.JFrame {

    private Timer worker = new Timer();
    private JFileChooser CSVFile;
    private Sender_Thread sender;
    private Viewer sms_track;
    
    public Client() {
        initComponents();
        sms_track = new Viewer(this, true);
        DestTable.setModel(Contacts_Container.getInstance().getTableModel());
        DBTable.setModel(Database_Handler.getInstance().getTableModel());
        setTableListeners();
        startTimerActions();
        LoadConfiguration();
        CSVFile = new JFileChooser();
        CSVFile.setMultiSelectionEnabled(false);
        CSVFile.setAcceptAllFileFilterUsed(false);
        CSVFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        CSVFile.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        if(!Database_Handler.getInstance().exists())
        {
            Database_Handler.getInstance().createDB();
        }
        sender = new Sender_Thread("Application-Sender", Status);
    }
    
    private void setTableListeners()
    {
        DBTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable)e.getSource();
                if (e.getClickCount() == 2 && target.getTableHeader().getColumnModel().getColumn(1).getHeaderValue().toString().equals("Template")) {
                    int row = target.getSelectedRow();
                    JOptionPane.showMessageDialog(rootPane, String.format("ID: %s\n<html><body><p style='width: 300px'>%s</p></body></html>", target.getValueAt(row, 0),target.getValueAt(row, 1)), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID),JOptionPane.INFORMATION_MESSAGE);
                }
                else if(e.getClickCount() == 2 && target.getTableHeader().getColumnModel().getColumn(1).getHeaderValue().toString().equals("Message")) {
                    int row = target.getSelectedRow();
                    sms_track.load(Integer.parseInt(target.getValueAt(row, 0).toString()), target.getValueAt(row,1).toString());
                    sms_track.setVisible(true);
                }
            }
        });
        DestTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable)e.getSource();
                if (e.getClickCount() == 2) {
                    int row = target.getSelectedRow();
                    ContactFirstName.setText(target.getValueAt(row, 0).toString());
                    ContactLastName.setText(target.getValueAt(row, 1).toString());
                    ContactNumber.setText(target.getValueAt(row, 2).toString());
                }
            }
        });
    }
    
    private void startTimerActions(){
        worker.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int num = (SMStext.getText().length()+159)/MessageNet.SINGLE_SMS_SIZE;
                limit.setText(String.format("%d/%d", SMStext.getText().length(), (Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength())));
                SMSSent.setText(String.format("%d", num));
                Cost.setText(String.format("%.3f€", (double)num*Configuration.getInstance().getPrice()*DestTable.getRowCount()));
            }
        }, 0, 200);
    }
    
    private void LoadConfiguration()
    {
        for(String lang : Localization.getInstance().getLangsList())
        {
            LanguageList.addItem(lang);
        }
        CurrAPI.setText(Configuration.getInstance().getAPI());
        if(Configuration.getInstance().ImportConfiguration())
        {
            if(Configuration.getInstance().isOverLimit())
            {
                OverLimit.setSelected(true);
                SMSSentInfo.setVisible(true);
                SMSSent.setVisible(true);
            }
            if(Configuration.getInstance().isGroupUp() == false)
            {
                SingleSend.setSelected(true);
                CustomizationInfo.setVisible(true);
            }
            if(Configuration.getInstance().isReport() == false)
            {
                DisableReport.setSelected(true);
            }
            if(!Configuration.getInstance().getUsername().equals("") || !Configuration.getInstance().getPassword().equals(""))
            {
                UseAccount.setText(String.format("%s (%s)", Configuration.getInstance().getUsername(), Configuration.getInstance().getSender()));
            }
            if(Configuration.getInstance().getPrice()!=0)
            {
                SingleCostInfo.setText(String.format("1 SMS = %.4f", Configuration.getInstance().getPrice()));
            }
            ResponseList.setSelectedItem(Configuration.getInstance().getFormat());
            LanguageList.setSelectedItem(Configuration.getInstance().getLanguage());
            setGUILanguage();
            setTitle("MessageNet Sender");
        }
        Status.setText(Configuration.getInstance().getResult());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SendModeOptionGroup = new javax.swing.ButtonGroup();
        ReportOptionGroup = new javax.swing.ButtonGroup();
        TabbedClient = new javax.swing.JTabbedPane();
        SenderTab = new javax.swing.JPanel();
        SMSPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SMStext = new javax.swing.JTextArea();
        limitinfo = new javax.swing.JLabel();
        limit = new javax.swing.JLabel();
        SMSSentInfo = new javax.swing.JLabel();
        SMSSent = new javax.swing.JLabel();
        CostInfo = new javax.swing.JLabel();
        Cost = new javax.swing.JLabel();
        CustomizationInfo = new javax.swing.JLabel();
        OverLimit = new javax.swing.JCheckBox();
        SendButton = new javax.swing.JButton();
        DatabasePane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DBTable = new javax.swing.JTable();
        SMSLogger = new javax.swing.JPanel();
        ShowAllSMS = new javax.swing.JButton();
        DBTemplatePane = new javax.swing.JPanel();
        AddTemplate = new javax.swing.JButton();
        LoadTemplate = new javax.swing.JButton();
        ShowAllTemplates = new javax.swing.JButton();
        DeleteTemplate = new javax.swing.JButton();
        DeleteAllTemplates = new javax.swing.JButton();
        Database_DoubleClick = new javax.swing.JLabel();
        ContactsTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DestTable = new javax.swing.JTable();
        AddContactPane = new javax.swing.JPanel();
        FirstNameInfo = new javax.swing.JLabel();
        LastNameInfo = new javax.swing.JLabel();
        ContactNumberInfo = new javax.swing.JLabel();
        ContactFirstName = new javax.swing.JTextField();
        ContactLastName = new javax.swing.JTextField();
        ContactNumber = new javax.swing.JFormattedTextField();
        AddContactButton = new javax.swing.JButton();
        CSVPane = new javax.swing.JPanel();
        LoadCSV = new javax.swing.JButton();
        CSVFormatInfo = new javax.swing.JLabel();
        CSVFormatCombo = new javax.swing.JComboBox<>();
        SaveCSV = new javax.swing.JButton();
        ManageLibraryPane = new javax.swing.JPanel();
        DeleteContactButton = new javax.swing.JButton();
        DeleteContactsButton = new javax.swing.JButton();
        SwapContactButton = new javax.swing.JButton();
        SwapContactsButton = new javax.swing.JButton();
        DatabaseLibraryPane = new javax.swing.JPanel();
        DeleteContactDatabase = new javax.swing.JButton();
        SaveContactDatabase = new javax.swing.JButton();
        DeleteAllContactsDatabase = new javax.swing.JButton();
        LoadFromDatabase = new javax.swing.JButton();
        SaveAllContactsDatabase = new javax.swing.JButton();
        ConfigTab = new javax.swing.JPanel();
        AccountPane = new javax.swing.JPanel();
        UsernameInfo = new javax.swing.JLabel();
        UsernameBox = new javax.swing.JTextField();
        PasswordInfo = new javax.swing.JLabel();
        PasswordBox = new javax.swing.JPasswordField();
        SenderInfo = new javax.swing.JLabel();
        SenderBox = new javax.swing.JFormattedTextField();
        ShowPass = new javax.swing.JCheckBox();
        SenderNote = new javax.swing.JLabel();
        SetAccount = new javax.swing.JButton();
        SMSCostPane = new javax.swing.JPanel();
        SetCost = new javax.swing.JButton();
        SMSCostInfo = new javax.swing.JLabel();
        CheckCost = new javax.swing.JButton();
        SMSCost = new javax.swing.JTextField();
        ApiPane = new javax.swing.JPanel();
        CurrAPIInfo = new javax.swing.JLabel();
        CurrAPI = new javax.swing.JLabel();
        APISeparator = new javax.swing.JSeparator();
        NewAPIInfo = new javax.swing.JLabel();
        NewAPIURLBox = new javax.swing.JTextField();
        NewAPIButton = new javax.swing.JButton();
        ResponsePane = new javax.swing.JPanel();
        ResponseList = new javax.swing.JComboBox<>();
        SaveConfig = new javax.swing.JButton();
        ReportPane = new javax.swing.JPanel();
        EnableReport = new javax.swing.JRadioButton();
        DisableReport = new javax.swing.JRadioButton();
        SendModePane = new javax.swing.JPanel();
        GroupUpSend = new javax.swing.JRadioButton();
        SingleSend = new javax.swing.JRadioButton();
        LanguagePane = new javax.swing.JPanel();
        LanguageList = new javax.swing.JComboBox<>();
        ThemeChanger = new javax.swing.JComboBox<>();
        ThemeInfo = new javax.swing.JLabel();
        FooterSeparator = new javax.swing.JSeparator();
        StatsPane = new javax.swing.JPanel();
        SingleCostInfo = new javax.swing.JLabel();
        CurrUserInfo = new javax.swing.JLabel();
        UseAccount = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        ContactsCountInfo = new javax.swing.JLabel();
        ContactsCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MessageNet Sender");
        setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(914, 550));
        setResizable(false);
        setSize(new java.awt.Dimension(920, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabbedClient.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TabbedClient.setPreferredSize(new java.awt.Dimension(915, 550));

        SenderTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SMSPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Write here your SMS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        SMSPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SMStext.setColumns(20);
        SMStext.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SMStext.setLineWrap(true);
        SMStext.setRows(5);
        jScrollPane1.setViewportView(SMStext);
        SMStext.setDocument(new SMSDocumentFilter(SMStext));

        SMSPane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 870, -1));

        limitinfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        limitinfo.setText("Length of the text:");
        SMSPane.add(limitinfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        limit.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        limit.setText("%SMS_SIZE%");
        SMSPane.add(limit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        SMSSentInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SMSSentInfo.setText("SMS per Receiver:");
        SMSSentInfo.setVisible(false);
        SMSPane.add(SMSSentInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        SMSSent.setVisible(false);
        SMSSent.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SMSSent.setText("%SMS*Dest%");
        SMSPane.add(SMSSent, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        CostInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CostInfo.setText("Presumable Cost:");
        SMSPane.add(CostInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        Cost.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Cost.setText("%COST%");
        SMSPane.add(Cost, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        CustomizationInfo.setVisible(false);
        CustomizationInfo.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        CustomizationInfo.setText("<html>Insert <font color=\"red\">%n (onlu one allowed)</font> anywhere in your SMS text. It will be replaced with the Contact's name while sending...</html>");
        CustomizationInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        SMSPane.add(CustomizationInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 360, 40));

        OverLimit.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        OverLimit.setText("%TEXT%");
        OverLimit.setToolTipText("Manda messaggi con lunghezza illimitata");
        OverLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OverLimitActionPerformed(evt);
            }
        });
        SMSPane.add(OverLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 20));

        SenderTab.add(SMSPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 890, 160));

        SendButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SendButton.setText("Send SMS");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });
        SenderTab.add(SendButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 890, -1));

        DatabasePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        DatabasePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        DBTable.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(DBTable);

        DatabasePane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 530, 230));

        SMSLogger.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SMS Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        SMSLogger.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ShowAllSMS.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ShowAllSMS.setText("Show all sent SMS");
        ShowAllSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllSMSActionPerformed(evt);
            }
        });
        SMSLogger.add(ShowAllSMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, -1));

        DatabasePane.add(SMSLogger, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 330, 100));

        DBTemplatePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Template", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        DBTemplatePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddTemplate.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddTemplate.setText("Add current text as template");
        AddTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTemplateActionPerformed(evt);
            }
        });
        DBTemplatePane.add(AddTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, -1));

        LoadTemplate.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LoadTemplate.setText("Load selected template as text");
        LoadTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadTemplateActionPerformed(evt);
            }
        });
        DBTemplatePane.add(LoadTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 310, -1));

        ShowAllTemplates.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ShowAllTemplates.setText("Show all templates");
        ShowAllTemplates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllTemplatesActionPerformed(evt);
            }
        });
        DBTemplatePane.add(ShowAllTemplates, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, -1));

        DeleteTemplate.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteTemplate.setText("Delete selected template");
        DeleteTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteTemplateActionPerformed(evt);
            }
        });
        DBTemplatePane.add(DeleteTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, -1));

        DeleteAllTemplates.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteAllTemplates.setText("Delete all templates");
        DeleteAllTemplates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllTemplatesActionPerformed(evt);
            }
        });
        DBTemplatePane.add(DeleteAllTemplates, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, -1));

        DatabasePane.add(DBTemplatePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 330, 150));

        Database_DoubleClick.setText("%DOUBLE_CLICK_FEATURE%");
        DatabasePane.add(Database_DoubleClick, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        SenderTab.add(DatabasePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 890, 270));

        TabbedClient.addTab("Send a SMS", SenderTab);

        ContactsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        DestTable.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        DestTable.setToolTipText("");
        DestTable.getTableHeader().setResizingAllowed(false);
        DestTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(DestTable);

        ContactsTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 470));

        AddContactPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Add a Contact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        AddContactPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FirstNameInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        FirstNameInfo.setText("First Name:");
        AddContactPane.add(FirstNameInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        LastNameInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LastNameInfo.setText("Last Name:");
        AddContactPane.add(LastNameInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        ContactNumberInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ContactNumberInfo.setText("Phone Number:");
        AddContactPane.add(ContactNumberInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        ContactFirstName.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddContactPane.add(ContactFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 250, -1));

        ContactLastName.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddContactPane.add(ContactLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 250, -1));

        try {
            ContactNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ContactNumber.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddContactPane.add(ContactNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 250, -1));

        AddContactButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddContactButton.setText("Add to the Library");
        AddContactButton.setActionCommand("Add Contact to the Library");
        AddContactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddContactButtonActionPerformed(evt);
            }
        });
        AddContactPane.add(AddContactButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 410, -1));

        ContactsTab.add(AddContactPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 430, 150));

        CSVPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Load/Save a CSV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        CSVPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoadCSV.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LoadCSV.setText("Load Contacts from CSV");
        LoadCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadCSVActionPerformed(evt);
            }
        });
        CSVPane.add(LoadCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 190, -1));

        CSVFormatInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CSVFormatInfo.setText("Select your CSV format:");
        CSVPane.add(CSVFormatInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        CSVFormatCombo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CSVFormatCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FirstName-LastName-PhoneNumber", "LatName-FirstName-PhoneNumber", "FullName-PhoneNumber", "PhoneNumber-FirstName-LastName", "PhoneNumber-LatName-FirstName", "PhoneNumber-FullName" }));
        CSVFormatCombo.setToolTipText("Select the format of your CSV file");
        CSVPane.add(CSVFormatCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 230, -1));

        SaveCSV.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SaveCSV.setText("Save Contacts in a CSV");
        SaveCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveCSVActionPerformed(evt);
            }
        });
        CSVPane.add(SaveCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 200, -1));

        ContactsTab.add(CSVPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 430, 90));

        ManageLibraryPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Manage Library", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        ManageLibraryPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeleteContactButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteContactButton.setText("Delete Selected Contact");
        DeleteContactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteContactButtonActionPerformed(evt);
            }
        });
        ManageLibraryPane.add(DeleteContactButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        DeleteContactsButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteContactsButton.setText("Delete All Contacts");
        DeleteContactsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteContactsButtonActionPerformed(evt);
            }
        });
        ManageLibraryPane.add(DeleteContactsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 200, -1));

        SwapContactButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SwapContactButton.setText("Swap first name and last name of the selected contact");
        SwapContactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwapContactButtonActionPerformed(evt);
            }
        });
        ManageLibraryPane.add(SwapContactButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 390, -1));

        SwapContactsButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SwapContactsButton.setText("Swap first name and last name of all contacts");
        SwapContactsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwapContactsButtonActionPerformed(evt);
            }
        });
        ManageLibraryPane.add(SwapContactsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 390, -1));

        ContactsTab.add(ManageLibraryPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 430, 130));

        DatabaseLibraryPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        DatabaseLibraryPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeleteContactDatabase.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteContactDatabase.setText("Delete Selected Contact");
        DeleteContactDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteContactDatabaseActionPerformed(evt);
            }
        });
        DatabaseLibraryPane.add(DeleteContactDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, -1));

        SaveContactDatabase.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SaveContactDatabase.setText("Save selected Contact");
        SaveContactDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveContactDatabaseActionPerformed(evt);
            }
        });
        DatabaseLibraryPane.add(SaveContactDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, -1));

        DeleteAllContactsDatabase.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DeleteAllContactsDatabase.setText("Clear Database");
        DeleteAllContactsDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllContactsDatabaseActionPerformed(evt);
            }
        });
        DatabaseLibraryPane.add(DeleteAllContactsDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 200, -1));

        LoadFromDatabase.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LoadFromDatabase.setText("Import");
        LoadFromDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadFromDatabaseActionPerformed(evt);
            }
        });
        DatabaseLibraryPane.add(LoadFromDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 80, -1));

        SaveAllContactsDatabase.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SaveAllContactsDatabase.setText("Save all Contact");
        SaveAllContactsDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAllContactsDatabaseActionPerformed(evt);
            }
        });
        DatabaseLibraryPane.add(SaveAllContactsDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 130, -1));

        ContactsTab.add(DatabaseLibraryPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 430, 100));

        TabbedClient.addTab("Contacts Library", ContactsTab);

        ConfigTab.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ConfigTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AccountPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MessageNet Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        AccountPane.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AccountPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UsernameInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        UsernameInfo.setText("Username:");
        AccountPane.add(UsernameInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        UsernameBox.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AccountPane.add(UsernameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 250, -1));

        PasswordInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        PasswordInfo.setText("Password:");
        AccountPane.add(PasswordInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        PasswordBox.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        PasswordBox.setEchoChar('*');
        AccountPane.add(PasswordBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 250, -1));

        SenderInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SenderInfo.setText("Certified Phone Number*:");
        AccountPane.add(SenderInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        try {
            SenderBox.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        SenderBox.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AccountPane.add(SenderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 250, -1));

        ShowPass.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ShowPass.setText("Show Password");
        ShowPass.setToolTipText("Display the entered password");
        ShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPassActionPerformed(evt);
            }
        });
        AccountPane.add(ShowPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        SenderNote.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        SenderNote.setForeground(new java.awt.Color(255, 0, 0));
        SenderNote.setText("<html>*If a invalid number is entered, the default parameter will be used.</html>");
        SenderNote.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        AccountPane.add(SenderNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 250, -1));

        SetAccount.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SetAccount.setText("Set Account");
        SetAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetAccountActionPerformed(evt);
            }
        });
        AccountPane.add(SetAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 420, -1));

        ConfigTab.add(AccountPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 441, 190));

        SMSCostPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SMS Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        SMSCostPane.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SMSCostPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SetCost.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SetCost.setText("Set Cost");
        SetCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetCostActionPerformed(evt);
            }
        });
        SMSCostPane.add(SetCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 200, -1));

        SMSCostInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SMSCostInfo.setText("Enter the actual cost for one SMS:");
        SMSCostPane.add(SMSCostInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));

        CheckCost.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CheckCost.setText("Check Costs");
        CheckCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCostActionPerformed(evt);
            }
        });
        SMSCostPane.add(CheckCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 200, -1));

        SMSCost.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        SMSCostPane.add(SMSCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 150, -1));

        ConfigTab.add(SMSCostPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 440, 90));

        ApiPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MessageNet API", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        ApiPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CurrAPIInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CurrAPIInfo.setText("Current API:");
        ApiPane.add(CurrAPIInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        CurrAPI.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        CurrAPI.setForeground(new java.awt.Color(0, 153, 51));
        CurrAPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CurrAPI.setText("%API%");
        CurrAPI.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        ApiPane.add(CurrAPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, -1));
        ApiPane.add(APISeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 420, 10));

        NewAPIInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        NewAPIInfo.setText("New API:");
        ApiPane.add(NewAPIInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        NewAPIURLBox.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        ApiPane.add(NewAPIURLBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 420, -1));

        NewAPIButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        NewAPIButton.setText("Use this API");
        NewAPIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewAPIButtonActionPerformed(evt);
            }
        });
        ApiPane.add(NewAPIButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 420, -1));

        ConfigTab.add(ApiPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 440, 170));

        ResponsePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "API's Response Format", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        ResponsePane.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ResponsePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResponseList.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ResponseList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "json", "plist", "xml" }));
        ResponseList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResponseListActionPerformed(evt);
            }
        });
        ResponsePane.add(ResponseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 35, 410, -1));

        ConfigTab.add(ResponsePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 440, 80));

        SaveConfig.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SaveConfig.setText("Save Configuration");
        SaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveConfigActionPerformed(evt);
            }
        });
        ConfigTab.add(SaveConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 440, -1));

        ReportPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Confirm SMS arrival", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        ReportPane.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ReportPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ReportOptionGroup.add(EnableReport);
        EnableReport.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        EnableReport.setSelected(true);
        EnableReport.setText("Report SMS arrival (can be checked via web)");
        EnableReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableReportActionPerformed(evt);
            }
        });
        ReportPane.add(EnableReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 23, -1, -1));

        ReportOptionGroup.add(DisableReport);
        DisableReport.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        DisableReport.setText("Don't report SMS arrival");
        DisableReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisableReportActionPerformed(evt);
            }
        });
        ReportPane.add(DisableReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 46, -1, -1));

        ConfigTab.add(ReportPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 440, 80));

        SendModePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Send Method", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        SendModePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SendModeOptionGroup.add(GroupUpSend);
        GroupUpSend.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        GroupUpSend.setSelected(true);
        GroupUpSend.setText("<html>Group up contacts and send them the SAME SMS with a single POST request</html>");
        GroupUpSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupUpSendActionPerformed(evt);
            }
        });
        SendModePane.add(GroupUpSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 420, 40));

        SendModeOptionGroup.add(SingleSend);
        SingleSend.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SingleSend.setText("<html>Do a request for each contact, allowing SMS customization with contact's name</html>");
        SingleSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingleSendActionPerformed(evt);
            }
        });
        SendModePane.add(SingleSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 420, -1));

        ConfigTab.add(SendModePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 440, 110));

        LanguagePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Interface Language", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N
        LanguagePane.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LanguagePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LanguageList.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        LanguageList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageListActionPerformed(evt);
            }
        });
        LanguagePane.add(LanguageList, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 35, 410, -1));

        ConfigTab.add(LanguagePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 440, 80));

        ThemeChanger.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ThemeChanger.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "Graphite", "HiFi", "Luna", "McWin", "Mint", "Noire", "Smart" }));
        ThemeChanger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemeChangerActionPerformed(evt);
            }
        });
        ConfigTab.add(ThemeChanger, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 210, 20));

        ThemeInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ThemeInfo.setText("Themes (by JTattoo):");
        ConfigTab.add(ThemeInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 405, -1, -1));

        TabbedClient.addTab("Settings", ConfigTab);

        getContentPane().add(TabbedClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));
        getContentPane().add(FooterSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 525, 914, 10));

        StatsPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SingleCostInfo.setBackground(new java.awt.Color(255, 153, 153));
        SingleCostInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        SingleCostInfo.setText("1 SMS = 0");
        SingleCostInfo.setOpaque(true);
        StatsPane.add(SingleCostInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        CurrUserInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        CurrUserInfo.setText("Current User:");
        StatsPane.add(CurrUserInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, -1, -1));

        UseAccount.setBackground(new java.awt.Color(255, 255, 0));
        UseAccount.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        UseAccount.setForeground(new java.awt.Color(0, 153, 51));
        UseAccount.setOpaque(true);
        StatsPane.add(UseAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 0, -1, -1));

        Status.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        Status.setForeground(new java.awt.Color(0, 153, 51));
        Status.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Status.setText("%STATUS%");
        Status.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        StatsPane.add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 380, -1));

        ContactsCountInfo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        ContactsCountInfo.setText("Contacts:");
        StatsPane.add(ContactsCountInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, -1));

        ContactsCount.setBackground(new java.awt.Color(255, 255, 0));
        ContactsCount.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        ContactsCount.setForeground(new java.awt.Color(0, 153, 51));
        ContactsCount.setText("0");
        ContactsCount.setOpaque(true);
        StatsPane.add(ContactsCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 10, -1));

        getContentPane().add(StatsPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void OverLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OverLimitActionPerformed
        if(OverLimit.isSelected()){
            Configuration.getInstance().setOverLimit(true);
            SMSSentInfo.setVisible(true);
            SMSSent.setVisible(true);
        }
        else{
            if(SMStext.getText().length()>((Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength())))
            {
                if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_OVERLIMIT_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    SMStext.setText(SMStext.getText().substring(0, (Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength())));
                    Configuration.getInstance().setOverLimit(false);
                    SMSSentInfo.setVisible(false);
                    SMSSent.setVisible(false);
                }
                else{
                    OverLimit.setSelected(true);
                }
            }
            else{
                Configuration.getInstance().setOverLimit(false);
                SMSSentInfo.setVisible(false);
                SMSSent.setVisible(false);
            }
            
        }
    }//GEN-LAST:event_OverLimitActionPerformed

    private void ShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPassActionPerformed
        if(ShowPass.isSelected())
        {
            PasswordBox.setEchoChar((char)0);
        }
        else{
            PasswordBox.setEchoChar('*');
        }
    }//GEN-LAST:event_ShowPassActionPerformed

    private void SetAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetAccountActionPerformed
        if(UsernameBox.getText().length() == 0 || PasswordBox.getPassword().length == 0)
        {
             JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_SETTING_TAB_INVALID_ACCOUNT_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if(!SenderBox.isEditValid())
            {
                JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_SETTING_TAB_INVALID_SENDER_ID) , Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                Configuration.getInstance().setSender(SenderBox.getText());
            }
            Configuration.getInstance().setAccount(UsernameBox.getText(), String.valueOf(PasswordBox.getPassword()));
            UseAccount.setText(String.format("%s (%s)", Configuration.getInstance().getUsername(), Configuration.getInstance().getSender()));
            Status.setText(Localization.getInstance().getText(Localization.CHANGE_ACCOUNT_ID));
       }
    }//GEN-LAST:event_SetAccountActionPerformed

    private void GroupUpSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupUpSendActionPerformed
        Configuration.getInstance().setGroupUp(true);
        CustomizationInfo.setVisible(false);
    }//GEN-LAST:event_GroupUpSendActionPerformed

    private void SingleSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingleSendActionPerformed
        Configuration.getInstance().setGroupUp(false);
        CustomizationInfo.setVisible(true);
    }//GEN-LAST:event_SingleSendActionPerformed

    private void EnableReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnableReportActionPerformed
        Configuration.getInstance().setReport(true);
    }//GEN-LAST:event_EnableReportActionPerformed

    private void DisableReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisableReportActionPerformed
        Configuration.getInstance().setReport(false);
    }//GEN-LAST:event_DisableReportActionPerformed

    private void AddContactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddContactButtonActionPerformed
        if(ContactFirstName.getText().length()>0 && ContactLastName.getText().length()>0 && ContactNumber.isEditValid())
        {
            Contacts_Container.getInstance().AddContact(ContactFirstName.getText(),ContactLastName.getText(), ContactNumber.getText());
            ContactFirstName.setText("");
            ContactLastName.setText("");
            ContactNumber.setValue("");
            ContactsCount.setText(String.format("%d", DestTable.getRowCount()));
            Status.setText(Contacts_Container.getInstance().getResult());
        }
        else
        {
            JOptionPane.showMessageDialog(ConfigTab, Localization.getInstance().getText(Localization.JOP_CONTACT_TAB_INVALID_CTC_ID) , Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_AddContactButtonActionPerformed

    private void DeleteContactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteContactButtonActionPerformed
        if(DestTable.getRowCount()>0 && DestTable.getSelectedRow()!=-1)
        {
            if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_REQUEST_PERMISSION_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                Contacts_Container.getInstance().RemoveContact(DestTable.getValueAt(DestTable.getSelectedRow(), 2).toString());
                ContactsCount.setText(String.format("%d", DestTable.getRowCount()));
                Status.setText(Contacts_Container.getInstance().getResult());  
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_CONTACT_TAB_NO_SEL_ID) , Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_DeleteContactButtonActionPerformed

    private void DeleteContactsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteContactsButtonActionPerformed
        if(DestTable.getRowCount()>0)
        {
            if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_REQUEST_PERMISSION_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                Contacts_Container.getInstance().RemoveAll();
                ContactsCount.setText(String.format("%d", DestTable.getRowCount()));
                Status.setText(Contacts_Container.getInstance().getResult());
            }
        }
    }//GEN-LAST:event_DeleteContactsButtonActionPerformed

    private int countCustoms()
    {
        int count = 0;
        int idx = 0;
            while ((idx = SMStext.getText().indexOf("%n", idx)) != -1)
            {
                idx++;
                count++;
            }
        return count;
    }
    
    private boolean checkCustom()
    {
        if(!Configuration.getInstance().isGroupUp() && countCustoms()>1)
        {
            return false;
        }
        return true;
    }
    
    private boolean isValidToSend()
    {
        if(DestTable.getRowCount()>0 && SMStext.getText().length()>0 &&
                SMStext.getText().length()<=(Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength())&& 
                (!Configuration.getInstance().getUsername().equals("") && !Configuration.getInstance().getPassword().equals("")) && Configuration.getInstance().getPrice()>0 && checkCustom())
        {
            return true;
        }
        else
        {
            if(DestTable.getRowCount()==0)
            {
                JOptionPane.showMessageDialog(rootPane, "Insert at least one receiver!", Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            }
            else if (SMStext.getText().length()==0)
            {
                JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_SMS_TAB_EMPITY_FIELD_ID), Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            }
            else if(Configuration.getInstance().getUsername().equals("") || Configuration.getInstance().getPassword().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Invalid MessageNet Account!", Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            }
            else if(SMStext.getText().length()>(Configuration.getInstance().isGroupUp() == true ? MessageNet.SINGLE_SMS_SIZE : MessageNet.SINGLE_SMS_SIZE-Contacts_Container.getInstance().getNameMaxLength()))
            {
                JOptionPane.showMessageDialog(rootPane, "Invalid SMS length", Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            }
            else if(Configuration.getInstance().getPrice()<=0)
            {
                JOptionPane.showMessageDialog(rootPane, "No Cost set", Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
            }
            if(!checkCustom())
            {
              JOptionPane.showMessageDialog(rootPane, "There are more than one '%n'", Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);  
            }
            return false;
        }
    }
    
    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        if(isValidToSend())
        {
            if(!sender.isActive())
            {
                Status.setText(String.format(Localization.getInstance().getText(Localization.SMS_TAB_SENDING_ID), ""));
                sender = new Sender_Thread("Application-Thread", Status);
                String type = Configuration.getInstance().isGroupUp() == true ? "group" : "single";
                sender.createJob(SMStext.getText(), type);
                sender.start();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_SMS_TAB_THREAD_ALIVE_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_SendButtonActionPerformed

    private void LoadCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadCSVActionPerformed
        if(CSVFile.showOpenDialog(CSVPane) == JFileChooser.APPROVE_OPTION)
        {
            Contacts_Container.getInstance().AddContactsFromCSV(CSVFile.getSelectedFile().getAbsolutePath(),CSVFormatCombo.getSelectedItem().toString());
            ContactsCount.setText(String.format("%d", DestTable.getRowCount()));
            Status.setText(Contacts_Container.getInstance().getResult());  
        }
    }//GEN-LAST:event_LoadCSVActionPerformed

    private void SaveCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveCSVActionPerformed
        if(DestTable.getRowCount()>0)
        {
            if(CSVFile.showSaveDialog(CSVPane) == JFileChooser.APPROVE_OPTION)
            {
                Contacts_Container.getInstance().SaveToCSV(CSVFile.getSelectedFile().getAbsolutePath(), CSVFormatCombo.getSelectedItem().toString());
                Status.setText(Contacts_Container.getInstance().getResult());  
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_CSV_EXPORT_NO_CTC_ID) , Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_SaveCSVActionPerformed

    private void ThemeChangerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemeChangerActionPerformed
        try {
            UIManager.setLookAndFeel(String.format("com.jtattoo.plaf.%s.%s%s", ThemeChanger.getSelectedItem().toString().toLowerCase(), ThemeChanger.getSelectedItem().toString(), "LookAndFeel"));
            Status.setText(Localization.getInstance().getText(Localization.CHANGE_THEME_ID));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
        super.pack();
    }//GEN-LAST:event_ThemeChangerActionPerformed

    private void SwapContactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwapContactButtonActionPerformed
        if(DestTable.getRowCount()>0 && DestTable.getSelectedRow()!=-1)
        {
            Contacts_Container.getInstance().SwapContactName(DestTable.getValueAt(DestTable.getSelectedRow(), 2).toString());
            Status.setText(Contacts_Container.getInstance().getResult());  
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_CONTACT_TAB_NO_SEL_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_SwapContactButtonActionPerformed

    private void SwapContactsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwapContactsButtonActionPerformed
        if(DestTable.getRowCount()>0)
        {
            Contacts_Container.getInstance().SwapAllNames();
            Status.setText(Contacts_Container.getInstance().getResult());  
        }
    }//GEN-LAST:event_SwapContactsButtonActionPerformed

    private void NewAPIButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewAPIButtonActionPerformed
        if(Network_Helper.getInstance().getAPI(NewAPIURLBox.getText())!=null)
        {
           Configuration.getInstance().setAPI(NewAPIURLBox.getText());
           CurrAPI.setText(Configuration.getInstance().getAPI()); 
           Status.setText(Localization.getInstance().getText(Localization.CHANGE_API_ID));
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_SETTING_TAB_INVALID_API_ID), Localization.getInstance().getText(Localization.JOP_W_TITLE_ID), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_NewAPIButtonActionPerformed

    private void SaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveConfigActionPerformed
        Configuration.getInstance().ExportConfiguration();
        Status.setText(Configuration.getInstance().getResult());  
    }//GEN-LAST:event_SaveConfigActionPerformed

    private void ResponseListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResponseListActionPerformed
        Configuration.getInstance().setFormat(ResponseList.getSelectedItem().toString());
        Status.setText(Localization.getInstance().getText(Localization.CHANGE_RESPONSE_ID));
    }//GEN-LAST:event_ResponseListActionPerformed

    private void LanguageListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageListActionPerformed
        Configuration.getInstance().setLanguage(LanguageList.getSelectedItem().toString().toLowerCase());
        setGUILanguage();
        Status.setText(Localization.getInstance().getText(Localization.CHANGE_LANG_ID));
    }//GEN-LAST:event_LanguageListActionPerformed

    private void SetCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetCostActionPerformed
        try
        {
            String price = SMSCost.getText().replace(",", ".");
            double tmp = Double.parseDouble(price);
            Configuration.getInstance().setPrice(price);
            SingleCostInfo.setText(String.format("1 SMS = %.4f€", Configuration.getInstance().getPrice()));
            Status.setText(Localization.getInstance().getText(Localization.CHANGE_COST_ID));
            JOptionPane.showMessageDialog(rootPane, String.format("%s %.4f€", Localization.getInstance().getText(Localization.JOP_SETTING_TAB_COST_SET_ID), Configuration.getInstance().getPrice()), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(rootPane, "NO DONE");
        }
    }//GEN-LAST:event_SetCostActionPerformed

    private void CheckCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCostActionPerformed
        try {
        Desktop.getDesktop().browse(new URL("https://www.messagenet.com/sms/tariffe/").toURI());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "NO SUPPORTED", Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_CheckCostActionPerformed

    private void ShowAllTemplatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllTemplatesActionPerformed
        Database_Handler.getInstance().UpdateTableTemplateMode();
    }//GEN-LAST:event_ShowAllTemplatesActionPerformed

    private void AddTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTemplateActionPerformed
        if(SMStext.getText().length()>0)
        {
            Database_Handler.getInstance().insertTemplate(SMStext.getText());
            Database_Handler.getInstance().UpdateTableTemplateMode();
        }
        else
        {
            JOptionPane.showMessageDialog(DatabasePane, Localization.getInstance().getText(Localization.JOP_SMS_TAB_EMPITY_FIELD_ID), Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddTemplateActionPerformed

    private void LoadTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadTemplateActionPerformed
        if(DBTable.getRowCount()>0 && DBTable.getSelectedRow()!=-1)
        {
            SMStext.setText(DBTable.getValueAt(DBTable.getSelectedRow(), 1).toString());  
        }
        else
        {
            JOptionPane.showMessageDialog(DatabasePane, Localization.getInstance().getText(Localization.JOP_DATABASE_TEMPLATE_NO_SEL_ID), Localization.getInstance().getText(Localization.JOP_E_TITLE_ID), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LoadTemplateActionPerformed

    private void ShowAllSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllSMSActionPerformed
        Database_Handler.getInstance().UpdateTableSMSLogMode();
    }//GEN-LAST:event_ShowAllSMSActionPerformed

    private void DeleteTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteTemplateActionPerformed
        if(DBTable.getRowCount()>0 && DBTable.getSelectedRow()!=-1)
        {
            if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_REQUEST_PERMISSION_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                Database_Handler.getInstance().deleteTemplate((int)DBTable.getValueAt(DBTable.getSelectedRow(), 0));
                Database_Handler.getInstance().UpdateTableTemplateMode();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_DATABASE_TEMPLATE_NO_SEL_ID) , Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_DeleteTemplateActionPerformed

    private void DeleteAllTemplatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllTemplatesActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_REQUEST_PERMISSION_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
           Database_Handler.getInstance().deleteTemplate();
           Database_Handler.getInstance().UpdateTableTemplateMode();
        }
    }//GEN-LAST:event_DeleteAllTemplatesActionPerformed

    private void DeleteContactDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteContactDatabaseActionPerformed
        if(DestTable.getRowCount()>0 && DestTable.getSelectedRow()!=-1)
        {
            if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_DATABASE_CT_DEL_INFO_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                String num = DestTable.getValueAt(DestTable.getSelectedRow(), 2).toString();
                Database_Handler.getInstance().deleteContacts(num);
                Status.setText(Database_Handler.getInstance().getResult());  
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_CONTACT_TAB_NO_SEL_ID) , Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_DeleteContactDatabaseActionPerformed

    private void SaveContactDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveContactDatabaseActionPerformed
        if(DestTable.getRowCount()>0 && DestTable.getSelectedRow()!=-1)
        {
            Database_Handler.getInstance().insertContact(new Contact(DestTable.getValueAt(DestTable.getSelectedRow(), 0).toString(), DestTable.getValueAt(DestTable.getSelectedRow(), 1).toString(),DestTable.getValueAt(DestTable.getSelectedRow(), 2).toString()));
            Status.setText(Database_Handler.getInstance().getResult());  
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, Localization.getInstance().getText(Localization.JOP_CONTACT_TAB_NO_SEL_ID) , Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_SaveContactDatabaseActionPerformed

    private void DeleteAllContactsDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllContactsDatabaseActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, Localization.getInstance().getText(Localization.JOP_DATABASE_CT_DEL_INFO_ID), Localization.getInstance().getText(Localization.JOP_I_TITLE_ID), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                Database_Handler.getInstance().deleteContacts();
                Status.setText(Database_Handler.getInstance().getResult());
            }
    }//GEN-LAST:event_DeleteAllContactsDatabaseActionPerformed

    private void LoadFromDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadFromDatabaseActionPerformed
        Contacts_Container.getInstance().AddContactsFromDatabase();
        Status.setText(Contacts_Container.getInstance().getResult());
    }//GEN-LAST:event_LoadFromDatabaseActionPerformed

    private void SaveAllContactsDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAllContactsDatabaseActionPerformed
        for(Contact c : Contacts_Container.getInstance().getContacts())
        {
            Database_Handler.getInstance().insertContact(c);
        }
        Status.setText(Database_Handler.getInstance().getResult());
    }//GEN-LAST:event_SaveAllContactsDatabaseActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            initFont();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    
    private static void initFont()
    {
        Font Verdana = new Font("Verdana", Font.PLAIN, 11);
        UIManager.put("Button.font", Verdana);
        UIManager.put("ToggleButton.font", Verdana);
        UIManager.put("RadioButton.font", Verdana);
        UIManager.put("CheckBox.font", Verdana);
        UIManager.put("ColorChooser.font", Verdana);
        UIManager.put("ComboBox.font", Verdana);
        UIManager.put("Label.font", Verdana);
        UIManager.put("List.font", Verdana);
        UIManager.put("MenuBar.font", Verdana);
        UIManager.put("MenuItem.font", Verdana);
        UIManager.put("RadioButtonMenuItem.font", Verdana);
        UIManager.put("CheckBoxMenuItem.font", Verdana);
        UIManager.put("Menu.font", Verdana);
        UIManager.put("PopupMenu.font", Verdana);
        UIManager.put("OptionPane.font", Verdana);
        UIManager.put("Panel.font", Verdana);
        UIManager.put("ProgressBar.font", Verdana);
        UIManager.put("ScrollPane.font", Verdana);
        UIManager.put("Viewport.font", Verdana);
        UIManager.put("TabbedPane.font", Verdana);
        UIManager.put("Table.font", Verdana);
        UIManager.put("TableHeader.font", Verdana);
        UIManager.put("TextField.font", Verdana);
        UIManager.put("PasswordField.font", Verdana);
        UIManager.put("TextArea.font", Verdana);
        UIManager.put("TextPane.font", Verdana);
        UIManager.put("EditorPane.font", Verdana);
        UIManager.put("TitledBorder.font", Verdana);
        UIManager.put("ToolBar.font", Verdana);
        UIManager.put("ToolTip.font", Verdana);
        UIManager.put("Tree.font", Verdana);
    }
    
    private void setGUILanguage()
    {
        //TABS
        TabbedClient.setTitleAt(0, Localization.getInstance().getText(Localization.SMS_TAB_ID));
        TabbedClient.setTitleAt(1, Localization.getInstance().getText(Localization.CONTACT_TAB_ID));
        TabbedClient.setTitleAt(2, Localization.getInstance().getText(Localization.SETTING_TAB_ID));
        //SMS TAB
        SMSPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SMS_TAB_SMS_SPACE_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        limitinfo.setText(Localization.getInstance().getText(Localization.SMS_TAB_TEXT_LENGTH_ID));
        OverLimit.setText(Localization.getInstance().getText(Localization.SMS_TAB_OVERLIMIT_ID));
        SMSSentInfo.setText(Localization.getInstance().getText(Localization.SMS_TAB_NUM_SMS_ID));
        CostInfo.setText(Localization.getInstance().getText(Localization.SMS_TAB_COST_ID));
        CustomizationInfo.setText(Localization.getInstance().getText(Localization.SMS_TAB_CUSTOM_NOTE_ID));
        AddTemplate.setText(Localization.getInstance().getText(Localization.DATABASE_ADD_TEMPLATE_ID));
        LoadTemplate.setText(Localization.getInstance().getText(Localization.DATABASE_LOAD_TEMPLATE_ID));
        DeleteTemplate.setText(Localization.getInstance().getText(Localization.DATABASE_DELETE_TEMPLATE_ID));
        DeleteAllTemplates.setText(Localization.getInstance().getText(Localization.DATABASE_DELETE_TEMPLATES_ID));
        ShowAllTemplates.setText(Localization.getInstance().getText(Localization.DATABASE_SHOW_TEMPLATES_ID));
        Database_DoubleClick.setText(Localization.getInstance().getText(Localization.DATABASE_DOUBLE_CLICK_ID));
        SendButton.setText(Localization.getInstance().getText(Localization.SMS_TAB_SEND_ID));
        ShowAllSMS.setText(Localization.getInstance().getText(Localization.DATABASE_SHOW_ALL_SMS_SENT_ID));
        //CONTACTS TAB
        DestTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_NAME_ID));
        DestTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_SURNAME_ID));
        DestTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_PHONE_ID));
        AddContactPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.CONTACT_TAB_ADD_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        FirstNameInfo.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_NAME_ID));
        LastNameInfo.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_SURNAME_ID));
        ContactNumberInfo.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_TABLE_PHONE_ID));
        AddContactButton.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_ADD_ACTION_ID));
        CSVPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.CONTACT_TAB_CSV_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        CSVFormatInfo.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_CSV_ID));
        LoadCSV.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_CSV_LOAD_ID));
        SaveCSV.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_CSV_SAVE_ID));
        ManageLibraryPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.CONTACT_TAB_MANAGE_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        DeleteContactButton.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_MANAGE_DEL_ID));
        DeleteContactsButton.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_MANAGE_DELALL_ID));
        SwapContactButton.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_MANAGE_SWAP_ID));
        SwapContactsButton.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_MANAGE_SWAPALL_ID));
        SaveContactDatabase.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_DATABASE_SAVECT_ID));
        SaveAllContactsDatabase.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_DATABASE_SAVECT_ALL_ID));
        DeleteContactDatabase.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_DATABASE_DELCT_ID));
        LoadFromDatabase.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_DATABASE_IMPORT_ID));
        DeleteAllContactsDatabase.setText(Localization.getInstance().getText(Localization.CONTACT_TAB_DATABASE_DELCT_ALL_ID));
        //SETTINGS TAB
        UsernameInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_USERNAME_ID));
        SenderInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_SENDER_ID));
        ShowPass.setText(Localization.getInstance().getText(Localization.SETTING_TAB_PASSWORD_ID));
        SenderNote.setText(Localization.getInstance().getText(Localization.SENDER_NOTE_ID));
        SetAccount.setText(Localization.getInstance().getText(Localization.SETTING_TAB_ACCOUNT_ID));
        CurrAPIInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_CURRENT_API_ID));
        NewAPIInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_NEW_API_ID));
        NewAPIButton.setText(Localization.getInstance().getText(Localization.SETTING_TAB_API_ID));
        ResponsePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SETTING_TAB_RESPONSE_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        ReportPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SETTING_TAB_REPORT_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        EnableReport.setText(Localization.getInstance().getText(Localization.SETTING_TAB_REPORT_ENABLE_ID));
        DisableReport.setText(Localization.getInstance().getText(Localization.SETTING_TAB_REPORT_DISABLE_ID));
        SendModePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SETTING_TAB_SEND_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        GroupUpSend.setText(Localization.getInstance().getText(Localization.SETTING_TAB_SEND_GROUP_ID));
        SingleSend.setText(Localization.getInstance().getText(Localization.SETTING_TAB_SEND_SINGLE_ID));
        LanguagePane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SETTING_TAB_LANGUAGE_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        SaveConfig.setText(Localization.getInstance().getText(Localization.SETTING_TAB_SAVE_CONF_ID));
        SMSCostPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Localization.getInstance().getText(Localization.SETTING_TAB_COST_ID), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        SMSCostInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_COST_TEXT_ID));
        CheckCost.setText(Localization.getInstance().getText(Localization.SETTING_TAB_COST_CHECK_ID));
        SetCost.setText(Localization.getInstance().getText(Localization.SETTING_TAB_COST_SET_ID));
        ThemeInfo.setText(Localization.getInstance().getText(Localization.SETTING_TAB_THEMES_ID));
        //FOOTER
        CurrUserInfo.setText(Localization.getInstance().getText(Localization.CURRENT_USER_ID));
        ContactsCountInfo.setText(Localization.getInstance().getText(Localization.CONTACTS_ID));
        //VIEWER
        sms_track.changeLanguage();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator APISeparator;
    private javax.swing.JPanel AccountPane;
    private javax.swing.JButton AddContactButton;
    private javax.swing.JPanel AddContactPane;
    private javax.swing.JButton AddTemplate;
    private javax.swing.JPanel ApiPane;
    private javax.swing.JComboBox<String> CSVFormatCombo;
    private javax.swing.JLabel CSVFormatInfo;
    private javax.swing.JPanel CSVPane;
    private javax.swing.JButton CheckCost;
    private javax.swing.JPanel ConfigTab;
    private javax.swing.JTextField ContactFirstName;
    private javax.swing.JTextField ContactLastName;
    private javax.swing.JFormattedTextField ContactNumber;
    private javax.swing.JLabel ContactNumberInfo;
    private javax.swing.JLabel ContactsCount;
    private javax.swing.JLabel ContactsCountInfo;
    private javax.swing.JPanel ContactsTab;
    private javax.swing.JLabel Cost;
    private javax.swing.JLabel CostInfo;
    private javax.swing.JLabel CurrAPI;
    private javax.swing.JLabel CurrAPIInfo;
    private javax.swing.JLabel CurrUserInfo;
    private javax.swing.JLabel CustomizationInfo;
    private javax.swing.JTable DBTable;
    private javax.swing.JPanel DBTemplatePane;
    private javax.swing.JPanel DatabaseLibraryPane;
    private javax.swing.JPanel DatabasePane;
    private javax.swing.JLabel Database_DoubleClick;
    private javax.swing.JButton DeleteAllContactsDatabase;
    private javax.swing.JButton DeleteAllTemplates;
    private javax.swing.JButton DeleteContactButton;
    private javax.swing.JButton DeleteContactDatabase;
    private javax.swing.JButton DeleteContactsButton;
    private javax.swing.JButton DeleteTemplate;
    private javax.swing.JTable DestTable;
    private javax.swing.JRadioButton DisableReport;
    private javax.swing.JRadioButton EnableReport;
    private javax.swing.JLabel FirstNameInfo;
    private javax.swing.JSeparator FooterSeparator;
    private javax.swing.JRadioButton GroupUpSend;
    private javax.swing.JComboBox<String> LanguageList;
    private javax.swing.JPanel LanguagePane;
    private javax.swing.JLabel LastNameInfo;
    private javax.swing.JButton LoadCSV;
    private javax.swing.JButton LoadFromDatabase;
    private javax.swing.JButton LoadTemplate;
    private javax.swing.JPanel ManageLibraryPane;
    private javax.swing.JButton NewAPIButton;
    private javax.swing.JLabel NewAPIInfo;
    private javax.swing.JTextField NewAPIURLBox;
    private javax.swing.JCheckBox OverLimit;
    private javax.swing.JPasswordField PasswordBox;
    private javax.swing.JLabel PasswordInfo;
    private javax.swing.ButtonGroup ReportOptionGroup;
    private javax.swing.JPanel ReportPane;
    private javax.swing.JComboBox<String> ResponseList;
    private javax.swing.JPanel ResponsePane;
    private javax.swing.JTextField SMSCost;
    private javax.swing.JLabel SMSCostInfo;
    private javax.swing.JPanel SMSCostPane;
    private javax.swing.JPanel SMSLogger;
    private javax.swing.JPanel SMSPane;
    private javax.swing.JLabel SMSSent;
    private javax.swing.JLabel SMSSentInfo;
    private javax.swing.JTextArea SMStext;
    private javax.swing.JButton SaveAllContactsDatabase;
    private javax.swing.JButton SaveCSV;
    private javax.swing.JButton SaveConfig;
    private javax.swing.JButton SaveContactDatabase;
    private javax.swing.JButton SendButton;
    private javax.swing.ButtonGroup SendModeOptionGroup;
    private javax.swing.JPanel SendModePane;
    private javax.swing.JFormattedTextField SenderBox;
    private javax.swing.JLabel SenderInfo;
    private javax.swing.JLabel SenderNote;
    private javax.swing.JPanel SenderTab;
    private javax.swing.JButton SetAccount;
    private javax.swing.JButton SetCost;
    private javax.swing.JButton ShowAllSMS;
    private javax.swing.JButton ShowAllTemplates;
    private javax.swing.JCheckBox ShowPass;
    private javax.swing.JLabel SingleCostInfo;
    private javax.swing.JRadioButton SingleSend;
    private javax.swing.JPanel StatsPane;
    private javax.swing.JLabel Status;
    private javax.swing.JButton SwapContactButton;
    private javax.swing.JButton SwapContactsButton;
    private javax.swing.JTabbedPane TabbedClient;
    private javax.swing.JComboBox<String> ThemeChanger;
    private javax.swing.JLabel ThemeInfo;
    private javax.swing.JLabel UseAccount;
    private javax.swing.JTextField UsernameBox;
    private javax.swing.JLabel UsernameInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel limit;
    private javax.swing.JLabel limitinfo;
    // End of variables declaration//GEN-END:variables
}

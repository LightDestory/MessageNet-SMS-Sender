package org.altervista.lightdestory.sms.customs;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{
    
    public CustomTableModel(Object[][] Rows, Object[] Cols)
    {
        super(Rows, Cols);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public void TemplateMode()
    {
        super.setColumnCount(0);
        super.addColumn("ID");
        super.addColumn("Template");
    }
    
    public void SMSLogMode()
    {
        super.setColumnCount(0);
        super.addColumn("ID");
        super.addColumn("Message");
        super.addColumn("Date");
    }
    
    public void removeAllRows(){
        for(int i = super.getRowCount()-1;i>=0;i--)
        {
            super.removeRow(i);
        }
    }   
}

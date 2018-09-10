package org.altervista.lightdestory.sms.contacts;

public class Contact {
    
    private String firstName,lastName,number;
    
    public Contact(String firstName, String lastName, String number)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void SwapNames() {
        String tmp = firstName;
        firstName = lastName;
        lastName = tmp;
    }
    
    public Object[] getTableData() {
        return new Object[] {firstName, lastName, number};
    }
    
}

package tech.ryanfehr.androiddevclass6application;

/**
 * Created by Ryan on 9/11/2017.
 */

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private int iconID;

    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress, int iconID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.iconID = iconID;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getIconID() {
        return iconID;
    }
}

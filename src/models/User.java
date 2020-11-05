// User class returns array of all users

package models;

public class User {

    // instance variables

    private int userID;
    private String email;
    private String title;
    private String forename;
    private String surname;
    private String password;
    private String accountType;

    // getters & setters

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getForename() {
        return this.forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return this.accountType;
    }

    // constructor
    
    public User(int userID, String email, String title, String forename, String surname, String password,
            String accountType) {
        this.userID = userID;
        this.email = email;
        this.title = title;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.accountType = accountType;
    }
}

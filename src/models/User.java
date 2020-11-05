// User class returns array of all users

package models;

public class User {

    // instance variables

    public static int userID;
    public static String email;
    public static String title;
    public static String forename;
    public static String surname;
    public static String password;
    public static String accountType;

    // getters & setters

    public void setUserID(int userID) {
        User.userID = userID;
    }

    public int getUserID() {
        return User.userID;
    }

    public void setEmail(String email) {
        User.email = email;
    }

    public String getEmail() {
        return User.email;
    }

    public void setTitle(String title) {
        User.title = title;
    }

    public String getTitle() {
        return User.title;
    }

    public void setForename(String forename) {
        User.forename = forename;
    }

    public String getForename() {
        return User.forename;
    }

    public void setSurname(String surname) {
        User.surname = surname;
    }

    public String getSurname() {
        return User.surname;
    }

    public void setPassword(String password) {
        User.password = password;
    }

    public String getPassword() {
        return User.password;
    }

    public void setAccountType(String accountType) {
        User.accountType = accountType;
    }

    public String getAccountType() {
        return User.accountType;
    }

    // constructor
    
    public User(int userID, String email, String title, String forename, String surname, String password,
            String accountType) {
        User.userID = userID;
        User.email = email;
        User.title = title;
        User.forename = forename;
        User.surname = surname;
        User.password = password;
        User.accountType = accountType;
    }
}

// User class returns array of all users

package models;

import models.Constants.AccountType;
import models.Constants.Title;

public class User {

    // instance variables

    private String email;
    private Title title;
    private String forename;
    private String surname;
    private AccountType accountType;

    // getters & setters

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Title getTitle() {
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

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public String toString() {
        return this.email + " > " + this.forename + " > " + this.surname + " > " + this.accountType;
    }

    // constructor

    public User(String email, Title title, String forename, String surname, AccountType accountType) {
        this.email = email;
        this.title = title;
        this.forename = forename;
        this.surname = surname;
        this.accountType = accountType;
    }
}

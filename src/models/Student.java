// Student class returns array of all students

package models;

import models.Constants.AccountType;
import models.Constants.Title;

public class Student extends User {

    // instance variables

    private Integer registrationNumber;
    private String personalTutor;

    // getters & setters

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setPersonalTutor(String personalTutor) {
        this.personalTutor = personalTutor;
    }

    public String getPersonalTutor() {
        return this.personalTutor;
    }

    public String toString() {
        return super.toString() + " > " + this.registrationNumber;
    }

    // constructor
    public Student(Integer userID, String email, Title title, String forename, String surname,
            Integer registrationNumber, String personalTutor) {
        super(userID, email, title, forename, surname, AccountType.STUDENT);
        this.registrationNumber = registrationNumber;
        this.personalTutor = personalTutor;
    }
}
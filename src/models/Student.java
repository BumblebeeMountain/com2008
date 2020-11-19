// Student class returns array of all students

package models;

import models.Constants.AccountType;
import models.Constants.Title;

public class Student extends User {

    // instance variables

    private Integer registrationNumber;
    private String personalTutor;
    private Boolean hasGraduated;

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

    public Boolean getHasGraduated () {
        return this.hasGraduated;
    }

    public void setHasGraduated (Boolean hasGraduated) {
        this.hasGraduated = hasGraduated;
    }

    public String toString() {
        return super.toString() + " > Reg no:" + this.registrationNumber + " > Graduated:" + this.hasGraduated;
    }

    // constructor
    public Student(String email, Title title, String forename, String surname, Integer registrationNumber,
            String personalTutor, Boolean hasGraduated) {
        super(email, title, forename, surname, AccountType.STUDENT);
        this.registrationNumber = registrationNumber;
        this.personalTutor = personalTutor;
        this.hasGraduated = hasGraduated;
    }
}
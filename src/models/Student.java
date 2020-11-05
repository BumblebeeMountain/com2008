// Student class returns array of all students

package models;

public class Student {

    // instance variables

    private int userID;
    private int registrationNumber;
    private String personalTutor;

    // getters & setters

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setPersonalTutor(String personalTutor) {
        this.personalTutor = personalTutor;
    }

    public String getPersonalTutor() {
        return this.personalTutor;
    }

    // constructor

    public Student (int userID, int registrationNumber, String personalTutor) {
        this.userID = userID;
        this.registrationNumber = registrationNumber;
        this.personalTutor = personalTutor;
    }
}
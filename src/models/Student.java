// Student class returns array of all students

package models;

public class Student extends User {

    // instance variables

    public int registrationNumber;
    public String personalTutor;

    // getters & setters

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
        super(userID, email, title, forename, surname, password, accountType);
        this.registrationNumber = registrationNumber;
        this.personalTutor = personalTutor;
    }
}
// SelectedModule class returns array of selected modules

package models;

public class SelectedModule {

    // instance variables

    private String moduleCode;
    private int studentRegistrationNumber;
    private int period;
    private float firstAttemptResult;
    private float secondAttemptResult;

    // getters & setters

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setStudentRegistrationNumber(int studentRegistrationNumber) {
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    public int getStudentRegistrationNumber() {
        return this.studentRegistrationNumber;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setFirstAttemptResult(float firstAttemptResult) {
        this.firstAttemptResult = firstAttemptResult;
    }

    public float getFirstAttemptResult() {
        return firstAttemptResult;
    }

    public void setSecondAttemptResult(float secondAttemptResult) {
        this.secondAttemptResult = secondAttemptResult;
    }

    public float getSecondAttemptResult() {
        return secondAttemptResult;
    }

    // constructor

    public SelectedModule (String moduleCode, int studentRegistrationNumber, int period, float firstAttemptResult, float secondAttemptResult) {
        this.moduleCode = moduleCode;
        this.studentRegistrationNumber = studentRegistrationNumber;
        this.period = period;
        this.firstAttemptResult = firstAttemptResult;
        this.secondAttemptResult = secondAttemptResult;
    }
}
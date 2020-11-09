// SelectedModule class returns array of selected modules

package models;

public class SelectedModule extends Module {

    // instance variables

    private Float firstAttemptResult;
    private Float secondAttemptResult;

    // getters & setters

    public void setFirstAttemptResult(Float firstAttemptResult) {
        this.firstAttemptResult = firstAttemptResult;
    }

    public Float getFirstAttemptResult() {
        return firstAttemptResult;
    }

    public void setSecondAttemptResult(Float secondAttemptResult) {
        this.secondAttemptResult = secondAttemptResult;
    }

    public Float getSecondAttemptResult() {
        return secondAttemptResult;
    }

    public String toString() {
        return super.toString() + " > 1st: " + this.firstAttemptResult + " > 2nd: " + this.secondAttemptResult;
    }

    // constructor

    public SelectedModule(String name, String code, Integer credits, String teachingPeriod, Float firstAttempt,
            Float secondAttempt) {
        super(name, code, credits, teachingPeriod);
        this.firstAttemptResult = firstAttempt;
        this.secondAttemptResult = secondAttempt;
    }
}
// Module class returns array of modules

package models;

public class Module {

    // instance variables

    public String name;
    public String code;
    private int credits;
    private String teachingPeriod;

    // getters & setters

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setTeachingPeriod(String teachingPeriod) {
        this.teachingPeriod = teachingPeriod;
    }

    public String getTeachingPeriod() {
        return this.teachingPeriod;
    }

    // constructor

    public Module(String name, String code, int credits, String teachingPeriod) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.teachingPeriod = teachingPeriod;
    }
}
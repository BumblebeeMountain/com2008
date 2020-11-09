// Module class returns array of modules

package models;

public class Module {

    // instance variables

    private String name;
    private String code;
    private Integer credits;
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

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public void setTeachingPeriod(String teachingPeriod) {
        this.teachingPeriod = teachingPeriod;
    }

    public String getTeachingPeriod() {
        return this.teachingPeriod;
    }

    public String toString() {
        return this.code + " > " + this.name + " > " + this.credits + " > " + this.teachingPeriod;
    }

    // constructor

    public Module(String name, String code, Integer credits, String teachingPeriod) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.teachingPeriod = teachingPeriod;
    }
}
// Module class returns array of modules

package models;

public class Module {

    // instance variables

    private String name;
    private String code;
    private Integer credits;
    private String teachingPeriod;
    private Boolean currentlyOffered;

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

    public Boolean getCurrentlyOffered () {
        return this.currentlyOffered;
    }

    public void setCurrentlyOffered (Boolean currentlyOffered) {
        this.currentlyOffered = currentlyOffered;
    }

    public String toString() {
        return "Mod:" + this.code + " > Name:" + this.name + " > Credits:" + this.credits + " > Period:" + this.teachingPeriod + " > Offered:" + this.currentlyOffered;
    }

    // constructor

    public Module(String name, String code, Integer credits, String teachingPeriod, Boolean currentlyOffered) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.teachingPeriod = teachingPeriod;
        this.currentlyOffered = currentlyOffered;
    }
}
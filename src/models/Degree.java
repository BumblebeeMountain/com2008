// A simple class to make an object of a record in the database
//  eg so we can return an array of degrees etc

package models;

public class Degree {
    
    // instance variables

    private String name;
    private String code;
    private Boolean hasYearInIndustry;
    private int maxLevel;

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

    public void setHasYearInIndustry(Boolean hasYearInIndustry) {
        this.hasYearInIndustry = hasYearInIndustry;
    }

    public Boolean getHasYearInIndustry() {
        return this.hasYearInIndustry;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
    
    // constructor

    public Degree (String name, String code, Boolean hasYearInIndustry, int maxLevel) {
        this.name = name;
        this.code = code;
        this.hasYearInIndustry = hasYearInIndustry;
        this.maxLevel = maxLevel;
    }
}
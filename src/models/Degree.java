// A simple class to make an object of a record in the database
//  eg so we can return an array of degrees etc

package models;

public class Degree {

    // instance variables

    private String name;
    private String code;
    private Boolean hasYearInIndustry;
    private int maxLevel;
    private Department leadDepartment;
    private Department[] partnerDepartments;
    private Module[] coreModules;
    private Module[] optionalModules;

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

    public Department getLeadDepartment () {
        return this.leadDepartment;
    }

    public void setLeadDepartment (Department lead) {
        this.leadDepartment = lead;
    }

    public Department[] getPartnerDepartments () {
        return this.partnerDepartments;
    }

    public void setPartnerDepartments (Department[] partners) {
        this.partnerDepartments = partners;
    }

    public Module[] getCoreModules () {
        return this.coreModules;
    }

    public void setCoreModules (Module[] coreModules) {
        this.coreModules = coreModules;
    }

    public Module[] getOptionalModules () {
        return this.optionalModules;
    }

    public void setOptionalModules (Module[] optionalModules) {
        this.optionalModules = optionalModules;
    }

    public String toString () {
        return this.code + " > " + this.name;
    }

    // Needs modifying to look at the U or P in the degree code
    public Integer getLevelOfEntry () {
        return 1;
    }

    // constructor

    public Degree(String name, String code, Boolean hasYearInIndustry, int maxLevel, Department leadDepartment,
            Department[] partnerDepartments, Module[] coreModules, Module[] optionalModules) {
        this.name = name;
        this.code = code;
        this.hasYearInIndustry = hasYearInIndustry;
        this.maxLevel = maxLevel;
        this.leadDepartment = leadDepartment;
        this.partnerDepartments = partnerDepartments;
        this.coreModules = coreModules;
        this.optionalModules = optionalModules;
    }
}
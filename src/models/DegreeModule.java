// DegreeModule class is meant to link the Degree class and Module class

package models;

public class DegreeModule {

    // instance variables

    public String degreeCode;
    public String moduleCode;
    public Boolean core;
    public int level;

    // getters & setters

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeCode() {
        return this.degreeCode;
    }

    public void setCore(Boolean core) {
        this.core = core;
    }

    public Boolean getCore() {
        return this.core;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    // constructor
    
    public DegreeModule (String moduleCode, String degreeCode, Boolean core, int level) {
        this.moduleCode = moduleCode;
        this.degreeCode = degreeCode;
        this.core = core;
        this.level = level;
    }
}
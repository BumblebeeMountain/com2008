package models;

public class DegreeModule extends Module {

    private String degreeCode;
    private Boolean isCore;
    private Character level;

    public String getDegreeCode() {
        return this.degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public Boolean getIsCore() {
        return this.isCore;
    }

    public void setIsCore(Boolean isCore) {
        this.isCore = isCore;
    }

    public String toString () {
        return super.toString() + " > Deg:" + this.degreeCode + " > isCore:" + this.isCore + " > Level:" + this.level;
    }

    public Character getLevel () {
        return this.level;
    }

    public void setLevel (Character level) {
        this.level = level;
    }

    public DegreeModule(String name, String code, Integer credits, String teachingPeriod, String degreeCode,
            Boolean isCore, Character level) {
        super(name, code, credits, teachingPeriod);
        this.degreeCode = degreeCode;
        this.isCore = isCore;
        this.level = level;
    }

}
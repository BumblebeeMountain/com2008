package models;

public class DegreeModule extends Module {

    private String degreeCode;
    private Boolean isCore;

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
        return super.toString() + " > " + this.degreeCode + " > " + this.isCore;
    }

    public DegreeModule(String name, String code, Integer credits, String teachingPeriod, String degreeCode,
            Boolean isCore) {
        super(name, code, credits, teachingPeriod);
        this.degreeCode = degreeCode;
        this.isCore = isCore;
    }

}

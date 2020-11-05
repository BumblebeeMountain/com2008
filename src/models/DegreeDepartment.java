// DegreeDepartment class is meant to link the Degree class and Department class

package models;

public class DegreeDepartment {

    // instance variables

    private String departmentCode;
    private String degreeCode;
    private Boolean isLead;

    // getters & setters

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentCode() {
        return this.departmentCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeCode() {
        return this.degreeCode;
    }

    public void setIsLead(Boolean isLead) {
        this.isLead = isLead;
    }

    public Boolean getIsLead() {
        return this.isLead;
    }

    public DegreeDepartment (String departmentCode, String degreeCode, Boolean isLead) {
        this.departmentCode = departmentCode;
        this.degreeCode = degreeCode;
        this.isLead = isLead;
    }
}
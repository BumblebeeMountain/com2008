// Registration class returns array of all registrations

package models;

public class Registration {

    // instance variables

    private Integer registrationNumber;
    private String degreeCode;
    private Character level;
    private Character period;
    private Integer startYear;
    private SelectedModule[] selectedModules;

    // getters & setters

    public void setLevel(Character level) {
        this.level = level;
    }

    public Character getLevel() {
        return this.level;
    }

    public void setPeriod(Character period) {
        this.period = period;
    }

    public Character getPeriod() {
        return this.period;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getStartYear() {
        return this.startYear;
    }

    public void setSelectedModules(SelectedModule[] selectedModules) {
        this.selectedModules = selectedModules;
    }

    public SelectedModule[] getSelectedModules() {
        return this.selectedModules;
    }

    public Integer getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDegreeCode() {
        return this.degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String toString() {
        return this.registrationNumber + " > " + this.degreeCode + " > " + this.level + " > " + this.period + " > "
                + this.startYear;
    }

    // constructor

    public Registration(Integer registrationNumber, String degreeCode, Character level, Character period, Integer startYear,
            SelectedModule[] selectedModules) {
        this.registrationNumber = registrationNumber;
        this.degreeCode = degreeCode;
        this.level = level;
        this.period = period;
        this.startYear = startYear;
        this.selectedModules = selectedModules;
    }
}
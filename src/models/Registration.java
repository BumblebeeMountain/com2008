// Registration class returns array of all registrations

package models;
import java.sql.Date;

public class Registration {

    // instance variables

    private String level;
    private String period;
    private Date startDate;

    // getters & setters

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    // constructor

    public Registration (String level, String period, Date startDate) {
        this.level = level;
        this.period = period;
        this.startDate = startDate;
    }
}
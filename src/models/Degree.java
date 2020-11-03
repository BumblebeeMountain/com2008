// A simple class to make an object of a record in the database
//  eg so we can return an array of degrees etc

package models;

public class Degree {
    
    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public Degree () {}

    public Degree (String code) {
        this.code = code;
    }

}

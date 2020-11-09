// Department class returns array of departments

package models;

public class Department {

    // instance variables

    private String name;
    private String code;

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

    public String toString () {
        return this.code + " > " + this.name;
    }

    // constructor

    public Department (String name, String code) {
        this.name = name;
        this.code = code;
    }
}
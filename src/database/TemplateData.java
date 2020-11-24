package database;

import controllers.DegreeController;
import controllers.DepartmentController;
import controllers.ModuleController;
import controllers.RegistrationController;
import controllers.StudentController;
import controllers.UserController;
import models.Constants;
import models.Student;

public class TemplateData {
    
    public static void main (String[] args) {

        // Create some departments
        try {   
            DepartmentController.createDepartment("COM", "Computer Science");
            DepartmentController.createDepartment("ENG", "Engineering");
            DepartmentController.createDepartment("BIO", "Biology");
        } catch (Exception e) {
            System.out.println("There was an error with department creation: " + e);
        }

        // Create some degrees
        try {
            DegreeController.createDegree("COMU01", "Computer Science", false, 3);
            DepartmentController.createDegreeDepartment("COM", "COMU01", true);
            DegreeController.createDegree("COMU02", "Computer Science w/ Year In Industry", true, 3);
            DepartmentController.createDegreeDepartment("COM", "COMU02", true);
            DepartmentController.createDegreeDepartment("ENG", "COMU02", false);
        } catch (Exception e) {
            System.out.println("There was an error with degree creation: " + e);
        }

        // Create some modules
        try {
            ModuleController.createModule("COM1001", "Intro to software engineering 1", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1002", "Intro to software engineering 2", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1003", "Intro to software engineering 3", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1004", "Intro to software engineering 4", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1005", "Intro to software engineering 5", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1006", "Intro to software engineering 6", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1007", "Intro to software engineering 7", 20, "AUTUMN~SPRING");
        } catch (Exception e) {
            System.out.println("There was an error with module creation: " + e);
        }

        // Link some modules
        try {
            DegreeController.createDegreeModule("COMU01", "COM1001", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1002", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1003", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1004", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1005", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1006", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1007", false, "1");
        } catch (Exception e) {
            System.out.println("There was an error with module linking: " + e);
        }

        // Insert some users
        try {
            UserController.createUser(Constants.Title.MR, "Brian", "Smith", "password", Constants.AccountType.TEACHER);
            UserController.createUser(Constants.Title.MR, "James", "Smith", "password", Constants.AccountType.REGISTRAR);
            UserController.createUser(Constants.Title.MR, "David", "Grey", "password", Constants.AccountType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println("There was an error with user creation: " + e);
        }

        // Create a student
        try {
            Student s = StudentController.createStudent(Constants.Title.MR, "Dominic", "Barter", "password", Constants.AccountType.STUDENT, "Dawn Walker");
            RegistrationController.createInitialRegistration(s.getRegistrationNumber(), "COMU01");
        } catch (Exception e) {
            System.out.println("There was an error with user creation: " + e);
        }

        // try {

        // } catch (Exception e) {
        //     System.out.println("There was an error with user creation: " + e);
        // }

    }





}

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

    public static void main(String[] args) {

        // data1();
        data2();

    }

    public static void data1() {

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
            DepartmentController.createDegreeDepartment("COM", "COMU01", false);
            DegreeController.createDegree("COMU02", "Computer Science w/ Year In Industry", false, 3);
            DepartmentController.createDegreeDepartment("COM", "COMU02", false);
            DepartmentController.createDegreeDepartment("ENG", "COMU02", false);
        } catch (Exception e) {
            System.out.println("There was an error with degree creation: " + e);
        }

        // Create some modules
        try {
            ModuleController.createModule("COM1001", "Intro to software engineering 1", 20, "AUTUMN");
            ModuleController.createModule("COM1002", "Intro to software engineering 2", 20, "AUTUMN");
            ModuleController.createModule("COM1003", "Intro to software engineering 3", 20, "AUTUMN");
            ModuleController.createModule("COM1004", "Intro to software engineering 4", 20, "AUTUMN");
            ModuleController.createModule("COM1005", "Intro to software engineering 5", 20, "AUTUMN");
            ModuleController.createModule("COM1006", "Intro to software engineering 6", 20, "AUTUMN");
            ModuleController.createModule("COM1007", "Intro to software engineering 7", 20, "AUTUMN");
        } catch (Exception e) {
            System.out.println("There was an error with module creation: " + e);
        }

        // Link some modules
        try {
            DegreeController.createDegreeModule("COMU01", "COM1001", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1002", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1003", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1004", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1005", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1006", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1007", false, "1");
        } catch (Exception e) {
            System.out.println("There was an error with module linking: " + e);
        }

        // Insert some users
        try {
            UserController.createUser(Constants.Title.MR, "Brian", "Smith", "password", Constants.AccountType.TEACHER);
            UserController.createUser(Constants.Title.MR, "James", "Smith", "password",
                    Constants.AccountType.REGISTRAR);
            UserController.createUser(Constants.Title.MR, "David", "Grey", "password",
                    Constants.AccountType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println("There was an error with user creation: " + e);
        }

        // Create a student
        try {
            Student s = StudentController.createStudent(Constants.Title.MR, "Dominic", "Barter", "password",
                    Constants.AccountType.STUDENT, "Dawn Walker");
            RegistrationController.createInitialRegistration(s.getRegistrationNumber(), "COMU01");
        } catch (Exception e) {
            System.out.println("There was an error with user creation: " + e);
        }

        // try {

        // } catch (Exception e) {
        // System.out.println("There was an error with user creation: " + e);
        // }

    }

    public static void data2() {

        try {
            ModuleController.createModule("COM1010", "Intro to software engineering 10", 10, "AUTUMN");
            ModuleController.createModule("COM1011", "Intro to software engineering 11", 10, "AUTUMN");
            ModuleController.createModule("COM1012", "Intro to software engineering 12", 10, "AUTUMN");
            ModuleController.createModule("COM1013", "Intro to software engineering 13", 10, "AUTUMN");
        } catch (Exception e) {
            System.out.println("There was an error with module creation: " + e);
        }

        // Link some modules
        try {
            DegreeController.createDegreeModule("COMU01", "COM1010", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1011", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1012", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1013", false, "1");
        } catch (Exception e) {
            System.out.println("There was an error with module linking: " + e);
        }

    }

    public static void fullData() {

        // Insert some users
        try {
            UserController.createUser(Constants.Title.MR, "Brian", "Smith", "password", Constants.AccountType.TEACHER);
            UserController.createUser(Constants.Title.MR, "James", "Smith", "password",
                    Constants.AccountType.REGISTRAR);
            UserController.createUser(Constants.Title.MR, "David", "Grey", "password",
                    Constants.AccountType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println("There was an error with user creation: " + e);
        }

        // Create some departments
        try {
            DepartmentController.createDepartment("BUS", "Business School");
            DepartmentController.createDepartment("COM", "Computer Science");
            DepartmentController.createDepartment("PSY", "Psychology");
            DepartmentController.createDepartment("LAN", "Modern Languages");
        } catch (Exception e) {
            System.out.println("There was an error with department creation: " + e);
        }

        // Create some degrees
        try {
            DegreeController.createDegree("BUSU01", "MSc in Business Administration", false, 4);
            DepartmentController.createDegreeDepartment("BUS", "BUSU01", true);

            DegreeController.createDegree("COMU01", "MEng Software Engineering with a Year in Industry", true, 4);
            DepartmentController.createDegreeDepartment("COM", "COMU01", true);

            DegreeController.createDegree("COMU02", "BSc Information Systems", false, 3);
            DepartmentController.createDegreeDepartment("COM", "COMU02", true);
            DepartmentController.createDegreeDepartment("BUS", "COMU02", false);
            DepartmentController.createDegreeDepartment("LAN", "COMU02", false);

            DegreeController.createDegree("PSYU01", "MPsy Cognitive Science", false, 4);
            DepartmentController.createDegreeDepartment("PSY", "PSYU01", true);
            DepartmentController.createDegreeDepartment("COM", "PSYU01", false);

            // One year degree for testing
            DegreeController.createDegree("PSYP01", "MPsy Cognitive Science", false, 4);
            DepartmentController.createDegreeDepartment("PSY", "PSYP01", true);
            DepartmentController.createDegreeDepartment("COM", "PSYP01", false);
        } catch (Exception e) {
            System.out.println("There was an error with degree creation: " + e);
        }

        // Create some modules
        try {

            // BUSU01

            // COMU01

            // COMU02

            // 

            ModuleController.createModule("COM1001", "Intro to software engineering 1", 20, "AUTUMN");
            ModuleController.createModule("COM1002", "Intro to software engineering 2", 20, "AUTUMN");
            ModuleController.createModule("COM1003", "Intro to software engineering 3", 20, "AUTUMN");
            ModuleController.createModule("COM1004", "Intro to software engineering 4", 20, "AUTUMN");
            ModuleController.createModule("COM1005", "Intro to software engineering 5", 20, "AUTUMN");
            ModuleController.createModule("COM1006", "Intro to software engineering 6", 20, "AUTUMN");
            ModuleController.createModule("COM1007", "Intro to software engineering 7", 20, "AUTUMN");
        } catch (Exception e) {
            System.out.println("There was an error with module creation: " + e);
        }

        // Link some modules
        try {
            DegreeController.createDegreeModule("COMU01", "COM1001", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1002", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1003", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1004", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1005", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1006", false, "1");
            DegreeController.createDegreeModule("COMU01", "COM1007", false, "1");
        } catch (Exception e) {
            System.out.println("There was an error with module linking: " + e);
        }

    }

}

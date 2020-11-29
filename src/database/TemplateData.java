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

        fullData();

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

            ModuleController.createModule("BUS1001", "Accounting and Finance for Managers", 20, "AUTUMN~SPRING"); // Year 1
            ModuleController.createModule("BUS1002", "Business Management in Context", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS1003", "Management Themes and Perspectives", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS1004", "Business Economics", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS1005", "Introduction to Behaviour at Work", 20, "AUTUMN~SPRING");

            ModuleController.createModule("BUS1010", "Analysis for Decision Making A", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS1011", "Analysis for Decision Making B", 20, "AUTUMN~SPRING");


            ModuleController.createModule("BUS2001", "Business Strategy", 20, "AUTUMN~SPRING"); // Year 2
            ModuleController.createModule("BUS2002", "Essentials of Marketing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS2003", "Organisational Behaviour", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS2004", "Principles of Operations Management", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS2005", "Business Intelligence", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS2006", "Business Statistics", 20, "AUTUMN~SPRING");


            ModuleController.createModule("BUS3001", "Corporate Social Responsibility", 20, "AUTUMN~SPRING"); // Year 3
            ModuleController.createModule("BUS3002", "Digital Marketing", 20, "AUTUMN~SPRING");

            ModuleController.createModule("BUS3010", "International Marketing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS3011", "New Venture Creation", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS3012", "Work-Related Health and Well-Being", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS3013", "Managing complex projects", 20, "AUTUMN~SPRING");
            
            ModuleController.createModule("BUS3100", "Dissertation Project", 40, "AUTUMN~SPRING");


            ModuleController.createModule("BUS4001", "Accounting and Finance", 15, "AUTUMN~SPRING"); // Year 4
            ModuleController.createModule("BUS4002", "Managing Organisational Behaviour", 15, "AUTUMN~SPRING");
            ModuleController.createModule("BUS4003", "Operations Management", 15, "AUTUMN~SPRING");
            ModuleController.createModule("BUS4004", "Global Managerial Economics", 15, "AUTUMN~SPRING");
            ModuleController.createModule("BUS4005", "The Intelligent Organisation", 20, "AUTUMN~SPRING");

            ModuleController.createModule("BUS4010", "Corporate Finance", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS4011", "Risk & Crisis Management", 20, "AUTUMN~SPRING");
            ModuleController.createModule("BUS4012", "Human Resource Management", 20, "AUTUMN~SPRING");

            ModuleController.createModule("BUS4100", "Dissertation Project", 60, "AUTUMN~SPRING");

            // COMU01

            ModuleController.createModule("COM1001", "Foundations of Computer Science", 20, "AUTUMN~SPRING"); // Year 1
            ModuleController.createModule("COM1002", "Introduction to Software Engineering", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1003", "Java Programming", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1004", "Machines & Intelligence", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1005", "Web & Internet Technology", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM1010", "Devices & Networks", 20, "AUTUMN~SPRING");


            ModuleController.createModule("COM2001", "Automata, Computation & Complexity", 20, "AUTUMN~SPRING"); // Year 2
            ModuleController.createModule("COM2002", "Data Driven Computing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2003", "Robotics", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2004", "Software Hut", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2005", "Systems Design & Security", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2006", "Functional Programming", 20, "AUTUMN~SPRING");


            ModuleController.createModule("COM3001", "Finance and Law for Engineers", 20, "AUTUMN~SPRING"); // Year 3
            ModuleController.createModule("COM3002", "Software Testing & Analysis", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM3010", "3D Computer Graphics", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM3011", "Adaptive Intelligence", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM3012", "Advanced Algorithms", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM3100", "Dissertation Project", 40, "AUTUMN~SPRING");


            ModuleController.createModule("ENG3000", "Year In Industry", 120, "AUTUMN~SPRING"); // YII


            ModuleController.createModule("COM4001", "Software Development for Mobile Devices", 15, "AUTUMN~SPRING"); // Year 4
            ModuleController.createModule("COM4002", "Introduction to Genesys", 15, "AUTUMN~SPRING");
            ModuleController.createModule("COM4003", "Object-Oriented Programming and Software Design", 15, "AUTUMN~SPRING");
            ModuleController.createModule("COM4004", "Testing and Verification in Safety-Critical Systems", 15, "AUTUMN~SPRING");
            ModuleController.createModule("COM4005", "Research Methods and Professional Issues", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM4010", "The Internet of Things", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM4011", "The Intelligent Web", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM4012", "Theory of Distributed Systems", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM4100", "Dissertation Project", 60, "AUTUMN~SPRING");
 
            // COMU02

            ModuleController.createModule("COM1021", "Employability And Work Based Learning", 20, "AUTUMN~SPRING"); // Year 1
            ModuleController.createModule("COM1022", "Information Analysis For Business", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1023", "Information Systems", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1024", "Introduction To Digital Technology", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM1025", "It Service Support", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM1030", "Understanding Organisations", 20, "AUTUMN~SPRING");


            ModuleController.createModule("COM2021", "Business Analysis For Enterprise Systems", 20, "AUTUMN~SPRING"); // Year 2
            ModuleController.createModule("COM2022", "Employability And Project Based Learning", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2023", "Introduction To Cloud Computing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2024", "It Infrastructure And Emerging Technologies", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2025", "Digital Marketing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM2026", "Information Management", 20, "AUTUMN~SPRING");


            ModuleController.createModule("COM3021", "Business Architecture Expert", 20, "AUTUMN~SPRING"); // Year 3
            ModuleController.createModule("COM3022", "Entrepreneurial Theory And Practice 3", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM3030", "Applied Business Intelligence", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM3031", "Developing Strategies For Change", 20, "AUTUMN~SPRING");
            ModuleController.createModule("COM3032", "Innovative Multimedia", 20, "AUTUMN~SPRING");

            ModuleController.createModule("COM3200", "Dissertation Project", 40, "AUTUMN~SPRING");

            // PSYU01 & PSYP01

            ModuleController.createModule("PSY1001", "Research Methods in Psychology", 20, "AUTUMN~SPRING"); // Year 1
            ModuleController.createModule("PSY1002", "Preparing for University Psychology", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY1003", "Introduction to Personal Development and Employability", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY1004", "Introduction to Programming", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY1005", "Object-Oriented Programming", 20, "AUTUMN~SPRING");

            ModuleController.createModule("PSY1010", "Statistics A", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY1011", "Statistics B", 20, "AUTUMN~SPRING");


            ModuleController.createModule("PSY2001", "Brain and Behaviour", 20, "AUTUMN~SPRING"); // Year 2
            ModuleController.createModule("PSY2002", "Memory Attention and Language", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY2003", "Enhancing employability and career planning", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY2004", "Artificial Intelligence", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY2005", "Seeing and Hearing", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY2006", "Data Structures and Algorithms", 20, "AUTUMN~SPRING");


            ModuleController.createModule("PSY3001", "Advanced employability skills and career progression", 20, "AUTUMN~SPRING"); // Year 3
            ModuleController.createModule("PSY3002", "The Neuroscience of Human Nature", 20, "AUTUMN~SPRING");

            ModuleController.createModule("PSY3010", "Decision making science in theory and practice", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY3011", "Information Retrieval", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY3012", "Evolutionary Computation and Genetic Programming", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY3013", "Computer Vision", 20, "AUTUMN~SPRING");
            
            ModuleController.createModule("PSY3100", "Dissertation Project", 40, "AUTUMN~SPRING");


            ModuleController.createModule("PSY4001", "Cognitive Studies Seminar", 15, "AUTUMN~SPRING"); // Year 4
            ModuleController.createModule("PSY4002", "Computational Neuroscience", 15, "AUTUMN~SPRING");
            ModuleController.createModule("PSY4003", "Current Issues in Second Language Acquisition", 15, "AUTUMN~SPRING");
            ModuleController.createModule("PSY4004", "Fundamentals of Cognition", 15, "AUTUMN~SPRING");
            ModuleController.createModule("PSY4005", "Fundamentals of Neuroscience", 20, "AUTUMN~SPRING");

            ModuleController.createModule("PSY4010", "Human Evolution", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY4011", "Introduction to Language and Linguistics", 20, "AUTUMN~SPRING");
            ModuleController.createModule("PSY4012", "Language and Communication", 20, "AUTUMN~SPRING");

            ModuleController.createModule("PSY4100", "Dissertation Project", 60, "AUTUMN~SPRING");


        } catch (Exception e) {
            System.out.println("There was an error with module creation: " + e);
        }

        // Link some modules
        try {

            // BUSU01
            DegreeController.createDegreeModule("BUSU01", "BUS1001", true, "1"); // Year 1
            DegreeController.createDegreeModule("BUSU01", "BUS1002", true, "1");
            DegreeController.createDegreeModule("BUSU01", "BUS1003", true, "1");
            DegreeController.createDegreeModule("BUSU01", "BUS1004", true, "1");
            DegreeController.createDegreeModule("BUSU01", "BUS1005", true, "1");

            DegreeController.createDegreeModule("BUSU01", "BUS1010", false, "1");
            DegreeController.createDegreeModule("BUSU01", "BUS1011", false, "1");


            DegreeController.createDegreeModule("BUSU01", "BUS2001", true, "2"); // Year 2
            DegreeController.createDegreeModule("BUSU01", "BUS2002", true, "2");
            DegreeController.createDegreeModule("BUSU01", "BUS2003", true, "2");
            DegreeController.createDegreeModule("BUSU01", "BUS2004", true, "2");
            DegreeController.createDegreeModule("BUSU01", "BUS2005", true, "2");
            DegreeController.createDegreeModule("BUSU01", "BUS2006", true, "2");


            DegreeController.createDegreeModule("BUSU01", "BUS3001", true, "3"); // Year 3
            DegreeController.createDegreeModule("BUSU01", "BUS3002", true, "3");

            DegreeController.createDegreeModule("BUSU01", "BUS3010", false, "3");
            DegreeController.createDegreeModule("BUSU01", "BUS3011", false, "3");
            DegreeController.createDegreeModule("BUSU01", "BUS3012", false, "3");
            DegreeController.createDegreeModule("BUSU01", "BUS3013", false, "3");

            DegreeController.createDegreeModule("BUSU01", "BUS3100", true, "3");
            

            DegreeController.createDegreeModule("BUSU01", "BUS4001", true, "4"); // Year 4
            DegreeController.createDegreeModule("BUSU01", "BUS4002", true, "4");
            DegreeController.createDegreeModule("BUSU01", "BUS4003", true, "4");
            DegreeController.createDegreeModule("BUSU01", "BUS4004", true, "4");
            DegreeController.createDegreeModule("BUSU01", "BUS4005", true, "4");

            DegreeController.createDegreeModule("BUSU01", "BUS4010", false, "4");
            DegreeController.createDegreeModule("BUSU01", "BUS4011", false, "4");
            DegreeController.createDegreeModule("BUSU01", "BUS4012", false, "4");

            DegreeController.createDegreeModule("BUSU01", "BUS4100", true, "4");

            // COMU01
            DegreeController.createDegreeModule("COMU01", "COM1001", true, "1"); // Year 1
            DegreeController.createDegreeModule("COMU01", "COM1002", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1003", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1004", true, "1");
            DegreeController.createDegreeModule("COMU01", "COM1005", true, "1");

            DegreeController.createDegreeModule("COMU01", "COM1010", false, "1");


            DegreeController.createDegreeModule("COMU01", "COM2001", true, "2"); // Year 2
            DegreeController.createDegreeModule("COMU01", "COM2002", true, "2");
            DegreeController.createDegreeModule("COMU01", "COM2003", true, "2");
            DegreeController.createDegreeModule("COMU01", "COM2004", true, "2");
            DegreeController.createDegreeModule("COMU01", "COM2005", true, "2");
            DegreeController.createDegreeModule("COMU01", "COM2006", true, "2");


            DegreeController.createDegreeModule("COMU01", "COM3001", true, "3"); // Year 3
            DegreeController.createDegreeModule("COMU01", "COM3001", true, "3");

            DegreeController.createDegreeModule("COMU01", "COM3010", false, "3");
            DegreeController.createDegreeModule("COMU01", "COM3011", false, "3");
            DegreeController.createDegreeModule("COMU01", "COM3012", false, "3");

            DegreeController.createDegreeModule("COMU01", "COM3100", true, "3");


            DegreeController.createDegreeModule("COMU01", "ENG3000", true, "P"); // YII


            DegreeController.createDegreeModule("COMU01", "COM4001", true, "4"); // Year 4
            DegreeController.createDegreeModule("COMU01", "COM4002", true, "4");
            DegreeController.createDegreeModule("COMU01", "COM4003", true, "4");
            DegreeController.createDegreeModule("COMU01", "COM4004", true, "4");
            DegreeController.createDegreeModule("COMU01", "COM4005", true, "4");

            DegreeController.createDegreeModule("COMU01", "COM4010", false, "4");
            DegreeController.createDegreeModule("COMU01", "COM4011", false, "4");
            DegreeController.createDegreeModule("COMU01", "COM4012", false, "4");

            DegreeController.createDegreeModule("COMU01", "COM4100", true, "4");

            // COMU02
            DegreeController.createDegreeModule("COMU02", "COM1021", true, "1"); // Year 1
            DegreeController.createDegreeModule("COMU02", "COM1022", true, "1");
            DegreeController.createDegreeModule("COMU02", "COM1023", true, "1");
            DegreeController.createDegreeModule("COMU02", "COM1024", true, "1");
            DegreeController.createDegreeModule("COMU02", "COM1025", true, "1");

            DegreeController.createDegreeModule("COMU02", "COM1030", false, "1");


            DegreeController.createDegreeModule("COMU02", "COM2021", true, "2"); // Year 2
            DegreeController.createDegreeModule("COMU02", "COM2022", true, "2");
            DegreeController.createDegreeModule("COMU02", "COM2023", true, "2");
            DegreeController.createDegreeModule("COMU02", "COM2024", true, "2");
            DegreeController.createDegreeModule("COMU02", "COM2025", true, "2");
            DegreeController.createDegreeModule("COMU02", "COM2026", true, "2");


            DegreeController.createDegreeModule("COMU02", "COM3021", true, "3"); // Year 3
            DegreeController.createDegreeModule("COMU02", "COM3022", true, "3");

            DegreeController.createDegreeModule("COMU02", "COM3030", false, "3");
            DegreeController.createDegreeModule("COMU02", "COM3031", false, "3");
            DegreeController.createDegreeModule("COMU02", "COM3032", false, "3");

            DegreeController.createDegreeModule("COMU02", "COM3200", true, "3");

            // PSYU01 
            DegreeController.createDegreeModule("PSYU01", "PSY1001", true, "1"); // Year 1
            DegreeController.createDegreeModule("PSYU01", "PSY1002", true, "1");
            DegreeController.createDegreeModule("PSYU01", "PSY1003", true, "1");
            DegreeController.createDegreeModule("PSYU01", "PSY1004", true, "1");
            DegreeController.createDegreeModule("PSYU01", "PSY1005", true, "1");

            DegreeController.createDegreeModule("PSYU01", "PSY1010", false, "1");
            DegreeController.createDegreeModule("PSYU01", "PSY1011", false, "1");


            DegreeController.createDegreeModule("PSYU01", "PSY2001", true, "2"); // Year 2
            DegreeController.createDegreeModule("PSYU01", "PSY2002", true, "2");
            DegreeController.createDegreeModule("PSYU01", "PSY2003", true, "2");
            DegreeController.createDegreeModule("PSYU01", "PSY2004", true, "2");
            DegreeController.createDegreeModule("PSYU01", "PSY2005", true, "2");
            DegreeController.createDegreeModule("PSYU01", "PSY2006", true, "2");


            DegreeController.createDegreeModule("PSYU01", "PSY3001", true, "3"); // Year 3
            DegreeController.createDegreeModule("PSYU01", "PSY3002", true, "3");
            
            DegreeController.createDegreeModule("PSYU01", "PSY3010", false, "3");
            DegreeController.createDegreeModule("PSYU01", "PSY3011", false, "3");
            DegreeController.createDegreeModule("PSYU01", "PSY3012", false, "3");
            DegreeController.createDegreeModule("PSYU01", "PSY3013", false, "3");

            DegreeController.createDegreeModule("PSYU01", "PSY3100", true, "3");


            DegreeController.createDegreeModule("PSYU01", "PSY4001", true, "4"); // Year 4
            DegreeController.createDegreeModule("PSYU01", "PSY4002", true, "4");
            DegreeController.createDegreeModule("PSYU01", "PSY4003", true, "4");
            DegreeController.createDegreeModule("PSYU01", "PSY4004", true, "4");
            DegreeController.createDegreeModule("PSYU01", "PSY4005", true, "4");

            DegreeController.createDegreeModule("PSYU01", "PSY4010", false, "4");
            DegreeController.createDegreeModule("PSYU01", "PSY4011", false, "4");
            DegreeController.createDegreeModule("PSYU01", "PSY4012", false, "4");

            DegreeController.createDegreeModule("PSYU01", "PSY4100", true, "4");

            // PSYP01
            DegreeController.createDegreeModule("PSYP01", "PSY4001", true, "4"); // Year 4
            DegreeController.createDegreeModule("PSYP01", "PSY4002", true, "4");
            DegreeController.createDegreeModule("PSYP01", "PSY4003", true, "4");
            DegreeController.createDegreeModule("PSYP01", "PSY4004", true, "4");
            DegreeController.createDegreeModule("PSYP01", "PSY4005", true, "4");

            DegreeController.createDegreeModule("PSYP01", "PSY4010", false, "4");
            DegreeController.createDegreeModule("PSYP01", "PSY4011", false, "4");
            DegreeController.createDegreeModule("PSYP01", "PSY4012", false, "4");

            DegreeController.createDegreeModule("PSYP01", "PSY4100", true, "4");

        } catch (Exception e) {
            System.out.println("There was an error with module linking: " + e);
        }

    }

}

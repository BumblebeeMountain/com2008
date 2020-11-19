package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Degree;
import models.Student;
import models.User;
import models.Constants.DegreeClass;
import models.Constants.AccountType;
import models.Constants.Title;
import database.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class StudentController {

    private final static String GET_ALL_REG_NO_COMMAND = "SELECT registrationNumber FROM Student;";
    private final static String GET_STUDENT_BY_REG_COMMAND = "SELECT * FROM Student WHERE registrationNumber = ?;";
    private final static String GET_REG_BY_EMAIL_COMMAND = "SELECT registrationNumber FROM Student WHERE email = ?;";
    private final static String GET_EMAIL_BY_REG_COMMAND = "SELECT email FROM Student WHERE registrationNumber = ?;";

    private final static String GET_DEGREE_CODE_FROM_REG_COMMAND = "SELECT Degree.code FROM " +
        "((Degree INNER JOIN Registration ON Degree.code = Registration.degreeCode) " +
        "INNER JOIN Student ON Registration.studentRegistrationNumber = Student.registrationNumber);";
    private final static String REMOVE_STUDENT_BY_REG_COMMAND = "DELETE FROM Student WHERE registrationNumber = ?;";
    private final static String CREATE_STUDENT_COMMAND = "INSERT INTO Student(email, personalTutor, hasGraduated) VALUES(?, ?, false);";

    public static void main(String[] args) {
        try {

            Student reg1 = createStudent(Title.MR, "James", "Edmundson", "pwd", AccountType.STUDENT, "Neil Walkinshaw");
            System.out.println("James " + reg1);

            Student reg2 = createStudent(Title.MR, "Dom", "Barter", "pwd", AccountType.STUDENT, "Dawn Walker");
            System.out.println("Dom " + reg2);

            System.out.println("all Students:");
            for (Student s : getAllStudents() ) {
                System.out.println(s);
            }

            try {
                Student james = getStudent(reg1.getRegistrationNumber());
                System.out.println(james);
            } catch (NoRecordException e) {
                System.out.println("James doesn't exist");
            }

            try {
                Student dom = getStudent(reg2.getRegistrationNumber());
                System.out.println(dom);
            } catch (NoRecordException e) {
                System.out.println("Dom doesn't exist");
            }
            
            System.out.println("randomer exists:");
            System.out.println(studentExists(28549505));

            System.out.println("removing james");
            removeStudent(reg1.getRegistrationNumber());

            System.out.println("james exists: ");
            System.out.println(studentExists(1));

            System.out.println("all Students:");
            for (Student s : getAllStudents() ) {
                System.out.println(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * getAllStudents()
     * Function which returns a list of all students
     * @return Student[] - the students
     * @throws GeneralProcessingException
     */
    public static Student[] getAllStudents() throws GeneralProcessingException {
        Student[] students;
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {

            pstmt = con.prepareStatement(GET_ALL_REG_NO_COMMAND);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Student> studentList = new ArrayList<>();

            while (rs.next()) {
                Student student;
                // get the reg num of the student
                int regNum = rs.getInt("registrationNumber");
                try {
                    // get the student object
                    student = getStudent(regNum);
                } catch (NoRecordException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
                // add the student object to the list
                studentList.add(student);

            }
            students = new Student[studentList.size()];
            studentList.toArray(students);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
        return students;
    }


    /**
     * getStudent()
     * Function which returns a student given the students registration number
     * @param registrationNumber - Integer- The students registration number
     * @return Student - the student object
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Student getStudent(Integer registrationNumber) throws GeneralProcessingException, NoRecordException {
        Student student;
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            pstmt = con.prepareStatement(GET_STUDENT_BY_REG_COMMAND);
            pstmt.setInt(1, registrationNumber);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null || !rs.next()) { throw new NoRecordException(); }

            int regNum = rs.getInt("registrationNumber");
            String email = rs.getString("email");
            String pTutor = rs.getString("personalTutor");
            Boolean hasGraduated = rs.getBoolean("hasGraduated");
            User user;
            try {
                // need to get the user account which is linked
                user = UserController.getUser(email);
            } catch (NoRecordException ex) {
                ex.printStackTrace();
                throw new GeneralProcessingException();
            }

            Title title = user.getTitle();
            String forename = user.getForename();
            String surname = user.getSurname();

            student = new Student(email, title, forename, surname, regNum, pTutor, hasGraduated);
            return student;

        } catch (SQLException ex ) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    /**
     * getStudent()
     * Function to get a student from theri email address
     * @param email - String - the email of the student to get
     * @return Student - the student with that email address
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Student getStudent(String email) throws GeneralProcessingException, NoRecordException {
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            pstmt = con.prepareStatement(GET_REG_BY_EMAIL_COMMAND);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null || !rs.next()) { throw new NoRecordException(); }

            // only get the registration number of the student
            int regNum = rs.getInt("registrationNumber");

            // use the above function to get the user from their reg number.
            return getStudent(regNum);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    /**
     * getStudentDegree()
     * Function that given a student registration number, returns their degree
     * @param registrationNumber - Integer - The student reg number
     * @return Degree - The students degree
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Degree getStudentDegree(Integer registrationNumber) throws GeneralProcessingException, NoRecordException {
        PreparedStatement pstmt = null;

        // if no student with that reg num exists
        if (!studentExists(registrationNumber)) { throw new NoRecordException(); }

        try (Connection con = ConnectionManager.getConnection()) {

            // get the students degree code
            pstmt = con.prepareStatement(GET_DEGREE_CODE_FROM_REG_COMMAND);
            pstmt.setInt(1, registrationNumber);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null || !rs.next()) { throw new NoRecordException(); }

            String code = rs.getString("code");

            // get the degree from the degree controller
            return DegreeController.getDegree(code);

        } catch (SQLException ex ) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    /**
     * createStudent()
     * Function to create a student account
     * Takes all of the users details, then creates the user account first, then creates the student account
     * @param title - Title
     * @param forename - String
     * @param surname - String
     * @param password - String
     * @param accountType - AccountType
     * @param pTutor - String - The student's personal tutor
     * @return Student - the newly formed student object
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static Student createStudent(
            Title title,
            String forename,
            String surname,
            String password,
            AccountType accountType,
            String pTutor
            ) throws GeneralProcessingException {

        // create the user account, and get the users email
        String email = UserController.createUser(title, forename, surname, password, accountType).getEmail();
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {

            // create the student
            pstmt = con.prepareStatement(CREATE_STUDENT_COMMAND);
            pstmt.setString(1, email);
            pstmt.setString(2, pTutor);
            pstmt.execute();

            pstmt.close();

            // get the reg number from their email
            pstmt = con.prepareStatement(GET_REG_BY_EMAIL_COMMAND);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs == null || !rs.next()) { throw new GeneralProcessingException(); }
            int regNum = rs.getInt("registrationNumber");

            return new Student(email, title, forename, surname, regNum, pTutor, false); // Assume not graduated

        } catch (SQLException ex ) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    /**
     * removeStudent()
     * Function to remove a student from their registration number
     * @param registrationNumber - Integer
     * @return void
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static void removeStudent(Integer registrationNumber) throws GeneralProcessingException, NoRecordException {
        PreparedStatement pstmt = null;

        // cannot remove a student which doesn't exist
        if (!studentExists(registrationNumber)) {
            System.out.println("apparently the student doesn't exist");
            throw new NoRecordException(); }

        try (Connection con = ConnectionManager.getConnection()) {

            // first get the user's email address
            pstmt = con.prepareStatement(GET_EMAIL_BY_REG_COMMAND);
            pstmt.setInt(1, registrationNumber);

            ResultSet rs = pstmt.executeQuery();
            if (rs == null || !rs.next()) {
                System.out.println("#######\nresult set is null or no next");
                throw new NoRecordException();
            }

            String email = rs.getString("email");
            System.out.println(String.format("student email: #%s#", email));

            pstmt.close();

            // then delete the student account
            pstmt = con.prepareStatement(REMOVE_STUDENT_BY_REG_COMMAND);
            pstmt.setInt(1, registrationNumber);
            pstmt.execute();

            // finally delete the user account
            try {
                System.out.println("removing user with email");
                System.out.println(email);
                UserController.removeUser(email);
            } catch (NoRecordException ex ) {
                System.out.println("no user exists?");
                ex.printStackTrace();
                throw new GeneralProcessingException();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    // TODO
    // Bit of an epic so will do later
    public static DegreeClass calculateDegreeClassification (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    /**
     * isStudent()
     * Function to determin if a user is a student
     * @param user - User - the user to determine
     * @return Boolean - If the user is a student
     */
    public static boolean isStudent(User user) {
        return user.getAccountType() == AccountType.STUDENT;
    }

    /**
     * studentExists()
     * Function that given a registration number, determins if the student exists
     * @param registrationNumber - Integer - The student reg number
     * @return Boolean - if the student exists
     * @throws GeneralProcessingException
     */
    public static boolean studentExists(int registrationNumber) throws GeneralProcessingException {
        
        try {
            getStudent(registrationNumber);
        } catch (NoRecordException e) {
            return false;
        } catch (Exception e) {
            throw new GeneralProcessingException();
        }

        return true;

    }

    /**
     * Graduate a given student
     * @param registrationNumber
     * @throws GeneralProcessingException
     */
    public static void graduateStudent(
            Integer registrationNumber
            ) throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("UPDATE Student SET hasGraduated = true WHERE registrationNumber = ?;");
            pstmt.setInt(1, registrationNumber);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) {

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try { 
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }
    }

}

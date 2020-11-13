package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

import database.ConnectionManager;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import exceptions.ShouldGraduateException;
import models.Degree;
import models.Registration;
import models.SelectedModule;
import models.Student;

public class RegistrationController {

    public static void main(String[] args) {

        try {

            final Integer REG_NUMBER = 11;

            // First output all the current students
            // for (Student s: StudentController.getAllStudents())
            // System.out.println(s);

            // Try creating a registration
            try {
                createInitialRegistration(REG_NUMBER, "COMU01");
            } catch (ExistingRecordException e) {
                System.out.println("REG_NUMBER/COMU01 has already been inserted");
            }

            // Try adding selected modules to this registration
            try {
                createSelectedModule(REG_NUMBER, 'A', "COM1001");
            } catch (ExistingRecordException e) {
                System.out.println("COM1001 has already been selected");
            }
            try {
                createSelectedModule(REG_NUMBER, 'A', "COM1003");
            } catch (ExistingRecordException e) {
                System.out.println("COM1003 has already been selected");
            }

            // try {
            //     generateNextRegistration(REG_NUMBER, '2');
            // } catch (Exception e) {
            //     System.out.println("Something went wrong");
            // }

            try {
                updateFirstGrade(REG_NUMBER, new Character('B'), "COM1001", new Float(78.9));
            } catch (NoRecordException e) {
                System.out.println("Module not selected");
            }

            // Output all the current registrations
            Registration[] arr = getStudentRegistrations(REG_NUMBER);
            for (Registration r : arr)
                System.out.println(r);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets all the student registrations
     * 
     * @param registrationNumber
     * @return
     * @throws GeneralProcessingException
     */
    public static Registration[] getStudentRegistrations(Integer registrationNumber) throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<Registration> regs = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Registration WHERE studentRegistrationNumber = ?;");
            pstmt.setInt(1, registrationNumber);

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                Character period = res.getString("period").charAt(0);
                Character level = res.getString("level").charAt(0);
                Integer startYear = res.getInt("startYear");
                String degreeCode = res.getString("degreeCode");
                SelectedModule[] mods = getStudentSelectedModules(registrationNumber, period);
                regs.add(new Registration(registrationNumber, degreeCode, level, period, startYear, mods));
            }

        } catch (Exception e) { // Catch general exception

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        Registration[] arr = new Registration[regs.size()];
        arr = regs.toArray(arr);
        return arr;

    }

    /**
     * Gets a single registration of a student, given a certain period
     * 
     * @param registrationNumber
     * @param period
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Registration getStudentRegistration(Integer registrationNumber, Character period)
            throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Character level = null;
        Integer startYear = null;
        String degreeCode = null;
        SelectedModule[] mods = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con
                    .prepareStatement("SELECT * FROM Registration WHERE studentRegistrationNumber = ? AND period = ?;");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, period.toString());

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            level = res.getString("level").charAt(0);
            startYear = res.getInt("startYear");
            degreeCode = res.getString("degreeCode");
            mods = getStudentSelectedModules(registrationNumber, period);

        } catch (NoRecordException e) {

            throw new NoRecordException(); // Caught and re-thrown if there are no records

        } catch (Exception e) { // Catch general exception

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        // Return a new department object
        return new Registration(registrationNumber, degreeCode, level, period, startYear, mods);

    }

    /**
     * Create an initial student registration - requires that there be a student
     * already made
     * 
     * @param registrationNumber
     * @param degreeCode
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createInitialRegistration(Integer registrationNumber, String degreeCode)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for an exisiting registration
        Boolean registrationExists = true;
        try {
            getStudentRegistration(registrationNumber, 'A');
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            registrationExists = false;
        }
        if (registrationExists)
            throw new ExistingRecordException();

        // Work out the level of study
        Character initialLevelOfStudy = '1';
        if (degreeCode.charAt(3) == 'P')
            initialLevelOfStudy = '4';
        Integer currentYear = Year.now().getValue();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO Registration VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, new Character('A').toString());
            pstmt.setString(3, initialLevelOfStudy.toString());
            pstmt.setInt(4, currentYear);
            pstmt.setString(5, degreeCode);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

    }

    /**
     * Get the selected modules for a given reg no and period
     * 
     * @param registrationNumber
     * @param period
     * @return
     * @throws GeneralProcessingException
     */
    private static SelectedModule[] getStudentSelectedModules(Integer registrationNumber, Character period)
            throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<SelectedModule> mods = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM SelectedModule INNER JOIN Module ON SelectedModule.moduleCode = Module.code WHERE studentRegistrationNumber = ? AND period = ?;");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, period.toString());

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                String name = res.getString("name");
                String code = res.getString("code");
                Integer credits = res.getInt("credits");
                String teachingPeriod = res.getString("teachingPeriod");
                Float firstAttempt = res.getFloat("firstAttemptResult");
                Float secondAttempt = res.getFloat("secondAttemptResult");
                mods.add(new SelectedModule(name, code, credits, teachingPeriod, firstAttempt, secondAttempt));
            }

        } catch (Exception e) { // Catch general exception

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        SelectedModule[] arr = new SelectedModule[mods.size()];
        arr = mods.toArray(arr);
        return arr;

    }

    /**
     * Get a single selected module
     * 
     * @param registrationNumber
     * @param period
     * @param moduleCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    private static SelectedModule getSelectedModule(Integer registrationNumber, Character period, String moduleCode)
            throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String name = null;
        String code = null;
        Integer credits = null;
        String teachingPeriod = null;
        Float firstAttempt = null;
        Float secondAttempt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Module INNER JOIN SelectedModule ON Module.code = SelectedModule.moduleCode WHERE studentRegistrationNumber = ? AND period = ? AND moduleCode = ?;");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, period.toString());
            pstmt.setString(3, moduleCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            name = res.getString("name");
            code = moduleCode;
            credits = res.getInt("credits");
            teachingPeriod = res.getString("teachingPeriod");
            firstAttempt = res.getFloat("firstAttemptResult");
            secondAttempt = res.getFloat("secondAttemptResult");

        } catch (NoRecordException e) {

            throw new NoRecordException(); // Caught and re-thrown if there are no records

        } catch (Exception e) { // Catch general exception

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        // Return a new department object
        return new SelectedModule(name, code, credits, teachingPeriod, firstAttempt, secondAttempt);

    }

    /**
     * Generate a selected module
     * 
     * @param registrationNumber
     * @param period
     * @param moduleCode
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createSelectedModule(Integer registrationNumber, Character period, String moduleCode)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for an exisiting registration
        Boolean selectedModuleExists = true;
        try {
            getSelectedModule(registrationNumber, period, moduleCode);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            selectedModuleExists = false;
        }
        if (selectedModuleExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "INSERT INTO SelectedModule (moduleCode, studentRegistrationNumber, period) VALUES (?, ?, ?);");
            pstmt.setString(1, moduleCode);
            pstmt.setInt(2, registrationNumber);
            pstmt.setString(3, period.toString());

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

    }

    /**
     * Generate the next registration in a series
     * @param registrationNumber
     * @param level
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void generateNextRegistration(Integer registrationNumber, Character level)
            throws GeneralProcessingException, ExistingRecordException {
        
        // Get current registration
        Registration currentReg = null;
        try {
            currentReg = getMostRecentRegistration(registrationNumber);
        } catch (Exception e) {
            throw new GeneralProcessingException();
        }

        // Calculate next year and period and degree code
        Character nextPeriod = getNextPeriod(currentReg.getPeriod());
        Integer nextYear = getNextYear(currentReg.getStartYear());
        String degreeCode = currentReg.getDegreeCode();


        // Check for an exisiting registration
        Boolean registrationExists = true;
        try {
            getStudentRegistration(registrationNumber, nextPeriod);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            registrationExists = false;
        }
        if (registrationExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO Registration VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, nextPeriod.toString());
            pstmt.setString(3, level.toString());
            pstmt.setInt(4, nextYear);
            pstmt.setString(5, degreeCode);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) {

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

    }

    /**
     * Given a current character - what is the next period
     * 
     * @param c
     * @return
     */
    private static Character getNextPeriod(Character c) {
        return new Character((char) (c.charValue() + 1));
    }

    /**
     * Given a current year - get the next year
     * @param y
     * @return
     */
    private static Integer getNextYear(Integer y) {
        return y + 1;
    }

    /**
     * Get the most recent registration
     * 
     * @param registrationNumber
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Registration getMostRecentRegistration(Integer registrationNumber)
            throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Character level = null;
        Character period = null;
        Integer startYear = null;
        String degreeCode = null;
        SelectedModule[] mods = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Registration WHERE studentRegistrationNumber = ? ORDER BY period DESC LIMIT 1;");
            pstmt.setInt(1, registrationNumber);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            level = res.getString("level").charAt(0);
            period = res.getString("period").charAt(0);
            startYear = res.getInt("startYear");
            degreeCode = res.getString("degreeCode");
            mods = getStudentSelectedModules(registrationNumber, period);

        } catch (NoRecordException e) {

            throw new NoRecordException(); // Caught and re-thrown if there are no records

        } catch (Exception e) { // Catch general exception

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        // Return a new department object
        return new Registration(registrationNumber, degreeCode, level, period, startYear, mods);

    }

    /**
     * Estimates what the next progressing level should be given current reg status and degree info
     * @param registrationNumber
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     * @throws ShouldGraduateException
     */
    public static Character getNextProgressingLevel(Integer registrationNumber) throws GeneralProcessingException, NoRecordException, ShouldGraduateException {

        try {

            // Get the current registration
            Registration currentReg = getMostRecentRegistration(registrationNumber);
            String degreeCode = currentReg.getDegreeCode();
            Character currentLevel = currentReg.getLevel();

            // Get info about the degree
            Degree degree = DegreeController.getDegree(degreeCode);
            Integer maxLevel = degree.getMaxLevel();

            // If currently on placement - go to max level
            if (currentLevel == 'P' || currentLevel == 'p') {
                return new Character(maxLevel.toString().charAt(0));
            }

            // If on max level - graduate
            if (currentLevel == maxLevel.toString().charAt(0)) {
                throw new ShouldGraduateException();
            }

            // If the degree has a year in industry
            if (degree.getHasYearInIndustry()) {

                if (Integer.parseInt(currentLevel.toString()) == maxLevel - 1) {
                    return new Character('P');
                } else {
                    return getNextPeriod(currentLevel);
                }

            } else {

                return getNextPeriod(currentLevel);

            }

        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralProcessingException();
        }

    }

    /**
     * Update the first grade in the selected module
     * @param registrationNumber
     * @param period
     * @param moduleCode
     * @param grade
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static void updateFirstGrade(Integer registrationNumber, Character period, String moduleCode, Float grade)
            throws GeneralProcessingException, NoRecordException {

        // Make sure that the module has been selected
        try {
            getSelectedModule(registrationNumber, period, moduleCode);
        } catch (NoRecordException e) {
            throw e;
        }

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("UPDATE SelectedModule SET firstAttemptResult = ? WHERE moduleCode = ? AND studentRegistrationNumber = ? AND period = ?;");
            pstmt.setFloat(1, grade);
            pstmt.setString(2, moduleCode);
            pstmt.setInt(3, registrationNumber);
            pstmt.setString(4, period.toString());

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) {

            throw new GeneralProcessingException();

        } finally { // Close the prepared statement

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

    }

    public static void updateResitGrade(Integer registrationNumber, Character period, String moduleCode, Float grade)
            throws GeneralProcessingException {

    }

    public static void removeSelectedModule(Integer registrationNumber, Character period, String moduleCode)
            throws GeneralProcessingException {

    }

    public static Integer calculateOverallGrade(Integer registrationNumber, Character period)
            throws GeneralProcessingException, NoRecordException {
        return null;
    }

}
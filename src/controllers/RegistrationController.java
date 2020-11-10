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
import models.Registration;
import models.SelectedModule;

public class RegistrationController {

    public static void main(String[] args) {

        try {

            try {
                createInitialRegistration(2, "COMU01");
            } catch (ExistingRecordException e) {
                System.out.println("2/COMU01 has already been inserted");
            }

            // Output all the current registrations
            Registration[] arr = getStudentRegistrations(2);
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
     * Create an initial student registration - requires that there be a student already made
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
            pstmt = con.prepareStatement("SELECT * FROM Module INNER JOIN SelectedModule ON Module.code = SelectedModule.moduleCode WHERE studentRegistrationNumber = ? AND period = ? AND moduleCode = ?;");
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

    public static void createSelectedModule(Integer registrationNumber, Character period, String moduleCode)
            throws GeneralProcessingException, ExistingRecordException {

    }

    public static void generateNextRegistration(Integer registrationNumber, Character level)
            throws GeneralProcessingException, ExistingRecordException {

    }

    public static Character getMostRecentPeriod(Integer registrationNumber)
            throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Character getMostRecentLevel(Integer registrationNumber)
            throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void updateFirstGrade(Integer registrationNumber, Character period, String moduleCode, Integer grade)
            throws GeneralProcessingException {

    }

    public static void updateResitGrade(Integer registrationNumber, Character period, String moduleCode, Integer grade)
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
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionManager;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Registration;
import models.SelectedModule;

public class RegistrationController {

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
            pstmt = con.prepareStatement("SELECT * FROM Registration WHERE studentRegistrationNumber = ? AND period = ?;");
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, period.toString());

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null | !res.next()) throw new NoRecordException();

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
                if (pstmt != null) pstmt.close();
                if (res != null) res.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

        // Return a new department object
        return new Registration(registrationNumber, degreeCode, level, period, startYear, mods);

    }

    public static void createInitialRegistration(Integer registrationNumber, String degreeCode)
            throws GeneralProcessingException, ExistingRecordException {
        
        

    }

    /**
     * Get the selected modules for a given reg no and period
     * @param registrationNumber
     * @param period
     * @return
     * @throws GeneralProcessingException
     */
    private static SelectedModule[] getStudentSelectedModules (Integer registrationNumber, Character period) 
    throws GeneralProcessingException {
        
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<SelectedModule> mods = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM SelectedModule INNER JOIN Module ON SelectedModule.moduleCode = Module.code WHERE studentRegistrationNumber = ? AND period = ?;");
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

    public static SelectedModule getSelectedModule(Integer registrationNumber, Character period, String moduleCode)
            throws GeneralProcessingException, NoRecordException {
        return null;
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
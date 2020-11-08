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
import models.Module;

public class ModuleController {

    /**
     * Get an array of all the modules
     * 
     * @return
     * @throws GeneralProcessingException
     */
    public static Module[] getAllModules() throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<Module> modules = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Module");

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                String code = res.getString("code");
                String name = res.getString("name");
                Integer credits = res.getInt("credits");
                String teachingPeriod = res.getString("teachingPeriod");
                modules.add(new Module(name, code, credits, teachingPeriod));
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

        Module[] arr = new Module[modules.size()];
        arr = modules.toArray(arr);
        return arr;

    }

    /**
     * Get a given module, if it exists
     */
    public static Module getModule(String moduleCode) throws GeneralProcessingException, NoRecordException {
        
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String code = null;
        String name = null;
        Integer credits = null;
        String teachingPeriod = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Module WHERE code = ?;");
            pstmt.setString(1, moduleCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null | !res.next()) throw new NoRecordException();

            // Filter through the output
            code = res.getString("code");
            name = res.getString("name");
            credits = res.getInt("credits");
            teachingPeriod = res.getString("teachingPeriod");

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
        return new Module(name, code, credits, teachingPeriod);

    }

    public static void createModule(String moduleCode, String moduleName, Integer credits, String teachingPeriod)
            throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeModule(String moduleCode) throws GeneralProcessingException {

    }

}
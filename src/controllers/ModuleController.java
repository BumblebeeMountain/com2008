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

    public static void main(String[] args) {

        try {

            // Example showing that duplicates cannot be made
            // try {
            //     createModule("PSY1111", "Test Psycho Module", 20, "AUTUMN~SPRING");
            // } catch (ExistingRecordException e) {
            //     System.out.println("COM1001 has already been inserted");
            // }

            // // Change these values to insert a new module
            // try {
            //     createModule("COM1003", "Intro to CS 2", 20, "AUTUMN~SPRING");
            // } catch (ExistingRecordException e) {
            //     System.out.println("Maybe try a different module!");
            // }

            // Change this value to delete a module
            // removeModule("PSY1090");

            System.out.println(getModule("BIO1001", true));

            removeModule("BIO1001");

            System.out.println(getModule("BIO1001", true));

            // Output all the current modules
            // Module[] arr = getAllModules(true);
            // for (Module m : arr) System.out.println(m);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get an array of all the modules
     * 
     * @return
     * @throws GeneralProcessingException
     */
    public static Module[] getAllModules(Boolean onlyOfferedModules) throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<Module> modules = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            if (onlyOfferedModules) {
                pstmt = con.prepareStatement("SELECT * FROM Module WHERE currentlyOffered = true");
            } else {
                pstmt = con.prepareStatement("SELECT * FROM Module");
            }

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                String code = res.getString("code");
                String name = res.getString("name");
                Integer credits = res.getInt("credits");
                String teachingPeriod = res.getString("teachingPeriod");
                Boolean currentlyOffered = res.getBoolean("currentlyOffered");
                modules.add(new Module(name, code, credits, teachingPeriod, currentlyOffered));
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
    public static Module getModule(String moduleCode, Boolean onlyOfferedModules)
            throws GeneralProcessingException, NoRecordException {

        moduleCode = moduleCode.toUpperCase();

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String code = null;
        String name = null;
        Integer credits = null;
        String teachingPeriod = null;
        Boolean currentlyOffered = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            if (onlyOfferedModules) {
                pstmt = con.prepareStatement("SELECT * FROM Module WHERE code = ? AND currentlyOffered = true;");
                pstmt.setString(1, moduleCode);
            } else {
                pstmt = con.prepareStatement("SELECT * FROM Module WHERE code = ?;");
                pstmt.setString(1, moduleCode);
            }

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            code = res.getString("code");
            name = res.getString("name");
            credits = res.getInt("credits");
            teachingPeriod = res.getString("teachingPeriod");
            currentlyOffered = res.getBoolean("currentlyOffered");

        } catch (NoRecordException e) {

            throw e; // Caught and re-thrown if there are no records

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
        return new Module(name, code, credits, teachingPeriod, currentlyOffered);

    }

    /**
     * Creates a given module
     * 
     * @param moduleCode
     * @param moduleName
     * @param credits
     * @param teachingPeriod
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createModule(String moduleCode, String moduleName, Integer credits, String teachingPeriod)
            throws GeneralProcessingException, ExistingRecordException {

        moduleCode = moduleCode.toUpperCase();

        // Check for an exisiting department
        Boolean moduleExists = true;
        try {
            // getModule(moduleCode, true);
            getModule(moduleCode, false);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            moduleExists = false;
        }
        if (moduleExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO Module VALUES (?, ?, ?, ?, true);");
            pstmt.setString(1, moduleCode);
            pstmt.setString(2, moduleName);
            pstmt.setInt(3, credits);
            pstmt.setString(4, teachingPeriod);

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
     * Remove a given module
     * 
     * @param moduleCode
     * @throws GeneralProcessingException
     */
    public static void removeModule(String moduleCode) throws GeneralProcessingException {

        moduleCode = moduleCode.toUpperCase();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Remove the degreeModule link
            DegreeController.removeDegreeModule(moduleCode);

            // Prepare the sql parameters
            pstmt = con.prepareStatement("UPDATE Module SET currentlyOffered = false WHERE code = ?;");
            pstmt.setString(1, moduleCode);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) { // Catch general exception

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

}
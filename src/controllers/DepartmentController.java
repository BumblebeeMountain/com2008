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
import models.Department;

public class DepartmentController {

    public static void main(String[] args) {

        try {

            // Example showing that duplicates cannot be made
            try {
                createDepartment("COM", "Computer Science");
            } catch (ExistingRecordException e) {
                System.out.println("COM has already been inserted");
            }

            // Change these values to insert a new department
            try {
                createDepartment("DEP2", "Department 2");
            } catch (ExistingRecordException e) {
                System.out.println("Maybe try a different department!");
            }

            // Change this value to delete a department
            removeDepartment("DEP1");

            // try {
            // createDegreeDepartment("COM", "COMU01", true);
            // } catch (ExistingRecordException e) {
            // System.out.println("COM/COM001 has already been inserted");
            // }

            // // // // Delete a degree department
            // removeDegreeDepartment("COM", "COMU01");

            // Output all the current departments
            Department[] arr = getAllDepartments();
            for (Department d : arr)
                System.out.println(d);

        } catch (GeneralProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns an array of all the current departments
     * 
     * @return
     * @throws GeneralProcessingException
     */
    public static Department[] getAllDepartments() throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<Department> departments = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Department");

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                String departmentCode = res.getString("code");
                String departmentName = res.getString("name");
                departments.add(new Department(departmentName, departmentCode));
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

        // Transform the arraylist into a standard array
        Department[] arr = new Department[departments.size()];
        arr = departments.toArray(arr);
        return arr;

    }

    /**
     * Returns a specific department by its code
     * 
     * @param departmentCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static Department getDepartment(String departmentCode) throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String code = null;
        String name = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Department WHERE code = ?;");
            pstmt.setString(1, departmentCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            code = res.getString("code");
            name = res.getString("name");

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
        return new Department(name, code);

    }

    /**
     * Creates a department from a given code and name
     * 
     * @param departmentCode
     * @param departmentName
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createDepartment(String departmentCode, String departmentName)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for an exisiting department
        Boolean departmentExists = true;
        try {
            getDepartment(departmentCode);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            departmentExists = false;
        }
        if (departmentExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO Department VALUES (?, ?);");
            pstmt.setString(1, departmentCode);
            pstmt.setString(2, departmentName);

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
     * Remove a given department from the database
     * 
     * @param departmentCode
     * @throws GeneralProcessingException
     */
    public static void removeDepartment(String departmentCode) throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("DELETE FROM Department WHERE code = ?;");
            pstmt.setString(1, departmentCode);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) { // Catch general exception

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
     * Private method to check if there is a degree department link in place
     * 
     * @param departmentCode
     * @param degreeCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    private static Department getDegreeDepartment(String departmentCode, String degreeCode)
            throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String degName = null;
        String degCode = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Degree INNER JOIN DegreeDepartment ON Degree.code = DegreeDepartment.degreeCode WHERE departmentCode = ? AND degreeCode = ?;");
            pstmt.setString(1, departmentCode);
            pstmt.setString(2, degreeCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            degName = res.getString("name");
            degCode = res.getString("code");

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

        // Return a new object
        return new Department(degName, degCode);

    }

    /**
     * Creates a degree department link
     * 
     * @param departmentCode
     * @param degreeCode
     * @param lead
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createDegreeDepartment(String departmentCode, String degreeCode, Boolean lead)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for an exisiting department link
        Boolean departmentLinkExists = true;
        try {
            getDegreeDepartment(departmentCode, degreeCode);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            departmentLinkExists = false;
        }
        if (departmentLinkExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO DegreeDepartment VALUES (?, ?, ?);");
            pstmt.setString(1, departmentCode);
            pstmt.setString(2, degreeCode);
            pstmt.setBoolean(3, lead);

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
     * Removes a department / degree link
     * 
     * @param departmentCode
     * @param degreeCode
     * @throws GeneralProcessingException
     */
    public static void removeDegreeDepartment(String departmentCode, String degreeCode)
            throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("DELETE FROM DegreeDepartment WHERE departmentCode = ? AND degreeCode = ?;");
            pstmt.setString(1, departmentCode);
            pstmt.setString(2, degreeCode);

            // Execute the query
            pstmt.executeUpdate();

        } catch (Exception e) { // Catch general exception

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
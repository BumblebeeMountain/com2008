package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionManager;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Department;

public class DepartmentController {

    public static void main (String[] args) {

        try {
            createDepartment("COM", "Computer Science");
            System.out.println("Inserted department");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Department[] getAllDepartments() throws GeneralProcessingException {
        return null;
    }

    public static Department getDepartment(String departmentCode) throws GeneralProcessingException, NoRecordException {
        return null;
    }

    /**
     * Creates a department from a given code and name
     * @param departmentCode
     * @param departmentName
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createDepartment(String departmentCode, String departmentName)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for existing item (to complete)

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
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new GeneralProcessingException();
            }

        }

    }

    public static void removeDepartment(String departmentCode) throws GeneralProcessingException {

    }

    public static void createDegreeDepartment(String departmentCode, String degreeCode, Boolean lead)
            throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDegreeDepartment(String departmentCode, String degreeCode)
            throws GeneralProcessingException {

    }

}
package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import database.ConnectionManager;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;

import models.Degree;
import models.DegreeModule;
import models.Department;
import models.Module;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.ArrayList;

public class DegreeController {

    public static void main(String[] args) {

        try {

            // Change these values to insert a new degree
            try {
                createDegree("COMU01", "Computer Science", false, 3);
                // createDegree("HISU02", "History", true, 4);
                System.out.println("Created successfully.");
            } catch (ExistingRecordException e) {
                System.out.println("This degree is already created!");
            } catch (GeneralProcessingException e) {
                System.out.println("General processing error in creating a Degree");
            }

            // // create a degree module 
            // try {
            //     createDegreeModule("COMU01","COM1001", true, "U");
            // } catch (ExistingRecordException e) {
            //     System.out.println("COM/COM001 has already been inserted");


            // displaying all the degrees
            try {
                Degree[] allDegrees = getAllDegrees();
                System.out.println(allDegrees);
            } catch (GeneralProcessingException er) {
                System.out.println("Couldn't print out all degrees.");
            }

            // getting a degree
            try {
                System.out.println(getDegree("COMU01").toString());
            } catch (NoRecordException er) {
                System.out.println("There is no degree with this code.");
            }
            // } catch (GeneralProcessingException er) {
            //     System.out.println("General processing error in getting a Degree");
            // }

            // // removing a degree
            // try {
            //     removeDegree("COMU01");
            //     System.out.println("Deleted COMU01");
            // } catch (GeneralProcessingException er) {
            //     System.out.println("Failed to delete the degree.");
            // }


            //get degree module 

            // get all degree modules

            // crete degree module

            // remove degree module

            // get lead department

            // get partner departments

            // get core modules

            // get optional modules

            // 

            
            // } try {
            //     DegreeModule trial = getDegreeModule("COM", "COM1001");
            //     System.out.println(trial.toString());
            // } catch (NoRecordException e) {
            //     System.out.println("There is no module called COM1001.");
            // }

        } catch (GeneralProcessingException e) {
            e.printStackTrace();
        } 

    } 
    

    /**
     * Returns an array of all the degrees
     * 
     * @return
     * @throws GeneralProcessingException
     */
    public static Degree[] getAllDegrees() throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ArrayList<Degree> degrees = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Degree");

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                String degreeName = res.getString("name");
                String degreeCode = res.getString("code");
                Boolean hasYearInIndustry = res.getBoolean("hasYearInIndustry");
                Integer maxLevel = res.getInt("maxLevel");
                Department leadDepartment = getLeadDepartment(degreeCode); 
                Department[] partnerDepartments = getPartnerDepartments(degreeCode);
                Module[] coreModules = getCoreModules(degreeCode);
                Module[] optionalModules = getOptionalModules(degreeCode);

                degrees.add(new Degree(degreeName, degreeCode, hasYearInIndustry, maxLevel, leadDepartment,
                partnerDepartments, coreModules, optionalModules));
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
        Degree[] arr = new Degree[degrees.size()];
        arr = degrees.toArray(arr);
        return arr;

    }

    /**
     * Returns a specific degree by its code
     * 
     * @param degreeCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */

    public static Degree getDegree(String degreeCode) throws GeneralProcessingException, NoRecordException {
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;

        String code = null;
        String name = null;
        Boolean hasYearInIndustry = null;
        Integer maxLevel = null;
        Department leadDepartment = null; 
        Department[] partnerDepartments = null;
        Module[] coreModules = null;
        Module[] optionalModules = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM Degree WHERE code = ?;");
            pstmt.setString(1, degreeCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            name = res.getString("name");
            code = res.getString("code");
            hasYearInIndustry = res.getBoolean("hasYearInIndustry");
            maxLevel = res.getInt("maxLevel");
            leadDepartment = getLeadDepartment(code);
            partnerDepartments = getPartnerDepartments(code);
            coreModules = getCoreModules(code);
            optionalModules = getOptionalModules(code);

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

        // Return a new degree object
        return new Degree(name, code, hasYearInIndustry, maxLevel, leadDepartment,
        partnerDepartments, coreModules, optionalModules);

    }

    /**
    * Creates a degree from a given code and name
    * 
    * @param degreeCode
    * @param degreeName
    * @throws GeneralProcessingException
    * @throws ExistingRecordException
    */
    public static void createDegree(String degreeCode, String degreeName, Boolean hasYearInIndustry, Integer maxLevel)
            throws GeneralProcessingException, ExistingRecordException {

        // Check for an exisiting degree
        Boolean degreeExists = true;
        try {
            getDegree(degreeCode);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            degreeExists = false;
        }
        if (degreeExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO Degree VALUES (?, ?, ?, ?);");
            pstmt.setString(1, degreeCode);
            pstmt.setString(2, degreeName);
            pstmt.setBoolean(3, hasYearInIndustry);
            pstmt.setInt(4, maxLevel);


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
     * Remove a given degree from the database
     * 
     * @param degreeCode
     * @throws GeneralProcessingException
     */
    public static void removeDegree(String degreeCode) throws GeneralProcessingException {

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("DELETE FROM Department WHERE code = ?;");
            pstmt.setString(1, degreeCode);

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

    // This is private as it is only for internal checks
    /**
     * Private method to check if there is a degree module link in place
     * 
     * @param degreeCode
     * @param moduleCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    private static DegreeModule getDegreeModule(String degreeCode, String moduleCode)
            throws GeneralProcessingException, NoRecordException {
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String modName = null;
        String modCode = null;
        Integer degCredits = null;
        String teachPeriod = null;
        String degCode = null;
        Boolean isCore = null;
        Character level = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Module INNER JOIN DegreeModule ON Module.code = DegreeModule.moduleCode WHERE degreeCode = ? AND moduleCode = ?;");
            pstmt.setString(1, degreeCode);
            pstmt.setString(2, moduleCode);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            modName = res.getString("name");
            modCode = res.getString("code");
            degCredits = res.getInt("credits");
            teachPeriod = res.getString("teachingPeriod");
            degCode = res.getString("degreeCode");
            isCore = res.getBoolean("core");
            level = res.getString("level").charAt(0);

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
        return new DegreeModule(modName, modCode, degCredits, teachPeriod, degCode, isCore, level);

    }

    /**
     * Returns an array of all the current DegreeModule objects
     * 
     * @param degreeCode
     * @param moduleCode
     * @return
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static DegreeModule[] getAllDegreeModules() throws GeneralProcessingException {
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;

        ArrayList<DegreeModule> degreeModules = new ArrayList<>();

        String modName = null;
        String modCode = null;
        Integer modCredits = null;
        String teachPeriod = null;
        String degCode = null;
        Boolean isCore = null;
        Character level = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM DegreeModule");

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            while (res.next()) {
                // Filter through the output
                modName = res.getString("name");
                modCode = res.getString("moduleCode");
                modCredits = res.getInt("credits");
                teachPeriod = res.getString("teachingPeriod");
                degCode = res.getString("degreeCode");
                isCore = res.getBoolean("core");
                level = res.getString("level").charAt(0);

                degreeModules.add(new DegreeModule(modName, modCode, modCredits, teachPeriod, degCode, isCore, level));
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
        DegreeModule[] arr = new DegreeModule[degreeModules.size()];
        arr = degreeModules.toArray(arr);
        return arr;

    }

      /**
     * Creates a degree module link
     * 
     * @param degreeCode
     * @param moduleCode
     * @param core
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static void createDegreeModule(String degreeCode, String moduleCode, Boolean core, String level)
            throws GeneralProcessingException, ExistingRecordException {
        
        // Check for an exisiting department link
        Boolean moduleLinkExists = true;
        try {
            getDegreeModule(degreeCode, moduleCode);
        } catch (GeneralProcessingException e) {
            throw e;
        } catch (NoRecordException e) {
            moduleLinkExists = false;
        }
        if (moduleLinkExists)
            throw new ExistingRecordException();

        // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("INSERT INTO DegreeModule VALUES (?, ?, ?, ?);");
            pstmt.setString(1, degreeCode);
            pstmt.setString(2, moduleCode);
            pstmt.setBoolean(3, core);
            pstmt.setString(4, level);

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
     * Removes a module / degree link
     * 
     * @param degreeCode
     * @param moduleCode
     * @throws GeneralProcessingException
     */
    public static void removeDegreeModule(String degreeCode, String moduleCode) throws GeneralProcessingException {
         // Variables
        PreparedStatement pstmt = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("DELETE FROM DegreeModule WHERE degreeCode = ? AND moduleCode = ?;");
            pstmt.setString(1, degreeCode);
            pstmt.setString(2, moduleCode);

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

    // This is private as it is only for internal checks
    private static Department getLeadDepartment(String degreeCode)
            throws GeneralProcessingException, NoRecordException {

        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String departCode = null;
        String departName = null;

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Department INNER JOIN DegreeDepartment ON Department.code = DegreeDepartment.departmentCode WHERE degreeCode = ? AND isLead = ?;");
            pstmt.setString(1, degreeCode);
            pstmt.setBoolean(2, true);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            // Filter through the output
            departCode = res.getString("departmentCode");
            departName = res.getString("name");
           
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
        return new Department(departName, departCode);

    }

    // This is private as it is only for internal checks
    private static Department[] getPartnerDepartments(String degreeCode) throws GeneralProcessingException {
            // Variables
            PreparedStatement pstmt = null;
            ResultSet res = null;
            String departCode = null;
            String departName = null;

            ArrayList<Department> partnerDepartments = new ArrayList<>();
   
            // Create the connection
            try (Connection con = ConnectionManager.getConnection()) {
   
                // Prepare the sql parameters
                pstmt = con.prepareStatement(
                       "SELECT * FROM Department INNER JOIN DegreeDepartment ON Department.code = DegreeDepartment.departmentCode WHERE degreeCode = ? AND isLead = ?;");
                pstmt.setString(1, degreeCode);
                pstmt.setBoolean(2, false);
   
                // Execute the query
                res = pstmt.executeQuery();
   
                // If it is null - there was nothing returned
                if (res == null || !res.next())
                    throw new NoRecordException();
   
                // Filter through the output
                while (res.next()) {
                    departName = res.getString("name");
                    departCode = res.getString("departmentCode");

                    partnerDepartments.add(new Department(departName, departCode));
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
        Department[] arr = new Department[partnerDepartments.size()];
        arr = partnerDepartments.toArray(arr);
        return arr;
    }

    // This is private as it is only for internal checks
    private static Module[] getCoreModules(String degreeCode) throws GeneralProcessingException {
        
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;

        String modName = null;
        String modCode = null;
        Integer modCredits = null;
        String teachingPeriod = null;

        ArrayList<Module> coreModules = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement("SELECT * FROM DegreeModule WHERE core = true");

            // Execute the query
            res = pstmt.executeQuery();

            // Filter through the output
            while (res.next()) {
                modName = res.getString("name");
                modCode = res.getString("code");
                modCredits = res.getInt("credits");
                teachingPeriod = res.getString("teachingPeriod");

                coreModules.add(new Module(modName, modCode, modCredits, teachingPeriod));
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
        Module[] arr = new Module[coreModules.size()];
        arr = coreModules.toArray(arr);
        return arr;

    }

    // This is private as it is only for internal checks
    private static Module[] getOptionalModules(String degreeCode) throws GeneralProcessingException {
        // Variables
        PreparedStatement pstmt = null;
        ResultSet res = null;

        String modName = null;
        String modCode = null;
        Integer modCredits = null;
        String teachPeriod = null;

        ArrayList<Module> optionalModules = new ArrayList<>();

        // Create the connection
        try (Connection con = ConnectionManager.getConnection()) {

            // Prepare the sql parameters
            pstmt = con.prepareStatement(
                    "SELECT * FROM Module INNER JOIN DegreeModule ON Module.code = DegreeModule.moduleCode WHERE degreeCode = ? AND core = ?;");
            pstmt.setString(1, degreeCode);
            pstmt.setBoolean(2, false);

            // Execute the query
            res = pstmt.executeQuery();

            // If it is null - there was nothing returned
            if (res == null || !res.next())
                throw new NoRecordException();

            while (res.next()) {
                // Filter through the output
                modName = res.getString("name");
                modCode = res.getString("code");
                modCredits = res.getInt("credits");
                teachPeriod = res.getString("teachingPeriod");

                optionalModules.add(new Module(modName, modCode, modCredits, teachPeriod));
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
        Module[] arr = new Module[optionalModules.size()];
        arr = optionalModules.toArray(arr);
        return arr;
    }

}
package database;

import java.sql.*;

public class Setup {

    private static final String tableNames[] = new String[] {
        "SelectedModule",
        "DegreeModule",
        "Module",
        "DegreeDepartment",
        "Department",
        "Registration",
        "Degree",
        "Student",
        "User"
    };

    public static void main(String[] args) {
        try {

            dropAllTables();

            createUserTable();
            createStudentTable();
            createDegreeTable();
            createRegistrationTable();
            createModuleTable();
            createSelectedModuleTable();
            createDegreeModuleTable();
            createDepartmentTable();
            createDegreeDepartmentTable();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void printTables() {
        String[] tables = new String[0];
        try {
            tables = getTables();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }

        for (String table : tables) {
            System.out.println(table);
        }
    }

    public static void dropAllTables() throws SQLException {
        for (String tableName : tableNames) {
            dropTable(tableName);
        }
    }

    public static boolean dropTable(String tableName) throws SQLException {
        Statement stmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            stmt = con.createStatement();
            stmt.execute("DROP TABLE IF EXISTS " + tableName + ";");
            return !tableExists(tableName);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (stmt != null) stmt.close();
        }
        return false;
    }

    public static boolean tableExists(String tableName) throws SQLException {
        int tableCount = 0;
        Statement stmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            stmt = con.createStatement();
            String command = String.format("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'team019' AND table_name = '%s'", tableName);
            ResultSet res = stmt.executeQuery(command);
            while (res.next()) {
                tableCount = res.getInt(1);
            }
            return (tableCount == 1);
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    public static String[] getTables() throws SQLException {
        String[] tables = new String[0];
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            pstmt = con.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE';",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            tables = ResultSetToArray.convertToString(rs);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return tables;

    }

    private static void createTable(String command) throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(command);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static void createUserTable() throws SQLException {
        String command = "CREATE TABLE User (" +
                        "userID INTEGER NOT NULL AUTO_INCREMENT, " +
                        "email VARCHAR(64) NOT NULL, " +
                        "title VARCHAR(16) NOT NULL, " +
                        "forename VARCHAR(64) NOT NULL, " +
                        "surname VARCHAR(32) NOT NULL, " +
                        "password VARCHAR(32) NOT NULL, " +
                        "accountType VARCHAR(16) NOT NULL, " +
                        "PRIMARY KEY (userID) " +
                        ");";
        createTable(command);
    }

    public static void createStudentTable() throws SQLException {
        String command = "CREATE TABLE Student (" +
                        "registrationNumber INTEGER NOT NULL, " +
                        "userID INTEGER NOT NULL, " +
                        "personalTutor VARCHAR(64) NOT NULL, " +
                        "PRIMARY KEY (registrationNumber), " +
                        "FOREIGN KEY (userID) REFERENCES User(userID) " +
                        ");";
        createTable(command);
    }

    private static void createRegistrationTable() throws SQLException {
        String command = "CREATE TABLE Registration (" +
                        "period CHAR(1) NOT NULL, " +
                        "studentRegistrationNumber INTEGER NOT NULL, " +
                        "level CHAR(1) NOT NULL, " +
                        "startYear SMALLINT NOT NULL, " +
                        "degreeCode VARCHAR(16) NOT NULL, " +
                        "PRIMARY KEY (period, studentRegistrationNumber), " +
                        "FOREIGN KEY (studentRegistrationNumber) REFERENCES Student(registrationNumber), " +
                        "FOREIGN KEY (degreeCode) REFERENCES Degree(code) " +
                        ");";
        createTable(command);
    }

    private static void createDegreeTable() throws SQLException {
        String command = "CREATE TABLE Degree (" +
                        "code VARCHAR(16) NOT NULL, " +
                        "name VARCHAR(64) NOT NULL, " +
                        "hasYearInIndustry BOOLEAN NOT NULL, " +
                        "maxLevel TINYINT NOT NULL, " +
                        "PRIMARY KEY (code) " +
                        ");";
        createTable(command);
    }

    private static void createModuleTable() throws SQLException {
        String command = "CREATE TABLE Module (" +
                        "code VARCHAR(16) NOT NULL, " +
                        "name VARCHAR(64) NOT NULL, " +
                        "credits SMALLINT NOT NULL, " +
                        "teachingPeriod VARCHAR(16) NOT NULL, " +
                        "PRIMARY KEY (code) " +
                        ");";
        createTable(command);
    }

    private static void createSelectedModuleTable() throws SQLException {
        String command = "CREATE TABLE SelectedModule (" +
                        "moduleCode VARCHAR(16) NOT NULL, " +
                        "studentRegistrationNumber INTEGER NOT NULL, " +
                        "period CHAR(1) NOT NULL, " +
                        "firstAttemptResult FLOAT, " +
                        "secondAttemptResult FLOAT, " +
                        "PRIMARY KEY (moduleCode, studentRegistrationNumber, period), " +
                        "FOREIGN KEY (moduleCode) REFERENCES Module(code), " +
                        "FOREIGN KEY (studentRegistrationNumber) REFERENCES Registration(studentRegistrationNumber), " +
                        "FOREIGN KEY (period) REFERENCES Registration(period) " +
                        ");";
        createTable(command);
    }

    private static void createDegreeModuleTable() throws SQLException {
        String command = "CREATE TABLE DegreeModule (" +
                        "degreeCode VARCHAR(16) NOT NULL, " +
                        "moduleCode VARCHAR(16) NOT NULL, " +
                        "core BOOLEAN NOT NULL, " +
                        "level CHAR(1) NOT NULL, " +
                        "PRIMARY KEY (degreeCode, moduleCode), " +
                        "FOREIGN KEY (degreeCode) REFERENCES Degree(code), " +
                        "FOREIGN KEY (moduleCode) REFERENCES Module(code) " +
                        ");";
        createTable(command);
    }

    private static void createDepartmentTable() throws SQLException {
        String command = "CREATE TABLE Department (" +
                        "code VARCHAR(16) NOT NULL, " +
                        "name VARCHAR(64) NOT NULL, " +
                        "PRIMARY KEY (code) " +
                        ");";
        createTable(command);
    }

    private static void createDegreeDepartmentTable() throws SQLException {
        String command = "CREATE TABLE DegreeDepartment (" +
                        "departmentCode VARCHAR(16) NOT NULL, " +
                        "degreeCode VARCHAR(16) NOT NULL, " +
                        "isLead BOOLEAN NOT NULL, " +
                        "PRIMARY KEY (departmentCode, degreeCode), " +
                        "FOREIGN KEY (departmentCode) REFERENCES Department(code), " +
                        "FOREIGN KEY (degreeCode) REFERENCES Degree(code) " +
                        ");";
        createTable(command);
    }
    
}

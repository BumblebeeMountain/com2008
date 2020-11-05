/**
 * ScriptRunner.java
 * Object to run prepared statements
 * @author James Edmundson
 */

package database;

import java.sql.*;

public class ScriptRunner {

    private final Connection con;
    private PreparedStatement pstmt;

    /**
     * Constructor
     * @param Connection connection - the connection to the database
     */
    public ScriptRunner(Connection connection) {
        this.con = connection;
    }

    /**
     * prepareStatement
     * Function which takes a string command, and creates a prepared statement from
     * that command
     * @param String command - the sql command to run
     * @return none
     * @throws SQLException
     */
    public void prepareStatement(String command) throws SQLException {
        this.pstmt = this.con.prepareStatement(command,
                ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
    }

    /**
     * setStringParam
     * Function to take an index and a string, and add it to the prepared statement
     * @param Integer n - the index of the parameter (1 - indexed)
     * @param String p - the string value of the parameter
     * @return none
     * @throws SQLException
     */
    public void setStringParam(Integer n, String p) throws SQLException {
        this.pstmt.setString(n, p);
    }

    /**
     * setIntegerParam
     * Function to take an index and an integer , and add it to the prepared statement
     * @param Integer n - the index of the parameter (1 - indexed)
     * @param Integer p - the integer value of the parameter
     * @return none
     * @throws SQLException
     */
    public void setIntegerParam(Integer n, Integer p) throws SQLException {
        this.pstmt.setInt(n, p);
    }

    /**
     * executeQuery
     * Function to execute the prepared statement query
     * @param none
     * @return ResultSet - the result set outputted from jdbc
     * @throws SQLException
     */
    public ResultSet executeQuery() throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    /**
     * executeUpdate
     * Function to execute the prepared statement update 
     * @param none
     * @return Integer- the integer outputted from jdbc
     */
    public Integer executeUpdate() throws SQLException {
        int result = pstmt.executeUpdate();
        return result;
    }

    public void execute() throws SQLException {
        pstmt.execute();
    }

    public void printStmt() {
        System.out.println(this.pstmt);
    }

    public void execute(String cmd) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute(cmd);
    }
}

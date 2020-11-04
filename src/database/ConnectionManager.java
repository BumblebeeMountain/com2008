// A wrapper for all the connection gumpf via JDBC, 
//  we can just call this wrapper instead
//  This wrapper will also be the central point for the login details of the db

package database;
import java.sql.*;

public class ConnectionManager {
    /**
     * getConnection()
     * Static function to get a new connection object from the group SQL database
     * @param null
     * @return Connection, the created connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://stusql.dcs.shef.ac.uk/team019",
                "team019",
                "21b87683");
    }
}

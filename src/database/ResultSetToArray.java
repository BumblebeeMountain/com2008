package database;
import java.sql.*;

public class ResultSetToArray {

    public static String[] convertToString(ResultSet rs) throws SQLException {
        int length = getLength(rs);
        String[] array = new String[length];

        int count = 0;
        while (rs.next()) {
            array[count] = rs.getString(1);
            count++;
        }
        
        return array;
    }

    private static int getLength(ResultSet rs) throws SQLException {
        int rowCount = 0;
        if (rs.last()) {
            rowCount = rs.getRow();
            rs.beforeFirst();
        }


        return rowCount;
    }
}

import java.sql.*;
import java.util.*;

public class DriverTest {
    public static void main(String[] args) throws Exception {
        System.out.println("\nDrivers loaded as properties:");
        System.out.println(System.getProperty("jdbc.drivers"));
        System.out.println("\nDrivers loaded by DriverManager:");
        Enumeration list = DriverManager.getDrivers();
        while (list.hasMoreElements())
            System.out.println(list.nextElement());
    }
}

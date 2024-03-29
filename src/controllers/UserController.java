package controllers;

import database.ConnectionManager;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.IncorrectLoginCredentialsException;
import exceptions.NoRecordException;
import models.Constants;
import models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * This BCrypt module was found online at: https://mvnrepository.com/artifact/org.springframework.security/spring-security-core/5.4.1
 * The documentation for this module can be found at: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCrypt.html
 */
import org.springframework.security.crypto.bcrypt.*;

public class UserController {

    private static String INSERT_USER_COMMAND = "INSERT INTO User(email, title, forename, surname, password, accountType) "
            + "VALUES (?, ?, ?, ?, ?, ?);";

    private static String DELETE_USER_COMMAND = "DELETE FROM User WHERE email = ?;";
    private static String GET_ALL_USERS_COMMAND = "SELECT * FROM User;";
    private static String GET_USER_FROM_EMAIL_COMMAND = "SELECT * FROM User WHERE email = ?;";
    private static String GET_USER_PASSWORD_COMMAND = "SELECT password FROM User WHERE email = ?;";

    public static void main(String[] args) {
        try {

            System.out.println("all users now: ");
            System.out.println(getAllUsers());

            // String email = createUser(
            // Constants.Title.MR,
            // "a",
            // "b",
            // "pas",
            // Constants.AccountType.STUDENT
            // ).getEmail();

            // createUser(
            // Constants.Title.MS,
            // "ms",
            // "ms",
            // "pas",
            // Constants.AccountType.STUDENT
            // );

            createUser(Constants.Title.MR, "David", "Grey", "password", Constants.AccountType.ADMINISTRATOR).getEmail();

            System.out.println("all users now: ");
            User[] users = getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }

            // System.out.println("Checking authentication");
            // login(email, "pas");
            // System.out.println("login successful");

            // try {
            // login(email, "wrong_pass");
            // System.out.println("I shouldn't be reached");
            // } catch (IncorrectLoginCredentialsException ex) {
            // System.out.println("incorrect password returned exception, as expected");
            // }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    /**
     * getAllUsers() Function to return an array of user objects
     * 
     * @param none
     * @return User[] - the array of users
     * @throws GeneralProcessingException
     */
    public static User[] getAllUsers() throws GeneralProcessingException {
        PreparedStatement pstmt = null;
        User[] users;
        try (Connection con = ConnectionManager.getConnection()) {

            // arrayList to store the users
            ArrayList<User> usersList = new ArrayList<>();

            // get all the users
            pstmt = con.prepareStatement(GET_ALL_USERS_COMMAND);
            ResultSet rs = pstmt.executeQuery();
            User user;

            // while there are more rows, create a new user
            while (rs.next()) {
                String email = rs.getString("email");
                Constants.Title title = Constants.Title.valueOf(rs.getString("title"));
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                Constants.AccountType accountType = Constants.AccountType.valueOf(rs.getString("accountType"));
                user = new User(email, title, forename, surname, accountType);
                usersList.add(user);
            }

            // User[] users = new User[usersList.size()];
            users = new User[usersList.size()];
            usersList.toArray(users);

        } catch (SQLException ex) {
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new GeneralProcessingException();
                }
            }
        }
        return users;
    }

    /**
     * getUser() Function that, given an email, will return the user Object.
     * 
     * @param email - String - the string email to get
     * @return User - the user object created
     * @throws GeneralProcessingException, NoRecordException
     */
    public static User getUser(String email) throws GeneralProcessingException, NoRecordException {

        email = email.toLowerCase().trim();

        User user = null;
        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {

            // select all rows from user table matching that email
            pstmt = con.prepareStatement(GET_USER_FROM_EMAIL_COMMAND);
            pstmt.setString(1, email);

            // get the results
            ResultSet rs = pstmt.executeQuery();

            // if there are no results, throw no record exception
            if (rs == null || !rs.next()) {
                throw new NoRecordException();
            }

            // extract the values and create the user
            Constants.Title title = Constants.Title.valueOf(rs.getString("title"));
            String forename = rs.getString("forename");
            String surname = rs.getString("surname");
            Constants.AccountType accountType = Constants.AccountType.valueOf(rs.getString("accountType"));

            user = new User(email, title, forename, surname, accountType);

            // cleanup
        } catch (SQLException e) {
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new GeneralProcessingException();
                }
            }
        }

        return user;
    }

    /**
     * genEmail() Function that given forename and surname returns the next
     * available email Email is the first letter of the forename, and the surname
     * plus an identifying number
     * 
     * @param forename - String - the forename
     * @param surname  - String - the surname
     * @return String - the string email
     * @throws GeneralProcessingException
     */
    private static String genEmail(String forename, String surname) throws GeneralProcessingException {
        String preEmail = (forename.charAt(0) + surname).toLowerCase().trim();
        String num = "1";
        int numI = 1;

        // while a user exists with that email
        while (userExists(preEmail + num + "@" + Constants.emailHost)) {
            // increment the number, and try the next email
            numI++;
            num = String.valueOf(numI);
        }

        // return the full email
        return preEmail + num + "@" + Constants.emailHost;
    }

    /**
     * createUser() Function that given the user parameters, creates the user and
     * returns their email
     * 
     * @param Title       - Constants.Title - the Title of the user
     * @param forename    - String - the forename
     * @param surname     - String - the surname
     * @param password    - String - the password
     * @param accountType - Constants.AccountType - the account type
     * @return User - the generated user
     * @throws GeneralProcessingException
     * @throws ExistingRecordException
     */
    public static User createUser(Constants.Title title, String forename, String surname, String password,
            Constants.AccountType accountType) throws GeneralProcessingException {

        PreparedStatement pstmt = null;

        try (Connection con = ConnectionManager.getConnection()) {

            // generate the email address of the user
            String email = genEmail(forename, surname);
            String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());

            pstmt = con.prepareStatement(INSERT_USER_COMMAND);
            pstmt.setString(1, email);
            pstmt.setString(2, title.toString());
            pstmt.setString(3, forename);
            pstmt.setString(4, surname);
            pstmt.setString(5, pw_hash);
            pstmt.setString(6, accountType.toString());

            pstmt.execute();

            return new User(email, title, forename, surname, accountType);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new GeneralProcessingException();
                }
            }
        }

    }

    /**
     * userExists() Function that return whether a user already exists
     * 
     * @param email - String - the users email address
     * @return boolean - whether the user exists
     * @throws GeneralProcessingException
     */
    public static boolean userExists(String email) throws GeneralProcessingException {

        email = email.toLowerCase().trim();

        try {
            getUser(email);
        } catch (NoRecordException e) {
            return false; // User doesn't exist
        } catch (Exception e) {
            throw new GeneralProcessingException();
        }

        return true; // User does exist

    }

    /**
     * removeUser() Function to remove a user, given the users email If the user
     * account is attempted to be deleted before the student account, will throw a
     * GeneralProcessingException due to foreign key constraits
     * 
     * @param email - String - the users email
     * @return void
     * @throws GeneralProcessingException
     * @throws NoRecordException
     */
    public static void removeUser(String email) throws GeneralProcessingException, NoRecordException {

        email = email.toLowerCase().trim();

        if (!userExists(email))
            throw new NoRecordException();
        PreparedStatement pstmt = null;

        try (Connection con = ConnectionManager.getConnection()) {

            // delete the user from table by email
            pstmt = con.prepareStatement(DELETE_USER_COMMAND);
            pstmt.setString(1, email);

            System.out.print(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new GeneralProcessingException();
                }
            }
        }
    }

    /**
     * login() Take an email and password, and return the User object if correct,
     * otherwise throw GeneralProcessingException
     * 
     * @param email    - String - The users email
     * @param password - String - the users plaintext password
     * @return User - The User object
     * @throws IncorrectLoginCredentialsException
     * @throws GeneralProcessingException
     */
    public static User login(String email, String password)
            throws IncorrectLoginCredentialsException, GeneralProcessingException {

        email = email.toLowerCase().trim();

        PreparedStatement pstmt = null;
        try (Connection con = ConnectionManager.getConnection()) {
            pstmt = con.prepareStatement(GET_USER_PASSWORD_COMMAND);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null || !rs.next()) {
                // no user with that email have been found, so throw
                // IncorrectLoginCredentialsException
                throw new IncorrectLoginCredentialsException();
            }

            String pw_hash = rs.getString("password");

            if (BCrypt.checkpw(password, pw_hash)) {
                // password matches
                return getUser(email);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new GeneralProcessingException();
        } catch (NoRecordException ex) {
            // will occur when no user is found when the getUser(email) function is called
            // If no user exists with that email, should throw
            // IncorrectLoginCredentialsException
            throw new IncorrectLoginCredentialsException();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new GeneralProcessingException();
                }
            }
        }
        // if this statement is reached, it means the password authentication failed
        throw new IncorrectLoginCredentialsException();
    }

}

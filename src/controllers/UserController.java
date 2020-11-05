package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.IncorrectLoginCredentialsException;
import exceptions.NoRecordException;
import models.Constants;
import models.User;

public class UserController {
    
    public static User[] getAllUsers () 
    throws GeneralProcessingException {
        return null;
    }

    public static User getUser (String email) 
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static User getUserByID (Integer userID)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createUser (Constants.Title title, String firstname, String surname, String password, Constants.AccountType accountType)
    throws GeneralProcessingException, ExistingRecordException {
        
    }

    public static void removeUser (String email) 
    throws GeneralProcessingException {
        
    }

    public static User login (String email, String password) 
    throws IncorrectLoginCredentialsException, GeneralProcessingException {
        return null;
    }

}
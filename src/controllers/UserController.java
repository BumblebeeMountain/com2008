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

    // Needs to return the generated email
    public static String createUser (Constants.Title title, String firstname, String surname, String password, Constants.AccountType accountType)
    throws GeneralProcessingException, ExistingRecordException {
        return null;
    }

    public static void removeUser (String email) 
    throws GeneralProcessingException {
        
    }

    public static User login (String email, String password) 
    throws IncorrectLoginCredentialsException, GeneralProcessingException {
        return null;
    }

}
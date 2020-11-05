package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Registration;
import models.Constants.DegreeClass;

public class RegistrationController {
    
    public static Registration[] getStudentRegistrations(Integer registrationNumber)
    throws GeneralProcessingException {
        return null;
    }

    public static Registration getStudentRegistration(Integer registrationNumber, Character period)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createInitialRegistration(Integer registrationNumber, String degreeCode)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void generateNextRegistration(Integer registrationNumber, Character level)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static DegreeClass calculateDegreeClassification (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Character getMostRecentPeriod (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Character getMostRecentLevel (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

}
package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Registration;
import models.SelectedModule;

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

    // Private as is only for internal checks
    private static SelectedModule getSelectedModule (Integer registrationNumber, Character period, String moduleCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createSelectedModule(Integer registrationNumber, Character period, String moduleCode)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void generateNextRegistration(Integer registrationNumber, Character level)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static Character getMostRecentPeriod (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Character getMostRecentLevel (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void updateFirstGrade(Integer registrationNumber, Character period, String moduleCode, Integer grade)
    throws GeneralProcessingException {

    }

    public static void updateResitGrade(Integer registrationNumber, Character period, String moduleCode, Integer grade)
    throws GeneralProcessingException {
        
    }

    public static void removeSelectedModule(Integer registrationNumber, Character period, String moduleCode)
    throws GeneralProcessingException {
        
    }

    public static Integer calculateOverallGrade(Integer registrationNumber, Character period)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

}
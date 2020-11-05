package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.DegreeModule;

public class DegreeModuleController {
    
    public static DegreeModule[] getAllDegreeModules()
    throws GeneralProcessingException {
        return null;
    }

    public static DegreeModule[] getDegreeModules (String degreeCode)
    throws GeneralProcessingException {
        return null;
    }

    public static DegreeModule getDegreeModule (String degreeCode, String moduleCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createDegreeModule (String degreeCode, String moduleCode, Boolean core)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDegreeModule (String degreeCode, String moduleCode)
    throws GeneralProcessingException {
        
    }

}
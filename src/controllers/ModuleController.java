package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Module;

public class ModuleController {
    
    public static Module[] getAllModules ()
    throws GeneralProcessingException {
        return null;
    }

    public static Module getModule (String moduleCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createModule (String moduleCode, String moduleName, Integer credits, String teachingPeriod)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeModule (String moduleCode) 
    throws GeneralProcessingException {
        
    }

}
package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Degree;
import models.DegreeModule;
import models.Department;

public class DegreeController {
    
    public static Degree[] getAllDegrees()
    throws GeneralProcessingException {
        return null;
    }

    public static Degree getDegree(String degreeCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createDegree(String degreeCode, String degreeName, Boolean hasYearInIndustry, Integer maxLevel)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDegree(String degreeCode)
    throws GeneralProcessingException {
        
    }

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

    public static Department getLeadDepartment (String degreeCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Department[] getPartnerDepartments (String degreeCode)
    throws GeneralProcessingException {
        return null;
    }

}

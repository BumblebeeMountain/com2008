package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Degree;

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

}

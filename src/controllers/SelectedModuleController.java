package controllers;

import exceptions.GeneralProcessingException;
import models.SelectedModule;

public class SelectedModuleController {

    public static SelectedModule[] getStudentSelectedModules(Integer registrationNumber, Character period) 
    throws GeneralProcessingException {
        return null;
    } 

    public static void createSelectedModule(Integer registrationNumber, Character period, String moduleCode)
    throws GeneralProcessingException {

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

}
package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Department;

public class DepartmentController {
    
    public static Department[] getAllDepartments()
    throws GeneralProcessingException {
        return null;
    }

    public static Department getDepartment(String departmentCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createDepartment(String departmentCode, String departmentName)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDepartment(String departmentCode)
    throws GeneralProcessingException {

    }

    public static void createDegreeDepartment (String departmentCode, String degreeCode, Boolean lead)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDegreeDepartment (String departmentCode, String degreeCode)
    throws GeneralProcessingException {
        
    }
    
}
package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Department;

public class DegreeDepartmentController {
    
    public static Department getLeadDepartment (String degreeCode)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Department[] getPartnerDepartments (String degreeCode)
    throws GeneralProcessingException {
        return null;
    }

    public static void createDegreeDepartment (String departmentCode, String degreeCode, Boolean lead)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeDegreeDepartment (String departmentCode, String degreeCode)
    throws GeneralProcessingException {
        
    }

}
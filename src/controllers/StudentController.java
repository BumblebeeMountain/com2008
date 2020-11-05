package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Degree;
import models.Student;

public class StudentController {
    
    public static Student[] getAllStudents() 
    throws GeneralProcessingException {
        return null;
    }

    public static Student getStudent (Integer registrationNumber) 
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Student getStudentByID(Integer userID) 
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Degree getStudentDegree (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static void createStudent(Integer userID, String personalTutor)
    throws GeneralProcessingException, ExistingRecordException {

    }

    public static void removeStudent (Integer registrationNumber) 
    throws GeneralProcessingException {

    }

}
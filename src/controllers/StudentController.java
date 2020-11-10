package controllers;

import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import exceptions.NoRecordException;
import models.Degree;
import models.Student;
import models.User;
import models.Constants.DegreeClass;
import models.Constants.AccountType;

public class StudentController {
    
    public static Student[] getAllStudents() 
    throws GeneralProcessingException {
        return null;
    }


    public static Student getStudent (Integer registrationNumber) 
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Student getStudent(String email)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Student getStudentByEmail(String email) 
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static Degree getStudentDegree (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    // Needs to return the generated registration number
    public static Integer createStudent(String email, String personalTutor)
    throws GeneralProcessingException, ExistingRecordException {
        return null;
    }

    public static void removeStudent (Integer registrationNumber) 
    throws GeneralProcessingException {

    }

    public static DegreeClass calculateDegreeClassification (Integer registrationNumber)
    throws GeneralProcessingException, NoRecordException {
        return null;
    }

    public static boolean isStudent(User user) {
        return user.getAccountType() == AccountType.STUDENT;
    }

}

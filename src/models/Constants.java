package models;

public class Constants {
    
    /**
     * Enum describing the account type of a login
     */
    public static enum AccountType {
        STUDENT,
        ADMINISTRATOR,
        REGISTRAR,
        TEACHER
    }

    /**
     * Enum describing different titles
     */
    public static enum Title {
        MR,
        MRS,
        MISS,
        MASTER,
        DR,
        MS
    }

    public static enum DegreeClass {
        FIRST_CLASS,
        UPPER_SECOND,
        LOWER_SECOND,
        THIRD_CLASS,
        DISTINCTION,
        MERIT,
        PASS_NON_HONOURS,
        PASS,
        FAIL
    }

    public static String emailHost = "sheffield.ac.uk";

}

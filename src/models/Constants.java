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
        FIRST_CLASS_BACHELORS,
        UPPER_SECOND_BACHELORS,
        LOWER_SECOND_BACHELORS,
        THIRD_CLASS_BACHELORS,
        PASS_NON_HONOURS_BACHELORS,
        FIRST_CLASS_MASTERS,
        UPPER_SECOND_MASTERS,
        LOWER_SECOND_MASTERS,
        DISTINCTION,
        MERIT,
        PASS,
        FAIL,
        PG_DIP,
        PG_CERT
    }

    public static enum PassLevel {
        PASS,
        FAIL,
        CONCEDED_PASS
    }

    public static String emailHost = "sheffield.ac.uk";

}

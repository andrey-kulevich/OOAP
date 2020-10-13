package models;

public class Education {

    public Specialty specialty;
    public Degree degree;

    public enum Specialty {
        DIRECTOR, MANAGER, PR_MANAGER, LAWYER, ECONOMIST, ENGINEER, CLERK, CLEANER
    }

    public enum Degree {
        SECONDARY_SPECIAL, BACHELOR, MASTER, GRADUATE_STUDENT, DOCTOR
    }


}

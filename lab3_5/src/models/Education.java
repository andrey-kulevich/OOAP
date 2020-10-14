package models;

public class Education {

    /*------------ Свойства -------------*/
    private final Specialty specialty;
    private final Degree degree;

    /*------------ Перечисления -------------*/

    public enum Specialty { DIRECTOR, MANAGER, PR_MANAGER, LAWYER, ECONOMIST, ENGINEER, CLERK, CLEANER }

    public enum Degree { SECONDARY_SPECIAL, BACHELOR, MASTER, GRADUATE_STUDENT, DOCTOR }

    /*------------ Конструктор -------------*/

    public Education (Specialty specialty, Degree degree) {
        this.specialty = specialty;
        this.degree = degree;
    }

    /*------------ Геттеры -------------*/

    public Specialty getSpecialty() { return specialty; }

    public Degree getDegree() { return degree; }

    /*------------ Операции -------------*/

    public static int compareDegrees(Degree degree1, Degree degree2) {
        return Integer.compare(degree1.ordinal(), degree2.ordinal());
    }
}

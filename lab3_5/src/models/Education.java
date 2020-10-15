package models;

/** Класс образования */
public class Education {

    /*------------ Свойства -------------*/
    /** Специальность */
    private final Specialty specialty;
    /** Ступень образования */
    private final Degree degree;

    /*------------ Перечисления -------------*/
    /** Список доступных специальностей */
    public enum Specialty { DIRECTOR, MANAGER, PR_MANAGER, LAWYER, ECONOMIST, ENGINEER, CLERK, CLEANER }
    /** Список ступеней образования */
    public enum Degree { SECONDARY_SPECIAL, BACHELOR, MASTER, GRADUATE_STUDENT, DOCTOR }

    /*------------ Конструктор -------------*/

    /** Конструктор образования
     *
     * @param specialty специальность
     * @param degree ступень образования
     */
    public Education (Specialty specialty, Degree degree) {
        this.specialty = specialty;
        this.degree = degree;
    }

    /*------------ Геттеры -------------*/

    /** Получить специальность
     *
     * @return специальность
     */
    public Specialty getSpecialty() { return specialty; }

    /** Получить ступень образования
     *
     * @return ступень образования
     */
    public Degree getDegree() { return degree; }

    /*------------ Операции -------------*/

    /** Сравнить две ступени образования
     *
     * @param degree1 первая ступень
     * @param degree2 вторая ступень
     * @return 1, если первая ступень выше второй, -1, если ниже, 0, если они равны
     */
    public static int compareDegrees(Degree degree1, Degree degree2) {
        return Integer.compare(degree1.ordinal(), degree2.ordinal());
    }
}

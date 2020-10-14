package models;

/** Класс работника */
public class Employee {

    /*------------ Свойства -------------*/
    /** ФИО работника */
    private final String FIO;
    /** карточка работника */
    private final Card idCard;
    /** образование */
    private Education education;
    /** отдел */
    private Department department;
    /** слушатель событий */
    private EmployeeListener actionListener;

    /*------------ Конструктор -------------*/

    /** Констуктор класса
     *
     * @param FIO ФИО работника
     * @param education образование
     * @param department отдел
     */
    public Employee(String FIO, Education education, Department department) {
        this.FIO = FIO;
        idCard = new Card();
        this.education = education;
        this.department = department;
        this.admitTo(department);
    }

    /*------------ Геттеры -------------*/

    /** Получить карточку работника
     *
     * @return карточку работника
     */
    public Card getIdCard() { return idCard; }

    /** Получить образование работника
     *
     * @return образование работника
     */
    public Education getEducation() { return education; }

    /** Получить отдел, в котором работает работник
     *
     * @return отдел, в котором работает работник
     */
    public Department getDepartment() { return department; }

    /** Получить ФИО работника
     *
     * @return ФИО работника
     */
    public String getFIO() { return FIO; }


    /*------------ Сеттеры -------------*/

    /** Сменить образование работника
     *
     * @param education новое образование (если специальность осталась прежней, ступень не может быть ниже)
     */
    public void setEducation(Education education) {
        if (this.education.getSpecialty() != education.getSpecialty() ||
                (this.education.getSpecialty() == education.getSpecialty() &&
                        Education.compareDegrees(this.education.getDegree(), education.getDegree()) <= 0)) {
            this.education = education;
            actionListener.actionPerformed(FIO + " получил степень "
                    + education.getDegree() + " по специальности " + education.getSpecialty());
        } else
            throw new IllegalArgumentException("Степень по специальности не может быть понижена");
    }

    /**
     *
     * @param actionListener
     */
    public void setActionListener(EmployeeListener actionListener) { this.actionListener = actionListener; }


    /*------------ Операции -------------*/

    /**
     *
     * @param department
     * @return
     */
    public boolean admitTo(Department department) {
        this.department = department;
        boolean isAdmitted = department.addEmployee(this);

        if (isAdmitted) actionListener.actionPerformed(FIO + " принят в отдел " + department.getName());
        return isAdmitted;
    }

    /**
     *
     */
    public void fireFromCompany() {
        idCard.setActive(false);
        department.removeEmployee(this);

        actionListener.actionPerformed(FIO + " уволен");
    }

    /**
     *
     * @param department
     * @return
     */
    public boolean transferTo(Department department) {
        this.department.removeEmployee(this);
        this.department = department;
        boolean isAdmitted = department.addEmployee(this);

        if (isAdmitted) actionListener.actionPerformed(FIO + " переведен в отдел " + department.getName());
        return isAdmitted;
    }


}

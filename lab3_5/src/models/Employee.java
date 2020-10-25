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
    private Management actionListener;
    /** компьютер работника */
    private Computer computer;

    /*------------ Конструктор -------------*/

    /** Констуктор класса
     *
     * @param FIO ФИО работника
     * @param education образование
     * @param department отдел
     */
    public Employee(String FIO, Education education, Department department) {
        if (FIO == null || education == null || department == null) throw new NullPointerException();

        this.FIO = FIO;
        idCard = new Card();
        this.education = education;
        this.admitTo(department);
    }

    /** Констуктор класса
     *
     * @param FIO ФИО работника
     * @param education образование
     * @param department отдел
     * @param actionListener слушатель событий работника
     */
    public Employee(String FIO, Education education, Department department, Management actionListener) {
        if (FIO == null || education == null || department == null || actionListener == null)
            throw new NullPointerException();

        this.FIO = FIO;
        idCard = new Card();
        this.education = education;
        this.actionListener = actionListener;
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

    /** Получить компьютер работника
     *
     * @return компьютер работника
     */
    public Computer getComputer() { return computer; }

    /** Получить слушателя событий для сотрудника
     *
     * @return слушатель событий
     */
    public Management getActionListener() { return actionListener; }

    /*------------ Сеттеры -------------*/

    /** Сменить образование работника
     *
     * @param education новое образование (если специальность осталась прежней, ступень не может быть ниже)
     */
    public void setEducation(Education education) {
        if (education == null) throw new NullPointerException();

        if (this.education.getSpecialty() != education.getSpecialty() ||
                (this.education.getSpecialty() == education.getSpecialty() &&
                        Education.compareDegrees(this.education.getDegree(), education.getDegree()) <= 0)) {
            this.education = education;
            if (actionListener != null)
                actionListener.actionPerformed(FIO + " получил степень "
                        + education.getDegree().getName() + " по специальности " + education.getSpecialty().getName());
        } else
            throw new IllegalArgumentException("Степень по специальности не может быть понижена");
    }

    /** Слушатель для всех действий работника
     *
     * @param actionListener слушатель
     */
    public void setActionListener(Management actionListener) {
        if (actionListener == null) throw new NullPointerException();
        this.actionListener = actionListener;
    }

    /** Установить новый отдел
     *
     * @param department отдел
     */
    protected void setDepartment(Department department) {
        this.department = department;
    }

    /** Задать компьютер для работника
     *
     * @param computer компьютер
     */
    protected void setComputer(Computer computer) { this.computer = computer; }

    /*------------ Операции -------------*/

    /** Принять работника в отдел
     *
     * @param department отдел
     * @return успешность принятия работника
     */
    public boolean admitTo(Department department) {
        if (department == null) throw new NullPointerException();

        if (this.department != null) return false;
        boolean isAdmitted = department.addEmployee(this);

        if (isAdmitted && actionListener != null)
            actionListener.actionPerformed(FIO + " принят в отдел " + department.getName());
        return isAdmitted;
    }

    /** Уволить работника */
    public void fireFromCompany() {
        department.removeEmployee(this);
        if (actionListener != null) actionListener.actionPerformed(FIO + " уволен");
    }

    /** Перевести работника в другой отдел
     *
     * @param department отдел
     * @return успешность перевода работника
     */
    public boolean transferTo(Department department) {
        if (department == null) throw new NullPointerException();

        this.department.removeEmployee(this);
        boolean isAdmitted = department.addEmployee(this);

        if (isAdmitted && actionListener != null)
            actionListener.actionPerformed(FIO + " переведен в отдел " + department.getName());
        return isAdmitted;
    }


}

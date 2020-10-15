package models;

import java.util.*;

/** Класс отдела компании */
public class Department {

    /*------------ Свойства -------------*/
    /** Название отдела */
    private String name;
    /** Список сотрудников */
    private final HashSet<Employee> employees = new HashSet<>();

    /*------------ Конструктор -------------*/

    /** Конструктор отдела
     *
     * @param name название отдела
     */
    public Department(String name) {
        this.name = name;
    }

    /*------------ Геттеры -------------*/

    /** Получить название отдела
     *
     * @return название отдела
     */
    public String getName() { return name; }

    /** Получить список работников
     *
     * @return список сотрудников
     */
    public HashSet<Employee> getEmployees() { return new HashSet<Employee>(employees); }

    /*------------ Сеттеры -------------*/

    /** Установить название отдела
     *
     * @param name новое название
     */
    public void setName(String name) { this.name = name; }

    /*------------ Операции -------------*/

    /** Добавить работника в отдел
     *
     * @param employee новый работник
     * @return успешность добавления (false, если работник уже есть в этом отделе)
     */
    public boolean addEmployee(Employee employee) {
        if (employee == null) throw new NullPointerException();

        boolean isAdded = employees.add(employee);
        if (isAdded) employee.setDepartment(this);
        return isAdded;
    }

    /** Убрать работника из отдела
     *
     * @param employee работник
     */
    public void removeEmployee(Employee employee) {

        if (employees.remove(employee)) employee.setDepartment(null);
    }
}

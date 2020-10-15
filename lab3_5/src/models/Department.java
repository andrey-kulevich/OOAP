package models;

import java.util.*;

/** Класс отдела компании */
public class Department {

    /*------------ Свойства -------------*/
    /** Название отдела */
    private String name;
    /** Список сотрудников */
    private final List<Employee> employees = new ArrayList<Employee>();

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
    public List<Employee> getEmployees() { return new ArrayList<Employee>(employees); }

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

        if (!employees.contains(employee)) {
            employees.add(employee);
            employee.setDepartment(this);
            return true;
        } else {
            return false;
        }
    }

    /** Убрать работника из отдела
     *
     * @param employee работник
     */
    public void removeEmployee(Employee employee) {
        if (employees.remove(employee)) employee.setDepartment(null);
    }
}

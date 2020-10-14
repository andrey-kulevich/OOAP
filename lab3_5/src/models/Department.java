package models;

import java.util.*;

public class Department {

    /*------------ Свойства -------------*/
    private String name;
    private final List<Employee> employees = new ArrayList<Employee>();

    /*------------ Конструктор -------------*/

    public Department(String name) {
        this.name = name;
    }

    /*------------ Геттеры -------------*/

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() { return new ArrayList<Employee>(employees); }

    /*------------ Сеттеры -------------*/

    public void setName(String name) { this.name = name; }



    public boolean addEmployee(Employee employee) {

        if (!employees.contains(employee)) {
            employees.add(employee);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }
}

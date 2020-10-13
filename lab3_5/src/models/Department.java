package models;

import java.util.*;

public class Department {

    private String name;
    private List<Employee> employees = new ArrayList<Employee>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public List<Employee> getEmployees() { return new ArrayList<Employee>(employees); }

    public boolean addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            return true;
        } else {
            return false;
        }
    }
}

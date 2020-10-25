package test;
import models.Department;
import models.Education;
import models.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** Тестирование связей между классами Employee и Department */
public class EmployeeAndDepartmentLinks_test {

    /** Попытка добавить работника сразу в два отдела */
    @Test
    void admitEmployeeToTwoDepartments() {

        Department dep1 = new Department("ООО рога и копыта");
        Department dep2 = new Department("ООО салам пополам");
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        boolean isAdded = employee.admitTo(dep2);
        Assert.assertFalse(isAdded);
    }

    /** Добавить одного сотрудника в отдел */
    @Test
    void admitEmployeeToDepartment() {

        Department dep1 = new Department("ООО рога и копыта");
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        Assert.assertTrue(employee.getDepartment() == dep1 && dep1.getEmployees().size() == 1);
    }

    /** Добавить сотрудника в отдел, в котором он уже находится */
    @Test
    void admitEmployeeToDepartmentIfHeAlreadyInside() {

        Department dep1 = new Department("ООО рога и копыта");
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        boolean isOk = employee.admitTo(dep1);
        Assert.assertFalse(isOk);
    }

    /** Добавить несколько сотрудников отдел */
    @Test
    void addEmployeeToDepartment() {

        Department dep1 = new Department("ООО рога и копыта");
        Employee employee1 = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        Employee employee2 = new Employee("Васян",
                new Education(Education.Specialty.CLERK, Education.Degree.BACHELOR), dep1);
        Employee employee3 = new Employee("Ростик",
                new Education(Education.Specialty.DIRECTOR, Education.Degree.MASTER), dep1);

        Assert.assertEquals(3, dep1.getEmployees().size());
    }

    /** Удалить сотрудника из отдела */
    @Test
    void removeEmployeeFromDepartment() {

        Department dep1 = new Department("ООО рога и копыта");
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        employee.fireFromCompany();
        Assert.assertTrue(employee.getDepartment() == null && dep1.getEmployees().size() == 0);
    }

    /** Перевести сотрудника в другой отдел */
    @Test
    void transferEmployee() {

        Department dep1 = new Department("ООО рога и копыта");
        Department dep2 = new Department("досвидули");
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR), dep1);
        employee.transferTo(dep2);
        Assert.assertTrue(employee.getDepartment() == dep2 &&
                dep1.getEmployees().size() == 0 && dep2.getEmployees().size() == 1);
    }

}

package test;

import models.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** Тестирование связей между классами Employee и Management */
public class EmployeeAndManagementLinks_test {

    /** Работник изменил специальность */
    @Test
    void employeeChangedSpecialty() {
        Management employeeListener = new Management();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("Продажи"));

        employee.setActionListener(employeeListener);
        employee.setEducation(new Education(Education.Specialty.LAWYER, Education.Degree.MASTER));
        Assert.assertEquals("Андреев Андрей Андреевич получил степень Магистр по специальности Юрист",
                employee.getActionListener().getLastAction());
    }

    /** Работник принят в отдел */
    @Test
    void employeeAdmittedToDepartment() {

        Management employeeListener = new Management();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("Продажи"), employeeListener);

        Assert.assertEquals("Андреев Андрей Андреевич принят в отдел Продажи",
                employee.getActionListener().getLastAction());
    }

    /** Работник переведен в отдел */
    @Test
    void employeeMovedToDepartment() {

        Management employeeListener = new Management();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("Продажи"));

        employee.setActionListener(employeeListener);
        Department dep = new Department("Дирекция");
        employee.transferTo(dep);
        Assert.assertEquals("Андреев Андрей Андреевич переведен в отдел Дирекция",
                employee.getActionListener().getLastAction());
    }

    /** Работник уволен */
    @Test
    void employeeFired() {

        Management employeeListener = new Management();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("Продажи"));

        employee.setActionListener(employeeListener);
        employee.fireFromCompany();
        Assert.assertEquals("Андреев Андрей Андреевич уволен",
                employee.getActionListener().getLastAction());
    }

    /** Получить полный отчет о всех действиях работника */
    @Test
    void fullReportWithAllPossibleActions() {

        Management employeeListener = new Management();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.MASTER),
                new Department("Продажи"), employeeListener);
        employee.setEducation(new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR));
        Department newDep = new Department("Новые разработки");
        employee.transferTo(newDep);
        employee.fireFromCompany();

        Assert.assertEquals("Андреев Андрей Андреевич принят в отдел Продажи\n" +
                        "Андреев Андрей Андреевич получил степень Доктор по специальности Уборщик\n" +
                        "Андреев Андрей Андреевич переведен в отдел Новые разработки\n" +
                        "Андреев Андрей Андреевич уволен",
                employee.getActionListener().getReport());
    }
}

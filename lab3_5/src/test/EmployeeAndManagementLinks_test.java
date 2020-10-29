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

    /** Получить отчет по конкретному работнику */
    @Test
    void fullReportByCertainEmployee() {

        Management employeeListener = new Management();
        Employee employee1 = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.LAWYER, Education.Degree.BACHELOR),
                new Department("Реклама"), employeeListener);
        Employee employee2 = new Employee("Вася Пупкин",
                new Education(Education.Specialty.CLEANER, Education.Degree.MASTER),
                new Department("Продажи"), employeeListener);

        employee1.setEducation(new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR));
        Department newDep1 = new Department("Старые разработки");
        employee1.transferTo(newDep1);
        employee2.setEducation(new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR));
        Department newDep2 = new Department("Новые разработки");
        employee2.transferTo(newDep2);
        employee1.fireFromCompany();
        employee2.fireFromCompany();

        Assert.assertEquals("Вася Пупкин принят в отдел Продажи\n" +
                        "Вася Пупкин получил степень Доктор по специальности Уборщик\n" +
                        "Вася Пупкин переведен в отдел Новые разработки\n" +
                        "Вася Пупкин уволен\n",
                employeeListener.getReport(employee2.getFIO()));
    }
}

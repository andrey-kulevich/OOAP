package test;
import models.Department;
import models.Education;
import models.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** Тестирование связей между классами Employee и Education */
public class EmployeeAndEducationLinks_test {

    /** Задать образование работнику */
    @Test
    void setEducationToEmployee() {

        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("ООО рога и копыта"));
        Assert.assertTrue(employee.getEducation().getDegree() == Education.Degree.DOCTOR &&
                employee.getEducation().getSpecialty() == Education.Specialty.CLEANER);
    }

    /** Сменить образование работника */
    @Test
    void changeEducation() {

        Education edu1 = new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR);
        Education edu2 = new Education(Education.Specialty.MANAGER, Education.Degree.BACHELOR);
        Employee employee = new Employee("Андреев Андрей Андреевич",
                edu1, new Department("ООО рога и копыта"));
        employee.setEducation(edu2);
        Assert.assertSame(employee.getEducation(), edu2);
    }

    /** Повысить ступень образования у работника */
    @Test
    void increaseDegreeOfEducation() {

        Education edu1 = new Education(Education.Specialty.CLEANER, Education.Degree.BACHELOR);
        Education edu2 = new Education(Education.Specialty.CLEANER, Education.Degree.MASTER);
        Employee employee = new Employee("Андреев Андрей Андреевич",
                edu1, new Department("ООО рога и копыта"));
        employee.setEducation(edu2);
        Assert.assertSame(employee.getEducation(), edu2);
    }

    /** Попытка понизить ступень образования у работника */
    @Test
    void decreaseDegreeOfEducation() {

        Education edu1 = new Education(Education.Specialty.CLEANER, Education.Degree.MASTER);
        Education edu2 = new Education(Education.Specialty.CLEANER, Education.Degree.BACHELOR);
        Employee employee = new Employee("Андреев Андрей Андреевич",
                edu1, new Department("ООО рога и копыта"));
        boolean isError = false;
        try {
            employee.setEducation(edu2);
        } catch (IllegalArgumentException err) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }
}

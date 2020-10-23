package test;
import models.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** Тестирование связей между классами Employee и Computer */
public class EmployeeAndComputerLinks_test {

    /** Прикрепить работника к компьютеру */
    @Test
    void attachEmployeeToComputer() {

        Computer computer = new Computer();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));
        computer.setOwner(employee);
        Assert.assertTrue(computer.getOwner() == employee && employee.getComputer() == computer);
    }

    /** Прикрепить работника к другому компьютеру */
    @Test
    void attachEmployeeToOtherComputer() {

        Computer computer1 = new Computer();
        Computer computer2 = new Computer();
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));
        computer1.setOwner(employee);
        computer2.setOwner(employee);
        Assert.assertTrue(computer1.getOwner() == null &&
                computer2.getOwner() == employee &&
                employee.getComputer() == computer2);
    }

    /** Сменить работника для компьютера */
    @Test
    void changeEmployeeForComputer() {

        Computer computer = new Computer();
        Employee employee1 = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));
        Employee employee2 = new Employee("Gregor",
                new Education(Education.Specialty.DIRECTOR, Education.Degree.BACHELOR),
                new Department("bla bla"));
        computer.setOwner(employee1);
        computer.setOwner(employee2);
        Assert.assertTrue(computer.getOwner() == employee2 &&
                        employee2.getComputer() == computer &&
                        employee1.getComputer() == null);
    }
}

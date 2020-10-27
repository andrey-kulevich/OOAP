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

    /** Установить свободный инвентарный номер для компьютера */
    @Test
    void setFreeId() {

        Computer.setFreeNumber(4);
        Computer computer = new Computer();
        Assert.assertEquals(4, computer.getPcId());
    }

    /** Установлен некорректный инвентарный номер для компьютера */
    @Test
    void invalidFreeId() {

        boolean isError = false;
        try {
            Computer.setFreeNumber(-4);
        } catch (IllegalArgumentException err) {
            isError = true;
        }

        Assert.assertTrue(isError);
    }

    /** Использование компьютера */
    @Test
    void usingOfComputer() throws InterruptedException {

        Computer computer = new Computer();
        Employee employee = new Employee("Gregor",
                new Education(Education.Specialty.DIRECTOR, Education.Degree.BACHELOR),
                new Department("bla bla"));
        computer.setOwner(employee);
        computer.start();
        Thread.sleep(1000);
        computer.stop();

        System.out.println(computer.getLastReport());
        Assert.assertTrue(computer.getLastReport().contains("Gregor использовал компьютер 4 c"));
    }

    /** Полный отчет об использовании компьютера */
    @Test
    void multipleUsingOfComputer() {

        Computer computer = new Computer();
        Employee employee1 = new Employee("Андреев Андрей",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));
        Employee employee2 = new Employee("Gregor",
                new Education(Education.Specialty.DIRECTOR, Education.Degree.BACHELOR),
                new Department("bla bla"));

        computer.setOwner(employee1);
        computer.start();
        computer.stop();

        computer.setOwner(employee2);
        computer.start();
        computer.stop();

        System.out.println(computer.getFullReport());
        Assert.assertTrue(computer.getFullReport().contains("Gregor использовал компьютер 5 c") &&
                computer.getFullReport().contains("Андреев Андрей использовал компьютер 5 c"));
    }
}

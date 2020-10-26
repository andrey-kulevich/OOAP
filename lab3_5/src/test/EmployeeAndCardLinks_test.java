package test;

import models.Card;
import models.Department;
import models.Education;
import models.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** Тестирование связей между классами Employee и Card */
public class EmployeeAndCardLinks_test {

    /** Прикрепить карточку к работнику */
    @Test
    void attachCardToEmployee() {

        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));

        Assert.assertEquals(0, employee.getIdCard().getNumber());
    }

    /** Задать свободный номер для карточки */
    @Test
    void setFreeNumberForCard() {

        Card.setFreeNumber(500);
        Employee employee = new Employee("Андреев Андрей Андреевич",
                new Education(Education.Specialty.CLEANER, Education.Degree.DOCTOR),
                new Department("name"));

        Assert.assertEquals(500, employee.getIdCard().getNumber());
    }
}

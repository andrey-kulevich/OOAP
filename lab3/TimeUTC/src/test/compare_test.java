package test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class compare_test {

    /** Одинаковый пояс, одинаковое время */
    @Test
    void SameTimeZoneAndSameTime() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        int result = time1.compare(time2);
        Assert.assertEquals(0, result);
    }

    /** Разные пояса, одинаковое время */
    @Test
    void DiffTimeZonesAndSameTime() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Разные пояса, одинаковые моменты времени */
    @Test
    void DiffTimeZonesAndSameTimeMoment() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC time2 = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(0, result);
    }

    /** Левое опережает по часам */
    @Test
    void leftTimeIsFasterByHours() {

        TimeUTC time1 = new TimeUTC(13, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает по часам */
    @Test
    void leftTimeIsSlowerByHours() {

        TimeUTC time1 = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Левое опережает по минутам */
    @Test
    void leftTimeIsFasterByMinutes() {

        TimeUTC time1 = new TimeUTC(12, 20, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает по минутам */
    @Test
    void leftTimeIsSlowerByMinutes() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 20, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Левое опережает по секундам */
    @Test
    void leftTimeIsFasterBySeconds() {

        TimeUTC time1 = new TimeUTC(12, 12, 15, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает по секундам */
    @Test
    void leftTimeIsSlowerBySeconds() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 15, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Левое опережает по миллисекундам */
    @Test
    void leftTimeIsFasterByMilliseconds() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 15, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает по миллисекундам */
    @Test
    void leftTimeIsSlowerByMilliseconds() {

        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 15, TimeUTC.TimeZones.UTC_plus3);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Левое опережает, правое при уравнивании поясов уходит на следующий день */
    @Test
    void leftTimeIsFasterAndRightGoToNextDay() {

        TimeUTC time1 = new TimeUTC(2, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC_plus1);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает, правое при уравнивании поясов уходит на следующий день */
    @Test
    void leftTimeIsSlowerAndRightGoToNextDay() {

        TimeUTC time1 = new TimeUTC(22, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC time2 = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC_plus1);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }

    /** Левое опережает, правое при уравнивании поясов уходит на предыдущий день */
    @Test
    void leftTimeIsFasterAndRightGoToPreviousDay() {

        TimeUTC time1 = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC_minus3);
        TimeUTC time2 = new TimeUTC(1, 12, 12, 12, TimeUTC.TimeZones.UTC);
        int result = time1.compare(time2);
        Assert.assertEquals(1, result);
    }

    /** Левое отстает, правое при уравнивании поясов уходит на предыдущий день */
    @Test
    void leftTimeIsSlowerAndRightGoToPreviousDay() {

        TimeUTC time1 = new TimeUTC(15, 12, 12, 12, TimeUTC.TimeZones.UTC_minus3);
        TimeUTC time2 = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC);
        int result = time1.compare(time2);
        Assert.assertEquals(-1, result);
    }
}

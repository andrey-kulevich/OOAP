package test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class TimeUTC_constructor_test {

    /** Некорректное значение часов */
    @Test
    void invalidHoursError() {

        boolean isError = false;

        try {
            TimeUTC time = new TimeUTC(24, 12, 12, 12, TimeUTC.TimeZones.UTC);
        }
        catch (IllegalArgumentException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Некорректное значение минут */
    @Test
    void invalidMinutesError() {

        boolean isError = false;

        try {
            TimeUTC time = new TimeUTC(12, -2, 12, 12, TimeUTC.TimeZones.UTC);
        }
        catch (IllegalArgumentException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Некорректное значение секунд */
    @Test
    void invalidSecondsError() {

        boolean isError = false;

        try {
            TimeUTC time = new TimeUTC(12, 12, 70, 12, TimeUTC.TimeZones.UTC);
        }
        catch (IllegalArgumentException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Некорректное значение миллисекунд */
    @Test
    void invalidMillisecondsError() {

        boolean isError = false;

        try {
            TimeUTC time = new TimeUTC(12, 12, 12, 1000, TimeUTC.TimeZones.UTC);
        }
        catch (IllegalArgumentException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Не указаны миллисекунды */
    @Test
    void millisecondsNotSpecified() {
        TimeUTC time = new TimeUTC(12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC expTime = new TimeUTC(12,12,12,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expTime, time);
    }

    /** Не указаны секунды */
    @Test
    void secondsNotSpecified() {
        TimeUTC time = new TimeUTC(12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC expTime = new TimeUTC(12,12,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expTime, time);
    }

    /** Не указаны минуты */
    @Test
    void minutesNotSpecified() {
        TimeUTC time = new TimeUTC(12, TimeUTC.TimeZones.UTC);
        TimeUTC expTime = new TimeUTC(12,0,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expTime, time);
    }
}

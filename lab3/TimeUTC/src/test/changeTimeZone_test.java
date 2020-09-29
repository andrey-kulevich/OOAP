package test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class changeTimeZone_test {

    /** Новый часовой пояс такой же, как и старый */
    @Test
    void newTimeZoneIsTheSame() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus3);
        TimeUTC expTime = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс опережает по часам */
    @Test
    void newTimeZoneIsGreaterByHours() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus4);
        TimeUTC expTime = new TimeUTC(13, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс отстает по часам */
    @Test
    void newTimeZoneIsSmallerByHours() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus2);
        TimeUTC expTime = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC_plus2);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс опережает по минутам */
    @Test
    void newTimeZoneIsGreaterByMinutes() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus3_30);
        TimeUTC expTime = new TimeUTC(12, 42, 12, 12, TimeUTC.TimeZones.UTC_plus3_30);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс отстает по минутам */
    @Test
    void newTimeZoneIsSmallerByMinutes() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus3_30);
        TimeUTC expTime = new TimeUTC(11, 42, 12, 12, TimeUTC.TimeZones.UTC_plus3_30);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс с отрицательным сдвигом по часам */
    @Test
    void newTimeZoneWithNegativeOffsetByHours() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_minus2);
        TimeUTC expTime = new TimeUTC(6, 12, 12, 12, TimeUTC.TimeZones.UTC_minus2);
        Assert.assertEquals(newTime, expTime);
    }

    /** Новый пояс с отрицательным сдвигом по часам и минутам */
    @Test
    void newTimeZoneWithNegativeOffsetByHoursAndMinutes() {

        TimeUTC time = new TimeUTC(15, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_minus9_30);
        TimeUTC expTime = new TimeUTC(1, 42, 12, 12, TimeUTC.TimeZones.UTC_minus9_30);
        Assert.assertEquals(expTime, newTime);
    }

    /** Время сдвинулось до предыдущего дня */
    @Test
    void timeOffsetToPreviousDay() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_minus9);
        TimeUTC expTime = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC_minus9);
        Assert.assertEquals(expTime, newTime);
    }

    /** Время сдвинулось до следующего дня */
    @Test
    void timeOffsetToNextDay() {

        TimeUTC time = new TimeUTC(20, 12, 12, 12, TimeUTC.TimeZones.UTC_plus4);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus8);
        TimeUTC expTime = new TimeUTC(0, 12, 12, 12, TimeUTC.TimeZones.UTC_plus8);
        Assert.assertEquals(expTime, newTime);
    }

    /** Максимальный сдвиг по времени */
    @Test
    void timeOffsetIsMaximum() {

        TimeUTC time = new TimeUTC(23, 12, 12, 12, TimeUTC.TimeZones.UTC_minus12);
        TimeUTC newTime = time.changeTimeZone(TimeUTC.TimeZones.UTC_plus11);
        TimeUTC expTime = new TimeUTC(22, 12, 12, 12, TimeUTC.TimeZones.UTC_plus11);
        Assert.assertEquals(expTime, newTime);
    }
}

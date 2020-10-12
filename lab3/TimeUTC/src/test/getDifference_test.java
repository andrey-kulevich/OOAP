package test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class getDifference_test {

    /** Одинаковые часовые пояса, одинаковое время */
    @Test
    void equalTimeZones() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по миллисекундам, первое больше второго */
    @Test
    void diffByMillisecondsAndFirstGreater() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 11, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,0,1, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по миллисекундам, первое меньше второго */
    @Test
    void diffByMillisecondsAndFirstSmaller() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 11, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,0,1, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по секундам, первое больше второго */
    @Test
    void diffBySecondsAndFirstGreater() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 11, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,1,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по секундам, первое меньше второго */
    @Test
    void diffBySecondsAndFirstSmaller() {
        TimeUTC time1 = new TimeUTC(12, 12, 11, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,1,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по минутам, первое больше второго */
    @Test
    void diffByMinutesAndFirstGreater() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 11, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,1,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по минутам, первое меньше второго */
    @Test
    void diffByMinutesAndFirstSmaller() {
        TimeUTC time1 = new TimeUTC(12, 11, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,1,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по часам, первое больше второго */
    @Test
    void diffByHoursAndFirstGreater() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(1,0,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Время отличается по часам, первое меньше второго */
    @Test
    void diffByHoursAndFirstSmaller() {
        TimeUTC time1 = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(1,0,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Разные часовые пояса, но время одинаковое */
    @Test
    void equalTimeAndDiffTimeZones() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(1,0,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Разные часовые пояса и разное время, но момент времени один и тот же */
    @Test
    void diffTimeAndDiffTimeZoneButSameMoment() {
        TimeUTC time1 = new TimeUTC(11, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Разные часовые пояса, первое опережает второе */
    @Test
    void diffTimeZonesAndFirstFaster() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_minus1);
        TimeUTC time2 = new TimeUTC(10, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(3,0,0,0, TimeUTC.TimeZones.UTC_minus1);

        Assert.assertEquals(expDiff, diff);
    }

    /** Разные часовые пояса, первое отстает от второго */
    @Test
    void diffTimeZonesAndFirstSlower() {
        TimeUTC time1 = new TimeUTC(12, 7, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 10, 12, 12, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,57,0,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

//    /** Разные часовые пояса, первое отстает от второго */
//    @Test
//    void diffTimeZonesAndFirstSlower() {
//        TimeUTC time1 = new TimeUTC(0, 0, 0, 0, TimeUTC.TimeZones.UTC);
//        TimeUTC time2 = new TimeUTC(20, 0, 0, 0, TimeUTC.TimeZones.UTC);
//        TimeUTC diff = time1.getDifference(time2);
//        TimeUTC expDiff = new TimeUTC(4,0,0,0, TimeUTC.TimeZones.UTC);
//
//        Assert.assertEquals(expDiff, diff);
//    }

    /** У первого миллисекунд меньше, чем у второго */
    @Test
    void firstHasLessMilliseconds() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 12, 11, 13, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,0,999, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** У первого секунд меньше, чем у второго */
    @Test
    void firstHasLessSeconds() {
        TimeUTC time1 = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC);
        TimeUTC time2 = new TimeUTC(12, 11, 13, 12, TimeUTC.TimeZones.UTC);
        TimeUTC diff = time1.getDifference(time2);
        TimeUTC expDiff = new TimeUTC(0,0,59,0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }
}

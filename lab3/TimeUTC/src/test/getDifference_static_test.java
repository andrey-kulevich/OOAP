package test;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class getDifference_static_test {

    /** Одинаковые часовые пояса */
    @Test
    void equalTimeZones() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus1, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC expDiff = new TimeUTC(0, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Первый часовой пояс имеет сдвиг только по часам и сдвиг больше второго */
    @Test
    void firstTimeZoneHasOffsetByHoursAndGreater() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus2, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC expDiff = new TimeUTC(1, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Первый часовой пояс имеет сдвиг только по часам и сдвиг меньше второго */
    @Test
    void firstTimeZoneHasOffsetByHoursAndSmaller() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus1, TimeUTC.TimeZones.UTC_plus2);
        TimeUTC expDiff = new TimeUTC(1, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Часовые пояса с отрицательным сдвигом, первый сдвиг больше второго */
    @Test
    void timeZonesHaveNegativeOffsetAndFirstGreater() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_minus1, TimeUTC.TimeZones.UTC_minus2);
        TimeUTC expDiff = new TimeUTC(1, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Часовые пояса с отрицательным сдвигом, первый сдвиг меньше второго */
    @Test
    void timeZonesHaveNegativeOffsetAndFirstSmaller() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_minus2, TimeUTC.TimeZones.UTC_minus1);
        TimeUTC expDiff = new TimeUTC(1, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** У одного пояса сдвиг положительный, а у другого отрицательный */
    @Test
    void oneTimeZoneHasPositiveOffsetAndAnotherHasNegative() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_minus2, TimeUTC.TimeZones.UTC_plus1);
        TimeUTC expDiff = new TimeUTC(3, 0, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Один из часовых поясов имеет сдвиг по часам и по минутам */
    @Test
    void oneOfTimesZonesHasOffsetByMinutes() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus4_30, TimeUTC.TimeZones.UTC_plus2);
        TimeUTC expDiff = new TimeUTC(2, 30, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Оба часовых пояса имеют сдвиг по часам и по минутам, в первом часов меньше и минут меньше */
    @Test
    void bothHasOffsetByMinutes() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus4_30, TimeUTC.TimeZones.UTC_plus5_45);
        TimeUTC expDiff = new TimeUTC(1, 15, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }

    /** Оба часовых пояса имеют сдвиг по часам и по минутам, в первом часов больше и минут меньше */
    @Test
    void bothHasOffsetByMinutesAndFirstGreaterByHours() {
        TimeUTC diff = TimeUTC.getDifference(TimeUTC.TimeZones.UTC_plus9_30, TimeUTC.TimeZones.UTC_plus8_45);
        TimeUTC expDiff = new TimeUTC(0, 45, TimeUTC.TimeZones.UTC);

        Assert.assertEquals(expDiff, diff);
    }
}

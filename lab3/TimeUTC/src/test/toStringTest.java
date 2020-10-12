package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

public class toStringTest {
    /** Часовой пояс без сдвига по минутам */
    @Test
    void noOffsetByMinutes() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3);
        Assert.assertEquals(time.toString(), "12:12:12:12 UTC+3");
    }

    /** Часовой пояс со сдвигом по минутам */
    @Test
    void withOffsetByMinutes() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_plus3_30);
        Assert.assertEquals(time.toString(), "12:12:12:12 UTC+3:30");
    }

    /** Отрицательный сдвиг времени */
    @Test
    void negativeOffset() {

        TimeUTC time = new TimeUTC(12, 12, 12, 12, TimeUTC.TimeZones.UTC_minus2);
        Assert.assertEquals(time.toString(), "12:12:12:12 UTC-2");
    }
}

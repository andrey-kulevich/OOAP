package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import timeutcapp.TimeUTC;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class getCurrentTimes_test {

    /** Вспомогательная функция для сравнения текущих моментов времени
     *
     * @param time текущее время
     * @param offset сдвиг относительно UTC
     */
    private void compareTimes (TimeUTC time, String offset) {
        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(offset));
        Assert.assertEquals(time.getHours(), now.getHour());
        Assert.assertTrue(Math.abs(time.getMinutes() - now.getMinute()) <= 1 );
        Assert.assertTrue(Math.abs(time.getSeconds() - now.getSecond()) <= 1);
    }

    /** Время в Калининграде */
    @Test
    void kaliningrad() {

        TimeUTC time = TimeUTC.getKaliningradTime();
        compareTimes(time, "+2");
    }

    /** Время в Москве */
    @Test
    void moscow() {

        TimeUTC time = TimeUTC.getMoscowTime();
        compareTimes(time, "+3");
    }

    /** Время в Казани */
    @Test
    void kazan() {

        TimeUTC time = TimeUTC.getKazanTime();
        compareTimes(time, "+3");
    }

    /** Время в Екатеринбурге */
    @Test
    void yekaterinburg() {

        TimeUTC time = TimeUTC.getYekaterinburgTime();
        compareTimes(time, "+5");
    }

    /** Время во Владивостоке */
    @Test
    void vladivostok() {

        TimeUTC time = TimeUTC.getVladivostokTime();
        compareTimes(time, "+10");
    }
}

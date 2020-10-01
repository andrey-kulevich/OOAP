package timeutcapp;

import java.util.Calendar;
import java.util.TimeZone;

/** Класс, представляющий время в разных часовых поясах */
public class TimeUTC {
    /* =========================== Свойства =============================== */

    /** Компоненты времени */
    private final int hours, minutes, seconds, milliseconds;
    /** Смещение времени относительно UTC */
    private final TimeZones timezone;

    /* =========================== Перечисления =============================== */

    /** Перечисление всех существующих часовых поясов со смещениями по часам и минутам относительно UTC */
    public enum TimeZones {

        /*---------- Список констант ------------*/

        UTC_minus12 (-12,0),
        UTC_minus11 (-11,0),
        UTC_minus10 (-10,0),
        UTC_minus9_30 (-9,-30),
        UTC_minus9 (-9,0),
        UTC_minus8 (-8,0),
        UTC_minus7 (-7,0),
        UTC_minus6 (-6,0),
        UTC_minus5 (-5,0),
        UTC_minus4 (-4,0),
        UTC_minus3_30 (-3,-30),
        UTC_minus3 (-3,0),
        UTC_minus2 (-2,0),
        UTC_minus1 (-1,0),
        UTC (0,0),
        UTC_plus1 (1,0),
        UTC_plus2 (2,0),
        UTC_plus3 (3,0),
        UTC_plus3_30 (3,30),
        UTC_plus4 (4,0),
        UTC_plus4_30 (4,30),
        UTC_plus5 (5,0),
        UTC_plus5_30 (5,30),
        UTC_plus5_45 (5,45),
        UTC_plus6 (6,0),
        UTC_plus6_30 (6,30),
        UTC_plus7 (7,0),
        UTC_plus8 (8,0),
        UTC_plus8_45 (8,45),
        UTC_plus9 (9,0),
        UTC_plus9_30 (9,30),
        UTC_plus10 (10,0),
        UTC_plus10_30 (10,30),
        UTC_plus11 (11,0);

        /*---------- Свойства ------------*/

        /** Смещение часов относительно UTC  */
        private final int hoursOffset;
        /** Смещение минут относительно UTC  */
        private final int minutesOffset;

        /*---------- Методы ------------*/

        /** Создать часовой пояс
         *
         * @param hoursOffset Смещение часов относительно UTC
         * @param minutesOffset Смещение минут относительно UTC
         */
        TimeZones(int hoursOffset, int minutesOffset) {
            this.hoursOffset = hoursOffset;
            this.minutesOffset = minutesOffset;
        }

        /** Вернуть смещение минут относительно UTC
         *
         * @return Смещение минут
         */
        public int getMinutesOffset() { return minutesOffset; }

        /** Вернуть смещение часов относительно UTC
         *
         * @return Смещение часов
         */
        public int getHoursOffset() { return hoursOffset; }
    }

    /* =========================== Методы ============================== */
    /* ---------------------------- Порождение ---------------------------- */

    /** Создать момент времени
     *
     * @param hours часы
     * @param minutes минуты
     * @param seconds секунды
     * @param milliseconds миллисекунды
     * @param timezone часовой пояс
     */
    public TimeUTC (int hours, int minutes, int seconds, int milliseconds, TimeZones timezone) {

        checkTimeValidity(hours, minutes, seconds, milliseconds);

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.timezone = timezone;
    }

    /** Создать момент времени без указания миллисекунд
     *
     * @param hours часы
     * @param minutes минуты
     * @param seconds секунды
     * @param timezone часовой пояс
     */
    public TimeUTC (int hours, int minutes, int seconds, TimeZones timezone) {

        checkTimeValidity(hours, minutes, seconds, 0);

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = 0;
        this.timezone = timezone;
    }

    /** Создать момент времени без указания секунд и миллисекунд
     *
     * @param hours часы
     * @param minutes минуты
     * @param timezone часовой пояс
     */
    public TimeUTC (int hours, int minutes, TimeZones timezone) {

        checkTimeValidity(hours, minutes, 0, 0);

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = 0;
        this.milliseconds = 0;
        this.timezone = timezone;
    }

    /** Создать момент времени с указанием только часов и часового пояса
     *
     * @param hours часы
     * @param timezone часовой пояс
     */
    public TimeUTC (int hours, TimeZones timezone) {

        checkTimeValidity(hours, 0, 0, 0);

        this.hours = hours;
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
        this.timezone = timezone;
    }

    /** Проверить корректность компонентов времени
     *
     * @param hours часы
     * @param minutes минуты
     * @param seconds секунды
     * @param milliseconds миллисекунды
     */
    private void checkTimeValidity (int hours, int minutes, int seconds, int milliseconds) {

        if (hours < 0 || hours > 23 ) throw new IllegalArgumentException("Invalid hours value");
        if (minutes < 0|| minutes > 59) throw new IllegalArgumentException("Invalid minutes value");
        if (seconds < 0 || seconds > 59) throw new IllegalArgumentException("Invalid seconds value");
        if (milliseconds < 0 || milliseconds > 999) throw new IllegalArgumentException("Invalid milliseconds value");
    }

    /* ---------------------------- Геттеры ---------------------------- */

    /** Получить количество часов
     *
     * @return кол-во часов
     */
    public int getHours() { return hours; }

    /** Получить количество минут
     *
     * @return кол-во минут
     */
    public int getMinutes() { return minutes; }

    /** Получить количество секунд
     *
     * @return кол-во секунд
     */
    public int getSeconds() { return seconds; }

    /** Получить количество миллисекунд
     *
     * @return кол-во миллисекунд
     */
    public int getMilliseconds() { return milliseconds; }

    /** Получить часовой пояс
     *
     * @return часовой пояс для данного времени
     */
    public TimeZones getTimezone() { return timezone; }

    /* ---------------------------- Операции ---------------------------- */

    /** Проверить два объекта на эквивалентность
     *
     * @param o объект, с которым происходит сравнение
     * @return эквивалентность объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeUTC timeUTC = (TimeUTC) o;
        return hours == timeUTC.hours &&
                minutes == timeUTC.minutes &&
                seconds == timeUTC.seconds &&
                milliseconds == timeUTC.milliseconds &&
                timezone == timeUTC.timezone;
    }

    /** Перевести объект в строковое представление
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {

        String minutesOffset = "";
        if (timezone.getMinutesOffset() != 0) minutesOffset += ":" + Math.abs(timezone.getMinutesOffset());

        String hoursOffset;
        if (timezone.getHoursOffset() > 0) hoursOffset = "+" + timezone.getHoursOffset();
        else hoursOffset = String.valueOf(timezone.getHoursOffset());

        return hours + ":" + minutes + ":" + seconds + ":" + milliseconds
                + " UTC" + hoursOffset + minutesOffset;
    }

    /** Сравнить два момента времени из разных часовых поясов
     *
     * @param other время, с которым производится сравнение
     * @return 0 - если время совпадает, 1 - если левое время опережает, -1 - если левое время отстает
     */
    public int compare (TimeUTC other) {

        TimeUTC otherWithSameTimeZone = other.changeTimeZone(timezone);
        if (hours == otherWithSameTimeZone.hours &&
            minutes == otherWithSameTimeZone.minutes &&
            seconds == otherWithSameTimeZone.seconds &&
            milliseconds == otherWithSameTimeZone.milliseconds) return 0;

        TimeUTC diffBetweenTZones = TimeUTC.getDifference(timezone, other.timezone);
        TimeUTC diffBetweenTMoments = getDifference(other);

        boolean isSyncHours = diffBetweenTZones.hours == diffBetweenTMoments.hours;
        boolean isSyncMinutes = diffBetweenTZones.minutes == diffBetweenTMoments.minutes;

        if (diffBetweenTZones.hours - diffBetweenTMoments.hours > 0 ||
                (diffBetweenTZones.hours == 0 && hours > other.hours) ||
                (isSyncHours && diffBetweenTZones.minutes - diffBetweenTMoments.minutes > 0) ||
                (diffBetweenTZones.hours == 0 && diffBetweenTZones.minutes == 0 && minutes > other.minutes) ||
                (isSyncHours && isSyncMinutes && seconds > other.seconds) ||
                (isSyncHours && isSyncMinutes && seconds == other.seconds &&
                        milliseconds > other.milliseconds)) return 1;

        return -1;
    }

    /** Сменить часовой пояс
     *
     * @param timezone новый часовой пояс
     * @return новое время со смененным часовым поясом
     */
    public TimeUTC changeTimeZone (TimeZones timezone) {

        int hoursNew = hours + timezone.getHoursOffset() - this.timezone.getHoursOffset();
        int minutesNew = minutes + timezone.getMinutesOffset() - this.timezone.getMinutesOffset();

        if (minutesNew < 0) {
            minutesNew += 60;
            hoursNew--;
        }

        if (minutesNew > 59) {
            minutesNew -= 60;
            hoursNew++;
        }

        if (hoursNew < 0) hoursNew += 24;
        if (hoursNew > 23) hoursNew -= 24;

        return new TimeUTC(hoursNew, minutesNew, seconds, milliseconds, timezone);
    }

    /** Получить разницу между двумя моментами времени из разных часовых поясов
     *
     * @param other время, относительно которого нужно получить разницу
     * @return новое время, представляющее модуль разницы между всеми компонентами от двух времен
     */
    public TimeUTC getDifference (TimeUTC other) {

        TimeUTC otherWithSameTimeZone = other.changeTimeZone(timezone);

        int hoursDiff = hours - otherWithSameTimeZone.hours;
        int minutesDiff = minutes - otherWithSameTimeZone.minutes;
        int secondsDiff = seconds - otherWithSameTimeZone.seconds;
        int millisecondsDiff = milliseconds - otherWithSameTimeZone.milliseconds;

        if (millisecondsDiff < 0 && secondsDiff > 0) {
            millisecondsDiff += 1000;
        }

        if (secondsDiff < 0 && minutesDiff > 0) {
            secondsDiff += 60;
            minutesDiff--;
        }

        if (minutesDiff < 0 && hoursDiff > 0) {
            minutesDiff += 60;
            hoursDiff--;
        }

        if (hoursDiff < -12) hoursDiff += 24;
        if (hoursDiff > 12) hoursDiff -= 24;

        return new TimeUTC(Math.abs(hoursDiff),
                Math.abs(minutesDiff),
                Math.abs(secondsDiff),
                Math.abs(millisecondsDiff), timezone);
    }

    /** Получить текущее время в поясе UTC
     *
     * @return текущее время в поясе UTC
     */
    private static TimeUTC getCurrentUTCTime () {

        Calendar now = Calendar.getInstance();
        now.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new TimeUTC(now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                now.get(Calendar.MILLISECOND), TimeZones.UTC);
    }

    /* ---------------------------- Текущее время для разных городов ---------------------------- */

    /** Получить текущее время в Калининграде
     *
     * @return текущее время в Калининграде
     */
    public static TimeUTC getKaliningradTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus2);
    }

    /** Получить текущее время в Москве
     *
     * @return текущее время в Москве
     */
    public static TimeUTC getMoscowTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus3);
    }

    /** Получить текущее время в Казани
     *
     * @return текущее время в Казани
     */
    public static TimeUTC getKazanTime () {
        return getMoscowTime();
    }

    /** Получить текущее время в Екатеринбурге
     *
     * @return текущее время в Екатеринбурге
     */
    public static TimeUTC getYekaterinburgTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus5);
    }

    /** Получить текущее время во Владивостоке
     *
     * @return текущее время во Владивостоке
     */
    public static TimeUTC getVladivostokTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus10);
    }

    /* ---------------------------- Статические операции ---------------------------- */

    /** Получить разницу во времени между двумя часовыми поясами
     *
     * @param timezone1 первый часовой пояс
     * @param timezone2 второй часовой пояс
     * @return разница во времени между двумя часовыми поясами
     */
    public static TimeUTC getDifference (TimeZones timezone1, TimeZones timezone2) {

        int hoursDiff = timezone1.getHoursOffset() - timezone2.getHoursOffset();
        int minutesDiff = timezone1.getMinutesOffset() - timezone2.getMinutesOffset();
        if (minutesDiff < 0 && hoursDiff > 0) {
            minutesDiff += 60;
            hoursDiff--;
        }

        return new TimeUTC(Math.abs(hoursDiff), Math.abs(minutesDiff), TimeZones.UTC);
    }
}

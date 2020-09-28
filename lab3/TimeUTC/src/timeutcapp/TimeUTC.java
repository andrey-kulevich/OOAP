package timeutcapp;

import java.util.Calendar;

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
        UTC_plus11 (11,0),
        UTC_plus12 (12,0),
        UTC_plus12_45 (12,45),
        UTC_plus13 (13,0),
        UTC_plus14 (14,0);

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
        if (hours < 0 || minutes < 0 || seconds < 0 || milliseconds < 0 ||
                hours > 23 || minutes > 59 || seconds > 59 || milliseconds > 999) {
            throw new IllegalArgumentException("Invalid time value");
        }
    }

    /* ---------------------------- Операции ---------------------------- */

    /** Перевести объект в строковое представление
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return hours + ":" + minutes + ":" + seconds + ":" + milliseconds
                + " UTC" + timezone.getHoursOffset() + ":" + Math.abs(timezone.getMinutesOffset());
    }

    /** Сравнить два момента времени из разных часовых поясов
     *
     * @param other время, с которым производится сравнение
     * @return 0 - если время совпадает, 1 - если левое время опережает, -1 - если левое время отстает
     */
    public int compare (TimeUTC other) {

        TimeUTC otherWithSameTimeZone = changeTimeZone(timezone);
        if (hours == otherWithSameTimeZone.hours &&
            minutes == otherWithSameTimeZone.minutes &&
            seconds == otherWithSameTimeZone.seconds &&
            milliseconds == otherWithSameTimeZone.milliseconds) return 0;

        TimeUTC diffBetweenTZones = TimeUTC.getDifference(timezone, other.timezone);
        TimeUTC diffBetweenTMoments = getDifference(other);

        boolean isSyncHours = diffBetweenTZones.hours - diffBetweenTMoments.hours == diffBetweenTZones.hours;
        boolean isSyncMinutes = diffBetweenTZones.minutes - diffBetweenTMoments.minutes == diffBetweenTZones.minutes;

        if (diffBetweenTZones.hours - diffBetweenTMoments.hours < diffBetweenTZones.hours ||
                (isSyncHours && diffBetweenTZones.minutes - diffBetweenTMoments.minutes < diffBetweenTZones.minutes) ||
                (isSyncHours && isSyncMinutes && seconds > other.seconds) ||
                (isSyncHours && isSyncMinutes && seconds == other.seconds && milliseconds > other.milliseconds)) return 1;

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
            minutesNew = 59 + minutesNew;
            hoursNew--;
        }

        if (hoursNew < 0) hoursNew = 23 + hoursNew;

        return new TimeUTC(hoursNew, minutesNew, seconds, milliseconds, timezone);
    }

    /** Получить разницу между двумя моментами времени из разных часовых поясов
     *
     * @param other время, относительно которого нужно получить разницу
     * @return новое время, представляющее модуль разницы между всеми компонентами от двух времен
     */
    public TimeUTC getDifference (TimeUTC other) {
        TimeUTC otherWithSameTimeZone = other.changeTimeZone(timezone);

        return new TimeUTC(Math.abs(hours - otherWithSameTimeZone.hours),
                Math.abs(minutes - otherWithSameTimeZone.minutes),
                Math.abs(seconds - otherWithSameTimeZone.seconds),
                Math.abs(milliseconds - otherWithSameTimeZone.milliseconds), timezone);
    }

    /** Получить текущее время в поясе UTC
     *
     * @return текущее время в поясе UTC
     */
    private TimeUTC getCurrentUTCTime () {
        Calendar now = Calendar.getInstance();
        return new TimeUTC(now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                now.get(Calendar.MILLISECOND), TimeZones.UTC);
    }

    /* ---------------------------- Текущее время для разных городов ---------------------------- */

    /** Получить текущее время в Калининграде
     *
     * @return текущее время в Калининграде
     */
    public TimeUTC getKaliningradTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus2);
    }

    /** Получить текущее время в Москве
     *
     * @return текущее время в Москве
     */
    public TimeUTC getMoscowTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus3);
    }

    /** Получить текущее время в Казани
     *
     * @return текущее время в Казани
     */
    public TimeUTC getKazanTime () {
        return getMoscowTime();
    }

    /** Получить текущее время в Екатеринбурге
     *
     * @return текущее время в Екатеринбурге
     */
    public TimeUTC getYekaterinburgTime () {
        return getCurrentUTCTime().changeTimeZone(TimeZones.UTC_plus5);
    }

    /** Получить текущее время во Владивостоке
     *
     * @return текущее время во Владивостоке
     */
    public TimeUTC getVladivostokTime () {
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
        return new TimeUTC(timezone1.getHoursOffset() - timezone2.getHoursOffset(),
                timezone1.getMinutesOffset() - timezone2.getMinutesOffset(), TimeZones.UTC);
    }
}

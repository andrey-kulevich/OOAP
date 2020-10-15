package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/** Класс компьютера */
public class Computer {

    /*------------ Свойства -------------*/
    /** Инвентарный номер */
    private final int pcId;
    /** Работник, за которым закреплен этот компьютер */
    private Employee owner;
    /** Состояние работы */
    private boolean isStarted;
    /** Время начала работы */
    private LocalDateTime timeStart;
    /** История использования компьютера */
    private final ArrayList<String> log;

    /*------------ Конструктор -------------*/

    /** Конструктор компьютера
     *
     * @param pcId инвентарный номер
     */
    public Computer (int pcId) {
        if (pcId >= 0) this.pcId = pcId;
        else throw new IllegalArgumentException("Инвентарный номер не может быть отрицательным");
        isStarted = false;
        log = new ArrayList<String>();
    }

    /*------------ Геттеры -------------*/

    /** Получить инвентарный номер
     *
     * @return инвентарный номер
     */
    public int getPcId() { return pcId; }

    /** Получить отчет об использовании компьютера
     *
     * @return отчет
     */
    public String getFullReport () { return String.join("\n", log); }

    /** Получить отчет о последнем использовании компьютера
     *
     * @return ответ
     */
    public String getLastReport () { return log.get(log.size() - 1); }

    /*------------ Сеттеры -------------*/

    /** Закрепить компьютер за работником
     *
     * @param owner работник
     */
    public void setOwner(Employee owner) { this.owner = owner; }

    /*------------ Операции -------------*/

    /** Начать использовать */
    public void start () {
        isStarted = true;
        timeStart = LocalDateTime.now();
    }

    /** Прекратить использование */
    public void stop () {
        isStarted = false;
        log.add(owner.getFIO() + " использовал компьютер " + pcId + " c " + timeStart + " по " + LocalDateTime.now());
    }
}

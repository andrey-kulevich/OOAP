package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/** Класс компьютера */
public class Computer {

    /*------------ Свойства -------------*/
    /** Свободный инвентарный номер */
    private static int freeId = 0;
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
     */
    public Computer () {
        pcId = freeId;
        freeId++;
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

    /** Получить работника, за которым закреплен компьютер
     *
     * @return работник
     */
    public Employee getOwner() { return owner; }

    /*------------ Сеттеры -------------*/

    /** Закрепить компьютер за работником
     *
     * @param owner работник
     */
    public void setOwner(Employee owner) {
        if (this.owner != null) {
            this.owner.setComputer(null);
        }
        if (owner != null && owner.getComputer() != null) {
            owner.getComputer().setOwner(null);
            owner.setComputer(null);
        }
        this.owner = owner;
        if (this.owner != null) owner.setComputer(this);
    }

    /** Установить свободный инвентарный номер
     *
     * @param freeNumber свободный инвентарный номер
     */
    public static void setFreeNumber(int freeNumber) {
        if (freeNumber >= 0) Computer.freeId = freeNumber;
        else throw new IllegalArgumentException("Инвентарный номер не может быть отрицательным");
    }

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

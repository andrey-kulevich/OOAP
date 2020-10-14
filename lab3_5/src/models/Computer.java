package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Computer {

    /*------------ Свойства -------------*/

    private int pcId;
    private Employee owner;
    private boolean isStarted;
    private LocalDateTime timeStart;
    private ArrayList<String> log;

    /*------------ Конструктор -------------*/

    public Computer (int pcId) {
        if (pcId >= 0) this.pcId = pcId;
        else throw new IllegalArgumentException("Инвентарный номер не может быть отрицательным");
        isStarted = false;
        log = new ArrayList<String>();
    }

    /*------------ Геттеры -------------*/

    public int getPcId() { return pcId; }

    public String getFullReport () { return String.join("\n", log); }

    public String getLastReport () { return log.get(log.size() - 1); }

    /*------------ Сеттеры -------------*/

    public void setOwner(Employee owner) { this.owner = owner; }

    /*------------ Операции -------------*/

    public void start () {
        isStarted = true;
        timeStart = LocalDateTime.now();
    }

    public void stop () {
        isStarted = false;
        log.add(owner.getFIO() + " использовал компьютер " + pcId + " c " + timeStart + " по " + LocalDateTime.now());
    }
}

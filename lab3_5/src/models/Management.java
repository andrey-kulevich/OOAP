package models;

import java.util.ArrayList;

/** Интерфейс класса для прослушивание действий работника */
interface EmployeeListener {
    /** Реакция на изменение состояния работника
     *
     * @param action произошедшее действие
     */
    void actionPerformed (String action);
}

public class Management implements EmployeeListener {

    /*------------ Свойства -------------*/

    /** Полная история действий работников */
    private final ArrayList<String> report = new ArrayList<String>();

    /*------------ Геттеры -------------*/

    /** Получить полную историю действий работников
     *
     * @return отчет
     */
    public String getReport () { return String.join("\n", report); }

    /** Получить последнее совершенное действие
     *
     * @return последнее действие
     */
    public String getLastAction () { return report.get(report.size() - 1); }

    /** Получить отчет по конкретному работнику
     *
     * @param FIO ФИО работника
     * @return отчет
     */
    public String getReport (String FIO) {
        StringBuilder rep = new StringBuilder();
        for (String item : report) {
            if (item.contains(FIO)) rep.append(item).append('\n');
        }
        return rep.toString();
    }


    /*------------ Операции -------------*/

    @Override
    public void actionPerformed (String action) { report.add(action); }
}

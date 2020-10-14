package models;

import java.util.ArrayList;

interface EmployeeListener {
    void actionPerformed (String action);
}

public class Management implements EmployeeListener {

    /*------------ Свойства -------------*/

    private final ArrayList<String> report = new ArrayList<String>();

    /*------------ Геттеры -------------*/

    public String getReport () { return String.join("\n", report); }
    public String getLastAction () { return report.get(report.size() - 1); }


    /*------------ Операции -------------*/

    @Override
    public void actionPerformed (String action) { report.add(action); }
}

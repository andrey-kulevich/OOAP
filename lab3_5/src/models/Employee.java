package models;

public class Employee {
    private String FIO;
    private Card IdCard;
    private Education education;
    private Department department;

    public Employee(String FIO, Card IdCard, Education education, Department department) {

    }

    public Card getIdCard() {
        return IdCard;
    }

    public Education getEducation() {
        return education;
    }

    public String getFIO() {
        return FIO;
    }

    public void fireFromCompany() {

    }

    public void transferTo(Department dep) {

    }
}

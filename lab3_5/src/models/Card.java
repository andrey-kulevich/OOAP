package models;

public class Card {

    /*------------ Свойства -------------*/

    private static int freeNumber = 1;
    private final int number;
    private boolean isActive;

    /*------------ Конструктор -------------*/

    public Card() {
        isActive = true;
        number = freeNumber;
        freeNumber++;
    }

    /*------------ Геттеры -------------*/

    public int getNumber() {
        return number;
    }

    /*------------ Сеттеры -------------*/

    public void setActive(boolean active) { isActive = active; }
}

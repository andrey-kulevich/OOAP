package models;

/** Карточка работника */
public class Card {

    /*------------ Свойства -------------*/
    /** Свободный номер карточки */
    private static int freeNumber = 1;
    /** Номер карточки */
    private final int number;

    /*------------ Конструктор -------------*/

    /** Конструктор карточки работника */
    public Card() {
        number = freeNumber;
        freeNumber++;
    }

    /*------------ Геттеры -------------*/

    /** Получить номер карточки
     *
     * @return номер карточки
     */
    public int getNumber() { return number; }

    /*------------ Сеттеры -------------*/

    /** Установить свободный номер карточки
     *
     * @param freeNumber свободный номер
     */
    public static void setFreeNumber(int freeNumber) { Card.freeNumber = freeNumber; }
}

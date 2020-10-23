package models;

/** Карточка работника */
public class Card {

    /*------------ Свойства -------------*/
    /** Свободный номер карточки */
    private static int freeNumber = 0;
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
    public static void setFreeNumber(int freeNumber) {
        if (freeNumber >= 0) Card.freeNumber = freeNumber;
        else throw new IllegalArgumentException("Номер карточки не может быть отрицательным");
    }
}

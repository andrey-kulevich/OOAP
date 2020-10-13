package models;

public class Card {
    private final int number;

    public Card(int number) {
        if (number >= 0) this.number = number;
        else throw new IllegalArgumentException("Номер каточки не может быть отрицательным");
    }

    public int getNumber() {
        return number;
    }
}

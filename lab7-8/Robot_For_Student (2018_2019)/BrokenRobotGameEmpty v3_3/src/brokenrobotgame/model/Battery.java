package brokenrobotgame.model;

import brokenrobotgame.model.navigation.CellPosition;

/**
 * Battery - обладает зарядом, который может отдавать другим устройствам
 */
public class Battery extends GameObject<CellPosition> {

    /** батарея подлежит использованию */
    private boolean isDestroy = false;
    /** емкость в условных единицах */
    private final int chargeCapacity;
    /** заряд в условных единицах */
    private int amountOfCharge;

    /** Конструктор
     *
     * @param field игровое поле
     * @param capacity емкость батареи
     * @param amount заряд батареи
     */
    public Battery(GameField field, int capacity, int amount) {
        if (amount > capacity)
            throw new IllegalArgumentException("Amount of charge cannot be more than capacity");
        setField(field);
        chargeCapacity = capacity;
        amountOfCharge = amount;
    }

    /** Уничтожить батарею */
    public void destroy() {
        amountOfCharge = 0;
        isDestroy = true;
    }

    /** Получить емкость батареи
     *
     * @return емкость
     */
    public int chargeCapacity() {
        if (isDestroy) throw new RuntimeException("Battery has destroyed");
        return chargeCapacity;
    }

    /** Получить заряд батареи
     *
     * @return заряд
     */
    public int amountOfCharge() {
        if (isDestroy) throw new RuntimeException("Battery has destroyed");
        return amountOfCharge;
    }

    /** Уменьшить заряд батареи
     *
     * @param delta на сколько уменьшить заряд
     */
    public void reduceCharge(int delta) {
        if (isDestroy) throw new RuntimeException("Батарея уничтожена");
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        amountOfCharge -= delta;
	    if(amountOfCharge < 0) amountOfCharge = 0;
    }
}

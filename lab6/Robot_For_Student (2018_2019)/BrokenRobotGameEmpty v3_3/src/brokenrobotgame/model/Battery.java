package brokenrobotgame.model;

import brokenrobotgame.model.navigation.CellPosition;

/**
 * Battery - обладает зарядом, который может отдавать другим устройствам
 */
public class Battery {

    /** батарея подлежит использованию */
    private boolean _isDestroy = false;
    /** игровое поле */
    private final GameField _field;
    /** емкость в условных единицах */
    private final int _chargeCapacity;
    /** заряд в условных единицах */
    private int _amountOfCharge;
    /** позиция батареи */
    private CellPosition _position;

    /** Конструктор
     *
     * @param field игровое поле
     * @param capacity емкость батареи
     * @param amount заряд батареи
     */
    public Battery(GameField field, int capacity, int amount) {
        if (field == null) throw new NullPointerException();
        if (amount > capacity) throw new IllegalArgumentException("Amount of charge cannot be more than capacity");
        
        _field = field;
        _chargeCapacity = capacity;
        _amountOfCharge = amount;
    }

    /** Уничтожить батарею */
    public void destroy() {
        _amountOfCharge = 0;
        _isDestroy = true;
    }

    // ---------------------- Заряд батареи ---------------------------

    /** Получить емкость батареи
     *
     * @return емкость
     */
    public int chargeCapacity() {
        if (_isDestroy) throw new RuntimeException("Battery has destroyed");
        return _chargeCapacity;
    }

    /** Получить заряд батареи
     *
     * @return заряд
     */
    public int amountOfCharge() {
        if (_isDestroy) throw new RuntimeException("Battery has destroyed");
        return _amountOfCharge;
    }

    /** Уменьшить заряд батареи
     *
     * @param delta на сколько уменьшить заряд
     */
    public void reduceCharge(int delta) {
        if (_isDestroy) throw new RuntimeException("Батарея уничтожена");
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        _amountOfCharge -= delta;
	    if(_amountOfCharge < 0) _amountOfCharge = 0;
    }
    
    // ----------------------- Позиция батарейки -------------------------

    /** Получить позицию батареи
     *
     * @return позиция
     */
    public CellPosition position() {
        if (_isDestroy) throw new RuntimeException("Батарея уничтожена");
        return _position;
    }

    /** Установить позицию батареи
     *
     * @param pos позиция
     * @return успешность установки позиции
     */
    public boolean setPosition(CellPosition pos) {

        if (pos == null) { // батарейка вне поля
            _position = null;
            return true;
        } else if (_field.battery(pos) != null) { // позиция уже занята другой батарейкой
            return false;
        } else { // позиция свободна
            _position = pos;
            return true;
        }
    }
}

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
    private int _chargeCapacity = 100;
    /** заряд в условных единицах */
    private int _amountOfCharge = _chargeCapacity;
    /** позиция батареи */
    private CellPosition _position;

    /** Конструктор
     *
     * @param field игровое поле
     * @param capacity емкость батареи
     * @param amount заряд батареи
     */
    public Battery(GameField field, int capacity, int amount) {
        // TODO исключение, если заряд больше емкости
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
     * @throws Exception если батарея уничтожена
     */
    public int chargeCapacity() throws Exception {
        // TODO исключение, если батарея уничтожена
        if (_isDestroy) throw new Exception("Battery has destroyed");
        return _chargeCapacity;
    }

    /** Получить заряд батареи
     *
     * @return заряд
     * @throws Exception если батарея уничтожена
     */
    public int amountOfCharge() throws Exception {
        // TODO исключение, если батарея уничтожена
        if (_isDestroy) throw new Exception("Battery has destroyed");
        return _amountOfCharge;
    }

    /** Уменьшить заряд батареи
     *
     * @param delta на сколько уменьшить заряд
     * @throws Exception если батарея уничтожена
     */
    public void reduceCharge(int delta) throws Exception {
        // TODO исключение, если батарея уничтожена
        if (_isDestroy) throw new Exception("Батарея уничтожена");
        // TODO исключение, если delta не положительное
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        _amountOfCharge -= delta;
	    if(_amountOfCharge < 0) _amountOfCharge = 0;
    }
    
    // ----------------------- Позиция батарейки -------------------------

    /** Получить позицию батареи
     *
     * @return позиция
     * @throws Exception если батарея уничтожена
     */
    public CellPosition position() throws Exception {
        // TODO исключение, если батарея уничтожена
        if (_isDestroy) throw new Exception("Батарея уничтожена");
        return _position;
    }

    /** Установить позицию батареи
     *
     * @param pos позиция
     * @return успешность установки позиции
     */
    boolean setPosition(CellPosition pos) {

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

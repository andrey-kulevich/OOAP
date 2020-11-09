package brokenrobotgame.model;

import brokenrobotgame.model.navigation.CellPosition;

/**
 * Battery - �������� �������, ������� ����� �������� ������ �����������
 */
public class Battery {

    /** ������� �������� ������������� */
    private boolean _isDestroy = false;
    /** ������� ���� */
    private final GameField _field;
    /** ������� � �������� �������� */
    private final int _chargeCapacity;
    /** ����� � �������� �������� */
    private int _amountOfCharge;
    /** ������� ������� */
    private CellPosition _position;

    /** �����������
     *
     * @param field ������� ����
     * @param capacity ������� �������
     * @param amount ����� �������
     */
    public Battery(GameField field, int capacity, int amount) {
        if (field == null) throw new NullPointerException();
        if (amount > capacity) throw new IllegalArgumentException("Amount of charge cannot be more than capacity");
        
        _field = field;
        _chargeCapacity = capacity;
        _amountOfCharge = amount;
    }

    /** ���������� ������� */
    public void destroy() {
        _amountOfCharge = 0;
        _isDestroy = true;
    }

    // ---------------------- ����� ������� ---------------------------

    /** �������� ������� �������
     *
     * @return �������
     */
    public int chargeCapacity() {
        if (_isDestroy) throw new RuntimeException("Battery has destroyed");
        return _chargeCapacity;
    }

    /** �������� ����� �������
     *
     * @return �����
     */
    public int amountOfCharge() {
        if (_isDestroy) throw new RuntimeException("Battery has destroyed");
        return _amountOfCharge;
    }

    /** ��������� ����� �������
     *
     * @param delta �� ������� ��������� �����
     */
    public void reduceCharge(int delta) {
        if (_isDestroy) throw new RuntimeException("������� ����������");
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        _amountOfCharge -= delta;
	    if(_amountOfCharge < 0) _amountOfCharge = 0;
    }
    
    // ----------------------- ������� ��������� -------------------------

    /** �������� ������� �������
     *
     * @return �������
     */
    public CellPosition position() {
        if (_isDestroy) throw new RuntimeException("������� ����������");
        return _position;
    }

    /** ���������� ������� �������
     *
     * @param pos �������
     * @return ���������� ��������� �������
     */
    public boolean setPosition(CellPosition pos) {

        if (pos == null) { // ��������� ��� ����
            _position = null;
            return true;
        } else if (_field.battery(pos) != null) { // ������� ��� ������ ������ ����������
            return false;
        } else { // ������� ��������
            _position = pos;
            return true;
        }
    }
}

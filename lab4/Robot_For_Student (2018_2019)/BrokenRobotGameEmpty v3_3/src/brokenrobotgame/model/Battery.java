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
    private int _chargeCapacity = 100;
    /** ����� � �������� �������� */
    private int _amountOfCharge = _chargeCapacity;
    /** ������� ������� */
    private CellPosition _position;

    /** �����������
     *
     * @param field ������� ����
     * @param capacity ������� �������
     * @param amount ����� �������
     */
    public Battery(GameField field, int capacity, int amount) {
        // TODO ����������, ���� ����� ������ �������
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
     * @throws Exception ���� ������� ����������
     */
    public int chargeCapacity() throws Exception {
        // TODO ����������, ���� ������� ����������
        if (_isDestroy) throw new Exception("Battery has destroyed");
        return _chargeCapacity;
    }

    /** �������� ����� �������
     *
     * @return �����
     * @throws Exception ���� ������� ����������
     */
    public int amountOfCharge() throws Exception {
        // TODO ����������, ���� ������� ����������
        if (_isDestroy) throw new Exception("Battery has destroyed");
        return _amountOfCharge;
    }

    /** ��������� ����� �������
     *
     * @param delta �� ������� ��������� �����
     * @throws Exception ���� ������� ����������
     */
    public void reduceCharge(int delta) throws Exception {
        // TODO ����������, ���� ������� ����������
        if (_isDestroy) throw new Exception("������� ����������");
        // TODO ����������, ���� delta �� �������������
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        _amountOfCharge -= delta;
	    if(_amountOfCharge < 0) _amountOfCharge = 0;
    }
    
    // ----------------------- ������� ��������� -------------------------

    /** �������� ������� �������
     *
     * @return �������
     * @throws Exception ���� ������� ����������
     */
    public CellPosition position() throws Exception {
        // TODO ����������, ���� ������� ����������
        if (_isDestroy) throw new Exception("������� ����������");
        return _position;
    }

    /** ���������� ������� �������
     *
     * @param pos �������
     * @return ���������� ��������� �������
     */
    boolean setPosition(CellPosition pos) {

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

package brokenrobotgame.model;

import brokenrobotgame.model.navigation.CellPosition;

/**
 * Battery - �������� �������, ������� ����� �������� ������ �����������
 */
public class Battery extends GameObject<CellPosition> {

    /** ������� �������� ������������� */
    private boolean isDestroy = false;
    /** ������� � �������� �������� */
    private final int chargeCapacity;
    /** ����� � �������� �������� */
    private int amountOfCharge;

    /** �����������
     *
     * @param field ������� ����
     * @param capacity ������� �������
     * @param amount ����� �������
     */
    public Battery(GameField field, int capacity, int amount) {
        if (amount > capacity)
            throw new IllegalArgumentException("Amount of charge cannot be more than capacity");
        setField(field);
        chargeCapacity = capacity;
        amountOfCharge = amount;
    }

    /** ���������� ������� */
    public void destroy() {
        amountOfCharge = 0;
        isDestroy = true;
    }

    /** �������� ������� �������
     *
     * @return �������
     */
    public int chargeCapacity() {
        if (isDestroy) throw new RuntimeException("Battery has destroyed");
        return chargeCapacity;
    }

    /** �������� ����� �������
     *
     * @return �����
     */
    public int amountOfCharge() {
        if (isDestroy) throw new RuntimeException("Battery has destroyed");
        return amountOfCharge;
    }

    /** ��������� ����� �������
     *
     * @param delta �� ������� ��������� �����
     */
    public void reduceCharge(int delta) {
        if (isDestroy) throw new RuntimeException("������� ����������");
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        amountOfCharge -= delta;
	    if(amountOfCharge < 0) amountOfCharge = 0;
    }
}

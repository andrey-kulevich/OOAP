package brokenrobotgame.model;

/** �����, ������� ����� ��������� */
public class DestroyableDoor extends Door implements Destroyable {

    /** ����� ��������� ����� */
    private int durability;

    /** �����������
     *
     * @param field ������� ����
     */
    public DestroyableDoor(GameField field) {
        super(field);
        durability = 2;
    }

    @Override
    public boolean isDestroyed() { return durability == 0; }

    @Override
    public void destroy() {
        durability = 0;
        field.removeObject(this);
    }

    @Override
    public void hit(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative");
        durability -= damage;
        if (durability <= 0) destroy();
    }

    @Override
    public int getDurabilityRest() {
        return durability;
    }
}

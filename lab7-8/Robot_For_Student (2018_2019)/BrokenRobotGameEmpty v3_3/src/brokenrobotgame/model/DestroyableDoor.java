package brokenrobotgame.model;

/** Дверь, которую можно разрушить */
public class DestroyableDoor extends Door implements Destroyable {

    /** Запас прочности двери */
    private int durability;

    /** Конструктор
     *
     * @param field игровое поле
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
        unsetField();
    }

    @Override
    public void hit(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative");
        durability -= damage;
        if (durability <= 0) destroy();
    }
}

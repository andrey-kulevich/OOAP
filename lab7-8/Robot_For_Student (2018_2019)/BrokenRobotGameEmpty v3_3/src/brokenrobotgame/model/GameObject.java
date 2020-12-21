package brokenrobotgame.model;

/** Абстрактый объект на игровом поле
 *
 * @param <TPosition> позиция объекта (может находиться в ячейке или на границе ячейки)
 */
public abstract class GameObject <TPosition> {

    /** игровое поле */
    protected GameField field;
    /** позиция объекта */
    protected TPosition position;

    /** Вернуть позицию объекта на поле
     *
     * @return позиция
     */
    public TPosition position() { return position; }

    /** Установить позицию объекта на поле
     *
     * @param pos позиция
     * @return успешность установки
     */
    public boolean setPosition(TPosition pos) {
        if (pos == null) throw new NullPointerException();
        if(field.getObject(pos) == null) {
            position = pos;
            return true;
        }
        return false;
    }

    /** Установить игровое поле
     *
     * @param field игровое поле
     */
    public void setField(GameField field) {
        if(this.field != null) throw new RuntimeException("Field has already set");
        if(field == null) throw new NullPointerException();
        this.field = field;
    }

    /** Отвязать игровое поле от объекта */
    public void unsetField() {
        if(field.contains(this)) throw new RuntimeException("Object is still on field");
        field = null;
        position = null;
    }
}

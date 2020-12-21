package brokenrobotgame.model;

/** ���������� ������ �� ������� ����
 *
 * @param <TPosition> ������� ������� (����� ���������� � ������ ��� �� ������� ������)
 */
public abstract class GameObject <TPosition> {

    /** ������� ���� */
    protected GameField field;
    /** ������� ������� */
    protected TPosition position;

    /** ������� ������� ������� �� ����
     *
     * @return �������
     */
    public TPosition position() { return position; }

    /** ���������� ������� ������� �� ����
     *
     * @param pos �������
     * @return ���������� ���������
     */
    public boolean setPosition(TPosition pos) {
        if (pos == null) throw new NullPointerException();
        if(field.getObject(pos) == null) {
            position = pos;
            return true;
        }
        return false;
    }

    /** ���������� ������� ����
     *
     * @param field ������� ����
     */
    public void setField(GameField field) {
        if(this.field != null) throw new RuntimeException("Field has already set");
        if(field == null) throw new NullPointerException();
        this.field = field;
    }

    /** �������� ������� ���� �� ������� */
    public void unsetField() {
        if(field.contains(this)) throw new RuntimeException("Object is still on field");
        field = null;
        position = null;
    }
}

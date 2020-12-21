package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/** Door - ����� ������ ����� ������ ������� ����� */
public class Door extends GameObject<MiddlePosition> {

    /** ����� � ������������ ������� */
    public static final int VERTICAL = 1;
    /** ����� � �������������� ������� */
    public static final int HORIZONTAL = 2;
    /** ��������� ����� */
    private boolean isOpen = false;

    /** �����������
     *
     * @param field ������� ����
     */
    public Door(GameField field) { setField(field); }

    /** �������� ���������� ����� �� ����
     *
     * @return 1 - ������������, 2 - ��������������
     */
    public int orientation() {
        Direction direct = position.direction();
        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;
        return -1;
    }

    /** ������� ����� */
    public void open() { isOpen = true; }
    /** ������� ����� */
    public void close() { isOpen = false; }
    /** ������� �� ����� */
    public boolean isOpen() { return isOpen; }
}

package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/**
 * Door - ����� ������ ����� ������ ������� �����
 */
public class Door {

    /** ����� � ������������ ������� */
    public  static final int VERTICAL = 1;
    /** ����� � �������������� ������� */
    public  static final int HORIZONTAL = 2;
    /** ������� ���� */
    private final GameField _field;
    /** ������� ����� */
    private MiddlePosition _position;
    /** ��������� ����� */
    private boolean _isOpen = false;

    // ------------------- ������������� ����� � ������� ����� -----------------

    /** �����������
     *
     * @param field ������� ����
     */
    public Door(GameField field) {
        _field = field;
    }

    // ----------------------- ������� ����� -------------------------

    /** �������� ������� �����
     *
     * @return �������
     */
    public MiddlePosition position() { return _position; }

    /** ������ ������� �����
     *
     * @param pos �������
     * @return ���������� ��������� (false, ���� �� ���� ������� ���� ������ ����� ��� �����)
     */
    public boolean setPosition(MiddlePosition pos) {
        
        if(!_field.isWall(pos) && _field.door(pos)== null) { // ������� ��������
            _position = pos;
            return true;
        }
        return false;
    }
    
    // ----------------------- ���������� ����� -------------------------

    /** �������� ���������� ����� �� ����
     *
     * @return 1 - ������������, 2 - ��������������
     */
    public int orientation() {
        Direction direct = _position.direction();

        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;

        return -1;
    }  
    
    // ---------------------- ����������/���������� ����� ----------------------

    /** ������� ����� */
    public void open() { _isOpen = true; }
    /** ������� ����� */
    public void close() { _isOpen = false; }
    /** ������� �� ����� */
    public boolean isOpen() { return _isOpen; }
}

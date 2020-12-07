package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/*
 * WallPiece - ������� ����� ������ �� ����� ����� ������
 */
class WallPiece
{
    // ------------------- ������������� ����� � ������� ����� -----------------
    private final GameField _field;

    public WallPiece(GameField field) {
        _field = field;
    }

    // ----------------------- ������� ����� -------------------------

    private MiddlePosition _position;
    
    public MiddlePosition position(){
        return _position;
    }

    boolean setPosition(MiddlePosition pos){
        
        if(!_field.isWall(pos))                  // ������� ��������
        {
            _position = pos;
            return true;
        }
        
        return false;
    }
    
    // ----------------------- ���������� ����� -------------------------
    
    public  static final int VERTICAL = 1; 
    public  static final int HORIZONTAL = 2; 
    
    public int orientation()
    {
        Direction direct = position().direction();

        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;

        // TODO ����������
        return -1;
    }
}

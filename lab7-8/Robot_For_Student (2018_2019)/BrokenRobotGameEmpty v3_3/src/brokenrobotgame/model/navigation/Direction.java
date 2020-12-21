package brokenrobotgame.model.navigation;

/*
 * Direction - ���������� ����������� � ������� ��������� "�����-��-������-�����"; 
 * ��������� ���������� ����������� � ��������� ����� ����������� ������������ 
 * ��������
 */
public class Direction {
    
    // ���������� ����������� ��� ���� � �������� �� 0 �� 360
    private int _angle = 90;

    private Direction(int angle) {
        // �������� �������� ���� � ����������� ��������� 
        angle = angle%360;
        if(angle < 0)    angle += 360;
        
        this._angle = angle;
    }
    
    // ------------------ ��������� ����������� ---------------------
    
    public static Direction north()
    { return new Direction(90); }
    
    public static Direction south()
    { return new Direction(270); }

    public static Direction east()
    { return new Direction(0); }

    public static Direction west()
    { return new Direction(180); }
    
  
    // ------------------ ����� ����������� ---------------------
    
    @Override
    public Direction clone(){ 
        return new Direction(this._angle); 
    }
  
    public Direction clockwise() { 
        return new Direction(this._angle-90); 
    }
    
    public Direction anticlockwise() { 
        return new Direction(this._angle+90); 
    }
    
    public Direction opposite() { 
        return new Direction(this._angle+180); 
    }
    
    public Direction rightword()  { 
        return clockwise(); 
    }
    
    public Direction leftword()  { 
        return anticlockwise(); 
    }
    
    // ------------------ �������� ����������� ---------------------
    
    @Override
    public boolean equals(Object other) {

        if(other instanceof Direction) {
            // ���� ����������, ����� �������� ��������������
            Direction otherDirect = (Direction)other;
            // ���������� ��������� ��������� �����
            return  _angle == otherDirect._angle;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return this._angle;
    }
    
    public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }
}
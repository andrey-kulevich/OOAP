package brokenrobotgame.model;

import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/**
 * GameField - ���������� ����, ���������� �� �����;  
 * ��������� ��� ������� ������ (������, ���� � ��������)
 */
public class GameField {

    /** ����� */
    private Robot robot;
    /** ����� */
    private final ArrayList<WallPiece> _wallPool = new ArrayList<>();
    /** ����� */
    private final ArrayList<Door> _doorPool = new ArrayList<>();
    /** ��������� */
    private final ArrayList<Battery> _batteryPool = new ArrayList<>();

    // ------------------------------ ������� ---------------------------

    /** ����������� */
    public GameField () {
        setSize(10, 10);
    }

    /** ���������� ������ �������� ����
     *
     * @param width ������
     * @param height ������
     */
    public final void setSize(int width, int height) {
        CellPosition.setHorizontalRange(1, width);
        CellPosition.setVerticalRange(1, height);
    }

    /** �������� ������ ����
     *
     * @return ������
     */
    public int width() {
        return CellPosition.horizontalRange().length();
    }

    /** �������� ������ ����
     *
     * @return ������
     */
    public int height() {
        return CellPosition.verticalRange().length();
    }
	
    // ---------------------------- ����� ----------------------------

    /** ���������� ������
     *
     * @param robot �����
     */
    public void setRobot(Robot robot) {
        if (robot == null) throw new NullPointerException();
        this.robot = robot;
    }

    /** �������� ������
     *
     * @return �����
     */
    public Robot getRobot() { return robot; }

    // ---------------------------- ����� ----------------------------

    /** ���������, ��������� �� �� ������ ������� �����
     *
     * @param pos ������� �� ����
     * @return ���������� �����
     */
    public boolean isWall(MiddlePosition pos) {
        
        for (WallPiece obj : _wallPool) {
            if(obj.position().equals(pos))  return true;
        }
        return false;
    }

    /** �������� ����� �� ����
     *
     * @param pos �������
     * @param obj �����
     * @return ���������� ���������� �����
     */
    public boolean addWall(MiddlePosition pos, WallPiece obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _wallPool.add(obj);
        return success;
    }

    // ---------------------------- ����� ----------------------------

    /** ������� �����, ����������� �� ������ �������
     *
     * @param pos �������
     * @return ������� �����, ���� ��� ���� �� ���� �������, ����� null
     */
    public Door door(MiddlePosition pos) {
        
        for (Door obj : _doorPool) {
            if(obj.position().equals(pos))  return obj;
        }
        return null;
    }

    /** �������� ����� �� ����
     *
     * @param pos �������
     * @param obj �����
     * @return ���������� ����������
     */
    public boolean addDoor(MiddlePosition pos, Door obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _doorPool.add(obj);
        return success;
    }
    
    // ---------------------------- ��������� ----------------------------

    /** ������� �������, ���� ��� ���� �� ������ �������
     *
     * @param pos �������
     * @return ������� �������, ���� ��� ���� �� ���� �������, ����� null
     */
    public Battery battery(CellPosition pos) {
        
        for (Battery obj : _batteryPool) {
            try {
                if(obj.position().equals(pos))  return obj;
            } catch (Exception ignored) { }
        }
        return null;
    }

    /** �������� ������� �� ����
     *
     * @param pos �������
     * @param obj �������
     * @return ���������� ���������� ��������
     */
    public boolean addBattery(CellPosition pos, Battery obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _batteryPool.add(obj);
        return success;
    }

    /** ������ ������� � ����
     *
     * @param obj �������
     * @return ���������� ��������
     */
    public boolean removeBattery(Battery obj) {
        
        boolean success = _batteryPool.remove(obj);
        if(success) obj.setPosition(null);
        return success;
    }
    
    // ---------------------------- ������� ----------------------------

    /** �������� ���� */
    public void clear() {
        _wallPool.clear();
        _batteryPool.clear();
        _doorPool.clear();
        robot = null;
    }
}
package brokenrobotgame.model;

import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;
import brokenrobotgame.model.quantities.TemperatureKelvin;

import java.util.ArrayList;
import java.util.HashMap;


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
    /** ������ �������� � ������ �� ������ */
    private final HashMap<CellPosition, RadiationSievert> _radioPollutionPull = new HashMap<>();
    /** ����������� � ������ �� ������ */
    private final HashMap<CellPosition, TemperatureKelvin> _temperatureLevelPull = new HashMap<>();
    /** ������ ����������� ������ */
    private final ArrayList<CellPosition> _paintedCells = new ArrayList<>();

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
    public int width() { return CellPosition.horizontalRange().length(); }

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
        
        for (Battery obj : _batteryPool) { if(obj.position().equals(pos))  return obj; }
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

    //----------------------------- ������� �������� -------------------------------

    /** �������� ������� �������� � ������ �������
     *
     * @param pos �������
     * @return ������� �������� � ��������
     */
    public RadiationSievert getRadiation(CellPosition pos) { return _radioPollutionPull.get(pos); }

    /** ���������� ������� �������� � ������ �������
     *
     * @param pos �������
     * @param rad ������� ��������
     */
    public void setRadiationToCell(CellPosition pos, RadiationSievert rad) {
        _radioPollutionPull.put(pos, rad);
    }

    //----------------------------- ����������� -------------------------------

    /** �������� ����������� � ������ �������
     *
     * @param pos �������
     * @return �����������
     */
    public TemperatureKelvin getTemperature(CellPosition pos) { return _temperatureLevelPull.get(pos); }

    /** ���������� ������� ����������� � ������ �������
     *
     * @param pos �������
     * @param temp �����������
     */
    public void setTemperatureToCell(CellPosition pos, TemperatureKelvin temp) {
        _temperatureLevelPull.put(pos, temp);
    }

    //----------------------------- ����������� ������ -------------------------------

    /** ���������, ��������� �� ������ ������
     *
     * @param pos �������
     * @return ��������� �� ������
     */
    public boolean isCellPainted(CellPosition pos) { return _paintedCells.contains(pos); }

    /** ��������� ������
     *
     * @param pos �������
     * @return true, ���� ��� �������, false, ���� ������ ��� ���������
     */
    public boolean paintCell(CellPosition pos) {
        if (isCellPainted(pos)) return false;
        _paintedCells.add(pos);
        return true;
    }

    /** �������� ������
     *
     * @param pos �������
     * @return true, ���� ��� �������, false, ���� ������ ��� ������
     */
    public boolean clearCell(CellPosition pos) {
        if (!isCellPainted(pos)) return false;
        _paintedCells.remove(pos);
        return true;
    }

    // ---------------------------- ������� ----------------------------

    /** �������� ���� */
    public void clear() {
        _wallPool.clear();
        _batteryPool.clear();
        _doorPool.clear();
        _paintedCells.clear();
        _radioPollutionPull.clear();
        _temperatureLevelPull.clear();
        robot = null;
    }
}
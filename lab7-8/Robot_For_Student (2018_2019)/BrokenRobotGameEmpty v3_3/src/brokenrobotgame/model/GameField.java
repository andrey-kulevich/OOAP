package brokenrobotgame.model;

import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;
import brokenrobotgame.model.quantities.TemperatureKelvin;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * GameField - ���������� ����, ���������� �� �����;  
 * ��������� ��� ������� ������ (������, ���� � ��������)
 */
public class GameField <TPosition> {

    /** ����� */
    private Robot robot;
    /** ������� ������� (�����, �����, ��������� � ��) */
    private final HashMap<Class, ArrayList<GameObject>> objects = new HashMap<>();
    /** ������ �������� � ������ �� ������ */
    private final HashMap<CellPosition, RadiationSievert> _radioPollutionPull = new HashMap<>();
    /** ����������� � ������ �� ������ */
    private final HashMap<CellPosition, TemperatureKelvin> _temperatureLevelPull = new HashMap<>();
    /** ������ ����������� ������ */
    private final ArrayList<CellPosition> _paintedCells = new ArrayList<>();

    // ------------------------------ ������� ---------------------------

    /** ����������� */
    public GameField () { setSize(10, 10); }

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

    //----------------------------- ���������� ��������� �� ���� -------------------

    /** �������� ��� ������� �� ����
     *
     * @return ��� ������� �� ����
     */
    public ArrayList<GameObject> objects() {
        ArrayList<GameObject> totalObjList = new ArrayList<>();
        for (ArrayList<GameObject> objList : objects.values()) totalObjList.addAll(objList);
        return totalObjList;
    }

    /** �������� ��� ������� ��������� ����
     *
     * @param objType ��� �������
     * @return �������
     */
    public ArrayList<GameObject> objects(Class objType) { return new ArrayList<>(objects.get(objType)); }

    /** �������� ������ �� �������� �������
     *
     * @param pos �������
     * @return ������ (null, ���� ������� ��������)
     */
    public GameObject getObject(TPosition pos) {
        for(ArrayList<GameObject> objList : objects.values()) {
            for(GameObject obj : objList) {
                if(obj.position().equals(pos)) return obj;
            }
        }
        return null;
    }

    /** ���������, �������� �� ���� ������ ������
     *
     * @param obj ������
     * @return ���� �� ������ ������ �� ����
     */
    public boolean contains(GameObject obj) { return objects().contains(obj); }

    /** �������� ������ �� ����
     *
     * @param obj ������
     * @return ���������� ����������
     */
    public boolean addObject(GameObject obj) {
        Class objClass = obj.getClass();
        if (obj.position() == null) throw new RuntimeException("Object must have position on field");
        if( objects.containsKey(objClass)) {
            return objects.get(objClass).add(obj);
        } else {
            ArrayList<GameObject> objList = new ArrayList<>();
            objList.add(obj);
            objects.put(objClass, objList);
            return true;
        }
    }

    /** ������ ������ � ����
     *
     * @param obj ������
     */
    public void removeObject(GameObject obj) {
        Class objClass = obj.getClass();

        if(objects.containsKey(objClass)) {
            objects.get(objClass).remove(obj);
            obj.unsetField();
        }
    }

    /** �������� ���� */
    public void clear() {
        objects.clear();
        for(GameObject obj : objects()) obj.unsetField();
        _paintedCells.clear();
        _radioPollutionPull.clear();
        _temperatureLevelPull.clear();
        robot.unsetField();
        robot = null;
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
}
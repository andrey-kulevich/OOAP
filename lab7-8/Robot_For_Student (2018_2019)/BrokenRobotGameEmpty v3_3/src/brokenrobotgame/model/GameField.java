package brokenrobotgame.model;

import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;
import brokenrobotgame.model.quantities.TemperatureKelvin;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * GameField - абстракция поля, состоящего из ячеек;  
 * контейнер для игровых юнитов (робота, стен и батареек)
 */
public class GameField <TPosition> {

    /** робот */
    private Robot robot;
    /** игровые объекты (стены, двери, батарейки и тд) */
    private final HashMap<Class, ArrayList<GameObject>> objects = new HashMap<>();
    /** уровни радиации в каждой из клеток */
    private final HashMap<CellPosition, RadiationSievert> _radioPollutionPull = new HashMap<>();
    /** температура в каждой из клеток */
    private final HashMap<CellPosition, TemperatureKelvin> _temperatureLevelPull = new HashMap<>();
    /** список закрашенных клеток */
    private final ArrayList<CellPosition> _paintedCells = new ArrayList<>();

    // ------------------------------ Размеры ---------------------------

    /** Конструктор */
    public GameField () { setSize(10, 10); }

    /** Установить размер игрового поля
     *
     * @param width ширина
     * @param height высота
     */
    public final void setSize(int width, int height) {
        CellPosition.setHorizontalRange(1, width);
        CellPosition.setVerticalRange(1, height);
    }

    /** Получить ширину поля
     *
     * @return ширина
     */
    public int width() { return CellPosition.horizontalRange().length(); }

    /** Получить высоту поля
     *
     * @return высота
     */
    public int height() {
        return CellPosition.verticalRange().length();
    }
	
    // ---------------------------- Робот ----------------------------

    /** Установить робота
     *
     * @param robot робот
     */
    public void setRobot(Robot robot) {
        if (robot == null) throw new NullPointerException();
        this.robot = robot;
    }

    /** Получить робота
     *
     * @return робот
     */
    public Robot getRobot() { return robot; }

    //----------------------------- Управление объектами на поле -------------------

    /** Получить все объекты на поле
     *
     * @return все объекты на поле
     */
    public ArrayList<GameObject> objects() {
        ArrayList<GameObject> totalObjList = new ArrayList<>();
        for (ArrayList<GameObject> objList : objects.values()) totalObjList.addAll(objList);
        return totalObjList;
    }

    /** Получить все объекты заданного типа
     *
     * @param objType тип объекта
     * @return объекты
     */
    public ArrayList<GameObject> objects(Class objType) { return new ArrayList<>(objects.get(objType)); }

    /** Получить объект на заданной позиции
     *
     * @param pos позиция
     * @return объект (null, если позиция свободна)
     */
    public GameObject getObject(TPosition pos) {
        for(ArrayList<GameObject> objList : objects.values()) {
            for(GameObject obj : objList) {
                if(obj.position().equals(pos)) return obj;
            }
        }
        return null;
    }

    /** Проверить, содержит ли поле данный объект
     *
     * @param obj объект
     * @return есть ли данный объект на поле
     */
    public boolean contains(GameObject obj) { return objects().contains(obj); }

    /** Добавить объект на поле
     *
     * @param obj объект
     * @return успешность добавления
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

    /** Убрать объект с поля
     *
     * @param obj объект
     */
    public void removeObject(GameObject obj) {
        Class objClass = obj.getClass();

        if(objects.containsKey(objClass)) {
            objects.get(objClass).remove(obj);
            obj.unsetField();
        }
    }

    /** Очистить поле */
    public void clear() {
        objects.clear();
        for(GameObject obj : objects()) obj.unsetField();
        _paintedCells.clear();
        _radioPollutionPull.clear();
        _temperatureLevelPull.clear();
        robot.unsetField();
        robot = null;
    }

    //----------------------------- Уровень радиации -------------------------------

    /** Получить уровень радиации в данной позиции
     *
     * @param pos позиция
     * @return уровень радиации в Зивертах
     */
    public RadiationSievert getRadiation(CellPosition pos) { return _radioPollutionPull.get(pos); }

    /** Установить уровень радиации в данной позиции
     *
     * @param pos позиция
     * @param rad уровень радиации
     */
    public void setRadiationToCell(CellPosition pos, RadiationSievert rad) {
        _radioPollutionPull.put(pos, rad);
    }

    //----------------------------- Температура -------------------------------

    /** Получить температуру в данной позиции
     *
     * @param pos позиция
     * @return температура
     */
    public TemperatureKelvin getTemperature(CellPosition pos) { return _temperatureLevelPull.get(pos); }

    /** Установить уровень температуры в данной позиции
     *
     * @param pos позиция
     * @param temp температура
     */
    public void setTemperatureToCell(CellPosition pos, TemperatureKelvin temp) {
        _temperatureLevelPull.put(pos, temp);
    }

    //----------------------------- Закрашенные клетки -------------------------------

    /** Проверить, закрашена ли данная клетка
     *
     * @param pos позиция
     * @return закрашена ли клетка
     */
    public boolean isCellPainted(CellPosition pos) { return _paintedCells.contains(pos); }

    /** Закрасить клетку
     *
     * @param pos позиция
     * @return true, если все успешно, false, если клетка уже закрашена
     */
    public boolean paintCell(CellPosition pos) {
        if (isCellPainted(pos)) return false;
        _paintedCells.add(pos);
        return true;
    }

    /** Очистить клетку
     *
     * @param pos позиция
     * @return true, если все успешно, false, если клетка уже чистая
     */
    public boolean clearCell(CellPosition pos) {
        if (!isCellPainted(pos)) return false;
        _paintedCells.remove(pos);
        return true;
    }
}
package brokenrobotgame.model;

import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/**
 * GameField - абстракция поля, состоящего из ячеек;  
 * контейнер для игровых юнитов (робота, стен и батареек)
 */
public class GameField {

    /** робот */
    private Robot robot;
    /** стены */
    private final ArrayList<WallPiece> _wallPool = new ArrayList<>();
    /** двери */
    private final ArrayList<Door> _doorPool = new ArrayList<>();
    /** батарейки */
    private final ArrayList<Battery> _batteryPool = new ArrayList<>();

    // ------------------------------ Размеры ---------------------------

    /** Конструктор */
    public GameField () {
        setSize(10, 10);
    }

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
    public int width() {
        return CellPosition.horizontalRange().length();
    }

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

    // ---------------------------- Стены ----------------------------

    /** Проверить, находится ли на данной позиции стена
     *
     * @param pos позиция на поле
     * @return нахождение стены
     */
    public boolean isWall(MiddlePosition pos) {
        
        for (WallPiece obj : _wallPool) {
            if(obj.position().equals(pos))  return true;
        }
        return false;
    }

    /** Добавить стену на поле
     *
     * @param pos позиция
     * @param obj стена
     * @return успешность добавления стены
     */
    public boolean addWall(MiddlePosition pos, WallPiece obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _wallPool.add(obj);
        return success;
    }

    // ---------------------------- Двери ----------------------------

    /** Вернуть дверь, находящуюся на данной позиции
     *
     * @param pos позиция
     * @return искомая дверь, если она есть на этой позиции, иначе null
     */
    public Door door(MiddlePosition pos) {
        
        for (Door obj : _doorPool) {
            if(obj.position().equals(pos))  return obj;
        }
        return null;
    }

    /** Добавить дверь на поле
     *
     * @param pos позиция
     * @param obj дверь
     * @return успешность добавления
     */
    public boolean addDoor(MiddlePosition pos, Door obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _doorPool.add(obj);
        return success;
    }
    
    // ---------------------------- Батарейки ----------------------------

    /** Вернуть батарею, если она есть на данной позиции
     *
     * @param pos позиция
     * @return искомая батарея, если она есть на этой позиции, иначе null
     */
    public Battery battery(CellPosition pos) {
        
        for (Battery obj : _batteryPool) {
            try {
                if(obj.position().equals(pos))  return obj;
            } catch (Exception ignored) { }
        }
        return null;
    }

    /** Добавить батарею на поле
     *
     * @param pos позиуия
     * @param obj батарея
     * @return успешность добавления баратери
     */
    public boolean addBattery(CellPosition pos, Battery obj) {
        
        boolean success = obj.setPosition(pos);
        if(success) _batteryPool.add(obj);
        return success;
    }

    /** Убрать батарею с поля
     *
     * @param obj батарея
     * @return успешность удаления
     */
    public boolean removeBattery(Battery obj) {
        
        boolean success = _batteryPool.remove(obj);
        if(success) obj.setPosition(null);
        return success;
    }
    
    // ---------------------------- Очистка ----------------------------

    /** Очистить поле */
    public void clear() {
        _wallPool.clear();
        _batteryPool.clear();
        _doorPool.clear();
        robot = null;
    }
}
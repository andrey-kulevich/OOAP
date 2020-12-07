package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;

/** Робот, умеющий закрашивать клетки */
public class RobotPainter extends Robot {

    /** Конструктор
     *
     * @param field игровое поле
     * @param battery батарейка
     */
    public RobotPainter(GameField field, Battery battery) { super(field, battery); }

    /** Закрасить клетку
     *
     * @param pos позиция
     */
    public void paintCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0) {
            if (getField().paintCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }

    /** Очистить клетку
     *
     * @param pos позиция
     */
    public void clearCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0) {
            if (getField().clearCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }
}

package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;

/** –обот, умеющий закрашивать клетки */
public class RobotPainter extends Robot {

    /**  онструктор
     *
     * @param field игровое поле
     * @param battery батарейка
     */
    public RobotPainter(GameField field, Battery battery) { super(field, battery); }

    /** «акрасить клетку
     *
     * @param pos позици€ (должна быть не от null и совпадать с позицией робота)
     */
    public void paintCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0 && position().equals(pos)) {
            if (getField().paintCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }

    /** ќчистить клетку
     *
     * @param pos позици€ (должна быть не от null и совпадать с позицией робота)
     */
    public void clearCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0 && position().equals(pos)) {
            if (getField().clearCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }
}

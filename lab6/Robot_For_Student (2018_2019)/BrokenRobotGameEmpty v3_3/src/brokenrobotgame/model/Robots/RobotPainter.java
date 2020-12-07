package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;

/** �����, ������� ����������� ������ */
public class RobotPainter extends Robot {

    /** �����������
     *
     * @param field ������� ����
     * @param battery ���������
     */
    public RobotPainter(GameField field, Battery battery) { super(field, battery); }

    /** ��������� ������
     *
     * @param pos �������
     */
    public void paintCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0) {
            if (getField().paintCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }

    /** �������� ������
     *
     * @param pos �������
     */
    public void clearCell(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0) {
            if (getField().clearCell(pos)) reduceCharge(1);
            fireRobotAction();
        }
    }
}

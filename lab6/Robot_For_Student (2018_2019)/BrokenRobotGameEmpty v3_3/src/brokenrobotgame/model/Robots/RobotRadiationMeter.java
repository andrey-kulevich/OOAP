package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;

/** �����, ������� �������� �������� */
public class RobotRadiationMeter extends Robot {

    /** �����������
     *
     * @param field ������� ����
     * @param battery ���������
     */
    public RobotRadiationMeter(GameField field, Battery battery) {
        super(field, battery);
    }

    /** �������� ������� �������� � ������ �������
     *
     * @param pos �������
     * @return �������� (null, ���� � ������ �������� ����� ��� ���������� ������� �� ��������� � �������� ������)
     */
    public RadiationSievert measureRadiation(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0 && position().equals(pos)) {
            reduceCharge(1);
            fireRobotAction();
            return getField().getRadiation(pos);
        }
        return null;
    }
}

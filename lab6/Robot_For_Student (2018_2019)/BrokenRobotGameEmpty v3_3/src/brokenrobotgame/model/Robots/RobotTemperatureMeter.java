package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.TemperatureKelvin;

/** �����, ������� �������� ����������� */
public class RobotTemperatureMeter extends Robot {

    /** �����������
     *
     * @param field ������� ����
     * @param battery ���������
     */
    public RobotTemperatureMeter(GameField field, Battery battery) { super(field, battery); }

    /** �������� ����������� � ������ �������
     *
     * @param pos �������
     * @return ����������� (null, ���� � ������ �������� �����)
     */
    public TemperatureKelvin measureTemperature(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if (amountOfCharge() > 0) {
            reduceCharge(1);
            fireRobotAction();
            return getField().getTemperature(pos);
        }
        return null;
    }
}

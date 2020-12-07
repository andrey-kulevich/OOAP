package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.TemperatureKelvin;

/** Робот, умеющий измерять температуру */
public class RobotTemperatureMeter extends Robot {

    /** Конструктор
     *
     * @param field игровое поле
     * @param battery батарейка
     */
    public RobotTemperatureMeter(GameField field, Battery battery) { super(field, battery); }

    /** Измерить температуру в данной позиции
     *
     * @param pos позиция
     * @return температура (null, если у робота кончился заряд)
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

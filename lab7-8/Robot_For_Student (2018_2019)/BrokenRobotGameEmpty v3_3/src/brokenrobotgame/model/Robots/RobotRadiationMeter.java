package brokenrobotgame.model.Robots;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;

/** –обот, умеющий измер€ть радиацию */
public class RobotRadiationMeter extends Robot {

    /**  онструктор
     *
     * @param field игровое поле
     * @param battery батарейка
     */
    public RobotRadiationMeter(GameField field, Battery battery) {
        super(field, battery);
    }

    /** »змерить уровень радиации в данной позиции
     *
     * @param pos позици€
     * @return радиаци€ (null, если у робота кончилс€ зар€д или переданна€ позици€ не совпадает с позицией робота)
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

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.Robots.RobotRadiationMeter;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.RadiationSievert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotRadiationMeter_test {
    /** Передана нулевая позиция */
    @Test
    void nullPos() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setRadiationToCell(pos, new RadiationSievert(666));
        RobotRadiationMeter robot = new RobotRadiationMeter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);

        boolean isError = false;
        try {
            robot.measureRadiation(null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assertions.assertTrue(isError);
    }

    /** У робота кончился заряд */
    @Test
    void amountOfChargeIsEnd() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setRadiationToCell(pos, new RadiationSievert(666));
        RobotRadiationMeter robot = new RobotRadiationMeter(field, new Battery(field, 10, 0));
        robot.setPosition(pos);
        Assertions.assertNull(robot.measureRadiation(pos));
    }

    /** Переданная позиция не совпадает с позицией робота */
    @Test
    void positionNotTheSameAsRobotHas() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setRadiationToCell(pos, new RadiationSievert(666));
        RobotRadiationMeter robot = new RobotRadiationMeter(field, new Battery(field, 10, 0));
        robot.setPosition(new CellPosition(1,2));
        Assertions.assertNull(robot.measureRadiation(pos));
    }

    /** Корректное измерение радиации */
    @Test
    void correctRadiationLevel() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setRadiationToCell(pos, new RadiationSievert(666));
        RobotRadiationMeter robot = new RobotRadiationMeter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        Assertions.assertEquals(robot.measureRadiation(pos).getRadiation(), 666);
    }
}

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.Robots.RobotTemperatureMeter;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.quantities.TemperatureKelvin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotTemperatureMeter_test {
    /** Передана нулевая позиция */
    @Test
    void nullPos() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setTemperatureToCell(pos, new TemperatureKelvin(666));
        RobotTemperatureMeter robot = new RobotTemperatureMeter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);

        boolean isError = false;
        try {
            robot.measureTemperature(null);
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
        field.setTemperatureToCell(pos, new TemperatureKelvin(666));
        RobotTemperatureMeter robot = new RobotTemperatureMeter(field, new Battery(field, 10, 0));
        robot.setPosition(pos);
        Assertions.assertNull(robot.measureTemperature(pos));
    }

    /** Переданная позиция не совпадает с позицией робота */
    @Test
    void positionNotTheSameAsRobotHas() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setTemperatureToCell(pos, new TemperatureKelvin(666));
        RobotTemperatureMeter robot = new RobotTemperatureMeter(field, new Battery(field, 10, 0));
        robot.setPosition(new CellPosition(1,2));
        Assertions.assertNull(robot.measureTemperature(pos));
    }

    /** Корреткное измерение температуры */
    @Test
    void correctTemperatureLevel() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.setTemperatureToCell(pos, new TemperatureKelvin(666));
        RobotTemperatureMeter robot = new RobotTemperatureMeter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        Assertions.assertEquals(robot.measureTemperature(pos).getTemperature(), 666);
    }
}

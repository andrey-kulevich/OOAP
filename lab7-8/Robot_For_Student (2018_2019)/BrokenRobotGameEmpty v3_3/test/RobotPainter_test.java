import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.Robots.RobotPainter;
import brokenrobotgame.model.navigation.CellPosition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotPainter_test {

    /** Передана нулевая позиция при закрашивании клетки */
    @Test
    void nullPosWhilePainting() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);

        boolean isError = false;
        try {
            robot.paintCell(null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assertions.assertTrue(isError);
    }

    /** Передана нулевая позиция при очистке клетки */
    @Test
    void nullPosWhileClearing() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.paintCell(pos);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);

        boolean isError = false;
        try {
            robot.clearCell(null);
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
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 0));
        robot.setPosition(pos);
        robot.paintCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos));
    }

    /** Переданная позиция не совпадает с позицией робота */
    @Test
    void positionNotTheSameAsRobotHas() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(new CellPosition(1,2));
        robot.paintCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos));
    }

    /** Попытка закрасить уже закрашенную клетку */
    @Test
    void attemptToPaintAlreadyPaintedCell() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.paintCell(pos);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        robot.paintCell(pos);
        Assertions.assertTrue(field.isCellPainted(pos) && robot.amountOfCharge() == 10);
    }

    /** Попытка очистить уже чистую клетку */
    @Test
    void attemptToClearAlreadyClearedCell() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.clearCell(pos);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        robot.clearCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos) && robot.amountOfCharge() == 10);
    }

    /** Корректная закраска клетки */
    @Test
    void correctPainting() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        robot.paintCell(pos);
        Assertions.assertTrue(field.isCellPainted(pos));
    }

    /** Корректная очистка клетки */
    @Test
    void correctClearing() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        field.paintCell(pos);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        robot.clearCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos));
    }
}

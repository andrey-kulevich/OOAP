import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.Robots.RobotPainter;
import brokenrobotgame.model.navigation.CellPosition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotPainter_test {

    /** �������� ������� ������� ��� ������������ ������ */
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

    /** �������� ������� ������� ��� ������� ������ */
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

    /** � ������ �������� ����� */
    @Test
    void amountOfChargeIsEnd() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 0));
        robot.setPosition(pos);
        robot.paintCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos));
    }

    /** ���������� ������� �� ��������� � �������� ������ */
    @Test
    void positionNotTheSameAsRobotHas() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(new CellPosition(1,2));
        robot.paintCell(pos);
        Assertions.assertFalse(field.isCellPainted(pos));
    }

    /** ������� ��������� ��� ����������� ������ */
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

    /** ������� �������� ��� ������ ������ */
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

    /** ���������� �������� ������ */
    @Test
    void correctPainting() {

        GameField field = new GameField();
        CellPosition pos = new CellPosition(1,1);
        RobotPainter robot = new RobotPainter(field, new Battery(field, 10, 10));
        robot.setPosition(pos);
        robot.paintCell(pos);
        Assertions.assertTrue(field.isCellPainted(pos));
    }

    /** ���������� ������� ������ */
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

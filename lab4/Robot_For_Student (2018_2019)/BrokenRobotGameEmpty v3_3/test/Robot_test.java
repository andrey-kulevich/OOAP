import brokenrobotgame.model.Battery;
import brokenrobotgame.model.Door;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.Robot;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class Robot_test {

    /** Переданы нулевые поля при создании робота */
    @Test
    void invalidNullParamInConstructor() {

        boolean isError = false;
        try {
            Robot robot = new Robot(null, null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Использовать батарейку */
    @Test
    void useBattery() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 2));
        robot.useBattery(battery);
        Assert.assertTrue(robot.chargeCapacity() == 1 && robot.amountOfCharge() == 1);
    }

    /** Батарейка находится не рядом с роботом */
    @Test
    void batteryIsFarAwayFromRobot() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 3));
        boolean isError = false;
        try {
            robot.useBattery(battery);
        } catch (IllegalArgumentException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Передано null вместо батарейки */
    @Test
    void nullInsteadOfBattery() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 3));
        boolean isError = false;
        try {
            robot.useBattery(null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Уменьшить заряд батарейки на единицу */
    @Test
    void reduceChargeOfBattery() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        field.getRobot();
        field.width();
        field.height();
        robot.reduceCharge(1);
        Assert.assertTrue(robot.chargeCapacity() == 3 && robot.amountOfCharge() == 2);
    }

    /** Уменьшить заряд больше чем на единицу */
    @Test
    void reduceChargeMoreThanOnePoint() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        robot.reduceCharge(3);
        Assert.assertTrue(robot.chargeCapacity() == 3 && robot.amountOfCharge() == 0);
    }

    /** Передано отрицательное число при уменьшении заряда */
    @Test
    void negativeNumberInReduceCharge() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        boolean isError = false;
        try {
            robot.reduceCharge(-1);
        } catch (IllegalArgumentException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Открыть дверь */
    @Test
    void openTheDoor() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));

        Door door = new Door(field);
        field.addDoor(new MiddlePosition(robot.position(), Direction.east()), door);
        robot.openCloseDoor(Direction.east());

        Assert.assertTrue(door.isOpen());
    }

    /** Закрыть дверь */
    @Test
    void closeTheDoor() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));

        Door door = new Door(field);
        door.open();
        field.addDoor(new MiddlePosition(robot.position(), Direction.east()), door);
        robot.openCloseDoor(Direction.east());

        Assert.assertFalse(door.isOpen());
    }

    /** Передано null вместо направления */
    @Test
    void nullInsteadOfDirectionDoor() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));

        Door door = new Door(field);
        door.setPosition(new MiddlePosition(robot.position(), Direction.east()));
        field.addDoor(new MiddlePosition(robot.position(), Direction.east()), door);
        boolean isError = false;
        try {
            robot.openCloseDoor(null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Попытка открыть дверь, у робота кончился заряд */
    @Test
    void endOfChargeDoor() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 0));
        robot.setPosition(new CellPosition(1,2));

        Door door = new Door(field);
        door.setPosition(new MiddlePosition(robot.position(), Direction.east()));
        field.addDoor(new MiddlePosition(robot.position(), Direction.east()), door);
        robot.openCloseDoor(Direction.east());

        Assert.assertFalse(door.isOpen());
    }

    /** Перед роботом нет двери */
    @Test
    void noDoorInFrontOfRobot() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));

        Door door = new Door(field);
        door.setPosition(new MiddlePosition(robot.position().next(Direction.east()), Direction.east()));
        field.addDoor(new MiddlePosition(robot.position(), Direction.east()), door);
        robot.openCloseDoor(Direction.north());

        Assert.assertFalse(door.isOpen());
    }

    /** Передана нулевая позиция */
    @Test
    void nullInsteadOfPos() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));

        boolean isError = false;
        try {
            robot.setPosition(null);
        } catch (NullPointerException ex) {
            isError = true;
        }

        Assert.assertTrue(isError);
    }

    /** Сделать шаг роботом */
    @Test
    void moveRobot() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        robot.makeMove(Direction.east());

        Assert.assertTrue(robot.position().equals(new CellPosition(1, 3))
                && robot.amountOfCharge() == 2);
    }

    /** Попытка сделать движение роботом, у робота кончился заряд */
    @Test
    void endOfChargeMovementImpossible() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 0));
        robot.setPosition(new CellPosition(1,2));
        robot.makeMove(Direction.east());

        Assert.assertTrue(robot.position().equals(new CellPosition(1, 2))
                && robot.amountOfCharge() == 0);
    }

    /** Попытка сделать движение роботом, движение в данном направлении невозможно */
    @Test
    void moveInThisDirectionIsImpossible() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        robot.makeMove(Direction.north());

        Assert.assertTrue(robot.position().equals(new CellPosition(1, 2))
                && robot.amountOfCharge() == 3);
    }

    /** Передано null вместо направления */
    @Test
    void nullInsteadOfDirectionMove() {

        GameField field = new GameField();
        Robot robot = new Robot(field, new Battery(field, 3, 3));
        robot.setPosition(new CellPosition(1,2));
        boolean isError = false;
        try {
            robot.makeMove(null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

}

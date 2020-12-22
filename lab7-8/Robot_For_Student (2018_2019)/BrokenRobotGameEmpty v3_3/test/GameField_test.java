import brokenrobotgame.model.*;
import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameField_test {

    /** Добавить дверь */
    @Test
    void addDoor() {
        GameField field = new GameField();
        Door door = new Door(field);
        door.setPosition(new MiddlePosition(new CellPosition(2, 2), Direction.east()));
        field.addObject(door);
        Assertions.assertEquals(door, field.getObject(door.position()));
    }

    /** Добавить стену */
    @Test
    void addWall() {
        GameField field = new GameField();
        WallPiece wall = new WallPiece(field);
        wall.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(wall);
        Assertions.assertEquals(wall, field.getObject(wall.position()));
    }

    /** Добавить разрушаемую дверь */
    @Test
    void addDestroyableDoor() {
        GameField field = new GameField();
        DestroyableDoor door = new DestroyableDoor(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        Assertions.assertEquals(door, field.getObject(door.position()));
    }

    /** Добавить батарейку */
    @Test
    void addBattery() {
        GameField field = new GameField();
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 1));
        field.addObject(battery);
        Assertions.assertEquals(battery, field.getObject(battery.position()));
    }

    /** Добавить множество разнородных объектов */
    @Test
    void addCoupleOfObjectsOfDifferentTypes() {
        GameField field = new GameField();
        Door door = new Door(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        DestroyableDoor destroyableDoor = new DestroyableDoor(field);
        destroyableDoor.setPosition(new MiddlePosition(new CellPosition(2, 2), Direction.east()));
        field.addObject(destroyableDoor);
        WallPiece wall = new WallPiece(field);
        wall.setPosition(new MiddlePosition(new CellPosition(3, 3), Direction.east()));
        field.addObject(wall);
        Battery battery = new Battery(field, 4, 4);
        battery.setPosition(new CellPosition(5, 5));
        field.addObject(battery);
        Assertions.assertTrue(field.objects().size() == 4 &&
                field.getObject(battery.position()).equals(battery) &&
                field.getObject(door.position()).equals(door) &&
                field.getObject(destroyableDoor.position()).equals(destroyableDoor) &&
                field.getObject(wall.position()).equals(wall));
    }

    /** Попытка добавить на поле объект, не имеющий позиции */
    @Test
    void objectHasNoPosition() {
        GameField field = new GameField();
        Door door = new Door(field);
        boolean isError = false;
        try {
            field.addObject(door);
        } catch (RuntimeException err) {
            isError = true;
        }
        Assertions.assertTrue(isError);
    }

    /** Удалить дверь */
    @Test
    void deleteDoor() {
        GameField field = new GameField();
        Door door = new Door(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        field.removeObject(door);
        Assertions.assertEquals(null, field.getObject(door.position()));
    }

    /** Удалить разрушаемую дверь */
    @Test
    void deleteDestroyableDoor() {
        GameField field = new GameField();
        DestroyableDoor door = new DestroyableDoor(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        field.removeObject(door);
        Assertions.assertEquals(null, field.getObject(door.position()));
    }
    /** Удалить стену */
    @Test
    void deleteWall() {
        GameField field = new GameField();
        WallPiece wall = new WallPiece(field);
        wall.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(wall);
        field.removeObject(wall);
        Assertions.assertEquals(null, field.getObject(wall.position()));
    }
    /** Удалить батарейку */
    @Test
    void deleteBattery() {
        GameField field = new GameField();
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 1));
        field.addObject(battery);
        field.removeObject(battery);
        Assertions.assertEquals(null, field.getObject(battery.position()));
    }

    /** Получить все объекты заданного типа */
    @Test
    void getObjectsOfCertainType() {
        GameField field = new GameField();
        Door door = new Door(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        Door door1 = new Door(field);
        door1.setPosition(new MiddlePosition(new CellPosition(2, 2), Direction.east()));
        field.addObject(door1);
        WallPiece wall = new WallPiece(field);
        wall.setPosition(new MiddlePosition(new CellPosition(3, 3), Direction.east()));
        field.addObject(wall);
        Battery battery = new Battery(field, 4, 4);
        battery.setPosition(new CellPosition(5, 5));
        field.addObject(battery);
        Assertions.assertTrue(field.objects(Door.class).size() == 2 &&
                field.objects(WallPiece.class).size() == 1 &&
                field.objects(Battery.class).size() == 1);
    }

    /** Поле содержит данный объект */
    @Test
    void fieldContainsTheObject() {
        GameField field = new GameField();
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 1));
        field.addObject(battery);
        Assertions.assertTrue(field.contains(battery));
    }

    /** Поле не содержит данный объект */
    @Test
    void fieldDoesNotContainTheObject() {
        GameField field = new GameField();
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(new CellPosition(1, 1));
        field.addObject(battery);
        field.removeObject(battery);
        Assertions.assertFalse(field.contains(battery));
    }

    /** Очистить поле */
    @Test
    void clearField() {
        GameField field = new GameField();
        Door door = new Door(field);
        door.setPosition(new MiddlePosition(new CellPosition(1, 1), Direction.east()));
        field.addObject(door);
        Door door1 = new Door(field);
        door1.setPosition(new MiddlePosition(new CellPosition(2, 2), Direction.east()));
        field.addObject(door1);
        WallPiece wall = new WallPiece(field);
        wall.setPosition(new MiddlePosition(new CellPosition(3, 3), Direction.east()));
        field.addObject(wall);
        Battery battery = new Battery(field, 4, 4);
        battery.setPosition(new CellPosition(5, 5));
        field.addObject(battery);
        Robot robot = new Robot(field, battery);
        robot.setPosition(new CellPosition(6, 6));
        field.setRobot(robot);
        field.clear();
        Assertions.assertTrue(field.objects().size() == 0 && field.getRobot() == null);
    }

    /** Повредить дверь */
    @Test
    void hitTheDoor() {
        GameField field = new GameField();
        DestroyableDoor door = new DestroyableDoor(field);
        door.setPosition(new MiddlePosition(new CellPosition(3, 3), Direction.east()));
        field.addObject(door);
        Robot robot = new Robot(field, new Battery(field, 4, 4));
        robot.setPosition(new CellPosition(3, 3));
        robot.makeDamage(door, 1);
        Assertions.assertEquals(1, ((DestroyableDoor) field.getObject(door.position())).getDurabilityRest());
    }

    /** Разрушить дверь */
    @Test
    void destroyTheDoor() {
        GameField field = new GameField();
        DestroyableDoor door = new DestroyableDoor(field);
        door.setPosition(new MiddlePosition(new CellPosition(3, 3), Direction.east()));
        field.addObject(door);
        Robot robot = new Robot(field, new Battery(field, 4, 4));
        robot.setPosition(new CellPosition(3, 3));
        robot.makeDamage(door, 2);
        Assertions.assertNull(field.getObject(door));
    }
}

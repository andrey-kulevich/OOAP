package brokenrobotgame.model;

import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.Robots.RobotPainter;
import brokenrobotgame.model.Robots.RobotRadiationMeter;
import brokenrobotgame.model.Robots.RobotTemperatureMeter;
import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.quantities.RadiationSievert;
import brokenrobotgame.model.quantities.TemperatureKelvin;

/**
 * GameModel - абстракция всей игры; генерирует стартовую обстановку; 
 * следит за роботом с целью определения конца игры
 */
public class GameModel {

    /** игровое поле */
    private final GameField _field = new GameField();
    /** целевая позиция робота */
    private CellPosition _targetPos;
    /** робот */
    private Robot _robot;

    // ----------------------- Игровое поле и робот на нем ------------------

    /** Получить игровое поле
     *
     * @return игровое поле
     */
    public GameField field() { return _field; }

    /** Получить робота
     *
     * @return робот
     */
    public Robot robot() { return _robot; }

    /** Установить робота
     *
     * @param robot робот
     */
    private void setRobot(Robot robot) {
        _field.setRobot(robot);
        this._robot = robot;
    }

    /** начать игру */
    public void start() {
        generateField(); // Генерируем игровое поле
        String[] className = _robot.getClass().getName().split("\\.");
        System.out.println("Current robot is " +
                className[className.length - 1]); // Выводим тип текущего робота
        identifyGameOver(); // Вдруг игра завершилась, еще не начавшись
        _robot.addRobotActionListener(new RobotObserver()); // "Следим" за роботом
    }
    
    // -------------------- Целевая позиция робота --------------------------

    /** Получить целевую позицию робота
     *
     * @return целевая позиция
     */
    public CellPosition targetPosition() { return _targetPos; }

    // ------------ Задаем обстановку и следим за окончанием игры  ------------

    /** Сгенерировать игровое поле */
    private void generateField() {

        // Обстановка = робот+стены+батарейка на поле+двери
        _field.clear();
        setRandomRobot();
        _field.setRobot(_robot);
        _robot.setPosition(new CellPosition(4, 5));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.east()), new WallPiece(_field));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.south()), new WallPiece(_field));
        _field.addBattery(new CellPosition(2, 1), new Battery(_field, 5, 3));
        _field.addDoor(new MiddlePosition(new CellPosition(5, 4), Direction.east()), new Door(_field));
        _field.addDoor(new MiddlePosition(new CellPosition(1, 1), Direction.north()), new Door(_field));

        // Целевая позиция рядом с роботом
        _targetPos = _robot.position().next(Direction.west());
    }

    /** Установить на поле любого из четырех роботов */
    public void setRandomRobot() {
        switch ((int)getRandomNumber(0, 4)) {
            case 0 -> setRobot(new Robot(_field, new Battery(_field, 30, 30)));
            case 1 -> {
                setRobot(new RobotRadiationMeter(_field, new Battery(_field, 30, 30)));
                initRadiationPollutionToCells();
            }
            case 2 -> {
                setRobot(new RobotTemperatureMeter(_field, new Battery(_field, 30, 30)));
                initTemperatureLevelsToCells();
            }
            default -> setRobot(new RobotPainter(_field, new Battery(_field, 30, 30)));
        }
    }

    /** Установить случайный уровень радиации во всех ячейках */
    public void initRadiationPollutionToCells() {
        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        _field.setRadiationToCell(pos, new RadiationSievert(getRandomNumber(0,8)));
        boolean hasNextInCol;
        boolean hasNextInRow;
        do {
            do {
                _field.setRadiationToCell(pos, new RadiationSievert(getRandomNumber(0,8)));
                hasNextInRow = pos.hasNext(direct);
                if(pos.hasNext(direct)) pos = pos.next(direct);
            } while(hasNextInRow);

            direct = direct.opposite();
            hasNextInCol = pos.hasNext(Direction.south());
            if(pos.hasNext(Direction.south())) pos = pos.next(Direction.south());
        } while(hasNextInCol);
    }

    /** Установить случайную температуру во всех ячейках */
    public void initTemperatureLevelsToCells() {
        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        _field.setTemperatureToCell(pos, new TemperatureKelvin(getRandomNumber(0,1000)));
        boolean hasNextInCol;
        boolean hasNextInRow;
        do {
            do {
                _field.setTemperatureToCell(pos, new TemperatureKelvin(getRandomNumber(0,1000)));
                hasNextInRow = pos.hasNext(direct);
                if(pos.hasNext(direct)) pos = pos.next(direct);
            } while(hasNextInRow);

            direct = direct.opposite();
            hasNextInCol = pos.hasNext(Direction.south());
            if(pos.hasNext(Direction.south())) pos = pos.next(Direction.south());
        } while(hasNextInCol);
    }

    /** Получить случайное дробное число в заданном диапазоне
     *
     * @param min нижняя граница
     * @param max верхняя граница
     * @return случайное число в данном диапазоне
     */
    private double getRandomNumber(int min, int max) { return (Math.random() * (max - min)) + min; }

    /** Определить окончание игры */
    private void identifyGameOver() {
        if (_robot.position().equals(_targetPos)) System.out.println("You reached target position!!!");
        else if (_robot.amountOfCharge() == 0) System.out.println("Your amount of charge is null!!!");
    }

    /** Класс-слушатель событий робота */
    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMadeMove(RobotActionEvent e) {
            identifyGameOver();
        }
    }
}

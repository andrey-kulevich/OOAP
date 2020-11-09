package brokenrobotgame.model;

import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/**
 * GameModel - абстракци€ всей игры; генерирует стартовую обстановку; 
 * следит за роботом с целью определени€ конца игры
 */
public class GameModel {

    /** игровое поле */
    private final GameField _field = new GameField();
    /** целева€ позици€ робота */
    private CellPosition _targetPos;
    /** робот */
    private Robot _robot;

    // ----------------------- »гровое поле и робот на нем ------------------

    /** ѕолучить игровое поле
     *
     * @return игровое поле
     */
    public GameField field() { return _field; }

    /** ѕолучить робота
     *
     * @return робот
     */
    public Robot robot() { return _robot; }

    /** ”становить робота
     *
     * @param robot робот
     */
    private void setRobot(Robot robot) {
        _field.setRobot(robot);
        this._robot = robot;
    }

    /** начать игру */
    public void start() {
        generateField();
        identifyGameOver(); // ¬друг игра завершилась, еще не начавшись
        _robot.addRobotActionListener(new RobotObserver()); // "—ледим" за роботом
    }
    
    // -------------------- ÷елева€ позици€ робота --------------------------

    /** ѕолучить целевую позицию робота
     *
     * @return целева€ позици€
     */
    public CellPosition targetPosition() { return _targetPos; }

    // ------------ «адаем обстановку и следим за окончанием игры  ------------

    /** —генерировать игровое поле */
    private void generateField() {

        // ќбстановка = робот+стены+батарейка на поле+двери
        _field.clear();
        setRobot(new Robot(_field, new Battery(_field, 10, 10)));
        _robot.setPosition(new CellPosition(4, 5));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.east()), new WallPiece(_field));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.south()), new WallPiece(_field));
        Battery outBattery = new Battery(_field, 5, 3);
        _field.addBattery(new CellPosition(2, 1), outBattery);
        //_field.addBattery(robot().position().next(Direction.south()).next(Direction.south()), outBattery);
        _field.addDoor(new MiddlePosition(new CellPosition(5, 4), Direction.east()), new Door(_field));
        _field.addDoor(new MiddlePosition(new CellPosition(1, 1), Direction.north()), new Door(_field));

        // ÷елева€ позици€ р€дом с роботом
        _targetPos = _robot.position().next(Direction.west());
    }

    /** ќпределить окончание игры */
    private void identifyGameOver() {
        if (_robot.position().equals(_targetPos)) System.out.println("You reached target position!!!");
        else if (_robot.amountOfCharge() == 0) System.out.println("Your amount of charge is null!!!");
    }

    /**  ласс-слушатель событий робота */
    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMadeMove(RobotActionEvent e) {
            identifyGameOver();
        }
    }
}

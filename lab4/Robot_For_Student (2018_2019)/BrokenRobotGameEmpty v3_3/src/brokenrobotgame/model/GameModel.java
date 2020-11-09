package brokenrobotgame.model;

import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/**
 * GameModel - ���������� ���� ����; ���������� ��������� ����������; 
 * ������ �� ������� � ����� ����������� ����� ����
 */
public class GameModel {

    /** ������� ���� */
    private final GameField _field = new GameField();
    /** ������� ������� ������ */
    private CellPosition _targetPos;
    /** ����� */
    private Robot _robot;

    // ----------------------- ������� ���� � ����� �� ��� ------------------

    /** �������� ������� ����
     *
     * @return ������� ����
     */
    public GameField field() { return _field; }

    /** �������� ������
     *
     * @return �����
     */
    public Robot robot() { return _robot; }

    /** ���������� ������
     *
     * @param robot �����
     */
    private void setRobot(Robot robot) {
        _field.setRobot(robot);
        this._robot = robot;
    }

    /** ������ ���� */
    public void start() {
        generateField();
        identifyGameOver(); // ����� ���� �����������, ��� �� ���������
        _robot.addRobotActionListener(new RobotObserver()); // "������" �� �������
    }
    
    // -------------------- ������� ������� ������ --------------------------

    /** �������� ������� ������� ������
     *
     * @return ������� �������
     */
    public CellPosition targetPosition() { return _targetPos; }

    // ------------ ������ ���������� � ������ �� ���������� ����  ------------

    /** ������������� ������� ���� */
    private void generateField() {

        // ���������� = �����+�����+��������� �� ����+�����
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

        // ������� ������� ����� � �������
        _targetPos = _robot.position().next(Direction.west());
    }

    /** ���������� ��������� ���� */
    private void identifyGameOver() {
        if (_robot.position().equals(_targetPos)) System.out.println("You reached target position!!!");
        else if (_robot.amountOfCharge() == 0) System.out.println("Your amount of charge is null!!!");
    }

    /** �����-��������� ������� ������ */
    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMadeMove(RobotActionEvent e) {
            identifyGameOver();
        }
    }
}

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
        generateField(); // ���������� ������� ����
        String[] className = _robot.getClass().getName().split("\\.");
        System.out.println("Current robot is " +
                className[className.length - 1]); // ������� ��� �������� ������
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
        setRandomRobot();
        _field.setRobot(_robot);
        _robot.setPosition(new CellPosition(4, 5));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.east()), new WallPiece(_field));
        _field.addWall(new MiddlePosition(_robot.position(), Direction.south()), new WallPiece(_field));
        _field.addBattery(new CellPosition(2, 1), new Battery(_field, 5, 3));
        _field.addDoor(new MiddlePosition(new CellPosition(5, 4), Direction.east()), new Door(_field));
        _field.addDoor(new MiddlePosition(new CellPosition(1, 1), Direction.north()), new Door(_field));

        // ������� ������� ����� � �������
        _targetPos = _robot.position().next(Direction.west());
    }

    /** ���������� �� ���� ������ �� ������� ������� */
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

    /** ���������� ��������� ������� �������� �� ���� ������� */
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

    /** ���������� ��������� ����������� �� ���� ������� */
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

    /** �������� ��������� ������� ����� � �������� ���������
     *
     * @param min ������ �������
     * @param max ������� �������
     * @return ��������� ����� � ������ ���������
     */
    private double getRandomNumber(int min, int max) { return (Math.random() * (max - min)) + min; }

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

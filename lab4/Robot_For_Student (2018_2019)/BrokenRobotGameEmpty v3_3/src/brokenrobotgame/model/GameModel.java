package brokenrobotgame.model;

import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/*
 * GameModel - ���������� ���� ����; ���������� ��������� ����������; 
 * ������ �� ������� � ����� ����������� ����� ����
 */
public class GameModel {

    // ----------------------- ������� ���� � ����� �� ��� ------------------
    GameField _field = new GameField();
    
    public GameField field(){
        return _field;
    }
    
    !!!
    
    public Robot robot(){
        !!!
    }

    public void start(){
        generateField();
        
        // ����� ���� �����������, ��� �� ���������
        identifyGameOver();
        
        // "������" �� �������
        !!!
    }
    
    // -------------------- ������� ������� ������ --------------------------
    CellPosition _targetPos;
    
    public CellPosition targetPosition(){
        return _targetPos;
    }
    
    // ------------ ������ ���������� � ������ �� ���������� ����  ------------

    private void generateField(){

        // ���������� = �����+�����+��������� �� ����+�����
        !!!
        _field.addWall(new MiddlePosition(robot().position(), Direction.east()), new WallPiece(_field));
        _field.addWall(new MiddlePosition(robot().position(), Direction.south()), new WallPiece(_field));
        Battery outBattery = new Battery(_field, 5, 3);
        _field.addBattery(new CellPosition(2, 1), outBattery);
        //_field.addBattery(robot().position().next(Direction.south()).next(Direction.south()), outBattery);
        _field.addDoor(new MiddlePosition(new CellPosition(5, 4), Direction.east()), new Door(_field));
        _field.addDoor(new MiddlePosition(new CellPosition(1, 1), Direction.north()), new Door(_field));
        
        
        // ������� ������� ����� � �������
        _targetPos = robot().position().next(Direction.west());
    }
    
    private void identifyGameOver(){
        
        if(robot().position().equals(_targetPos))
        {
            System.out.println("You reach target position!!!");
        }
        else if(robot().amount�f�harge()==0)
        {
            System.out.println("Your amount of charge is null!!!");
        }
    }   
    
    private class RobotObserver implements RobotActionListener{

        @Override
        public void robotMakedMove(RobotActionEvent e) {
            identifyGameOver();
        }
    }
}

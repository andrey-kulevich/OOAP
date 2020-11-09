package brokenrobotgame.model;

import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/**
 * Robot - ����� ������������� �� ����, ���� ������� ����� ���������� ���������; 
 * ��������������  ����������, ���� ����� ������; ����� ������������ ���������, 
 * ����������� � ����
 */
public class Robot
{
    /** ������� ���� */
    private final GameField field;
    /** ��������� ������� ������ */
    private final ArrayList<RobotActionListener> listeners = new ArrayList<>();
    /** ������� ������ �� ���� */
    private CellPosition _position;
    /** ������� */
    private Battery battery;

    // ------------------- ������������� ����� � ������� ����� -----------------

    /** �����������
     *
     * @param field ������� ����
     * @param battery �������
     */
    public Robot(GameField field, Battery battery) {
        if (field == null || battery == null) throw new NullPointerException();
        this.field = field;
        this.battery = battery;
    }
	
  
    // ------------------- ����� "��������" �� ��������� � ����� �� ������ -----------------

    /** ������������ �������
     *
     * @param battery �������
     */
    public void useBattery(Battery battery) {

        if (battery == null) throw new NullPointerException();
        // ����� ��������� ������ ���������� ����� � ������� ��� ���� ��� ����
        if (!_position.equals(battery.position()) && battery.position() != null)
            throw new IllegalArgumentException("������� �� ��������� ����� � �������");

        this.battery.destroy(); // ���������� ������ ���������

        // ��������� ��������� � ������
        this.battery = battery;
        field.removeBattery(battery);

        fireRobotAction(); // ���������� �������
    }

    /** �������� ����� ������
     *
     * @return �����
     */
    public int amountOfCharge() { return battery.amountOfCharge(); }

    /** �������� ������� ������ ������
     *
     * @return ������� ������
     */
    public int chargeCapacity() { return battery.chargeCapacity(); }

    /** ��������� ����� ������
     *
     * @param delta ����������
     */
    public void reduceCharge(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        battery.reduceCharge(delta);
    }
	
    
    // ------------------- ����� ����� ��������� � ��������� ����� -----------------

    /** �������������� � ������
     *
     * @param direction ����������� ��������������
     */
    public void openCloseDoor(Direction direction) {

        if (direction == null) throw new NullPointerException();
        if (battery.amountOfCharge() > 0)    // ����� ������ ����� �����
        {
            Door door = field.door(new MiddlePosition(_position, direction));
            if (door != null)    // ����� ������� �����
            {
                // ��������������� � ������
                if (door.isOpen()) door.close();
                else door.open();

                reduceCharge(1); // ���������� �����
                fireRobotAction(); // ���������� �������
            }
        }    
    }
    
    // ------------------- ������� ������ -----------------

    /** �������� ������� ������
     *
     * @return �������
     */
    public CellPosition position() { return _position; }

    /** ������ ������� ������
     *
     * @param pos �������
     * @return ���������� ��������� �������
     */
    public boolean setPosition(CellPosition pos) {

        if (pos == null) throw new NullPointerException();
        if (pos.isValid()) {
            _position = pos;
            return true;
        }
        return false;
    }

    // ------------------- ������� ������ -----------------

    /** ����������� ������
     *
     * @param direction �����������
     */
    public void makeMove(Direction direction) {

        if (direction == null) throw new NullPointerException();
        if(amountOfCharge() > 0 && isMovePossible(direction)) { // ����� ������ ����� ����� � ����������� ���������
            setPosition(_position.next(direction)); // ������������ � ������ ������
            reduceCharge(1); // ���������� �����
            fireRobotAction(); // ���������� �������
        }    
    }

    /** ��������� ����������� �������� � ������ �����������
     *
     * @param direction �����������
     * @return ����������� �������� � ������ �����������
     */
    private boolean isMovePossible(Direction direction) {

        if (direction == null) throw new NullPointerException();
        if(!position().hasNext(direction)) return false; // ���� ��� �����������
        MiddlePosition nextMiddlePos = new MiddlePosition(_position, direction);
        Door door = field.door(nextMiddlePos);
        // ����������� ����� ��� �������� �����
        return !field.isWall(nextMiddlePos) && (door == null || door.isOpen());
    }
    
    // ---------------------- ��������� ������� -----------------------------
    
    // ������������ ���������
    public void addRobotActionListener(RobotActionListener listener) {
        if (listener == null) throw new NullPointerException();
        listeners.add(listener);
    }
    
    // ����������� ���������
    public void removeRobotActionListener(RobotActionListener listener) {
        if (listener == null) throw new NullPointerException();
        listeners.remove(listener);
    }
    
    // ��������� ���������� � �������
    protected void fireRobotAction() {
        for (RobotActionListener listener : listeners) {
            listener.robotMadeMove(new RobotActionEvent(this));
        }
    } 
}
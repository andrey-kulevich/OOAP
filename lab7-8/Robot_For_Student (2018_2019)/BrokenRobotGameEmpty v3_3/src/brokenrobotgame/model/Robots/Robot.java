package brokenrobotgame.model.Robots;

import brokenrobotgame.model.*;
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
public class Robot extends GameObject<CellPosition> {

    /** ��������� ������� ������ */
    private final ArrayList<RobotActionListener> listeners = new ArrayList<>();
    /** ������� */
    private Battery battery;

    /** �������� ������� ����
     *
     * @return ������� ����
     */
    public GameField getField() { return field; }

    // ------------------- ������������� ����� � ������� ����� -----------------

    /** �����������
     *
     * @param field ������� ����
     * @param battery �������
     */
    public Robot(GameField field, Battery battery) {
        if (field == null || battery == null) throw new NullPointerException();
        setField(field);
        this.battery = battery;
    }
  
    // ------------------- ����� "��������" �� ��������� � ����� �� ������ -----------------

    /** ������������ �������
     *
     * @param battery �������
     */
    public void useBattery(Battery battery) {
        if (battery == null) throw new NullPointerException();
        if (!position.equals(battery.position()) && battery.position() != null)
            throw new IllegalArgumentException("������� �� ��������� ����� � �������");
        this.battery.destroy();
        this.battery = battery;
        field.removeObject(battery);
        fireRobotAction();
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
        if (amountOfCharge() > 0) {
            Door door = (Door) field.getObject(new MiddlePosition(position, direction));
            if (door != null) {
                if (door.isOpen()) door.close();
                else door.open();
                reduceCharge(1);
                fireRobotAction();
            }
        }    
    }


    // ------------------- ����� ����� �������� ����������� ���������� �������� -----------------

    /** ������� ����������� �������
     *
     * @param target ������� ������
     * @param damage ����
     */
    public void makeDamage(Destroyable target, int damage) {
        if (target != null) {
            target.hit(damage);
            reduceCharge(damage);
            fireRobotAction();
        }
    }

    
    // ------------------- ������� ������ -----------------

    /** ���������� ������� ������� �� ����
     *
     * @param pos �������
     * @return ���������� ���������
     */
    public boolean setPosition(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if(pos.isValid()) {
            position = pos;
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
        if(amountOfCharge() > 0 && isMovePossible(direction)) {
            setPosition(position.next(direction));
            reduceCharge(1);
            fireRobotAction();
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
        GameObject object = field.getObject(new MiddlePosition(position, direction));
        return object == null ||
                (object instanceof Door && ((Door)object).isOpen()) ||
                object instanceof Battery;
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
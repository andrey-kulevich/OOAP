package brokenrobotgame.model.Robots;

import brokenrobotgame.model.*;
import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/**
 * Robot - может передвигатьс€ по полю, если имеетс€ зар€д внутренней батарейки; 
 * самосто€тельно  определ€ет, куда может ходить; может использовать батарейки, 
 * наход€щиес€ в поле
 */
public class Robot extends GameObject<CellPosition> {

    /** слушатель событий робота */
    private final ArrayList<RobotActionListener> listeners = new ArrayList<>();
    /** батаре€ */
    private Battery battery;

    /** ѕолучить игровое поле
     *
     * @return игровое поле
     */
    public GameField getField() { return field; }

    // ------------------- ”станавливаем св€зь с игровым полем -----------------

    /**  онструктор
     *
     * @param field игровое поле
     * @param battery батаре€
     */
    public Robot(GameField field, Battery battery) {
        if (field == null || battery == null) throw new NullPointerException();
        setField(field);
        this.battery = battery;
    }
  
    // ------------------- –обот "питаетс€" от батарейки и может их мен€ть -----------------

    /** »спользовать батарею
     *
     * @param battery батаре€
     */
    public void useBattery(Battery battery) {
        if (battery == null) throw new NullPointerException();
        if (!position.equals(battery.position()) && battery.position() != null)
            throw new IllegalArgumentException("Ѕатаре€ не находитс€ р€дом с роботом");
        this.battery.destroy();
        this.battery = battery;
        field.removeObject(battery);
        fireRobotAction();
    }

    /** ѕолучить зар€д робота
     *
     * @return зар€д
     */
    public int amountOfCharge() { return battery.amountOfCharge(); }

    /** ѕолучить емкость зар€да робота
     *
     * @return емкость зар€да
     */
    public int chargeCapacity() { return battery.chargeCapacity(); }

    /** ”меньшить зар€д робота
     *
     * @param delta уменьшение
     */
    public void reduceCharge(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        battery.reduceCharge(delta);
    }
	
    
    // ------------------- –обот может открывать и закрывать двери -----------------

    /** ¬заимодействие с дверью
     *
     * @param direction направление взаимодействи€
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
    
    // ------------------- ѕозици€ робота -----------------

    /** ѕолучить позицию робота
     *
     * @return позици€
     */
    public CellPosition position() { return position.clone(); }

    /** ”становить позицию объекта на поле
     *
     * @param pos позици€
     * @return успешность установки
     */
    public boolean setPosition(CellPosition pos) {
        if (pos == null) throw new NullPointerException();
        if(pos.isValid()) {
            position = pos;
            return true;
        }
        return false;
    }

    // ------------------- ƒвигаем робота -----------------

    /** ѕереместить робота
     *
     * @param direction направление
     */
    public void makeMove(Direction direction) {
        if (direction == null) throw new NullPointerException();
        if(amountOfCharge() > 0 && isMovePossible(direction)) {
            setPosition(position.next(direction));
            reduceCharge(1);
            fireRobotAction();
        }    
    }

    /** ѕроверить возможность движени€ в данном направлении
     *
     * @param direction направление
     * @return возможность движени€ в данном направлении
     */
    private boolean isMovePossible(Direction direction) {
        if (direction == null) throw new NullPointerException();
        if(!position().hasNext(direction)) return false; // поле уже закончилось
        GameObject object = field.getObject(new MiddlePosition(position, direction));
        return object == null ||
                (object instanceof Door && ((Door)object).isOpen()) ||
                object instanceof Battery;
    }
    
    // ---------------------- ѕорождает событи€ -----------------------------
    
    // присоедин€ет слушател€
    public void addRobotActionListener(RobotActionListener listener) {
        if (listener == null) throw new NullPointerException();
        listeners.add(listener);
    }
    
    // отсоедин€ет слушател€
    public void removeRobotActionListener(RobotActionListener listener) {
        if (listener == null) throw new NullPointerException();
        listeners.remove(listener);
    }
    
    // оповещает слушателей о событии
    protected void fireRobotAction() {
        for (RobotActionListener listener : listeners) {
            listener.robotMadeMove(new RobotActionEvent(this));
        }
    } 
}
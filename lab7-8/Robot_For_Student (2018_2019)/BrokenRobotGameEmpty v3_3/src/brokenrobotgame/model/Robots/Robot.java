package brokenrobotgame.model.Robots;

import brokenrobotgame.model.*;
import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/**
 * Robot - может передвигаться по полю, если имеется заряд внутренней батарейки; 
 * самостоятельно  определяет, куда может ходить; может использовать батарейки, 
 * находящиеся в поле
 */
public class Robot extends GameObject<CellPosition> {

    /** слушатель событий робота */
    private final ArrayList<RobotActionListener> listeners = new ArrayList<>();
    /** батарея */
    private Battery battery;

    /** Получить игровое поле
     *
     * @return игровое поле
     */
    public GameField getField() { return field; }

    // ------------------- Устанавливаем связь с игровым полем -----------------

    /** Конструктор
     *
     * @param field игровое поле
     * @param battery батарея
     */
    public Robot(GameField field, Battery battery) {
        if (field == null || battery == null) throw new NullPointerException();
        setField(field);
        this.battery = battery;
    }
  
    // ------------------- Робот "питается" от батарейки и может их менять -----------------

    /** Использовать батарею
     *
     * @param battery батарея
     */
    public void useBattery(Battery battery) {
        if (battery == null) throw new NullPointerException();
        if (!position.equals(battery.position()) && battery.position() != null)
            throw new IllegalArgumentException("Батарея не находится рядом с роботом");
        this.battery.destroy();
        this.battery = battery;
        field.removeObject(battery);
        fireRobotAction();
    }

    /** Получить заряд робота
     *
     * @return заряд
     */
    public int amountOfCharge() { return battery.amountOfCharge(); }

    /** Получить емкость заряда робота
     *
     * @return емкость заряда
     */
    public int chargeCapacity() { return battery.chargeCapacity(); }

    /** Уменьшить заряд робота
     *
     * @param delta уменьшение
     */
    public void reduceCharge(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta cannot be negative");
        battery.reduceCharge(delta);
    }
	
    
    // ------------------- Робот может открывать и закрывать двери -----------------

    /** Взаимодействие с дверью
     *
     * @param direction направление взаимодействия
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


    // ------------------- Робот может наносить повреждения разрушимым объектам -----------------

    /** Нанести повреждение объекту
     *
     * @param target целевой объект
     * @param damage урон
     */
    public void makeDamage(Destroyable target, int damage) {
        if (target != null) {
            target.hit(damage);
            reduceCharge(damage);
            fireRobotAction();
        }
    }

    
    // ------------------- Позиция робота -----------------

    /** Установить позицию объекта на поле
     *
     * @param pos позиция
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

    // ------------------- Двигаем робота -----------------

    /** Переместить робота
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

    /** Проверить возможность движения в данном направлении
     *
     * @param direction направление
     * @return возможность движения в данном направлении
     */
    private boolean isMovePossible(Direction direction) {
        if (direction == null) throw new NullPointerException();
        if(!position().hasNext(direction)) return false; // поле уже закончилось
        GameObject object = field.getObject(new MiddlePosition(position, direction));
        return object == null ||
                (object instanceof Door && ((Door)object).isOpen()) ||
                object instanceof Battery;
    }
    
    // ---------------------- Порождает события -----------------------------
    
    // присоединяет слушателя
    public void addRobotActionListener(RobotActionListener listener) {
        if (listener == null) throw new NullPointerException();
        listeners.add(listener);
    }
    
    // отсоединяет слушателя
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
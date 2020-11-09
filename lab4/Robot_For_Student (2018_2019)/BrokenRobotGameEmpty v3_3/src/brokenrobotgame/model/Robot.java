package brokenrobotgame.model;

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
public class Robot
{
    /** игровое поле */
    private final GameField field;
    /** слушатель событий робота */
    private final ArrayList<RobotActionListener> listeners = new ArrayList<>();
    /** позиция робота на поле */
    private CellPosition _position;
    /** батарея */
    private Battery battery;

    // ------------------- Устанавливаем связь с игровым полем -----------------

    /** Конструктор
     *
     * @param field игровое поле
     * @param battery батарея
     */
    public Robot(GameField field, Battery battery) {
        if (field == null || battery == null) throw new NullPointerException();
        this.field = field;
        this.battery = battery;
    }
	
  
    // ------------------- Робот "питается" от батарейки и может их менять -----------------

    /** Использовать батарею
     *
     * @param battery батарея
     */
    public void useBattery(Battery battery) {

        if (battery == null) throw new NullPointerException();
        // Новая батарейка должна находиться рядом с роботом или быть вне поля
        if (!_position.equals(battery.position()) && battery.position() != null)
            throw new IllegalArgumentException("Батарея не находится рядом с роботом");

        this.battery.destroy(); // Уничтожаем старую батарейку

        // Вставляем батарейку в робота
        this.battery = battery;
        field.removeBattery(battery);

        fireRobotAction(); // Генерируем событие
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
        if (battery.amountOfCharge() > 0)    // робот должен иметь заряд
        {
            Door door = field.door(new MiddlePosition(_position, direction));
            if (door != null)    // перед роботом дверь
            {
                // Взаимодействуем с дверью
                if (door.isOpen()) door.close();
                else door.open();

                reduceCharge(1); // Используем заряд
                fireRobotAction(); // Генерируем событие
            }
        }    
    }
    
    // ------------------- Позиция робота -----------------

    /** Получить позицию робота
     *
     * @return позиция
     */
    public CellPosition position() { return _position; }

    /** Задать позицию роботу
     *
     * @param pos позиция
     * @return успешность установки позиции
     */
    public boolean setPosition(CellPosition pos) {

        if (pos == null) throw new NullPointerException();
        if (pos.isValid()) {
            _position = pos;
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
        if(amountOfCharge() > 0 && isMovePossible(direction)) { // робот должен иметь заряд и способность двигаться
            setPosition(_position.next(direction)); // Перемещаемся в другую клетку
            reduceCharge(1); // Используем заряд
            fireRobotAction(); // Генерируем событие
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
        MiddlePosition nextMiddlePos = new MiddlePosition(_position, direction);
        Door door = field.door(nextMiddlePos);
        // встретилась стена или закрытая дверь
        return !field.isWall(nextMiddlePos) && (door == null || door.isOpen());
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
package brokenrobotgame.model;

import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.navigation.CellPosition;
import java.util.ArrayList;


/*
 * Robot - может передвигаться по полю, если имеется заряд внутренней батарейки; 
 * самостоятельно  определяет, куда может ходить; может использовать батарейки, 
 * находящиеся в поле
 */
public class Robot
{
    private GameField field;
    private CellPosition _position;
    private int amountOfCharge;
    private int chargeCapacity;

    // ------------------- Устанавливаем связь с игровым полем -----------------
    public Robot(GameField field, int chargeCapacity, int amountOfCharge) {
        if (field == null) throw new NullPointerException();
        this.field = field;
        this.amountOfCharge = amountOfCharge;
        this.chargeCapacity = chargeCapacity;
    }
	
  
    // ------------------- Робот "питается" от батарейки и может их менять -----------------

    public void useBattery(Battery battery) {
        
        // Новая батарейка должна находиться рядом с роботом или быть вне поля
        if(!_position.equals(battery._position))
            throw new IllegalArgumentException("Батарея не находится рядом с роботом");
               
        // Уничтожаем старую батарейку
	    battery.destroy();
	    amountOfCharge = 0;
	    chargeCapacity = 0;
        
        // Вставляем батарейку в робота

        
        // Генерируем событие

    }
	
    public int amountOfCharge() { return amountOfCharge; }

    public int chargeCapacity() { return chargeCapacity; }
    
    protected void reduceCharge(int delta) { chargeCapacity -= delta; }
	
    
    // ------------------- Робот может открывать и закрывать двери -----------------
    
    public void openCloseDoor(Direction direct) {
    
        if (amountОfСharge() > 0)    // робот должен иметь заряд
        {
            !!!if ()    // перед роботом дверь
            {
                // Открыть или закрыть дверь
                !!!
                        
                // Используем заряд
                reduceCharge(1);

                // Генерируем событие
                !!!
            }
        }    
    }
    
    // ------------------- Позиция робота -----------------
    
    public CellPosition position() {
        return _position;
    }
    
    protected boolean setPosition(CellPosition pos) {
        _position = pos;
        return true;
    }
	

    // ------------------- Двигаем робота -----------------
        
    public void makeMove(Direction direct){

        if(amountОfСharge() > 0)    // робот должен иметь заряд
        {
            if(moveIsPossible(direct)) // роботу есть куда ходить и ему ничего не мешает
            { 
                // Перемещаемся в другую клетку
                setPosition(position().next(direct));
                // Используем заряд
                reduceCharge(1);

                // Генерируем событие
                !!!
            }
        }    
    }
    
    private boolean moveIsPossible(Direction direct){

        // поле уже закончилось
        if(!position().hasNext(direct))     return false;

        MiddlePosition nextMiddlePos = new MiddlePosition(position(), direct);        
        
        // встретилась стена
        !!!if()    return false;

        // встретилась закрытая дверь
        !!!if()    return false;
        
        return true;
    }
    
    // ---------------------- Порождает события -----------------------------
    
    !!!
    
    // присоединяет слушателя
    public void addRobotActionListener(RobotActionListener l) { 
        !!! 
    }
    
    // отсоединяет слушателя
    public void removeRobotActionListener(RobotActionListener l) { 
        !!!
    } 
    
    // оповещает слушателей о событии
    protected void fireRobotAction() {
        !!!
    } 
}
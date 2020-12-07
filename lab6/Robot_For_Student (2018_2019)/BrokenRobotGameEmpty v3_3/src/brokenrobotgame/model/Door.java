package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/**
 * Door - чтобы пройти робот должен открыть дверь
 */
public class Door {

    /** дверь в вертикальной позиции */
    public  static final int VERTICAL = 1;
    /** дверь в горизонтальной позиции */
    public  static final int HORIZONTAL = 2;
    /** игровое поле */
    private final GameField _field;
    /** позици€ двери */
    private MiddlePosition _position;
    /** состо€ние двери */
    private boolean _isOpen = false;

    // ------------------- ”станавливаем св€зь с игровым полем -----------------

    /**  онструктор
     *
     * @param field игровое поле
     */
    public Door(GameField field) {
        _field = field;
    }

    // ----------------------- ѕозици€ двери -------------------------

    /** ѕолучить позицию двери
     *
     * @return позици€
     */
    public MiddlePosition position() { return _position; }

    /** «адать позицию двери
     *
     * @param pos позици€
     * @return успешность установки (false, если на этой позиции есть друга€ дверь или стена)
     */
    public boolean setPosition(MiddlePosition pos) {
        
        if(!_field.isWall(pos) && _field.door(pos)== null) { // позици€ свободна
            _position = pos;
            return true;
        }
        return false;
    }
    
    // ----------------------- ќриентаци€ двери -------------------------

    /** ѕолучить ориентацию двери на поле
     *
     * @return 1 - вертикальна€, 2 - горизонтальна€
     */
    public int orientation() {
        Direction direct = _position.direction();

        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;

        return -1;
    }  
    
    // ---------------------- ќткрывание/закрывание двери ----------------------

    /** ќткрыть дверь */
    public void open() { _isOpen = true; }
    /** «акрыть дверь */
    public void close() { _isOpen = false; }
    /** ќткрыта ли дверь */
    public boolean isOpen() { return _isOpen; }
}

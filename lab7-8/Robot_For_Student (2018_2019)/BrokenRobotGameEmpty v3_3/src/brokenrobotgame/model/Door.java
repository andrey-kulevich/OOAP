package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/** Door - чтобы пройти робот должен открыть дверь */
public class Door extends GameObject<MiddlePosition> {

    /** дверь в вертикальной позиции */
    public static final int VERTICAL = 1;
    /** дверь в горизонтальной позиции */
    public static final int HORIZONTAL = 2;
    /** состо€ние двери */
    private boolean isOpen = false;

    /**  онструктор
     *
     * @param field игровое поле
     */
    public Door(GameField field) { setField(field); }

    /** ѕолучить ориентацию двери на поле
     *
     * @return 1 - вертикальна€, 2 - горизонтальна€
     */
    public int orientation() {
        Direction direct = position.direction();
        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;
        return -1;
    }

    /** ќткрыть дверь */
    public void open() { isOpen = true; }
    /** «акрыть дверь */
    public void close() { isOpen = false; }
    /** ќткрыта ли дверь */
    public boolean isOpen() { return isOpen; }
}

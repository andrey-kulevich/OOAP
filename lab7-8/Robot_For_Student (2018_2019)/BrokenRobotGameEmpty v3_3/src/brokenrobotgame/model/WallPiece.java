package brokenrobotgame.model;

import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;

/** WallPiece - участок стены длиной не более одной ячейки */
public class WallPiece extends GameObject<MiddlePosition> {

    /** стена в вертикальной позиции */
    public  static final int VERTICAL = 1;
    /** стена в горизонтальной позиции */
    public  static final int HORIZONTAL = 2;

    /** Конструктор
     *
     * @param field игровое поле
     */
    public WallPiece(GameField field) { setField(field); }

    /** Получить ориентацию стены на поле
     *
     * @return 1 - вертикальная, 2 - горизонтальная
     */
    public int orientation() {
        Direction direct = position().direction();
        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;
        return -1;
    }
}
;
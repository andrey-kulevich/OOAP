import java.awt.*;

/** Класс, описывающий квадрат */
public class MySquare extends MyRectangle {

    /** Конктрутор
     *
     * @param pos левый верхний угол квадрата
     * @param size размер стороны квадрата
     */
    public MySquare(Point pos, int size) {
        super(pos, size, size);
    }

    /** Вернуть размер стороны квадрата
     *
     * @return размер стороны
     */
    public int size() { return width(); }
}

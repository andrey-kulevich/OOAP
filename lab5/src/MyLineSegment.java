import java.awt.*;

/** Класс, описывающий произвольный отрезок */
public class MyLineSegment extends MyGraphicPrimitive2D {

    /** Конструктор
     *
     * @param point1 координата первого края отрезка
     * @param point2 координата второго края отрезка
     */
    public MyLineSegment(Point point1, Point point2) {
        super(Math.min(point1.x, point2.x) == point1.x ? point1 : point2,
                Math.max(point1.x, point2.x) == point1.x ? point1 : point2);
    }

    /** Получить левый край отрезка
     *
     * @return левый край
     */
    public Point leftPoint() { return new Point(leftTop); }

    /** Получить правый край отрезка
     *
     * @return правый край
     */
    public Point rightPoint() { return new Point(rightBottom); }

    /** Получить длину отрезка
     *
     * @return длина
     */
    public int length() {
        return (int)Math.sqrt(Math.pow((leftTop.x - rightBottom.x), 2) + Math.pow((leftTop.y - rightBottom.y), 2));
    }

    @Override
    public void move(int dx, int dy) {
        leftTop.move(leftTop.x + dx, leftTop.y + dy);
        rightBottom.move(rightBottom.x + dx, rightBottom.y + dy);
    }

    @Override
    public boolean isCovering(Point point) {
        return (leftTop.x == rightBottom.x && leftTop.y == rightBottom.y
                && point.x == leftTop.x && point.y == leftTop.y) ||
                (leftTop.x != rightBottom.x && leftTop.y != rightBottom.y) &&
                        (point.x >= leftTop.x && point.x <= rightBottom.x &&
                                point.y >= leftTop.y && point.y <= rightBottom.y &&
                                (point.x - leftTop.x)/(rightBottom.x - leftTop.x) ==
                                        (point.y - leftTop.y)/(rightBottom.y - leftTop.y));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

import java.awt.*;

/** Класс, описывающий произвольный отрезок */
public class MyLineSegment extends MyGraphicPrimitive2D {

    /** Левая точка */
    private Point leftPoint;
    /** Правая точка */
    private Point rightPoint;

    /** Конструктор
     *
     * @param point1 координата первого края отрезка
     * @param point2 координата второго края отрезка
     */
    public MyLineSegment(Point point1, Point point2) {

        super(Math.min(point1.x, point2.x) == point1.x ?
                        point1.y <= point2.y ? point1 : new Point(point1.x, point2.y) :
                        point2.y <= point1.y ? point2 : new Point(point2.x, point1.y),
                Math.max(point1.x, point2.x) == point2.x ? new Point(point2) : new Point(point1));
        this.leftPoint = Math.min(point1.x, point2.x) == point1.x ? new Point(point1) : new Point(point2);
        this.rightPoint = Math.max(point1.x, point2.x) == point2.x ? new Point(point2) : new Point(point1);
    }

    /** Получить левый край отрезка
     *
     * @return левый край
     */
    public Point leftPoint() { return new Point(leftPoint); }

    /** Получить правый край отрезка
     *
     * @return правый край
     */
    public Point rightPoint() { return new Point(rightPoint); }

    /** Получить длину отрезка
     *
     * @return длина
     */
    public int length() {
        return (int)Math.sqrt(Math.pow((leftPoint.x - rightPoint.x), 2) + Math.pow((leftPoint.y - rightPoint.y), 2));
    }

    @Override
    public void move(int dx, int dy) {
        leftTop.move(leftTop.x + dx, leftTop.y + dy);
        rightBottom.move(rightBottom.x + dx, rightBottom.y + dy);
        leftPoint.move(leftPoint.x + dx, leftPoint.y + dy);
        rightPoint.move(rightPoint.x + dx, rightPoint.y + dy);
    }

    @Override
    public boolean isCovering(Point point) {
        return (leftPoint.x == rightPoint.x && leftPoint.y == rightPoint.y
                && point.x == leftPoint.x && point.y == leftPoint.y) ||
                (leftPoint.x != rightPoint.x && leftPoint.y != rightPoint.y) &&
                        (point.x >= leftPoint.x && point.x <= rightPoint.x &&
                                point.y >= leftPoint.y && point.y <= rightPoint.y &&
                                (point.x - leftPoint.x)/(rightPoint.x - leftPoint.x) ==
                                        (point.y - leftPoint.y)/(rightPoint.y - leftPoint.y));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

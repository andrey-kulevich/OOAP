import java.awt.*;

/** Класс, описывающий окружность */
public class MyCircle extends MyAreaPrimitive2D {

    /** координата центра окружности */
    private Point center;
    /** радиус окружности */
    private final int radius;

    /** Конструктор
     *
     * @param center горизонтальная координата центра окружности
     * @param radius радиус окружности
     */
    public MyCircle(Point center, int radius) {
        super(new Point(center.x - radius, center.y - radius),
                new Point(radius * 3 - center.x, radius * 3 - center.y));
        this.center = center;
        if (radius >= 0) this.radius = radius;
        else throw new IllegalArgumentException("Radius of circle cannot be negative");
    }

    /** Вернуть центр окружности
     *
     * @return центр
     */
    public Point center() { return new Point(center); }

    /** Вернуть радиус окруждности
     *
     * @return радиус
     */
    public int radius() { return radius; }

    @Override
    public void move(int dx, int dy) {
        leftTop.move(leftTop.x + dx, leftTop.y + dy);
        rightBottom.move(rightBottom.x + dx, rightBottom.y + dy);
        center.move(center.x + dx, center.y + dy);
    }

    @Override
    public boolean isCovering(Point point) {
        return Math.sqrt(Math.pow((center.x - point.x), 2) + Math.pow((center.y - point.y), 2)) < radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

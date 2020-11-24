import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/** Класс, описывающий прямоугольник */
public class MyRectangle extends MyAbstractPolygon {

    /** ширина прямоугольника */
    private final int width;
    /** высота прямоугольника */
    private final int height;

    /** Конструктор
     *
     * @param pos левый верхний угол прямоугольника
     * @param width ширина прямоугольника
     * @param height высота прямоугольника
     */
    public MyRectangle(Point pos, int width, int height) {
        super(new ArrayList<Point>(Arrays.asList(pos,
                new Point(pos.x + width, pos.y + height))));
        this.width = width;
        this.height = height;
    }

    /** Получить высоту прямоугольника
     *
     * @return высота
     */
    public int height() { return height; }

    /** Получить ширину прямоугольника
     *
     * @return ширина
     */
    public int width() { return width; }

    @Override
    public boolean isCovering(Point point) {
        return point.x >= leftTop.x && point.y >= leftTop.y &&
                point.x <= rightBottom.x && point.y <= rightBottom.y;
    }

    @Override
    public double area() { return width * height; }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

import java.util.ArrayList;
import java.awt.*;

/** Абстрактный класс, описывающий произвольный многоугольник */
public abstract class MyAbstractPolygon extends MyAreaPrimitive2D {

    /** множество вершин многоугольника */
    protected ArrayList<Point> points;

    /** Конструктор
     *
     * @param points множество вершин многоугольника
     */
    public MyAbstractPolygon(ArrayList<Point> points) {
        super(getCorner(points, corners.LEFT_TOP),
                getCorner(points, corners.RIGHT_BOTTOM));
        this.points = new ArrayList<>(points);
    }

    @Override
    public void move(int dx, int dy) {
        leftTop.move(leftTop.x + dx, leftTop.y + dy);
        rightBottom.move(rightBottom.x + dx, rightBottom.y + dy);
        for (Point p : points) {
            p.move(p.x + dx, p.y + dy);
        }
    }

    /** Получить угол прямоугольной области, в которую вписан многоугольник
     *
     * @param points множество вершин многоугольника
     * @param corner угол прямоугольной области
     * @return координата угла
     */
    private static Point getCorner(ArrayList<Point> points, corners corner) {
        if (points == null || points.size() == 0) throw new IllegalArgumentException("Invalid set of points");
        if (points.size() < 3) throw new IllegalArgumentException("This is not polygon (less than 3 points)");

        int left = points.get(0).x;
        int top = points.get(0).y;

        for (int i = 1; i < points.size(); i++) {
            if (points.get(i) == null) throw new NullPointerException();
            if (corner == corners.LEFT_TOP) {
                if (points.get(i).x < left) left = points.get(i).x;
                if (points.get(i).y < top) top = points.get(i).y;
            } else {
                if (points.get(i).x > left) left = points.get(i).x;
                if (points.get(i).y > top) top = points.get(i).y;
            }
        }

        return new Point(left, top);
    }

    /** вспомогательное перечисление для задания угла прямоугольной области, в которую вписан многоугольник */
    private enum corners {LEFT_TOP, RIGHT_BOTTOM}
}

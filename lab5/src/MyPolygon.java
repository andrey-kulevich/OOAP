import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/** Класс, описывающий произвольный многоугольник */
public class MyPolygon extends MyAbstractPolygon {

    /** Конструктор
     *
     * @param points множество вершин многоугольника
     */
    public MyPolygon(ArrayList<Point> points) { super(points); }

    /** Полуичть список вершин многоугольника
     *
     * @return список вершин
     */
    public ArrayList<Point> getPoints() { return new ArrayList<>(points); }

    @Override
    public boolean isCovering(Point point) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if ((points.get(i).y >= point.y) != (points.get(j).y >= point.y) &&
                    (point.x <= (points.get(j).x - points.get(i).x) * (point.y - points.get(i).y) /
                            (points.get(j).y - points.get(i).y) + points.get(i).x)) {
                result = !result;
            }
        }
        return result;
    }

    @Override
    public double area() {
        double area = 0.0;

        int j = points.size() - 1;
        for (int i = 0; i < points.size(); i++) {
            area += (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y);
            j = i;
        }

        return Math.abs(area / 2.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPolygon that = (MyPolygon) o;
        return Objects.equals(leftTop, that.leftTop) &&
                Objects.equals(rightBottom, that.rightBottom) &&
                points.equals(that.points);
    }
}

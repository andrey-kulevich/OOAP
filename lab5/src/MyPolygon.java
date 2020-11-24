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
        boolean result = false;
        int j = points.size() - 1;

        for (int i = 0; i < points.size(); i++) {
            if ((points.get(i).y < point.y && points.get(j).y >= point.y
                    || points.get(j).y < point.y && points.get(i).y >= point.y) &&
                    (points.get(i).x + (point.y - points.get(i).y) / (points.get(j).y - points.get(i).y) *
                            (points.get(j).x - points.get(i).x) < point.x))
                result = !result;
            j = i;
        }
        return result;
    }

    @Override
    public double area() {
        double result = 0;
        int min = points.get(0).y;

        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).y < min) min = points.get(i).y;
        }
        if (min < 0) {
            for (Point point : points) {
                point.move(point.x, point.y - min);
            }
        }
        result = Math.abs((points.get(0).x - points.get(points.size() - 1).x) *
                (points.get(0).y + points.get(points.size() - 1).y));

        for (int i = 1; i < points.size(); i++) {
            result = result + Math.abs((points.get(i).x - points.get(i - 1).x) *
                    (points.get(i).y + points.get(i - 1).y));
        }
        return result;
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

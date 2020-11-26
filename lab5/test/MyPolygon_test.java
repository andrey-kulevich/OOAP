import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPolygon_test {

    /** Передан нулевой список вершин */
    @Test
    void nullList() {

        boolean isError = false;
        try {
            MyPolygon polygon = new MyPolygon(null);
        } catch (IllegalArgumentException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Передан пустой список вершин */
    @Test
    void emptyList() {

        boolean isError = false;
        try {
            MyPolygon polygon = new MyPolygon(new ArrayList<>());
        } catch (IllegalArgumentException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Одна из вершин null */
    @Test
    void nullPos() {

        boolean isError = false;
        try {
            MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                    new Point(1,1),
                    null,
                    new Point(0,4))));
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Переместить многоугольник */
    @Test
    void movePolygon() {

        MyPolygon polygon = new MyPolygon(new ArrayList<Point>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));
        polygon.move(2, 3);
        Assert.assertTrue(polygon.leftTop.equals(new Point(2, 4)) &&
                polygon.rightBottom.equals(new Point(4, 7)) &&
                polygon.points.equals(new ArrayList<>(Arrays.asList(
                        new Point(3,4),
                        new Point(4,5),
                        new Point(2,7)))));
    }

    /** Точка внутри многоугольника */
    @Test
    void checkCoveringInside() {

        MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                new Point(10,10),
                new Point(20,20),
                new Point(0,40))));
        Assert.assertTrue(polygon.isCovering(new Point(10,20)));
    }

    /** Точка на вершине многоугольника */
    @Test
    void checkCoveringOnTheMountain() {

        MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));
        Assert.assertFalse(polygon.isCovering(new Point(1,1)));
    }

    /** Точка вне многоугольника */
    @Test
    void noCovering() {

        MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));
        Assert.assertFalse(polygon.isCovering(new Point(-3,2)));
    }

    /** Плащадь */
    @Test
    void getArea() {

        MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));
        Assert.assertEquals(2, polygon.area(), 0.0);
    }

    /** Комплексный тест */
    @Test
    void complexTest() {

        MyPolygon polygon = new MyPolygon(new ArrayList<>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));
        Assert.assertEquals(new Point(0, 1), polygon.getLeftTop());
        polygon.move(0,2);
        Assert.assertEquals(polygon.points, new ArrayList<>(Arrays.asList(
                new Point(1, 3),
                new Point(2, 4),
                new Point(0, 6))));
        Assert.assertTrue(polygon.isCovering(new Point(2,4)));
        Assert.assertEquals(2, polygon.area(), 0.0);
    }
}

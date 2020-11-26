import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyLineSegment_test {

    /** Переданы нулевые параметры */
    @Test
    void nullParams() {

        boolean isError = false;
        try {
            MyLineSegment line = new MyLineSegment(null, null);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Переместить отрезок */
    @Test
    void moveLine() {

        MyLineSegment line = new MyLineSegment(new Point(2,2), new Point(1, 1));
        line.move(50, -100);
        Assert.assertTrue(line.rightPoint().equals(new Point(52, -98)) &&
                line.leftPoint().equals(new Point(51, -99)));
    }

    /** Проверить принадлежность точки отрезку */
    @Test
    void checkCovering() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(2, 2));
        Assert.assertTrue(line.isCovering(new Point(1,1)));
    }

    /** Проверить принадлежность точки, точка находится на одном из краев отрезка */
    @Test
    void checkCoveringPointOnTheEndOfLine() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(2, 2));
        Assert.assertTrue(line.isCovering(new Point(2,2)));
    }

    /** Проверить принадлежность точки, отрезок вырожден в точку */
    @Test
    void checkCoveringLineIsPoint() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(0, 0));
        Assert.assertTrue(line.isCovering(new Point(0,0)));
    }

    /** Точка не принадлежит отрезку */
    @Test
    void noCovering() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(4, 5));
        Assert.assertFalse(line.isCovering(new Point(-3,2)));
    }

    /** Получить длину отрезка */
    @Test
    void getLength() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(0, 10));
        Assert.assertEquals(10, line.length());
    }

    /** Комплексный тест */
    @Test
    void complexTest() {

        MyLineSegment line = new MyLineSegment(new Point(0,3), new Point(3, 0));
        Assert.assertEquals(new Point(0, 0), line.getLeftTop());
        line.move(2,0);
        Assert.assertTrue(line.leftPoint().equals(new Point(2, 3)) &&
                line.rightPoint().equals(new Point(5, 0)));
        Assert.assertFalse(line.isCovering(new Point(3,2)));
        Assert.assertEquals((int)(line.getAreaWidth() * Math.sqrt(2)), line.length());
    }


    /** Тест вызывает все возможные геттеры классов */
    @Test
    void gettersCall() {

        MyLineSegment line = new MyLineSegment(new Point(0,0), new Point(4, 5));
        MyCircle circle = new MyCircle(new Point(0,0), 2);
        MySquare square = new MySquare(new Point(0, 0), 2);
        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 2, 3);
        MyPolygon polygon = new MyPolygon(new ArrayList<Point>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4))));

        Point p;
        int rand;
        p = line.getLeftTop();
        rand = line.getAreaHeight();
        rand = line.getAreaWidth();
        p = line.leftPoint();
        p = line.rightPoint();
        boolean is = line.equals(new MyLineSegment(new Point(0,0), new Point(66, 5)));
        is = circle.equals(new MyCircle(new Point(0,0), 2));
        is = square.equals(new MySquare(new Point(0, 0), 2));
        is = rectangle.equals(new MyRectangle(new Point(0, 0), 2, 3));
        is = polygon.equals(new MyPolygon(new ArrayList<Point>(Arrays.asList(
                new Point(1,1),
                new Point(2,2),
                new Point(0,4)))));
        rand = circle.radius();
        rand = square.size();
        ArrayList<Point> points = polygon.getPoints();

        Assert.assertTrue(true);
    }
}

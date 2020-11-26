import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyRectangle_test {

    /** Передана нулевая позиция */
    @Test
    void nullList() {

        boolean isError = false;
        try {
            MyRectangle rectangle = new MyRectangle(null, 1, 2);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Переместить многоугольник */
    @Test
    void moveRectangle() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        rectangle.move(2, 3);
        Assert.assertTrue(rectangle.leftTop.equals(new Point(2, 3)) &&
                rectangle.rightBottom.equals(new Point(7, 13)));
    }

    /** Точка внутри прямоугольника */
    @Test
    void checkCoveringInside() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        Assert.assertTrue(rectangle.isCovering(new Point(2, 3)));
    }

    /** Точка на грани прямоугольника */
    @Test
    void checkCoveringOnTheSide() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        Assert.assertTrue(rectangle.isCovering(new Point(0, 3)));
    }

    /** Точка на вершине прямоугольника */
    @Test
    void checkCoveringOnTheMountain() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        Assert.assertTrue(rectangle.isCovering(new Point(0, 0)));
    }

    /** Точка вне прямоугольника */
    @Test
    void noCovering() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        Assert.assertFalse(rectangle.isCovering(new Point(-2, 3)));
    }

    /** Площадь */
    @Test
    void getArea() {

        MyRectangle rectangle = new MyRectangle(new Point(0, 0), 5, 10);
        Assert.assertEquals(50, rectangle.area(),0.0);
    }

    /** Комплексный тест */
    @Test
    void complexTest() {

        MyRectangle rectangle = new MyRectangle(new Point(1, 1), 5, 3);
        Assert.assertEquals(new Point(1, 1), rectangle.getLeftTop());
        rectangle.move(0,2);
        Assert.assertEquals(rectangle.points, new ArrayList<>(Arrays.asList(
                new Point(1, 3),
                new Point(6,3),
                new Point(1, 6),
                new Point(6, 6))));
        Assert.assertTrue(rectangle.isCovering(new Point(2,4)));
        Assert.assertEquals(15, rectangle.area(), 0.0);
    }
}

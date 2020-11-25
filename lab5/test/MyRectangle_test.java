import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
}

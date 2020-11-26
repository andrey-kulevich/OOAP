import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class MyCircle_test {

    /** Передана нулевая позиция центра */
    @Test
    void nullPos() {

        boolean isError = false;
        try {
            MyCircle circle = new MyCircle(null, 2);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Передан отрицательный радиус */
    @Test
    void negativeRadius() {

        boolean isError = false;
        try {
            MyCircle circle = new MyCircle(new Point(1,1), -2);
        } catch (IllegalArgumentException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Переместить окружность */
    @Test
    void moveCircle() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        circle.move(2, 3);
        Assert.assertTrue(circle.leftTop.equals(new Point(2, 3)) &&
                circle.rightBottom.equals(new Point(6, 7)) &&
                circle.center().equals(new Point(4,5)));
    }

    /** Точка внутри окружности */
    @Test
    void checkCoveringInside() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        Assert.assertTrue(circle.isCovering(new Point(1,1)));
    }

    /** Точка на границе окружности */
    @Test
    void checkCoveringOnTheCorner() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        Assert.assertTrue(circle.isCovering(new Point(0,2)));
    }

    /** Точка вне окружности */
    @Test
    void noCovering() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        Assert.assertFalse(circle.isCovering(new Point(-3,2)));
    }

    /** Плащадь */
    @Test
    void getArea() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        Assert.assertEquals(Math.PI * 4, circle.area(), 0.0);
    }

    /** Комплексный тест */
    @Test
    void complexTest() {

        MyCircle circle = new MyCircle(new Point(2,2), 2);
        Assert.assertEquals(new Point(0, 0), circle.getLeftTop());
        circle.move(2,0);
        Assert.assertEquals(circle.center(), new Point(4, 2));
        Assert.assertTrue(circle.isCovering(circle.center()));
        Assert.assertEquals((int)Math.PI * 4, (int)circle.area());
    }
}

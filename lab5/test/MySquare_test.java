import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MySquare_test {

    /** Передана нулевая позиция */
    @Test
    void nullList() {

        boolean isError = false;
        try {
            MySquare square = new MySquare(null, 1);
        } catch (NullPointerException ex) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    /** Переместить многоугольник */
    @Test
    void moveSquare() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        square.move(2, 3);
        Assert.assertTrue(square.leftTop.equals(new Point(2, 3)) &&
                square.rightBottom.equals(new Point(7, 8)));
    }

    /** Точка внутри прямоугольника */
    @Test
    void checkCoveringInside() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        Assert.assertTrue(square.isCovering(new Point(2, 3)));
    }

    /** Точка на грани прямоугольника */
    @Test
    void checkCoveringOnTheSide() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        Assert.assertTrue(square.isCovering(new Point(0, 3)));
    }

    /** Точка на вершине прямоугольника */
    @Test
    void checkCoveringOnTheMountain() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        Assert.assertTrue(square.isCovering(new Point(0, 0)));
    }

    /** Точка вне прямоугольника */
    @Test
    void noCovering() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        Assert.assertFalse(square.isCovering(new Point(-2, 3)));
    }

    /** Площадь */
    @Test
    void getArea() {

        MySquare square = new MySquare(new Point(0, 0), 5);
        Assert.assertEquals(25, square.area(),0.0);
    }

    /** Комплексный тест */
    @Test
    void complexTest() {

        MySquare square = new MySquare(new Point(1, 1), 5);
        Assert.assertEquals(new Point(1, 1), square.getLeftTop());
        square.move(0,2);
        Assert.assertEquals(square.points, new ArrayList<>(Arrays.asList(
                new Point(1, 3),
                new Point(6,3),
                new Point(1,8),
                new Point(6,8))));
        Assert.assertTrue(square.isCovering(new Point(2,4)));
        Assert.assertEquals(25, square.area(), 0.0);
    }
}

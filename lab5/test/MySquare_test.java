import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
}

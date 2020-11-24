import java.awt.*;

/** Абстрактный класс определяет все графические примитивы, имеющие площадь */
public abstract class MyAreaPrimitive2D extends MyGraphicPrimitive2D {

    /** Конструктор
     *
     * @param leftTop координата левого верхнего угла прямоугольной области
     * @param rightBottom координата нижнего правого угла прямоугольной области
     */
    public MyAreaPrimitive2D(Point leftTop, Point rightBottom) {
        super(leftTop, rightBottom);
    }

    /** Вернуть площадь примитива
     *
     * @return площадь
     */
    public abstract double area();
}

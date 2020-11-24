import java.awt.*;
import java.util.Objects;

/** Абстрактный класс, описывающий прямоугольную область, в которой находится примитив */
public abstract class MyGraphicPrimitive2D {

    /** координата левого верхнего угла прямоугольной области */
    protected Point leftTop;
    /** координата нижнего правого угла прямоугольной области */
    protected Point rightBottom;

    /** Конструктор
     *
     * @param leftTop координата левого верхнего угла прямоугольной области
     * @param rightBottom координата нижнего правого угла прямоугольной области
     */
    public MyGraphicPrimitive2D(Point leftTop, Point rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    /** Получить координату левого верхнего угла прямоугольной области, в которую вписан примитив
     *
     * @return координата
     */
    public Point getLeftTop() { return new Point(leftTop); }

    /** Получить ширину прямоугольной области, в которую вписан примитив
     *
     * @return ширина
     */
    public int getAreaWidth() { return rightBottom.x - leftTop.x; }

    /** Получить высоту прямоугольной области, в которую вписан примитив
     *
     * @return высота
     */
    public int getAreaHeight() { return rightBottom.y - leftTop.y; }

    /** Сместить примитив
     *
     * @param dx смещение по горизонтали
     * @param dy смещение по вертикали
     */
    public abstract void move(int dx, int dy);

    /** Проверить, покрывает ли примитив заданную позицию
     *
     * @param point позиция
     * @return наличие покрытия
     */
    public abstract boolean isCovering(Point point);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyGraphicPrimitive2D that = (MyGraphicPrimitive2D) o;
        return Objects.equals(leftTop, that.leftTop) &&
                Objects.equals(rightBottom, that.rightBottom);
    }
}

/** Класс определяет все графические примитивы, имеющие площадь */
public abstract class MyAreaPrimitive2D extends MyGraphicPrimitive2D {

    /** Конструктор
     *
     * @param x координата левого верхнего угла по горизонтальной оси
     * @param y координата левого верхнего угла по вертикальной оси
     * @param width ширина примитива
     * @param height высота примитива
     */
    public MyAreaPrimitive2D(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /** Вернуть площадь примитива
     *
     * @return площадь
     */
    public abstract double area();
}

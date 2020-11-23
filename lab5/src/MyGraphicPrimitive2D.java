
/** Абстрактный класс, описывающий прямоугольную область, в которой находится примитив */
public abstract class MyGraphicPrimitive2D {

    /** координата левого верхнего угла по горизонтальной оси */
    private final int x;
    /** координата левого верхнего угла по вертикальной оси */
    private final int y;
    /** ширина примитива */
    private final int width;
    /** высота примитива */
    private final int height;

    /** Конструктор
     *
     * @param x координата левого верхнего угла по горизонтальной оси
     * @param y координата левого верхнего угла по вертикальной оси
     * @param width ширина примитива
     * @param height высота примитива
     */
    public MyGraphicPrimitive2D(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /** Сместить примитив
     *
     * @param dx смещение по горизонтали
     * @param dy смещение по вертикали
     */
    public abstract MyGraphicPrimitive2D move(int dx, int dy);

    /** Проверить, покрывает ли примитив заданную позицию
     *
     * @param posX позиция по горизонтали
     * @param posY позиция по вертикали
     * @return наличие покрытия
     */
    public abstract boolean isCovering(int posX, int posY);
}

package brokenrobotgame.model.navigation;

import java.util.HashMap;
import java.util.Objects;

/**
 * Позиция ячейки 
 */
public class CellPosition {

    /** Горизонтальный диапазон для позиций */
    private static CellRange _horizontalRange = new CellRange(0, 0);
    /** Вертикальный диапазон для позиций */
    private static CellRange _verticalRange = new CellRange(0, 0);
    /** Позиция по горизонтали */
    private final int _row;
    /** Позиция по вертикали */
    private final int _column;

    // ------------------ Позиция внутри диапазона ---------------------

    /** Конструктор
     *
     * @param row позиция по горизонтали
     * @param col позиция по вертикали
     */
    public CellPosition(int row, int col) {

        if(!isValid(row, col)) throw new IllegalArgumentException("Invalid position");
        _row = row;
        _column = col;
    }

    /** Проверить валидность позиции (если изменились диапазоны допустимых значений)
     *
     * @return валидность позиции
     */
    public boolean isValid() {
        return isValid(_row, _column);
    }

    /** Проверить валидность позиции
     *
     * @param row позиция по горизонтали
     * @param col позиция по вертикали
     * @return валидность позиции
     */
    public static boolean isValid(int row, int col) {
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    /** Получить копию позиции
     *
     * @return копия
     */
    @Override
    public CellPosition clone() {
        return new CellPosition(_row, _column);
    }

    // ----------------- Геттеры и сеттеры ---------------------------

    /** Установить диапазон возможных позиций по горизонтали
     *
     * @param min минимальный
     * @param max максимальный
     */
    public static void setHorizontalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _horizontalRange = new CellRange(min, max); }
    }

    /** Получить диапазон позиций по горизонтали
     *
     * @return диапазон
     */
    public static CellRange horizontalRange() {
      return _horizontalRange;
    }

    /** Установить диапазон возможных позиций по вертикали
     *
     * @param min минимальный
     * @param max максимальный
     */
    public static void setVerticalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _verticalRange = new CellRange(min, max); }
    }

    /** Получить диапазон позиций по вертикали
     *
     * @return диапазон
     */
    public static CellRange verticalRange() {
        return _verticalRange;
    }

    /** Получить позицию по горизонтали
     *
     * @return позиция
     */
    public int row() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _row;
    }

    /** Получить позицию по вертикали
     *
     * @return позиция
     */
    public int column() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _column;
    }
    
    // ------------------ Порождение и проверка смежных позиций ---------------------

    /** Сменить позицию
     *
     * @param direct направление смещения
     * @return новая позиция
     */
    public CellPosition next(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }

    /** Проверка возможности движения в данном направлении
     *
     * @param direct направлении
     * @return возможность движения
     */
    public boolean hasNext(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    /** Вернуть новую позицию в зависимости от направления
     *
     * @param row позиция по горизонтали
     * @param col позиция по вертикали
     * @param direct направление
     * @return массив из двух элементов: индекс строки, индекс столбца
     */
    private int[] calcNewPosition(int row, int col, Direction direct) {
        
        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int []> offset = new HashMap<>();
        
        offset.put(Direction.north(),   new int []{ 0, -1} );
        offset.put(Direction.south(),   new int []{ 0,  1} );
        offset.put(Direction.east(),    new int []{ 1,  0} );
        offset.put(Direction.west(),    new int []{-1,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
    
    // ------------------ Сравнение позиций ---------------------

    /** Сравнить две позиции
     *
     * @param other позиция, с которой сравнивается текущая
     * @return равенство позиций
     */
    @Override
    public boolean equals(Object other) {

        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        if(other instanceof CellPosition) {
            CellPosition otherPosition = (CellPosition)other;
            return _row == otherPosition._row && _column == otherPosition._column;
        }
        return false;
    }

    /** возвращает хэш значение объекта (нужно для поиска данной позиции в хэш таблице)
     *
     * @return хэш значение
     */
    @Override
    public int hashCode() { return Objects.hash(_row, _column); }
}

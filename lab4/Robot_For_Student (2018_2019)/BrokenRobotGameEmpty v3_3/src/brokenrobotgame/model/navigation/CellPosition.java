package brokenrobotgame.model.navigation;

import java.util.HashMap;

/**
 * ѕозици€ €чейки 
 */
public class CellPosition {

    /** √оризонтальный диапазон дл€ позиций */
    private static CellRange _horizontalRange = new CellRange(0, 0);
    /** ¬ертикальный диапазон дл€ позиций */
    private static CellRange _verticalRange = new CellRange(0, 0);
    /** ѕозици€ по горизонтали */
    private final int _row;
    /** ѕозици€ по вертикали */
    private final int _column;

    // ------------------ ѕозици€ внутри диапазона ---------------------

    /**  онструктор
     *
     * @param row позици€ по горизонтали
     * @param col позици€ по вертикали
     */
    public CellPosition(int row, int col) {

        if(!isValid(row, col)) throw new IllegalArgumentException("Invalid position");
        _row = row;
        _column = col;
    }

    /** ѕроверить валидность позиции (если изменились диапазоны допустимых значений)
     *
     * @return валидность позиции
     */
    public boolean isValid() {
        return isValid(_row, _column);
    }

    /** ѕроверить валидность позиции
     *
     * @param row позици€ по горизонтали
     * @param col позици€ по вертикали
     * @return валидность позиции
     */
    public static boolean isValid(int row, int col) {
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    /** ѕолучить копию позиции
     *
     * @return копи€
     */
    @Override
    public CellPosition clone() {
        return new CellPosition(_row, _column);
    }

    // ----------------- √еттеры и сеттеры ---------------------------

    /** ”становить диапазон возможных позиций по горизонтали
     *
     * @param min минимальный
     * @param max максимальный
     */
    public static void setHorizontalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _horizontalRange = new CellRange(min, max); }
    }

    /** ѕолучить диапазон позиций по горизонтали
     *
     * @return диапазон
     */
    public static CellRange horizontalRange() {
      return _horizontalRange;
    }

    /** ”становить диапазон возможных позиций по вертикали
     *
     * @param min минимальный
     * @param max максимальный
     */
    public static void setVerticalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _verticalRange = new CellRange(min, max); }
    }

    /** ѕолучить диапазон позиций по вертикали
     *
     * @return диапазон
     */
    public static CellRange verticalRange() {
        return _verticalRange;
    }

    /** ѕолучить позицию по горизонтали
     *
     * @return позици€
     */
    public int row() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _row;
    }

    /** ѕолучить позицию по вертикали
     *
     * @return позици€
     */
    public int column() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _column;
    }
    
    // ------------------ ѕорождение и проверка смежных позиций ---------------------

    /** —менить позицию
     *
     * @param direct направление смещени€
     * @return нова€ позици€
     */
    public CellPosition next(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }

    /** ѕроверка возможности движени€ в данном направлении
     *
     * @param direct направлении
     * @return возможность движени€
     */
    public boolean hasNext(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    /** ¬ернуть новую позицию в зависимости от направлени€
     *
     * @param row позици€ по горизонтали
     * @param col позици€ по вертикали
     * @param direct направление
     * @return массив из двух элементов: индекс строки, индекс столбца
     */
    private int[] calcNewPosition(int row, int col, Direction direct) {
        
        // “аблица смещени€ дл€ различных направлений: (горизонталь,вертикаль)
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
    
    // ------------------ —равнение позиций ---------------------

    /** —равнить две позиции
     *
     * @param other позици€, с которой сравниваетс€ текуща€
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
}

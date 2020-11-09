package brokenrobotgame.model.navigation;

import java.util.HashMap;

/**
 * ������� ������ 
 */
public class CellPosition {

    /** �������������� �������� ��� ������� */
    private static CellRange _horizontalRange = new CellRange(0, 0);
    /** ������������ �������� ��� ������� */
    private static CellRange _verticalRange = new CellRange(0, 0);
    /** ������� �� ����������� */
    private final int _row;
    /** ������� �� ��������� */
    private final int _column;

    // ------------------ ������� ������ ��������� ---------------------

    /** �����������
     *
     * @param row ������� �� �����������
     * @param col ������� �� ���������
     */
    public CellPosition(int row, int col) {

        if(!isValid(row, col)) throw new IllegalArgumentException("Invalid position");
        _row = row;
        _column = col;
    }

    /** ��������� ���������� ������� (���� ���������� ��������� ���������� ��������)
     *
     * @return ���������� �������
     */
    public boolean isValid() {
        return isValid(_row, _column);
    }

    /** ��������� ���������� �������
     *
     * @param row ������� �� �����������
     * @param col ������� �� ���������
     * @return ���������� �������
     */
    public static boolean isValid(int row, int col) {
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    /** �������� ����� �������
     *
     * @return �����
     */
    @Override
    public CellPosition clone() {
        return new CellPosition(_row, _column);
    }

    // ----------------- ������� � ������� ---------------------------

    /** ���������� �������� ��������� ������� �� �����������
     *
     * @param min �����������
     * @param max ������������
     */
    public static void setHorizontalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _horizontalRange = new CellRange(min, max); }
    }

    /** �������� �������� ������� �� �����������
     *
     * @return ��������
     */
    public static CellRange horizontalRange() {
      return _horizontalRange;
    }

    /** ���������� �������� ��������� ������� �� ���������
     *
     * @param min �����������
     * @param max ������������
     */
    public static void setVerticalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _verticalRange = new CellRange(min, max); }
    }

    /** �������� �������� ������� �� ���������
     *
     * @return ��������
     */
    public static CellRange verticalRange() {
        return _verticalRange;
    }

    /** �������� ������� �� �����������
     *
     * @return �������
     */
    public int row() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _row;
    }

    /** �������� ������� �� ���������
     *
     * @return �������
     */
    public int column() {
        if(!isValid()) throw new IllegalArgumentException("Invalid position");
        return _column;
    }
    
    // ------------------ ���������� � �������� ������� ������� ---------------------

    /** ������� �������
     *
     * @param direct ����������� ��������
     * @return ����� �������
     */
    public CellPosition next(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }

    /** �������� ����������� �������� � ������ �����������
     *
     * @param direct �����������
     * @return ����������� ��������
     */
    public boolean hasNext(Direction direct) {
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    /** ������� ����� ������� � ����������� �� �����������
     *
     * @param row ������� �� �����������
     * @param col ������� �� ���������
     * @param direct �����������
     * @return ������ �� ���� ���������: ������ ������, ������ �������
     */
    private int[] calcNewPosition(int row, int col, Direction direct) {
        
        // ������� �������� ��� ��������� �����������: (�����������,���������)
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
    
    // ------------------ ��������� ������� ---------------------

    /** �������� ��� �������
     *
     * @param other �������, � ������� ������������ �������
     * @return ��������� �������
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

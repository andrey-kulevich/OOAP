package brokenrobotgame.model.navigation;

/**
 * ������� �� ����� ���� �����
 */
public class MiddlePosition
{
    // ---------------------------------------------------------------------
    /* ���������� ��� ������� ������ � ����������� �� ���. 
     � ��������������� ���� ������������ ������ ����������� "�����" � "�����", 
     ����� ������� ������� ������ � ����� ���� */
    // ---------------------------------------------------------------------

    /** ������, ������� ������� ��������� */
    private CellPosition _cellPosition;
    /** ���� �� ������� ������ ������ */
    private Direction _direction;

    /** ������� ����������� ������� ������
     *
     * @return �����������
     */
    public Direction direction() { return _direction; }

    /** ������� ������, ���� �� ������ ������� �����������
     *
     * @return �������
     */
    public CellPosition cellPosition() { return _cellPosition; }
    
    // ------------------ ���������� "�������" ������� ---------------------

    /** �����������
     *
     * @param cellPos ������, ������� ������� ���������
     * @param direct ���� �� ������� ������ ������
     */
    public MiddlePosition(CellPosition cellPos, Direction direct) {
        
        if(!cellPos.isValid()) throw new IllegalArgumentException("Invalid position");
        _cellPosition = cellPos;
        _direction = direct;
        normalize();
    }

    /** �������� ������� � ����������� �� ����������� � ������ ��� ������ */
    private void normalize() {
       
        // �� ����������� �������� � ����������� "�����"
        if(_direction.equals(Direction.south()) && _cellPosition.hasNext(_direction)) {
            _cellPosition = _cellPosition.next(_direction);
            _direction = Direction.north();
        }
        
        // �������� � ����������� "�����"
        if(_direction.equals(Direction.east()) && _cellPosition.hasNext(_direction)) {
            _cellPosition = _cellPosition.next(_direction);
            _direction = Direction.west();
        }
    }

    /** ������� ����� ������� �������
     *
     * @return �����
     */
    @Override
    public MiddlePosition clone() { return new MiddlePosition(_cellPosition, _direction); }

    /** ������� ��������� ������� ������� � �������� �����������
     *
     * @param direct �����������
     * @return ����� ������� �������
     */
    public MiddlePosition next(Direction direct) {
		
        // � �������� ����������� ������� ������
        if(_cellPosition.hasNext(direct)) return new MiddlePosition(_cellPosition.next(direct), _direction);
        // � �������� ����������� ��� ������, �� � ������� ������ ������� ������ "�������" �������
        else if(_direction.isOpposite(direct)) return  new MiddlePosition(_cellPosition, _direction.opposite());
        else return null;
    }

    /** ���������, ���� �� � ������ ����������� ������ ������
     *
     * @param direct �����������
     * @return ������� ������
     */
    public boolean hasNext(Direction direct) {
	    return _cellPosition.hasNext(direct) || _direction.isOpposite(direct);
    }
	

    // ------------------ ���������� ������� ����� ---------------------

    /** �������� ������ ������ ������������ ��������� �������
     *
     * @param direct �����������
     * @return ������� ������������ ��������� (null, ���� �� �������)
     */
    public CellPosition cellPosition(Direction direct) {
        // ��� ��������� � ������
	    if(_direction.isOpposite(direct)) return _cellPosition.clone();
	    // ���������� �������� ������ ������
	    else if(_direction.equals(direct) && _cellPosition.hasNext(direct)) return _cellPosition.next(direct);
        else return null;
    }

    /** ����������, ���� �� � ������ ����������� ������
     *
     * @param direct �����������
     * @return ������� ������ � ������ �����������
     */
	public boolean hasCellPosition(Direction direct) {
        return _direction.isOpposite(direct) || _cellPosition.hasNext(direct);
	}
        
    // ------------------ ��������� ������� ---------------------

    /** �������� �� ��������� ���� ��������� �������
     *
     * @param other ������ �������
     * @return ��������� �������
     */
    public boolean equals(Object other) {
        
        if(other instanceof MiddlePosition) {
            MiddlePosition otherPosition = (MiddlePosition)other;
            return _cellPosition.equals(otherPosition._cellPosition) && _direction.equals(otherPosition._direction);
        }
        return false;
    }
}
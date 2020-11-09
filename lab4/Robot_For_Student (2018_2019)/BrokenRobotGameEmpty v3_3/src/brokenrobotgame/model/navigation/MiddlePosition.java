package brokenrobotgame.model.navigation;

/**
 * Позиция на стыке двух ячеек
 */
public class MiddlePosition
{
    // ---------------------------------------------------------------------
    /* Определяем как позицию ячейки и направление от нее. 
     В нормализованном виде используются только направления "запад" и "север", 
     кроме крайних позиций справа и снизу поля */
    // ---------------------------------------------------------------------

    /** Ячейка, границу которой описываем */
    private CellPosition _cellPosition;
    /** Одна из четырех границ ячейки */
    private Direction _direction;

    /** Вернуть описываемую границу ячейки
     *
     * @return направление
     */
    public Direction direction() { return _direction; }

    /** Позиция ячейки, одна из границ которой описывается
     *
     * @return позиция
     */
    public CellPosition cellPosition() { return _cellPosition; }
    
    // ------------------ Порождение "средних" позиций ---------------------

    /** Конструктор
     *
     * @param cellPos ячейка, границу которой описываем
     * @param direct одна из четырех границ ячейки
     */
    public MiddlePosition(CellPosition cellPos, Direction direct) {
        
        if(!cellPos.isValid()) throw new IllegalArgumentException("Invalid position");
        _cellPosition = cellPos;
        _direction = direct;
        normalize();
    }

    /** Привести позицию и направление по возможности к северу или западу */
    private void normalize() {
       
        // По возможности приводим к направлению "север"
        if(_direction.equals(Direction.south()) && _cellPosition.hasNext(_direction)) {
            _cellPosition = _cellPosition.next(_direction);
            _direction = Direction.north();
        }
        
        // Приводим к направлению "запад"
        if(_direction.equals(Direction.east()) && _cellPosition.hasNext(_direction)) {
            _cellPosition = _cellPosition.next(_direction);
            _direction = Direction.west();
        }
    }

    /** Создать копию средней позиции
     *
     * @return копия
     */
    @Override
    public MiddlePosition clone() { return new MiddlePosition(_cellPosition, _direction); }

    /** Вернуть следующую среднюю позицию в заданном направлении
     *
     * @param direct направление
     * @return новая средняя позиция
     */
    public MiddlePosition next(Direction direct) {
		
        // В заданном направлении имеется ячейка
        if(_cellPosition.hasNext(direct)) return new MiddlePosition(_cellPosition.next(direct), _direction);
        // В заданном направлении нет ячейки, но у крайней ячейки имеется вторая "средняя" позиция
        else if(_direction.isOpposite(direct)) return  new MiddlePosition(_cellPosition, _direction.opposite());
        else return null;
    }

    /** Проверить, если ли в данном направлении другая ячейка
     *
     * @param direct направление
     * @return наличие ячейки
     */
    public boolean hasNext(Direction direct) {
	    return _cellPosition.hasNext(direct) || _direction.isOpposite(direct);
    }
	

    // ------------------ Порождение позиций ячеек ---------------------

    /** Получить другую ячейку относительно срединной позиции
     *
     * @param direct направление
     * @return позиция относительно срединной (null, если не найдена)
     */
    public CellPosition cellPosition(Direction direct) {
        // Уже находимся в ячейке
	    if(_direction.isOpposite(direct)) return _cellPosition.clone();
	    // Необходимо получить другую ячейку
	    else if(_direction.equals(direct) && _cellPosition.hasNext(direct)) return _cellPosition.next(direct);
        else return null;
    }

    /** Определить, есть ли в данном направлении ячейка
     *
     * @param direct направление
     * @return наличие ячейки в данном направлении
     */
	public boolean hasCellPosition(Direction direct) {
        return _direction.isOpposite(direct) || _cellPosition.hasNext(direct);
	}
        
    // ------------------ Сравнение позиций ---------------------

    /** Проверка на равенство двух срединных позиций
     *
     * @param other другая позиция
     * @return равенство позиций
     */
    public boolean equals(Object other) {
        
        if(other instanceof MiddlePosition) {
            MiddlePosition otherPosition = (MiddlePosition)other;
            return _cellPosition.equals(otherPosition._cellPosition) && _direction.equals(otherPosition._direction);
        }
        return false;
    }
}
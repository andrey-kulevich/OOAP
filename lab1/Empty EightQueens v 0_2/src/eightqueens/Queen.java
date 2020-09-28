package eightqueens;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

/** Ферзь, который самостоятельно ищет приемлемую позицию.
 */
public class Queen {  
    
    /* =========================== Свойства =============================== */
    
    /* ------------------------ Позиция на доске --------------------------- */
    private final int col;
    private int row;

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    /* --------------------------- Сосед слева ---------------------------- */
    private final Queen neighbor; 
    
    
    /* --------------------------- Доска ---------------------------- */
    private final Desk owner; 
  
    
    /* =========================== Операции =============================== */

    /* ---------------------------- Порождение ---------------------------- */
    public Queen(Desk owner, int col, int row, Queen neighbor) {
        this.owner = owner;
        this.col = col;
        this.row = row;
        this.neighbor = neighbor;
    }
    
    /* -------------------------- Поиск решения --------------------------- */
    
    /** Ищет НОВУЮ приемлемую позицию для себя.
    *
    * @return признак того, что позиция найдена
    */
    public boolean findNewAcceptablePosition() {
        boolean isFound = true;

        //Ищем  позицию собственными силами...
        //Если ферзь имеет приемлемую позицию или он еще не искал ее
        if (row != owner.posAboveDesk() || row == Desk.posBelowDesk()) {
            do { //Делать
                row++; //Делаем шаг вверх
            } while (neighbor != null
                    && neighbor.canAttack(col, row)
                    && row != owner.posAboveDesk()); //Пока позиция не найдена и не вышли за пределы доски
        }

        if (row == owner.posAboveDesk()) { //Если не удалось найти позицию собственными силами
            if (neighbor != null) {
                isFound = neighbor.findNewAcceptablePosition();
                if (isFound) {
                    row = 0;
                    isFound = findNewAcceptablePosition();
                }
            } else {
                isFound = false; //Приемлемая позиция не может быть найдена
            }
        }

        return isFound;
    }
    
    
    /** Атакует ли ферзь или его соседи слева указанную позицию.
     * 
     * @param col номер столбца (1...owner.colCount())
     * @param row номер строки (1...owner.rowCount(), owner.posAboveDesk(), owner.posBelowDesk())
     * @return признак того, что ферзь и его соседи слева атакуют указанную позицию
     */ 
    public boolean canAttack(int col, int row) {
        boolean isAttack;
        
        // Атакует ферзь
        isAttack = (this.row != owner.posAboveDesk())
                    && (row != Desk.posBelowDesk())
                    && (this.col == col || this.row == row 
                        || Math.abs(this.col-col) == Math.abs(this.row - row));
        
        // Атакуют ли соседи, если ферзь не атакует
        if(!isAttack && neighbor != null)
        { isAttack = neighbor.canAttack(col, row); }
        
        return isAttack;
    }
    
    
    
    /* -------------------------- Отрисовка --------------------------- */
    
    /** Отрисовка ферзя
     * @param g графический контекст, в котором происходит отрисовка ферзя.
    */
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((col-1)*Desk.CELL_SIZE, (owner.rowCount() - row)*Desk.CELL_SIZE, 
                    Desk.CELL_SIZE, Desk.CELL_SIZE);
    }
}
package eightqueens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/** Класс доски, содержащий 8 ферзей (модель и представление).
*/
public class Desk extends JPanel{

    /* ========================== Константы =============================== */

    static final int CELL_SIZE = 30;
    
    /* =========================== Свойства =============================== */
    
    /** Размеры доски (в количестве клеток).
    */
    public int colCount(){
        return 8;
    }
    public int rowCount(){
        return 8;
    }
    
    public int posAboveDesk() {
        return rowCount()+1;
    }

    public static int posBelowDesk() {
        return 0;
    }
    
    /* Список ферзей */    
    private Queen[] queens;

    public Queen getQueen(int col) {

        for(Queen q : queens) {

            if(q.col() == col ) {
                return q;
            }
        }

        return null;
    }

    public int queenCount() {
        return queens.length;
    }

    /* =========================== Операции =============================== */

    /* ---------------------------- Порождение ---------------------------- */
    
    /** Порождает доску со стандартным расположением ферзей
    */     
    public static Desk buildStandartDesk() {
        Desk d = new Desk();
        d.setQueens( d.initQueensPos() );
        
        return d;
    }
    
    /** Порождает доску с произвольным расположеним ферзей
    */    
    public static Desk buildDesk( Point[] queens_pos) {
        Desk d = new Desk();
        d.setQueens( queens_pos );
        
        return d;
    }
    
    private Desk( ) {
        // Настравиаем визуальное отображение доски
        setBounds(0, 0, colCount()*CELL_SIZE, rowCount()*CELL_SIZE);
        setPreferredSize(new Dimension(colCount()*CELL_SIZE, rowCount()*CELL_SIZE) );
        setBackground(Color.white);                
    }
    
    private Point[] initQueensPos() {
        Point[] queens_pos = new Point[ colCount() ];

        for(int i = 0; i < colCount(); i++) {
            queens_pos[i] = new Point( i+1, posBelowDesk() );
        }

        return queens_pos;
    }       
    
    private void setQueens(Point[] queens_pos) {
        
        // Расставляем ферзей
        queens = new Queen[queens_pos.length];
        
        // Устанавливаем первого ферзя
        queens[0] = new Queen(this, queens_pos[0].x, queens_pos[0].y, null);

        // Устанавливаем остальных ферзей
        for( int i = 1; i < queens_pos.length; i++  ) {
           int col = queens_pos[i].x;
           int row = queens_pos[i].y;
           
           queens[i] = new Queen(this, col, row, queens[i-1]);
        }
    }
    
    
    /* -------------------------- Поиск решения --------------------------- */
    
    /** Ищет первое решение и отображает его
    */
    public void firstSolution()
    {
        for(Queen q : queens)
        {
            q.findNewAcceptablePosition();
        }
        repaint();
    }

    /** Ищет новое решение и отображает его
    */
    public void newSolution()
    {
        queens[queens.length-1].findNewAcceptablePosition();
        repaint();
    }
    
    /* -------------------------- Отрисовка --------------------------- */
    
    /** Отрисовывает доску
    */
    @Override
    public void paint(Graphics g)
    {
        // отрисовка черных клеток
        super.paint(g);
        g.setColor(Color.black);
        for(int col=0; col < colCount(); col++)
        {
            for(int row=0; row < rowCount()/2; row++)
            {
                g.fillRect(row*2*CELL_SIZE+CELL_SIZE-CELL_SIZE*(col%2), col*CELL_SIZE, 
                            CELL_SIZE, CELL_SIZE);
            }
        }
        
        // отрисовка ферзей
        for(Queen q: this.queens){
            q.paint(g);
        }
    }
}

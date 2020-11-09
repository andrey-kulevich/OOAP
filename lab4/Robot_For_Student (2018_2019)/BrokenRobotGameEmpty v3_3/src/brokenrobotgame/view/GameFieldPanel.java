
package brokenrobotgame.view;

import brokenrobotgame.model.Battery;
import brokenrobotgame.model.Door;
import brokenrobotgame.model.GameModel;
import brokenrobotgame.model.Robot;
import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GameFieldPanel extends JPanel implements KeyListener {
    
    // ------------------------------ ������ ���� ------------------------------
    private final GameModel _model;
    
    
    // ------------------------------ ������� ---------------------------------
    
    private static final int CELL_SIZE = 30;
    private static final int GAP = 2;
    private static final int FONT_HEIGHT = 15;

    // ------------------------- �������� ���������� ---------------------------
    
    private static final Color BACKGROUND_COLOR = new Color(175, 255, 175);
    private static final Color GRID_COLOR = Color.GREEN;

    
    
    public GameFieldPanel(GameModel model){
        _model = model;
        
        // ������������� �������
        int width = 2*GAP + CELL_SIZE * _model.field().width();
        int height = 2*GAP + CELL_SIZE * _model.field().height();
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.RED);
        
        _model.robot().addRobotActionListener(new GameFieldPanel.RepaintByAction());
        addKeyListener(this);
    }
    
    /** ������ ���� */
    @Override
    public void paintComponent(Graphics g) {
        
        // ��������� ����
        int width  = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);   // ���������������� ���� ����
              
        // ��������� �����
        drawGrid(g);

        // ��������� ������
        Point lefTop = leftTopCell(_model.robot().position());
        drawRobot(g, _model.robot(), lefTop);
        
        // ��������� ��������� ������, ���� � ������
        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        boolean isPostLastColumn;
        do
        {
            boolean isPostLastRow;
            do
            {
                // ��������� ���������
                Battery battery = _model.field().battery(pos);
                if(battery != null)
                {
                    lefTop = leftTopCell(pos);
                    drawBattery(g, battery, lefTop);
                }
                
                // ��������� ���� � ������
                Direction d = Direction.north();
                for(int n = 1; n<=4; n++)
                {
                   d = d.clockwise();
                   MiddlePosition mpos = new MiddlePosition(pos, d);
                   
                   if(_model.field().isWall(mpos))      // ��������� �����
                   {
                        lefTop = leftTopCell(mpos);
                        drawWall(g, lefTop, mpos.direction());
                   }
                   
                   else 
                   {
                       Door door = _model.field().door(mpos);
                       if(door != null)                 // ��������� �����
                       {
                            lefTop = leftTopCell(mpos);
                            drawDoor(g, door, lefTop);
                       }
                   }
                }
                
                isPostLastRow = !pos.hasNext(direct);
                if(!isPostLastRow)    { pos = pos.next(direct); }
            }
            while(!isPostLastRow);
            
            direct = direct.opposite();
            
            isPostLastColumn = !pos.hasNext(Direction.south());
            if(!isPostLastColumn)    { pos = pos.next(Direction.south()); }
        }
        while( !isPostLastColumn );

        // ��������� ������� �������
        lefTop = leftTopCell(_model.targetPosition());
        drawTargetPosition(g, lefTop);
    }
    
    
    private void drawGrid(Graphics g) {
        int width  = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);
        
        for(int i = 1; i <= _model.field().width()+1; i++)  // ������������ �����
        {
            int x = GAP + CELL_SIZE*(i-1);
            g.drawLine(x, 0, x, height);
        }

        for(int i = 1; i <= _model.field().width()+1; i++)  // �������������� �����
        {
            int y = GAP + CELL_SIZE*(i-1);
            g.drawLine(0, y, width, y);
        }

    }
    
    private void drawRobot(Graphics g, Robot robot, Point lefTop) {
        g.setColor(Color.RED);   

        String str = "�/" + robot.amountOfCharge();
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);

        g.setColor(Color.BLACK);   // ��������������� ���� ����
    }
    
    private void drawBattery(Graphics g, Battery battery, Point lefTop) {
        g.setColor(Color.RED);   

        String str = "�/" + battery.amountOfCharge();
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);
    
        g.setColor(Color.BLACK);   // ��������������� ���� ����
    }
            
    private void drawWall(Graphics g, Point lefTop, Direction direct) {
        g.setColor(Color.RED);   

        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))
        {
            g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE);
        }
        else
        {
            g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);                   
        }    

        g.setColor(Color.BLACK);   // ��������������� ���� ����
    }

    private void drawTargetPosition(Graphics g, Point lefTop) {
        g.setColor(Color.RED);   

        g.drawString("�", lefTop.x+CELL_SIZE/3, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);

        g.setColor(Color.BLACK);   // ��������������� ���� ����
    }

    private void drawDoor(Graphics g, Door door, Point lefTop) {
        g.setColor(Color.BLUE);   

        Direction direct = door.position().direction();
        
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))  // ���. �����
        {
            if(!door.isOpen())  // ����� ��������
            {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE);
            }
            else                // ����� ��������
            {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE/5);
                g.drawLine(lefTop.x, lefTop.y+4*CELL_SIZE/5, lefTop.x, lefTop.y+CELL_SIZE);
            }
        }
        else                                                                    // ����. �����
        {
            if(!door.isOpen())  // ����� ��������
            {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);
            }
            else                // ����� ��������
            {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE/5, lefTop.y);
                g.drawLine(lefTop.x+4*CELL_SIZE/5, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);
            }    
        }    

        g.setColor(Color.BLACK);   // ��������������� ���� ����
    }
    
    private Point leftTopCell(CellPosition pos) {
        
        int left = GAP + CELL_SIZE * (pos.column()-1);
        int top = GAP + CELL_SIZE * (pos.row()-1);
        
        return new Point(left, top);
    }

    private Point leftTopCell(MiddlePosition mpos) {
        
        Point p = leftTopCell(mpos.cellPosition());
        
        if(mpos.direction().equals(Direction.south()))
        {
            p.y += CELL_SIZE;
            //p.x += CELL_SIZE;
        }
        else if(mpos.direction().equals(Direction.east()))
        {
            p.x += CELL_SIZE;
        }
        
        return p;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.isControlDown())
        {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // ����/���� ����� ������
                _model.robot().openCloseDoor(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // ����/���� ����� �����
                _model.robot().openCloseDoor(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // ����/���� ����� �����
                _model.robot().openCloseDoor(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // ����/���� ����� ������
                _model.robot().openCloseDoor(Direction.east());
            }
        }
        else
        {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // ������������ �����
                _model.robot().makeMove(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // ������������ ����
                _model.robot().makeMove(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // ������������ �����
                _model.robot().makeMove(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // ������������ ������
                _model.robot().makeMove(Direction.east());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_SPACE) { // ����� ���������
                Battery battery = _model.field().battery(_model.robot().position());
                if(battery != null) _model.robot().useBattery(battery);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
            
    
    private class RepaintByAction implements RobotActionListener{

        @Override
        public void robotMadeMove(RobotActionEvent e) {
            repaint();
        }
    }
}
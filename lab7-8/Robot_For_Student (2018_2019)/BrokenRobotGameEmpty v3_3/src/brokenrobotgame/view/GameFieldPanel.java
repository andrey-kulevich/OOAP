
package brokenrobotgame.view;

import brokenrobotgame.model.*;
import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.Robots.RobotPainter;
import brokenrobotgame.model.Robots.RobotRadiationMeter;
import brokenrobotgame.model.Robots.RobotTemperatureMeter;
import brokenrobotgame.model.events.RobotActionEvent;
import brokenrobotgame.model.events.RobotActionListener;
import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;
import brokenrobotgame.model.navigation.MiddlePosition;
import brokenrobotgame.model.quantities.RadiationSievert;
import brokenrobotgame.model.quantities.TemperatureKelvin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GameFieldPanel extends JPanel implements KeyListener {
    
    // ------------------------------ Модель игры ------------------------------
    private final GameModel _model;

    // ------------------------------ Размеры ---------------------------------
    
    private static final int CELL_SIZE = 30;
    private static final int GAP = 2;
    private static final int FONT_HEIGHT = 15;

    // ------------------------- Цветовое оформление ---------------------------
    
    private static final Color BACKGROUND_COLOR = new Color(175, 255, 175);
    private static final Color GRID_COLOR = Color.GREEN;
    private static final Color FILLED_CELL_COLOR = new Color(219, 170, 136);


    public GameFieldPanel(GameModel model) {
        _model = model;
        
        // Инициализация графики
        int width = 2*GAP + CELL_SIZE * _model.field().width();
        int height = 2*GAP + CELL_SIZE * _model.field().height();
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.RED);
        
        _model.robot().addRobotActionListener(new GameFieldPanel.RepaintByAction());
        addKeyListener(this);
    }
    
    /** Рисуем поле */
    @Override
    public void paintComponent(Graphics g) {
        
        // Отрисовка фона
        int width  = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);   // восстнанваливаем цвет пера
              
        // Отрисовка сетки
        drawGrid(g);
        
        // Отрисовка остальных юнитов, стен и дверей
        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        Point lefTop;
        boolean isPostLastColumn;
        do {
            boolean isPostLastRow;
            do {
                // Отрисовка стен и дверей
                Direction d = Direction.north();
                for(int n = 1; n<=4; n++) {
                   d = d.clockwise();

                   MiddlePosition mpos = new MiddlePosition(pos, d);
                   GameObject object = _model.field().getObject(mpos);

                   if(object instanceof WallPiece) { // Отрисовка стены
                        lefTop = leftTopCell(mpos);
                        drawWall(g, lefTop, mpos.direction());
                   } else if (object instanceof Door) { // Отрисовка двери
                        lefTop = leftTopCell(mpos);
                        drawDoor(g, (Door) object, lefTop);
                   }
                }

                // Отрисовка закрашенных клеток
                if (_model.field().isCellPainted(pos)) g.setColor(FILLED_CELL_COLOR);
                else g.setColor(BACKGROUND_COLOR);

                g.fillRect((pos.column() - 1) * CELL_SIZE + GAP + 1,
                        (pos.row() - 1) * CELL_SIZE + GAP + 1, CELL_SIZE - 1, CELL_SIZE - 1);

                // Отрисовка батарейки
                Battery battery = (Battery) _model.field().getObject(pos);
                if(battery != null) {
                    lefTop = leftTopCell(pos);
                    drawBattery(g, battery, lefTop);
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

        // Отрисовка робота
        drawRobot(g, _model.robot(), leftTopCell(_model.robot().position()));

        // Отрисовка целевой позиции
        lefTop = leftTopCell(_model.targetPosition());
        drawTargetPosition(g, lefTop);
    }
    
    
    private void drawGrid(Graphics g) {
        int width  = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);
        
        for(int i = 1; i <= _model.field().width()+1; i++) { // вертикальные линии
            int x = GAP + CELL_SIZE*(i-1);
            g.drawLine(x, 0, x, height);
        }

        for(int i = 1; i <= _model.field().width()+1; i++) { // горизонтальные линии
            int y = GAP + CELL_SIZE*(i-1);
            g.drawLine(0, y, width, y);
        }
    }
    
    private void drawRobot(Graphics g, Robot robot, Point lefTop) {
        g.setColor(Color.RED);
        String str = "Р/" + robot.amountOfCharge();
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);
        g.setColor(Color.BLACK);   // восстанавливаем цвет пера
    }
    
    private void drawBattery(Graphics g, Battery battery, Point lefTop) {
        g.setColor(Color.RED);
        String str = "Б/" + battery.amountOfCharge();
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);
        g.setColor(Color.BLACK);   // восстанавливаем цвет пера
    }
            
    private void drawWall(Graphics g, Point lefTop, Direction direct) {
        g.setColor(Color.RED);
        if(direct.equals(Direction.west()) || direct.equals(Direction.east())) {
            g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE);
        } else {
            g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);                   
        }
        g.setColor(Color.BLACK);   // восстанавливаем цвет пера
    }

    private void drawTargetPosition(Graphics g, Point lefTop) {
        g.setColor(Color.RED);
        g.drawString("Ц", lefTop.x+CELL_SIZE/3, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);
        g.setColor(Color.BLACK);   // восстанавливаем цвет пера
    }

    private void drawDoor(Graphics g, Door door, Point lefTop) {

        if (door.getClass() == DestroyableDoor.class) g.setColor(Color.MAGENTA);
        else g.setColor(Color.BLUE);
        Direction direct = door.position().direction();
        
        if(direct.equals(Direction.west()) || direct.equals(Direction.east())) {
            if(!door.isOpen()) {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE);
            } else {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x, lefTop.y+CELL_SIZE/5);
                g.drawLine(lefTop.x, lefTop.y+4*CELL_SIZE/5, lefTop.x, lefTop.y+CELL_SIZE);
            }
        } else {
            if(!door.isOpen()) {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);
            } else {
                g.drawLine(lefTop.x, lefTop.y, lefTop.x+CELL_SIZE/5, lefTop.y);
                g.drawLine(lefTop.x+4*CELL_SIZE/5, lefTop.y, lefTop.x+CELL_SIZE, lefTop.y);
            }    
        }
        g.setColor(Color.BLACK);   // восстанавливаем цвет пера
    }
    
    private Point leftTopCell(CellPosition pos) {
        
        int left = GAP + CELL_SIZE * (pos.column()-1);
        int top = GAP + CELL_SIZE * (pos.row()-1);
        return new Point(left, top);
    }

    private Point leftTopCell(MiddlePosition mpos) {

        Point p = leftTopCell(mpos.cellPosition());
        if(mpos.direction().equals(Direction.south())) p.y += CELL_SIZE;
        else if(mpos.direction().equals(Direction.east())) p.x += CELL_SIZE;
        return p;
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.isControlDown()) {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // откр/закр дверь сверху
                _model.robot().openCloseDoor(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // откр/закр дверь снизу
                _model.robot().openCloseDoor(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // откр/закр дверь слева
                _model.robot().openCloseDoor(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // откр/закр дверь справа
                _model.robot().openCloseDoor(Direction.east());
            }

        } else if (ke.isAltDown()) { // Разрушить объект
            GameObject object = null;

            if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
                object = _model.field().getObject(_model.robot().position());
            } else if(ke.getKeyCode() == KeyEvent.VK_UP) {
                object = _model.field().getObject(new MiddlePosition(_model.robot().position(), Direction.north()));
            } else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
                object = _model.field().getObject(new MiddlePosition(_model.robot().position(), Direction.south()));
            } else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                object = _model.field().getObject(new MiddlePosition(_model.robot().position(), Direction.west()));
            } else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                object = _model.field().getObject(new MiddlePosition(_model.robot().position(), Direction.east()));
            }

            if (object instanceof Destroyable) _model.robot().makeDamage((Destroyable) object, 1);

        } else {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // перемещаемся вверх
                _model.robot().makeMove(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _model.robot().makeMove(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _model.robot().makeMove(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _model.robot().makeMove(Direction.east());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_SPACE) { // берем батарейку
                Battery battery = (Battery) _model.field().getObject(_model.robot().position());
                if(battery != null) _model.robot().useBattery(battery);
            }
            else if(ke.getKeyCode() == KeyEvent.VK_ENTER) { // специальные возможности робота
                if (_model.robot().getClass() == RobotPainter.class) {
                    ((RobotPainter) _model.robot()).paintCell(_model.robot().position());
                } else if(_model.robot().getClass() == RobotRadiationMeter.class) {
                    RadiationSievert rad =
                            ((RobotRadiationMeter) _model.robot()).measureRadiation(_model.robot().position());
                    if (rad != null)
                        System.out.println(rad.getFormattedRadiation() +
                                " (" + rad.getEstimateOfRadiationPollution() + ")");
                } else if(_model.robot().getClass() == RobotTemperatureMeter.class) {
                    TemperatureKelvin temp =
                            ((RobotTemperatureMeter) _model.robot()).measureTemperature(_model.robot().position());
                    if (temp != null) System.out.println(temp.getFormattedTemperature());
                }
            }
            else if(ke.getKeyCode() == KeyEvent.VK_SHIFT) { // очистка ячейки
                if (_model.robot().getClass() == RobotPainter.class)
                    ((RobotPainter)_model.robot()).clearCell(_model.robot().position());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
            
    
    private class RepaintByAction implements RobotActionListener {

        @Override
        public void robotMadeMove(RobotActionEvent e) {
            repaint();
        }
    }
}
package eightqueens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The main class of the application.
 */
public class EightQueensWindow extends JFrame {


    public EightQueensWindow() {       

        JPanel content = (JPanel)getContentPane();        
        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS) );
        
        Desk d =  Desk.buildStandartDesk();
        d.firstSolution();
        add( d );
        

        content.add(Box.createRigidArea(new Dimension(0,10)));

        JButton btnNewSolution = new JButton();
        btnNewSolution.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnNewSolution.setText("Новое решение"); 
        add( btnNewSolution );
        
        btnNewSolution.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d.newSolution();
            }
        });
        

        content.add(Box.createRigidArea(new Dimension(0,10)));

        
        pack(); // подгоняем размеры окна под его содержимое
        this.setResizable(false); // в играх редко приходится изменять размер окна
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EightQueensWindow();
            }
        });
    }
}

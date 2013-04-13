/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JFrame;

/**
 *
 * @author Jonas
 */
public class GameFrame extends JFrame{
    private GameField field;
    
    public GameFrame(GameField field){
        super("Snake!");
        this.field = field;
        this.add(field);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        field.createBufferStrategy(2);
        
    }
}

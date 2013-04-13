/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author Jonas
 */
public class Snake {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameField field = new GameField(40,40);
        GameFrame frame = new GameFrame(field);
        Engine engine = new Engine(field, new SnakeBrain());
        
        frame.repaint();
        engine.start();
    }
}

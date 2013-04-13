/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class Engine extends Thread {

    //private Point snakeHead, snakeTail, food;
    private GameField field;
    private BrainInterface brain;

    public Engine(GameField field, BrainInterface brain) {
        this.field = field;
        this.brain = brain;
        reset();
    }

    public void reset() {
        field.ClearAll();
        for (int x = 3; x < 7; x++) {
            field.setTile(GameTile.SnakeRight, new Point(x, 4));
        }
        field.setSnakeTail(new Point(3, 4));
        field.setSnakeHead(new Point(7, 4));

        field.setFood(field.getRandomEmpty());
        //field.setTile(GameTile.Food, food);
    }

    /**
     * 
     * @return true if the snake is still alive 
     */
    public boolean tick() {
        Direction d = brain.decideDirection(new GameFieldAccessor(field));
        //If we move the snake, we move the tail
        Point newHead;
        Point snakeHead = field.getHeadPosition();
        Point snakeTail = field.getTailPosition();
        switch (d) {
            case left:
                field.setTile(GameTile.SnakeLeft, snakeHead);
                newHead = new Point(snakeHead.x - 1, snakeHead.y);
                break;
            case right:
                field.setTile(GameTile.SnakeRight, snakeHead);
                newHead = new Point(snakeHead.x + 1, snakeHead.y);
                break;
            case up:
                field.setTile(GameTile.SnakeUp, snakeHead);
                newHead = new Point(snakeHead.x, snakeHead.y - 1);
                break;
            case down:
                field.setTile(GameTile.SnakeDown, snakeHead);
                newHead = new Point(snakeHead.x, snakeHead.y + 1);
                break;
            default:
                return false;
        }

        //Move the tail if the tile infront is empty
        if (field.isEmpty(newHead)) {
            GameTile tail = field.getTile(snakeTail);
            field.resetTile(snakeTail);
            switch (tail) {
                case SnakeLeft:
                    snakeTail = new Point(snakeTail.x - 1, snakeTail.y);
                    break;
                case SnakeRight:
                    snakeTail = new Point(snakeTail.x + 1, snakeTail.y);
                    break;
                case SnakeUp:
                    snakeTail = new Point(snakeTail.x, snakeTail.y - 1);
                    break;
                case SnakeDown:
                    snakeTail = new Point(snakeTail.x, snakeTail.y + 1);
                    break;
            }
            field.setSnakeTail(snakeTail);
        } else if (!field.isFood(newHead)) {
            return false;
        } else {
            //it must be food!
            field.removeFood();

            Point nfood = field.getRandomEmpty();
            if (nfood == null) {
                System.out.println("You won!!");
                return false;
            } else {
                field.setFood(nfood);
            }

        }

        field.setSnakeHead(newHead);
        field.setTile(GameTile.SnakeDown, newHead);


        return true;

    }

    @Override
    public void run() {
        while (tick()) {
            
            field.repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

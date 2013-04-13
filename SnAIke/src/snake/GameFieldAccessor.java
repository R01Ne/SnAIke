/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;

/**
 * The GameFieldAccessor is mainly to keep the snakes from cheating.
 *
 * @author Jonas
 */
public class GameFieldAccessor {

    private GameField field;

    public GameFieldAccessor(GameField field) {
        this.field = field;
    }

    public int getHeight() {
        return field.getFieldHeight();
    }

    public int getWidth() {
        return field.getFieldWidth();
    }

    /**
     * Get the snake head position
     *
     * @return
     */
    public Point getHeadPosition() {
        return field.getHeadPosition();
    }

    Point getFoodPosition() {
        return field.getFoodPosition();
    }

    boolean isEmpty(Point e1) {
        return field.isEmpty(e1);
    }
}

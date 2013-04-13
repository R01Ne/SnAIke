/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;

/**
 *
 * @author Jonas
 */
public interface BrainInterface {
    
    /**
     * This method should return a direction for the snake in the next step
     * 
     * @param fieldAccessor
     * @return 
     */
    public Direction decideDirection(GameFieldAccessor fieldAccessor);

}

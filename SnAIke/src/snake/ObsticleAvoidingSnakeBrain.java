/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
class ObsticleAvoidingSnakeBrain implements BrainInterface{

    
    @Override
    public Direction decideDirection(GameFieldAccessor field){
        Point head = field.getHeadPosition();
        Point food = field.getFoodPosition();
        ArrayList<Tuple<Direction,Point>> directions = new ArrayList<Tuple<Direction,Point>>();
        directions.add(new Tuple<Direction,Point>(Direction.left, new Point(head.x-1,head.y)));
        directions.add(new Tuple<Direction,Point>(Direction.right, new Point(head.x+1,head.y)));
        directions.add(new Tuple<Direction,Point>(Direction.up, new Point(head.x,head.y-1)));
        directions.add(new Tuple<Direction,Point>(Direction.down, new Point(head.x,head.y+1)));
        
        ArrayList<Tuple<Direction,Point>> avail = new ArrayList<Tuple<Direction,Point>>();
        //Loop through all directions to see if they are available.
        for (Tuple<Direction,Point> d : directions){
            if (food.equals(d.e1)) {
                return d.e0;
            } else if (field.isEmpty(d.e1)) {
                avail.add(d);
            }
        }
        
        int min = Integer.MAX_VALUE;
        Direction shortest = Direction.left;
        
        for (Tuple<Direction, Point> d : avail){
            int squareDistance = (d.e1.x - food.x) * (d.e1.x - food.x) + (d.e1.y - food.y) * (d.e1.y - food.y);
            if (squareDistance < min){
                shortest = d.e0;
                min = squareDistance;
            }
        }
        
        return shortest;
    }
}

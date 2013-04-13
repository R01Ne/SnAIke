/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Jonas
 */
public class GameField extends Canvas{
    private int width;
    private int height;
    
    private GameTile[][] buffer;
    
    public int getFieldWidth(){
        return width;
    }
    
    public int getFieldHeight(){
        return height;
    }
    
    public GameField(int width, int height){
        super();
        this.width = width;
        this.height = height;
        buffer = new GameTile[width][height];
        for (int y =0; y < height; y++){
            buffer[y] = new GameTile[width];
            
        }
        ClearAll();
    }

    
    public void setTile(GameTile tile, int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height){
            return;
        }
        buffer[x][y] = tile;
    }
    
    public void setTile(GameTile tile, Point point){
        setTile(tile, point.x,point.y);
    }
    
    public GameTile getTile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height){
            return GameTile.OutOfBounds;
        } else {
            return buffer[x][y];
        }
    }
    public GameTile getTile(Point point){
        return getTile(point.x,point.y);
    }
    
    public void resetTile(int x, int y){
        setTile(GameTile.Empty, x,y);
    }
    
    public void resetTile(Point point){
        resetTile(point.x,point.y);
    }
    
    public Point getRandomEmpty(){
        int x = (new Random()).nextInt(width);
        int y = (new Random()).nextInt(height);
        for(int i = 0; i < width;i++){
            for (int j = 0; j < height; y++){
                int x1 = (i + x) % width;
                int y1 = (j +y) % height;
                if (buffer[x1][y1] == GameTile.Empty){
                    return new Point(x1,y1);
                }
            }
        }
        return null;
        
    }
    
    public boolean isEmpty(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height){
            return false;
        }
        return buffer[x][y] == GameTile.Empty;
    }
    
    public boolean isEmpty(Point point){
        return isEmpty(point.x,point.y);
    }
    
    public boolean isFood(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height){
            return false;
        }
        return buffer[x][y] == GameTile.Food;
    }
    
    public boolean isFood(Point point) {
        return isFood(point.x, point.y); 
    }
    
    public final void ClearAll(){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                buffer[x][y] = GameTile.Empty;
                
                //DEBUG
                //if (x == y) buffer[x][y] = GameTile.Food;
            }
        }
    }
    
    private int scale = 5; 
    
    @Override
    public void paint(Graphics g) {
        Graphics g2 = this.getBufferStrategy().getDrawGraphics();
        g2.setPaintMode();
        g2.clearRect(0, 0, this.getWidth(),this.getHeight());
        scale = Math.min(this.getWidth()/this.width, this.getHeight()/this.height);
        
        g.setColor(Color.black);
        for (int x = 0 ; x < width; x++){
            for (int y = 0; y < height; y++){
                if (buffer[x][y] != GameTile.Empty){
                    drawPixel(x,y,g2);
                }
            }
        }
        this.getBufferStrategy().show();
    }
    
    private void drawPixel(int x, int y, Graphics g){
        g.fillRect(x*scale, y*scale, scale, scale);
    }
    
}

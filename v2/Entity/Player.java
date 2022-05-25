package Entity;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Blocks.Block;
import Blocks.ButtonFlag;
import LevelRelated.TileMap;
import Settings.MapSettings;

public class Player extends Entity {

    public int lives;
    public int coins;

    public double xPos;
    public double yPos;
    public int height;
    public int width;

    //1 right/up, 0 not moving, -1 left/down
    public int movingX = 0;
    public int movingY = 0;

    public boolean falling = true;
    public boolean jumping = false;

    public double yVelo = 4;
    public double xVelo = 0;

    public Player() {
        this.xPos = 400;
        this.yPos = 00;
        this.height = 80;
        this.width = 40;
        coins = 0;
        lives = 5;
    }

    
    public void tick(TileMap tileMap) {
        
        if (falling) {
            yVelo = 6;
        }
        rigidCollision(tileMap);
        xPos += xVelo;
        yPos += yVelo;
        rigidCollision(tileMap);
        nonRigidCollision(tileMap);
    }
    
    public void rigidCollision(TileMap tilemap) {
        for (int i = 0; i < tilemap.rigidBlocks.size(); i++) {

            if (getBottomBounds().intersects(tilemap.rigidBlocks.get(i).getBounds())) {
                yVelo = 0;
               if (tilemap.rigidBlocks.get(i).getId().equals("button")) {
                   System.out.println(tilemap.rigidBlocks.get(i).getId());
                  
               }
               
            }
            if (getRightBounds().intersects(tilemap.rigidBlocks.get(i).getBounds())) {
                xPos = tilemap.rigidBlocks.get(i).getX() - width;               
            }

            if (getLeftBounds().intersects(tilemap.rigidBlocks.get(i).getBounds())) {
                xPos = tilemap.rigidBlocks.get(i).getX() + MapSettings.tileSize;               
            }

            
            
           

        }
    }
    //
    public void nonRigidCollision(TileMap tilemap) { 
        for (int i = 0; i < tilemap.nonRigidBlocks.size(); i++) {
            
           if (getBounds().intersects(tilemap.nonRigidBlocks.get(i).getBounds())) {
               coins++;
               System.out.println(tilemap.nonRigidBlocks.get(i).getRow() + " " + tilemap.nonRigidBlocks.get(i).getCol());
               tilemap.setBlock(tilemap.nonRigidBlocks.get(i).getRow(), tilemap.nonRigidBlocks.get(i).getCol(), null);
               tilemap.nonRigidBlocks.remove(i);
           }
        }
    }

    public void draw(Graphics g) {
        Graphics o = g.create();
        o.setColor(Color.LIGHT_GRAY);
	    o.fillRect((int) xPos, (int) yPos, width, height);
        o.setColor(Color.RED);
        o.fillRect((int) xPos + 1, (int) yPos + height - 4, width - 1, 5);
        // o.setColor(Color.BLACK);
        // o.fillRect((int) playerX, (int) playerY + playerHeight, playerWidth, 4);
    }

    public void right() {
        xVelo = 4;
    }

    public void left() {
        xVelo = -4;
    }

    public void up() {
        jumping = true;
        yPos -= 100;
    }

    public void down() {
        yVelo = -4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xPos, (int) yPos, width, height);
    }

    public Rectangle getRightBounds() {
        return new Rectangle((int) xPos + width - 4, (int) yPos, 4, height - 4);
    }

    public Rectangle getLeftBounds() {
        return new Rectangle((int) xPos + 1, (int) yPos, 4, height - 4);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle((int) xPos + 1, (int) yPos + height - 4, width - 1, 5); //4 is arbitrary
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {
            falling = false;
            up();
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            movingY = -1;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            left();
        }
        if(e.getKeyCode()==KeyEvent.VK_D) {
            right();
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W) {
            falling = true;
            movingY = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            movingY = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            xVelo = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_D) {
            xVelo = 0;
        }
    }
}
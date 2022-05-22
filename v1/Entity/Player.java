package Entity;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Blocks.Block;
import Settings.MapSettings;

public class Player {

    public int lives;
    public int coins;

    public float playerX;
    public float playerY;
    public int playerHeight;
    public int playerWidth;

    //1 right/up, 0 not moving, -1 left/down
    public int movingX = 0;
    public int movingY = 0;

    public boolean falling = true;
    public boolean jumping = false;

    public double yVelo = 4;
    public double xVelo = 0;

    public Player() {
        this.playerX = 400;
        this.playerY = 00;
        this.playerHeight = 80;
        this.playerWidth = 40;
        coins = 0;
        lives = 5;
    }

    
    public void tick(ArrayList<Block> rigidBlocks) {
        
        if (falling) {
            yVelo = 2;
        }
        rigidCollision(rigidBlocks);
        playerX += xVelo;
        playerY += yVelo;
        rigidCollision(rigidBlocks);
    }
    
    public void rigidCollision(ArrayList<Block> rigidBlocks) {
        for (int i = 0; i < rigidBlocks.size(); i++) {

            if (getBottomBounds().intersects(rigidBlocks.get(i).getBounds())) {
                yVelo = 0;
               
               
            }
            if (getRightBounds().intersects(rigidBlocks.get(i).getBounds())) {
                playerX = rigidBlocks.get(i).getX() - playerWidth;               
            }

            if (getLeftBounds().intersects(rigidBlocks.get(i).getBounds())) {
                playerX = rigidBlocks.get(i).getX() + MapSettings.tileSize;               
            }
            
           

        }
    }

    public void nonRigitCollision(ArrayList<Block> nonRigidBlocks) {
        for (int i = 0; i < nonRigidBlocks.size(); i++) {
            
           if (getBounds().intersects(nonRigidBlocks.get(i).getBounds())) {
               coins++;
               nonRigidBlocks.remove(i);
           }

        }
    }

    public void draw(Graphics g) {
        Graphics o = g.create();
        o.setColor(Color.LIGHT_GRAY);
	    o.fillRect((int) playerX, (int) playerY, playerWidth, playerHeight);
        o.setColor(Color.RED);
        o.fillRect((int) playerX + 1, (int) playerY + playerHeight - 4, playerWidth - 1, 5);
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
        playerY -= 100;
    }

    public void down() {
        yVelo = -4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) playerX, (int) playerY, playerWidth, playerHeight);
    }

    public Rectangle getRightBounds() {
        return new Rectangle((int) playerX + playerWidth - 4, (int) playerY, 4, playerHeight - 4);
    }

    public Rectangle getLeftBounds() {
        return new Rectangle((int) playerX + 1, (int) playerY, 4, playerHeight - 4);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle((int) playerX + 1, (int) playerY + playerHeight - 4, playerWidth - 1, 5); //4 is arbitrary
    }

    public double getX() {
        return playerX;
    }

    public double getY() {
        return playerY;
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
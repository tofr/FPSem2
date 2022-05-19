package Entity;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Blocks.Block;

public class Player {
    public int playerX;
    public int playerY;
    public int playerHeight;
    public int playerWidth;

    //1 right/up, 0 not moving, -1 left/down
    public int movingX = 0;
    public int movingY = 0;
    public int translationX = 0;
    public int translationY = 0;
    public boolean translateX = false;
    public boolean translateY = false;

    public boolean falling = true;
    public boolean jumping = false;

    public int yVelo = 4;
    public int xVelo = 0;

    public Player() {
        this.playerX = 400;
        this.playerY = 300;
        this.playerHeight = 60;
        this.playerWidth = 30;
    }

    public void moveX(int dir, int x) {
		playerX -= dir;

		if (playerX <= 400 || playerX >= x - 400) {
			translateX = false;
		} else {
			translateX = true;
		}
		if (translateX) {
			translationX += dir;
			playerX = -translationX + 400;
		}
	}
	public void moveY(int dir, int y) {
		playerY -= dir;

		if (playerY <= 300 || playerY >= y - 300) {
			translateY = false;
		} else {
			translateY = true;
		}
		if (translateY) {
			translationY += dir;
			playerY = -translationY + 300;
		}
	}
    
    public void tick(ArrayList<Block> blocks) {
        if (!collision(blocks)) {
            moveX(xVelo, 2000);
            moveY(yVelo, 2000);
        }
        xVelo = 0;
        if (jumping) {
            moveY(20, 2000);
        }
        
    }
    
    public boolean collision(ArrayList<Block> blocks) {
        for (int i = 0; i < blocks.size(); i++) {
            if (getBottomBounds().intersects(blocks.get(i).getBounds())) {
                yVelo = 0;
                return true;
            }
            if (getRightBounds().intersects(blocks.get(i).getBounds())) {
                xVelo = 0;
                System.out.println("intersect!");
                return true;
            }
            if (falling) yVelo = -4;
           

        }
        return false;
        
    }

    public void draw(Graphics g) {
        Graphics o = g.create();
        o.setColor(Color.LIGHT_GRAY);
	    o.fillRect(playerX, playerY, 30, 60);
        o.setColor(Color.RED);
        o.fillRect(playerX + playerWidth - 4, playerY, 4, playerHeight);
    }

    public void right() {
        xVelo = -4;
    }

    public void left() {
        xVelo = 4;
    }

    public void up() {
        jumping = true;
    }

    public void down() {
        yVelo = -4;
    }

    public Rectangle getBounds() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }

    public Rectangle getRightBounds() {
        return new Rectangle(playerX + playerWidth - 4, playerY, 4, playerHeight);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(playerX, playerY + playerHeight, playerWidth, 4); //4 is arbitrary
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {
            movingY = 1;
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            movingY = -1;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            movingX = -1;
        }
        if(e.getKeyCode()==KeyEvent.VK_D) {
            movingX = 1;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W) {
            movingY = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            movingY = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            movingX = 0;
        }
        if(e.getKeyCode()==KeyEvent.VK_D) {
            movingX = 0;
        }
    }
}
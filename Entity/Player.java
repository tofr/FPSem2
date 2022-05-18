package Entity;

import java.awt.*;
import java.awt.event.*;

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

    public int yVelo = 4;

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
    
    

    public void draw(Graphics g) {
        if (falling) playerY += yVelo;
        Graphics o = g.create();
        o.setColor(Color.LIGHT_GRAY);
	    o.fillRect(playerX, playerY, 30, 60);
    }

    public Rectangle getBounds() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
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
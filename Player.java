
import java.awt.*;
import java.awt.event.*;

public class Player {
    int playerX;
    int playerY;

    //1 right/up, 0 not moving, -1 left/down
    int movingX = 0;
    int movingY = 0;
    int translationX = 0;
    int translationY = 0;
    boolean translateX = false;
    boolean translateY = false;

    public Player() {
        this.playerX = 300;
        this.playerY = 300;
    }

    public void moveX(int dir, int x) {
		playerX -= dir;

		if (playerX <= 300 || playerX >= x - 300) {
			translateX = false;
		} else {
			translateX = true;
		}
		if (translateX) {
			translationX += dir;
			playerX = -translationX + 300;
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
        g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(playerX, playerY, 20, 20);
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
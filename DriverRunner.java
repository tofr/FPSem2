
//*********************************

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import Blocks.Block;
import Blocks.Grass;
import Entity.Player;


public class DriverRunner extends JPanel implements Runnable{

	static final int GAME_WIDTH = 800;
	static final int GAME_HEIGHT = 600;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	
    MapDrawer map = new MapDrawer();
	Thread gameThread;
	Image image;
	Graphics graphics;
	Player player = new Player();
    BufferedImage imag2;
	Random random;
	int x = 0;
	int y = 0;
	int playerX = 400;
	int playerY = 300;
	int imag2x;
	int imag2y;
	boolean movingX = false;
	boolean movingY = false;
	boolean translateX = true;
	boolean translateY = true;
	
	// DELETE THIS ONLY HERE FOR TESTING
	ArrayList<Block> smallMap = new ArrayList<>();
	boolean falling = true;
	
	public DriverRunner() {
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		map.loadImg();
        imag2x = map.imag2x;
        imag2y = map.imag2y;
		gameThread = new Thread(this);
		gameThread.start();

		//DELETE THIS
		smallMap.add(new Grass(400, 400));
		smallMap.add(new Grass(420, 400));
		smallMap.add(new Grass(440, 400));
    }
	
    
	
	public void paint(Graphics g) {

		Toolkit.getDefaultToolkit().sync(); 
		draw(g);
		
	}
	
	public void draw(Graphics g) {
		
		
		Toolkit.getDefaultToolkit().sync(); 
		g.translate(player.translationX, player.translationY);
		//shoudl start 1 TILE BACK!!!
		map.draw(g, this);
		for (int i = 0; i < smallMap.size(); i++) {
			smallMap.get(i).draw(g);
		}
		
		player.draw(g);
		
		


	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				
				if (player.movingX == 1) { //moving right
					player.moveX(-4, 2 * map.imag2x);

					if (player.getBounds().intersects(smallMap.get(0).getBounds())){
						playerX = smallMap.get(0).getX();
						
					} else {
						
					}
				}
				if (player.movingX == -1) {
					if (player.playerX - 20 == 400 && (player.playerY <= 420 && player.playerY >= 380)){
						
					} else {
						player.moveX(4, 2* map.imag2x);

					}
				}
				
				if (player.movingY == 1) {
					System.out.println("ding");
					if (player.playerY - 20 == 400 && (player.playerX <= 420 && player.playerX >= 380)){
						
					} else {
						player.moveY(4, map.imag2y);
						player.falling = true;
						player.yVelo = 4;

					}
				}
				
				if (true) {
					if (player.getBounds().intersects(smallMap.get(0).getBounds()) || player.getBounds().intersects(smallMap.get(1).getBounds())){
						// playerX = smallMap.get(0).getX();
						player.yVelo = 0;
						player.falling = false;
						
						System.out.println(playerY);
						System.out.println("doing this");
						playerY -= 4;

						
					} else {
						
					
					}
				}				
				repaint();
				delta--;
				
				
			}
			
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
	public static void main(String[] args) {
		
	}
}
//*********************************



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
		smallMap.add(new Grass(420, 380));
		smallMap.add(new Grass(440, 360));
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
			repaint();
			
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;

			if(delta >=1) {
				
				player.falling = true;
				
				if (player.movingX == 1) { //moving right
					player.right();
				}
				if (player.movingX == -1) {
					player.left();
				}
				
				if (player.movingY == 1) {
					player.falling = false;
					player.up();
				}
				
				if (player.movingY == -1) {
					player.down();
					
				}	
				player.tick(smallMap);
				player.jumping = false;

				System.out.println(player.yVelo);
				

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


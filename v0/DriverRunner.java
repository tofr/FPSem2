
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Entity.Player;
import LevelRelated.BackGroundDrawer;
import Settings.MapSettings;


public class DriverRunner extends JPanel implements Runnable{

	static final Dimension SCREEN_SIZE = new Dimension(MapSettings.GAME_WIDTH, MapSettings.GAME_HEIGHT);
	
    BackGroundDrawer map = new BackGroundDrawer();
	Stack<Handler> gameStack;
	Thread gameThread;
	
	Graphics graphics;
	Player player;

	LevelHandler levelHandler;

	int imag2x;
	int imag2y;
	
	public DriverRunner() {
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		player = new Player();
		levelHandler = new LevelHandler(player);
		gameStack = new Stack<Handler>();
		map.loadImg();
        imag2x = map.imag2x;
        imag2y = map.imag2y;
		gameThread = new Thread(this);
		gameThread.start();

		startup();
    }

	public void startup() {
		gameStack.push(levelHandler);
	}
	
	
    
	
	public void paint(Graphics g) {
		Toolkit.getDefaultToolkit().sync(); 
		draw(g);
	}
	
	public void draw(Graphics g) {
		gameStack.peek().draw(g, this);

	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; // fps
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;

			if(delta >=1) {
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
}


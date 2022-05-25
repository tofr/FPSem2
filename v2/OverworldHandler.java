import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entity.Player;
import LevelRelated.Camera;
import LevelRelated.Level;

import java.awt.*;

// will handle overworld and level selection

public class OverworldHandler extends Handler {
    
    public class Lock {

        public int xPos;
        public int yPos;

        public Lock(int x, int y) {
            this.xPos = x;
            this.yPos = y;
        }

        // REPLACE W/ A CARTOON LOCK PNG
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect(xPos, yPos, 40, 40);
        }
    }

    public Player player;
    public DriverRunner driver; //is this needed?
    public Camera cam;
    
    public final int DISTANCE_BETWEEN_LEVELS = 300;



    public OverworldHandler(DriverRunner driver) {
        this.driver = driver;
        player = new Player(100, 300);
        cam = new Camera(player.getX(), player.getY());
        snapCamera(player);
        
    }

    public void snapCamera(Player player) {
		cam.setX(player.xPos);
		cam.setY(player.yPos);
	}

    @Override
    public void draw(Graphics g, DriverRunner driver) {
        // TODO Auto-generated method stub
        cam.tick(player);
		Toolkit.getDefaultToolkit().sync(); 
       
        g.clearRect(0, 0, 800, 600);
		g.fillRect(0, 0, 800, 600); //background
		if (cam.getX() < 0) g.translate((int) cam.getX(), 0);
        for (int i = 0; i < driver.levelHandler.levels.size(); i++) {
            g.drawImage(driver.levelHandler.levels.get(i).getImage(), 100 + i * DISTANCE_BETWEEN_LEVELS, 100, driver);
        }
		player.draw(g);
		if (cam.getX() < 0) g.translate((int) -cam.getX(), 0);
    }

    @Override
    public void tick(DriverRunner driver) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }



}

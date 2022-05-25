package LevelRelated;

import java.util.ArrayList;

import Entity.Player;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;



public class Level {

    public Player player;
    public TileMap levMap;
    public Camera cam;
    public static ArrayList<Integer> scores; //add quicksort to sort levels
    
    public Level(int level) {
        startup(level);
    }

    public void startup(int level) {
        player = new Player();
        levMap = new TileMap();
        cam = new Camera(player.getX(), player.getY());
        snapCamera(player);
        
        switch (level) {
            case 1:
                loadLev("./LevelRelated/Lev1.txt");
                break;
            default:
                
                break;
        }
    }

    public void snapCamera(Player player) {
		cam.setX(player.xPos);
		cam.setY(player.yPos);
	}

    public void loadLev(String filename) {
		levMap.loadFile(filename);
		levMap.load();
	}

    public void tick() {
        player.tick(levMap);
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public void draw(Graphics g) {
        tick();
		cam.tick(player);
		Toolkit.getDefaultToolkit().sync(); 
		g.clearRect(0, 0, 800, 600);
		g.fillRect(0, 0, 800, 600); //background
		// map.draw(g, this);		
		if (cam.getX() < 0) g.translate((int) cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) cam.getY());
		levMap.draw(g);
		player.draw(g);
		if (cam.getX() < 0) g.translate((int) -cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) -cam.getY());
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyReleased(e);

	}

    
}

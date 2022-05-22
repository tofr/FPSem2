

/***
 * Handler Class for Levels
 */

import Entity.Player;
import LevelRelated.Camera;
import LevelRelated.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelHandler extends Handler { // Graphics to handle events during a level

    public TileMap levMap;
    public int currLev;
	public DriverRunner driverRunner;

    public Camera cam;

    public LevelHandler(DriverRunner driverRunner, String filename) {
        currLev = 0;
        levMap = new TileMap();
		this.driverRunner = driverRunner;
        cam = new Camera(driverRunner.player.getX(), driverRunner.player.getY());
        snapCamera(driverRunner.player);
		loadLev(filename);
    }

    public void snapCamera(Player player) {
		cam.setX(player.xPos);
		cam.setY(player.yPos);
	}

    public void loadLev(String filename) {
		levMap.loadFile(filename);
		levMap.load();
	}

    public void tick(DriverRunner driver) {
        driver.player.tick(levMap);
    }

    public void draw(Graphics g, DriverRunner driver) {
        tick(driver);
		cam.tick(driver.player);
		Toolkit.getDefaultToolkit().sync(); 
		g.clearRect(0, 0, 800, 600);
		g.fillRect(0, 0, 800, 600);
		// map.draw(g, this);		
		if (cam.getX() < 0) g.translate((int) cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) cam.getY());
		
		levMap.draw(g);
		driver.player.draw(g);
		if (cam.getX() < 0) g.translate((int) -cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) -cam.getY());
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		driverRunner.player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		driverRunner.player.keyReleased(e);

	}

   

}

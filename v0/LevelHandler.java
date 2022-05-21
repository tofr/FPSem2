package Handler;

/***
 * Handler Class for Levels
 */

import Entity.Player;
import LevelRelated.Camera;
import LevelRelated.TileMap;

import java.awt.*;

public class LevelHandler extends Handler { // Graphics to handle events during a level

    public TileMap levMap;
    public int currLev;

    public Camera cam;

    public LevelHandler(Player player) {
        currLev = 0;
        levMap = new TileMap();
        cam = new Camera(player.getX(), player.getY());
        snapCamera(player);
		loadLev();
    }

    public void snapCamera(Player player) {
		cam.setX(player.playerX);
		cam.setY(player.playerY);
	}

    public void loadLev() {
		levMap.loadFile("/LevelRelated/Levels/test.txt");
		levMap.load();
	}

    public void tick(DriverRunner driver) {
        driver.player.tick(levMap.rigidBlocks);
    }

    public void draw(Graphics g, DriverRunner driver) {
        tick(driver);
		cam.tick(driver.player);
		Toolkit.getDefaultToolkit().sync(); 
		g.fillRect(0, 0, 1000, 500);
		// map.draw(g, this);		
		if (cam.getX() < 0) g.translate((int) cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) cam.getY());
		
		levMap.draw(g);
		driver.player.draw(g);
		if (cam.getX() < 0) g.translate((int) -cam.getX(), 0);
		if (cam.getY() < 0) g.translate(0, (int) -cam.getY());

	}

   

}

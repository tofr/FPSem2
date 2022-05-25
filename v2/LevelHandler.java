

/***
 * Handler Class for Levels
 */

import Entity.Player;
import LevelRelated.Camera;
import LevelRelated.Level;
import LevelRelated.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LevelHandler extends Handler { // Graphics to handle events during a level

	public ArrayList<Level> levels;

    public DriverRunner driverRunner;

	public int currLev;

    public LevelHandler(DriverRunner driverRunner) {
        currLev = 0;
        loadLev();
    }

	public void loadLev() {
		levels.add(new Level(1)); 
	}

	public void tick(DriverRunner driver) {
		// empty here will add sutff later
	}

    public void draw(Graphics g, DriverRunner driver) {
        levels.get(currLev).draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		levels.get(currLev).keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		levels.get(currLev).keyReleased(e);

	}

   

}

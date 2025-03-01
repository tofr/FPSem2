

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

    public DriverRunner driverRunner; // I don't think this is needed atm

	public int latestLev;
	public int currLev;

    public LevelHandler(DriverRunner driverRunner) {
		levels = new ArrayList<Level>();
		latestLev = currLev;
        currLev = 0;
        loadLev();
    }

	public void loadLev() {
		levels.add(new Level(1)); 
		levels.add(new Level(2));
		// levels.add(new Level(3));
		// levels.add(new Level(4));
		// levels.add(new Level(5));
		// levels.add(new Level(6));
		// levels.add(new Level(7));
		// levels.add(new Level(8));

	}

	public void tick(DriverRunner driver) {
		if (levels.get(currLev).isDone) {
			latestLev++;
			currLev++; // delete this later when world screen thing
		}
		// empty here will add sutff later
	}

    public void draw(Graphics g, DriverRunner driver) {
		tick(driver);
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

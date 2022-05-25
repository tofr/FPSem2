//first type of enemy(gomba)
package Entity;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Blocks.Block;
import Blocks.ButtonFlag;
import LevelRelated.Level;
import LevelRelated.TileMap;
import Settings.MapSettings;


public class Kooler extends Enemy{
    
    Kooler(){
    yVelo = 4;
    xVelo = -4;
    falling = true;
    }

    

    
    public void tick(Level level) {
        
       
        if (falling) {
            yVelo = 6;
        }
        rigidCollision(level);
        xPos -= xVelo;
        yPos += yVelo;
        rigidCollision(level);
        nonRigidCollision(level);
    }

    public void rigidCollision(Level level) {
        for (int i = 0; i < level.levMap.rigidBlocks.size(); i++) {

            if (getBottomBounds().intersects(level.levMap.rigidBlocks.get(i).getBounds())) {
                yVelo = 0;
               if (level.levMap.rigidBlocks.get(i).getId().equals("button")) {
                   ((ButtonFlag) level.levMap.rigidBlocks.get(i)).setPressed();
                    level.setDone();
               }
               
            }
            if (getRightBounds().intersects(level.levMap.rigidBlocks.get(i).getBounds())) {
                xPos = level.levMap.rigidBlocks.get(i).getX() - width;               
            }

            if (getLeftBounds().intersects(level.levMap.rigidBlocks.get(i).getBounds())) {
                xPos = level.levMap.rigidBlocks.get(i).getX() + MapSettings.tileSize;               
            }

            
            
           

        }
    }
    //
    public void nonRigidCollision(Level level) { 
        for (int i = 0; i < level.levMap.nonRigidBlocks.size(); i++) {
            
           if (getBounds().intersects(level.levMap.nonRigidBlocks.get(i).getBounds())) {
               coins++;
               System.out.println(level.levMap.nonRigidBlocks.get(i).getRow() + " " + level.levMap.nonRigidBlocks.get(i).getCol());
               level.levMap.setBlock(level.levMap.nonRigidBlocks.get(i).getRow(), level.levMap.nonRigidBlocks.get(i).getCol(), null);
               level.levMap.nonRigidBlocks.remove(i);
           }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xPos, (int) yPos, width, height);
    }

    public Rectangle getRightBounds() {
        return new Rectangle((int) xPos + width - 4, (int) yPos, 4, height - 4);
    }

    public Rectangle getLeftBounds() {
        return new Rectangle((int) xPos + 1, (int) yPos, 4, height - 4);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle((int) xPos + 1, (int) yPos + height - 4, width - 1, 5); //4 is arbitrary
    }

    public double getX() {
        return xPos;
    }


}
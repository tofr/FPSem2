package v0.LevelRelated;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Blocks.Block;
import Blocks.Grass;
import Settings.MapSettings;

import java.awt.*;


public class TileMap {
    public String[][] rawMap;
    public Block[][] map;

    public ArrayList<Block> rigidBlocks;
    // public ArrayList<MapEntity> entities;
    // public Location start;
    // public Location end;

    public TileMap() {
        this.map = new Block[0][0];
        this.rawMap = new String[0][0];
        rigidBlocks = new ArrayList<Block>();
        // this.entities = new ArrayList<>();
        // this.start = null;
        // this.end = null;
    }

    
    public void loadFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            ArrayList<String[]> blockMap = new ArrayList<>();
            while (sc.hasNextLine()) {
                ArrayList<String> tiles = new ArrayList<>();
                for (char tileKey : sc.nextLine().toCharArray()) {
                    tiles.add(Character.toString(tileKey));
                }
                blockMap.add(tiles.toArray(new String[0]));
            }
            this.rawMap = blockMap.toArray(new String[0][0]);
        } catch (Exception e) {
            System.out.println(e + "hey error heere");
        }
        this.map = new Block[rawMap.length][rawMap[0].length];
    }

    public boolean load() {
        for (int row = 0; row < this.rawMap.length; row++) {
            for (int col = 0; col < this.rawMap[row].length; col++) {
                switch (rawMap[row][col]) {
                    case "G":
                        Block temp =  new Grass(col * MapSettings.tileSize, row * MapSettings.tileSize);
                        map[row][col] = temp;
                        rigidBlocks.add(temp);
                        
                        break;
                    default:
                        break;
                        
                }
            }
            
        }
        return true;
    }

    // public void load(ArrayList<MapEntity> entities) {
    //     this.entities = entities;
    // }

    // public void add(MapEntity entity) {
    //     this.entities.add(entity);
    // }

    // public void remove(MapEntity entity) {
    //     for (int i = 0; i < this.entities.size(); i++) {
    //         if (entity.getLocation().equals(this.entities.get(i).getLocation())) {
    //             entities.remove(i);
    //         }
    //     }
    // }

    // public boolean inBounds(int row, int col) {
    //     return row >= 0 && row < this.map.length && col >= 0 && col < this.map[row].length;
    // }

    // public MapEntity entityOn(int row, int col) {
    //     for (MapEntity entity : this.entities) {
    //         if (entity.getLocation().equals(new Location(row, col))) {
    //             return entity;
    //         }
    //     }
    //     return null;
    // }

    public Block getBlock(int row, int col) {
        return this.map[row][col];
    }

    public void draw(Graphics g) {
        for (int row = 0; row < this.map.length; row++) {
            for (int col = 0; col < this.map[row].length; col++) {
                if (map[row][col] == null) {
                    continue;
                }
                map[row][col].draw(g);
            }
            
        }
    }
}
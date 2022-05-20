import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Blocks.Block;
import java.awt.*;


public class TileMap {
    public Block[][] map;
    // public ArrayList<MapEntity> entities;
    // public Location start;
    // public Location end;

    public TileMap() {
        this.map = new Block[0][0];
        // this.entities = new ArrayList<>();
        // this.start = null;
        // this.end = null;
    }


    public boolean load(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            ArrayList<Block[]> blockMap = new ArrayList<>();
            while (sc.hasNextLine()) {
                ArrayList<Block> tiles = new ArrayList<>();
                for (char tileKey : sc.nextLine().toCharArray()) {
                    switch(tileKey) {
                        case 'G':
                            tiles.add()
                    }
                }
                blockMap.add(tiles.toArray(new Tile[0]));
            }
            this.map = blockMap.toArray(new Block[0][0]);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            
            return false;
        }
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
                map[row][col].draw(g);
            }
            
        }
    }
}
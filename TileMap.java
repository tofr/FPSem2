package mapentities;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import constants.Tile;

public class TileMap {
    public Tile[][] map;
    public ArrayList<MapEntity> entities;
    public Location start;
    public Location end;

    public TileMap() {
        this.map = new Tile[0][0];
        this.entities = new ArrayList<>();
        this.start = null;
        this.end = null;
    }

    public TileMap(Location start, Location end) {
        this.map = new Tile[0][0];
        this.entities = new ArrayList<>();
        this.start = start;
        this.end = end;
    }

    public boolean load(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            ArrayList<Tile[]> tileMap = new ArrayList<>();
            while (sc.hasNextLine()) {
                ArrayList<Tile> tiles = new ArrayList<>();
                for (char tileKey : sc.nextLine().toCharArray()) {
                    tiles.add(Tile.keyToTile(tileKey));
                }
                tileMap.add(tiles.toArray(new Tile[0]));
            }
            this.map = tileMap.toArray(new Tile[0][0]);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            
            return false;
        }
    }

    public void load(ArrayList<MapEntity> entities) {
        this.entities = entities;
    }

    public void add(MapEntity entity) {
        this.entities.add(entity);
    }

    public void remove(MapEntity entity) {
        for (int i = 0; i < this.entities.size(); i++) {
            if (entity.getLocation().equals(this.entities.get(i).getLocation())) {
                entities.remove(i);
            }
        }
    }

    public boolean inBounds(int row, int col) {
        return row >= 0 && row < this.map.length && col >= 0 && col < this.map[row].length;
    }

    public MapEntity entityOn(int row, int col) {
        for (MapEntity entity : this.entities) {
            if (entity.getLocation().equals(new Location(row, col))) {
                return entity;
            }
        }
        return null;
    }

    public Tile getTile(int row, int col) {
        return this.map[row][col];
    }

    public static void clearScreen() {
        System.out.println("\u001b[H\u001b[2J");
    }

    public void render() {
        clearScreen();
        for (int row = 0; row < this.map.length; row++) {
            for (int col = 0; col < this.map[row].length; col++) {
                if (entityOn(row, col) != null) {
                    System.out.print(entityOn(row, col).render());
                } else {
                    System.out.print(this.map[row][col].toString());
                }
            }
            System.out.println();
        }
    }
    //being lazy
    public void render(boolean bool) {
       
        for (int row = 0; row < this.map.length; row++) {
            for (int col = 0; col < this.map[row].length; col++) {
                if (entityOn(row, col) != null) {
                    System.out.print(entityOn(row, col).render());
                } else {
                    System.out.print(this.map[row][col].toString());
                }
            }
            System.out.println();
        }
    }
}
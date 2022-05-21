package Blocks;

import java.awt.*;

public abstract class Block {
    protected int Xpos;
    protected int Ypos;

    public Block() {

    }
    
    public Block(int X, int Y) {
        Xpos = X;
        Ypos = Y;
    }

    public abstract void draw(Graphics g);

    public int getX() {
        return Xpos;
    }

    public int getY() {
        return Ypos;
    }

    public abstract Rectangle getBounds();
        
        
}

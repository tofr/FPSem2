import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Settings.MapSettings;

public class Button extends Block {

    public Button(int X, int Y) {
        super(X, Y);
    }

    
    public void draw(Graphics g) {
        // TODO Auto-generated method stub\
        g.setColor(Color.RED);
        g.drawRoundRect(Xpos + 2, Ypos + (MapSettings.tileSize / 2), MapSettings.tileSize - 4, MapSettings.tileSize / 2, 1, 1);
        g.setColor(Color.WHITE);
    }

    
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(Xpos + 2, Ypos + (MapSettings.tileSize / 2), MapSettings.tileSize - 4, MapSettings.tileSize / 2);
    }
    
}

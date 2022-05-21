

import java.awt.*;

public abstract class Handler {
    public abstract void draw(Graphics g, DriverRunner driver);
    public abstract void tick(DriverRunner driver);
}

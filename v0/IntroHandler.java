import java.awt.Graphics;

import LevelRelated.BackGroundDrawer;
import music.MusicThing;

public class IntroHandler extends Handler {

    public MusicThing music;
    public String filePath;
    public boolean started; 

    BackGroundDrawer backGround;

    public IntroHandler() {
        backGround = new BackGroundDrawer("logo.jpg");
        try {
            music = new MusicThing("./music/test.mid");
        } catch (Exception e) {
            System.out.println(e + "catch error");
        }    
        started = false;
    }

    public void tick(DriverRunner driver) {
        // TODO Auto-generated method stub
        if (!started) {
            music.play();
            System.out.println("playing");
            started = true;
        }
        
    }
    
    public void draw(Graphics g, DriverRunner driver) {
        // TODO Auto-generated method stub
        tick(driver);
        backGround.draw(g, driver);
    }


    
    
    
}

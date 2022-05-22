import LevelRelated.BackGroundDrawer;
import music.MusicThing;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.*;

public class LogoHandler extends Handler {

    public MusicThing music;
    public String filePath;
    public boolean started; 

    private long localTime;

    private String part;
    private boolean isFinished;

    Image logoImg;

    public float fade = 0f;

    public LogoHandler() {
        try {
            music = new MusicThing("./music/test.mid");
        } catch (Exception e) {
            System.out.println(e + "catch error");
        }  
        music.play();
        music.pause();  
        started = false;
        isFinished = false;
        part = "papple";
        localTime = System.currentTimeMillis();
        loadImg();

    }

    // do something to make this generic to every class
    public void loadImg() {
		try {
			logoImg = ImageIO.read(new File("logo.png"));

		} catch (Exception e) {
			//TODO: handle exception
		}
		logoImg = logoImg.getScaledInstance(640, 480, logoImg.SCALE_DEFAULT);
	}

    public void tick(DriverRunner driver) {
        if ((System.currentTimeMillis() - localTime) > 500) {
            if (!started) {
                music.play();
                started = true;
            }
        }

        if (System.currentTimeMillis() - localTime < 5000) {
            fadeIn();
        } else if (System.currentTimeMillis() - localTime > 17000) {
            music.pause();
            isFinished = true;
        }
        else if (System.currentTimeMillis() - localTime > 13000) {
            fadeOut();
        } 
        else if (System.currentTimeMillis() - localTime > 10000) {
            part = "";
            fade = 1f;
        } 
        else if (System.currentTimeMillis() - localTime > 5000) {
            fadeOut();
        }
        

        

    }

    public void fadeIn() {
        if (fade < 0.995f) {
            fade += 0.005f;
        } else {
            fade = 1f;
        }
        
    }

    public void fadeOut() {
        if (fade > 0.005f) {
            fade -= 0.005f;
        } else {
            fade = 0f;
        }  
    }
    
    public void draw(Graphics g, DriverRunner driver) {
        
        
        // for color fading
        Graphics2D g2d = (Graphics2D) g;
        // g2d.clearRect(0, 0, 800, 600);
        tick(driver);

        // TODO Auto-generated method stub
        g2d.fillRect(0, 0, 800, 600);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
        if (part.equals("papple")) {
            
            
            
    
            g2d.setColor(Color.BLACK);
            
            g2d.drawImage(logoImg, 80, 60, driver);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        } else {

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 800, 600);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.PLAIN, 100));
            g2d.drawString("PRESENTS", 180, 300);
        }
        if (isFinished) driver.gameStack.pop();
        
    }




    
    
    
}

import LevelRelated.BackGroundDrawer;
import music.MusicThing;
import java.awt.image.*;
import java.io.File;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class IntroMenuHandler extends Handler {

    public enum States {
        AnimatedBeginning,
        Settings,
        Main,
        Play,
    }

    public Timer timer;

    public MusicThing music;
    public String filePath;
    public boolean started; 

    private long localTime;

    private String part;
    private boolean isFinishedWithPartOne;
    private boolean isFinished;

    public Stack<States> state;

    public States selection;
    

    Image logoImg;

    public float fade = 0f;

    public IntroMenuHandler() {
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
        selection = null;
       state = new Stack<States>();
       startStates();

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

        if (System.currentTimeMillis() - localTime < 6000) {
            fadeIn();
        } else if (System.currentTimeMillis() - localTime > 18000) {
            isFinishedWithPartOne = true;
        }
        else if (System.currentTimeMillis() - localTime > 14000) {
            fadeOut();
        } 
        else if (System.currentTimeMillis() - localTime > 11000) {
            part = "";
            fade = 1f;
        } 
        else if (System.currentTimeMillis() - localTime > 6000) {
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

    public void startStates() {
        state.push(States.Main);
    }

    public void selectOther() {
        if (selection == States.Play) {
            selection = States.Settings;
        } else {
            selection = States.Play;
        }
    }

    public void drawSettings(Graphics g) {
        g.setColor(Color.WHITE);
        g.clearRect(0, 0, 800, 600);
    }

    public void drawMain(Graphics g) {
        // draw background <- insert code here when u find out
        g.setColor(Color.WHITE);
        g.fillRect(300, 200, 100, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.PLAIN, 25));
        g.drawString("Play", 320, 350);
        System.out.println(selection);
    }

    public void drawLogo(Graphics g, DriverRunner driver) {
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
        
    }
    
    public void draw(Graphics g, DriverRunner driver) {
        if (!isFinishedWithPartOne) {
            drawLogo(g, driver);
        } else {
            if (state.peek() == States.Main) {
                drawMain(g);
            } else if (state.peek() == States.Settings) {
                drawSettings(g);
            } else if (state.peek() == States.Play) {

                music.pause();
                driver.gameStack.pop();
            }
        }
        
        
    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (isFinishedWithPartOne) {
            if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                selectOther();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER && selection != null) {
                state.push(selection);
                selection = null;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE && state.peek() != States.Main) {
                state.pop();
            }
        } else {
            isFinishedWithPartOne = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }




    
    
    
}

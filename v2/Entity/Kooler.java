//first type of enemy(goomba)
public class Kooler extends Enemy{
    
    Kooler(){
    public double yVelo = 4;
    public double xVelo = -4;
    }

    

    
    public void tick(TileMap tileMap) {
        
       
        if (falling) {
            yVelo = 6;
        }
        rigidCollision(tileMap);
        xPos += xVelo;
        yPos += yVelo;
        rigidCollision(tileMap);
        nonRigidCollision(tileMap);
    }

}
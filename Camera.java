import Entity.Player;

public class Camera {
    public double x, y;
    public double formerX, formerY;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void tick(Player player) { // have the camera follow the player!
        
        x = -player.getX() + DriverRunner.GAME_WIDTH/2;
        y = -player.getY() + DriverRunner.GAME_HEIGHT/2;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}

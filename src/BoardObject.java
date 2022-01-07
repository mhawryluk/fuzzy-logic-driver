import java.awt.*;
import java.util.Random;

public class BoardObject {

    protected double x, y;
    public int width, height;

    public double velX = 0, velY = 0;
    public double fuzzyVelX = 0, fuzzyVelY = 0;
    public final double velValue = 4;

    public Image pic;

    static protected final Random rand = new Random();

    public double getX(){ return x;}
    public double getY(){ return y;}

    public void move(){
        x += velX + fuzzyVelX;
        y += velY + fuzzyVelY;

        wrap(800, 800);
    }

    public void wrap(int boardWidth, int boardHeight){
        if (x > boardWidth) x -= boardWidth;
        if (x < 0) x += boardWidth;
        if (y > boardHeight) y -= boardHeight;
        if (y < 0) y += boardHeight;
    }

    public void changeVelocity(double x_change, double y_change){
        velX += x_change;
        velY += y_change;
    }

    public void changePosition(){
        x = rand.nextInt(600) + 100;
        y = rand.nextInt(600) + 100;
    }

    public void setPosition (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void chase(double targetX, double targetY){
        double steer_x = targetX - x;
        double steer_y = targetY - y;

        changeVelocity(steer_x, steer_y);
        int velVal = (int) Math.round(Math.sqrt(velX * velX + velY * velY));
        velX = (int) (velX * this.velValue/(velVal*1.));
        velY = (int) (velY * this.velValue/(velVal*1.));
    }
}

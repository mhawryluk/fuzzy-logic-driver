import java.awt.*;
import java.util.Random;

public class BoardObject {

    protected double x, y;
    public int width, height;

    public double velX = 0, velY = 0;
    public final double velValue = 2;

    public Image pic;

    static protected final Random rand = new Random();

    public double getX(){ return x;}
    public double getY(){ return y;}

    public void move(){
        x += velX;
        y += velY;
    }

    public void changeVelocity(double x_change, double y_change){
        velX += x_change;
        velY += y_change;
    }

    public void changePosition(){
        x = rand.nextInt(800);
        y = rand.nextInt(800);
    }

    public void setPosition (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void chase(double targertX, double targetY){
        double steer_x = targertX - x;
        double steer_y = targetY - y;

        changeVelocity(steer_x, steer_y);
        int velVal = (int) Math.round(Math.sqrt(velX * velX + velY * velY));
        velX = (int) (velX * this.velValue/(velVal*1.));
        velY = (int) (velY * this.velValue/(velVal*1.));
    }
}

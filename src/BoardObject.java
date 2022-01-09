import java.awt.*;
import java.util.Random;

public class BoardObject {

    protected double x, y;
    public int size;



    public Image pic;

    static protected final Random rand = new Random();

    public double getX(){ return x;}
    public double getY(){ return y;}

    public int getDrawX(){
        return (int)(Math.round(x - size/2.));
    }
    public int getDrawY(){
        return (int)(Math.round(y - size/2.));
    }


    public boolean checkCollision(double x, double y, int size) {
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) < Math.pow(size/2., 2);
    }

    public void changePosition(){
        x = rand.nextInt(600) + 100;
        y = rand.nextInt(600) + 100;
    }

    public void setPosition (double x, double y) {
        this.x = x;
        this.y = y;
    }
}

import javax.swing.*;
import java.awt.*;

public class Runner {

    protected int x=100, y=100;
    protected int velX = 0, velY = 0;
    public int width = 50;
    public int height = 50;
    protected Image pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);

    private final FuzzyControl fuzzyController = new FuzzyControl();


    public void move(){
        x += velX;
        y += velY;
    }

    public void changeVelocity(int x_change, int y_change){
        velX += x_change;
        velY += y_change;
    }

    public int getX(){ return x;}
    public int getY(){ return y;}

    public Image getPic() {
        return pic;
    }

    public void setPosition (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void fuzzyControl(int chaserX, int chaserY){
        double velocityChangeX = fuzzyController.getVelocityChange(x, chaserX - x);
        double velocityChangeY = fuzzyController.getVelocityChange(y, chaserY - y);

        if (!Double.isNaN(velocityChangeX) && !Double.isNaN(velocityChangeY)){
            changeVelocity((int)(velocityChangeX/4), (int)(velocityChangeY/4));
        }

//        System.out.println(velocityChangeX + " " + velocityChangeY);
    }
}

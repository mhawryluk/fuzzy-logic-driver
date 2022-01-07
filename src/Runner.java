import javax.swing.*;
import java.awt.*;

public class Runner extends BoardObject{

    private final FuzzyControl fuzzyController = new FuzzyControl();

    public Runner(){
        changePosition();
        width = 50;
        height = 50;
        pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public void fuzzyControl(double chaserX, double chaserY){
        double velocityChangeX = fuzzyController.getVelocityChange(x, chaserX - x);
        double velocityChangeY = fuzzyController.getVelocityChange(y, chaserY - y);

        if (!Double.isNaN(velocityChangeX) && !Double.isNaN(velocityChangeY)){
            changeVelocity(velocityChangeX, velocityChangeY);
        }

//        System.out.println(velocityChangeX + " " + velocityChangeY);
    }

    public boolean checkCollision(double x, double y, int width, int height) {
        if (this.x > x + width) return false;
        if (this.y > y + height) return false;
        if (this.x + this.width < x) return false;
        if (this.y + this.height < y) return false;

        return true;
    }
}

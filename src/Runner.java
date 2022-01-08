import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Runner extends BoardObject{

    private final FuzzyControl fuzzyController = new FuzzyControl();
    private final Random noiseX = new Random();
    private final Random noiseY = new Random();

    private final double velVal = 2;

    public Runner(){
        changePosition();
        width = 50;
        height = 50;
        pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public void fuzzyControl(double chaserX, double chaserY, double coinX, double coinY) {
        fuzzyVelX = fuzzyController.getVelocityChange(coinX - x, chaserX - x)*2;
        fuzzyVelY = fuzzyController.getVelocityChange(coinY - y, chaserY - y)*2;
    }

    public boolean checkCollision(double x, double y, int width, int height) {
        if (this.x > x + width) return false;
        if (this.y > y + height) return false;
        if (this.x + this.width < x) return false;
        if (this.y + this.height < y) return false;

        return true;
    }

    public void noiseRun() {
        velX = noiseX.nextGaussian();
        velY = noiseY.nextGaussian();

        double vel = Math.sqrt(velX*velX + velY*velY);
        velX *= (velVal/vel);
        velY *= (velVal/vel);

//        System.out.println(velX + " " + velY);
    }


}

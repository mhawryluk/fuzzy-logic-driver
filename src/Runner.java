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
        size = 50;
        pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public void fuzzyControl(double chaserX, double chaserY, double coinX, double coinY) {
        fuzzyVelX = fuzzyController.getVelocityChange(coinX - x, chaserX - x);
        fuzzyVelY = fuzzyController.getVelocityChange(coinY - y, chaserY - y);
    }

    public boolean checkCollision(double x, double y, int size) {
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) < Math.pow(size, 2);
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

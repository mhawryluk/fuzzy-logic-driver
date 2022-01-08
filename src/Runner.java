import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Runner extends BoardObject{

    private final FuzzyControl fuzzyController = new FuzzyControl();
    private final Random noiseX = new Random();
    private final Random noiseY = new Random();

    private int lives = 5;

    private final double velVal = 2;
    private final double steerVal = 2;

    public Runner(){
        changePosition();
        size = 50;
        pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public void fuzzyControl(double chaserX, double chaserY, double coinX, double coinY) {
        fuzzyVelX = fuzzyController.getVelocityChange(coinX - x, chaserX - x);
        fuzzyVelY = fuzzyController.getVelocityChange(coinY - y, chaserY - y);
    }



    public void noiseRun() {
        double steerX = noiseX.nextGaussian();
        double steerY = noiseY.nextGaussian();

        double vel = Math.sqrt(steerX*steerX + steerY*steerY);
        steerX *= (steerVal/vel);
        steerY *= (steerVal/vel);

        velX += steerX;
        velY += steerY;

        vel = Math.sqrt(velX*velX + velY*velY);
        velX *= (velVal/vel);
        velY *= (velVal/vel);

//        System.out.println(velX + " " + velY);
    }

    public void lifeDelta(int delta){
        lives += delta;
        if (lives > 5) lives = 5;
        if (lives < 0) lives = 0;
    }

    public int getLives(){
        return lives;
    }
}

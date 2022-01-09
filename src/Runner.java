import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Runner extends BoardObject{

    private final FuzzyControl fuzzyController = new FuzzyControl();
    private final Random noiseX = new Random();
    private final Random noiseY = new Random();

    public double velX = 0, velY = 0;
    public double fuzzyVelX = 0, fuzzyVelY = 0;

    private int lives = 5;

    private final double velVal = 2;
    private final double steerVal = 0.5;
    private double steerX=0, steerY=0;

    public Runner(){
        changePosition();
        size = 50;
        pic = new ImageIcon("pics/cat.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public void fuzzyControl(double chaserX, double chaserY, double coinX, double coinY) {
        fuzzyVelX = fuzzyController.getVelocityChange(coinX - x, chaserX - x);
        fuzzyVelY = fuzzyController.getVelocityChange(coinY - y, chaserY - y);
    }

    public void move(){
        applySteering();
        x += velX;
        y += velY;

        if (!Double.isNaN(fuzzyVelX)) x += fuzzyVelX;
        if (!Double.isNaN(fuzzyVelY)) y += fuzzyVelY;

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

    private void applySteering(){
        velX += steerX;
        velY += steerY;

        double vel = Math.sqrt(velX*velX + velY*velY);
        if (vel != 0) {
            velX *= (velVal / vel);
            velY *= (velVal / vel);
        }
    }

    public void noiseRun() {
        steerX = noiseX.nextGaussian();
        steerY = noiseY.nextGaussian();

        double vel = Math.sqrt(steerX*steerX + steerY*steerY);
        if (vel != 0) {
            steerX *= (steerVal / vel);
            steerY *= (steerVal / vel);
        }
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

import javax.swing.*;
import java.awt.*;

public class Chaser extends Runner{

    private final int velValue = 2;

    public Chaser() {
        super();
        width = 100;
        height = 100;
        x = 500;
        y = 500;

        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public void chase(int runnerX, int runnerY){
        int steer_x = runnerX - x;
        int steer_y = runnerY - y;

        changeVelocity(steer_x, steer_y);
        int velVal = (int) Math.round(Math.sqrt(velX * velX + velY * velY));
        velX = (int) (velX * this.velValue/(velVal*1.));
        velY = (int) (velY * this.velValue/(velVal*1.));
    }
}

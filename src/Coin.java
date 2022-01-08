import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Coin extends BoardObject{

    public Coin(ArrayList<Obstacle> obstacles) {
        size = 100;
        do changePosition();
        while (collideWithObstacle(obstacles));
        pic = new ImageIcon("pics/coin.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public boolean collideWithObstacle(ArrayList<Obstacle> obstacles){
        for (var obstacle : obstacles){
            if (checkCollision(obstacle.getX(), obstacle.getY(), obstacle.size)) return true;
        }
        return false;
    }
}

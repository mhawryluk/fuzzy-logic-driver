import javax.swing.*;
import java.awt.*;

public class Obstacle extends BoardObject{

    private void init(){
        size = 60;
        pic = new ImageIcon("pics/bomb.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public Obstacle() {
        changePosition();
        init();
    }

    public Obstacle(int x, int y){
        this.x = x;
        this.y = y;

        init();
    }
}

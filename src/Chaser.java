import javax.swing.*;
import java.awt.*;

public class Chaser extends BoardObject{

    public Chaser() {
        changePosition();
        size = 100;
        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public Chaser(int x, int y){
        this.x = x;
        this.y = y;

        size = 100;
        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }
}

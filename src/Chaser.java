import javax.swing.*;
import java.awt.*;

public class Chaser extends BoardObject{

    public Chaser() {
        changePosition();
        width = 100;
        height = 100;
        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public Chaser(int x, int y){
        this.x = x;
        this.y = y;

        width = 100;
        height = 100;
        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}

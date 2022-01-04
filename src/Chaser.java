import javax.swing.*;
import java.awt.*;

public class Chaser extends BoardObject{

    public Chaser() {
        width = 100;
        height = 100;
        x = 500;
        y = 500;

        pic = new ImageIcon("pics/bear.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}

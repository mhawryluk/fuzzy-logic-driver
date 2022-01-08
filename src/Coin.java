import javax.swing.*;
import java.awt.*;

public class Coin extends BoardObject{


    public Coin() {
        size = 50;
        changePosition();
        pic = new ImageIcon("pics/coin.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }
}

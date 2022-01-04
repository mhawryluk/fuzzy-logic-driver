import javax.swing.*;
import java.awt.*;

public class Coin extends BoardObject{

    public final int width=50, height=50;

    public Coin() {
        changePosition();
    }

    protected Image pic = new ImageIcon("pics/coin.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
}

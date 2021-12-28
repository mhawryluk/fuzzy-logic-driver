import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Fuzzy chase");
//        setLayout(new BorderLayout(0, 0));

        setBounds(0, 0, 500, 250);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        pack();
        setVisible(true);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
//        setLocation(screenDim.width/2 - getSize().width/2, screenDim.height/2 - getSize().height/2);
    }
}

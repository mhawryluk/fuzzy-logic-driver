import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final Panel panel;

    public Window() {

        setTitle("Fuzzy chase");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0, 0, 800, 800);
        setPreferredSize(new Dimension(800, 800));

        this.panel = new Panel(800, 800);
        add(panel);

        pack();
        setResizable(false);
        setVisible(true);

//        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
//        setLocation(screenDim.width/2 - getSize().width/2, screenDim.height/2 - getSize().height/2);
    }
}

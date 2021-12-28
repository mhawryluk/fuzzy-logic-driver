import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel implements ActionListener {

    public final int width, height;
    private final Runner runner;
    private final Chaser chaser;
    private final Timer timer;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        this.runner = new Runner();
        this.chaser = new Chaser();

        timer = new Timer(50, this);
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                runner.setPosition(e.getX(), e.getY());
            }
        });
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        // background
        g2D.setPaint(new Color(137, 176, 174));
        g2D.fillRect(0, 0, this.width, this.height);

        // runner
        g2D.drawImage(runner.getPic(), runner.getX(), runner.getY(), runner.width, runner.height, null, this);

        // chaser
        g2D.drawImage(chaser.getPic(), chaser.getX(), chaser.getY(), chaser.width, chaser.height, null, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            runner.move();
            chaser.move();
            chaser.chase(runner.x, runner.y);
        }
    }
}

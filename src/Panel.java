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
    private Coin coin = new Coin();
    private int score = 0;

    private final int fontSize = 50;
    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);

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
        g2D.drawImage(runner.pic, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), runner.width, runner.height, null, this);

        // chaser
        g2D.drawImage(chaser.pic, (int)Math.round(chaser.getX()), (int)Math.round(chaser.getY()), chaser.width, chaser.height, null, this);

        //coin
        if (coin != null){
            g2D.drawImage(coin.pic, (int)Math.round(coin.getX()), (int)Math.round(coin.getY()), coin.width, coin.height, null, this);
        }

        // score
        g2D.setPaint(new Color(255, 255, 255));
        g2D.setFont(font);
        g2D.drawString("score: "+score, 400, 750);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            runner.move();
            chaser.move();
            chaser.chase(runner.getX(), runner.getY());

            runner.velX = 0;
            runner.velY = 0;
            runner.chase(coin.getX(), coin.getY());
            runner.fuzzyControl(chaser.getX(), chaser.getY());

            if (runner.checkCollision(chaser.getX(), chaser.getY(), chaser.width, chaser.height)){
                score -= 1;
            }

            if (runner.checkCollision(coin.getX(), coin.getY(), coin.width, coin.height)){
                score += 1;
                coin.changePosition();
            }

//            System.out.println(runner.velX + " " + runner.velY + " " + chaser.velX + " " + chaser.velY);
        }
    }
}

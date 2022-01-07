import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    public final int width, height;
    private final Runner runner;
    private final ArrayList<Chaser> chasers = new ArrayList<>();
    private final Timer timer;
    private Coin coin = new Coin();
    private int score = 0;
    private long frame = 0;

    private final int fontSize = 50;
    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        setFocusable(true);
        requestFocus();

        this.runner = new Runner();
        chasers.add(new Chaser());

        timer = new Timer(50, this);
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chasers.add(new Chaser(e.getX(), e.getY()));
//                runner.setPosition(e.getX(), e.getY());
            }
        });

        addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == ' '){
                    chasers.removeAll(chasers);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        if (!hasFocus()) requestFocus();

        // background
        g2D.setPaint(new Color(50, 75, 74));
        g2D.fillRect(0, 0, this.width, this.height);

        g2D.setPaint(new Color(137, 176, 174));
        g2D.fillRect(50, 50, this.width-100, this.height-100);

        // runner
        g2D.drawImage(runner.pic, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), runner.width, runner.height, null, this);

        // chaser
        for (var chaser : chasers) {
            g2D.drawImage(chaser.pic, (int) Math.round(chaser.getX()), (int) Math.round(chaser.getY()), chaser.width, chaser.height, null, this);
        }
        //coin
        if (coin != null){
            g2D.drawImage(coin.pic, (int)Math.round(coin.getX()), (int)Math.round(coin.getY()), coin.width, coin.height, null, this);
        }

        // score
        g2D.setPaint(new Color(255, 255, 255));
        g2D.setFont(font);
        g2D.drawString("score: "+ score, 400, 750);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            frame++;
            runner.move();

            if (frame % 50 == 0) runner.noiseRun();

            for (var chaser : chasers) {
                runner.fuzzyControl(chaser.getX(), chaser.getY(), coin.getX(), coin.getY());
            }

            for (var chaser : chasers) {
                if (runner.checkCollision(chaser.getX(), chaser.getY(), chaser.width, chaser.height)) {
                    score -= 1;
                }
            }

            if (runner.checkCollision(coin.getX(), coin.getY(), coin.width, coin.height)){
                score += 1;
                coin.changePosition();
            }

//            System.out.println(runner.velX + " " + runner.velY + " " + chaser.velX + " " + chaser.velY);
        }
    }
}

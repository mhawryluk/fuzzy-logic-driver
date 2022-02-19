import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    public final int width, height;
    private final Runner runner;
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final Timer timer;
    private Coin coin;
    private final int heartSize = 50;
    private final Image heartPic = new ImageIcon("pics/heart.png").getImage().getScaledInstance(heartSize, heartSize, Image.SCALE_DEFAULT);


    private long frame = 0;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        setFocusable(true);
        requestFocus();

        this.runner = new Runner();
//        System.out.println(runner.getX());
        obstacles.add(new Obstacle());
        coin = new Coin(obstacles);

        timer = new Timer(50, this);
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                obstacles.add(new Obstacle(e.getX(), e.getY()));
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
                    obstacles.removeAll(obstacles);
                    obstacles.add(new Obstacle());
                }

                if (keyEvent.getKeyChar() == 'a'){
                    timer.stop();
                }

                if (keyEvent.getKeyChar() == 's'){
                    timer.restart();
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

//        System.out.println(runner.getX());

        if (!hasFocus()) requestFocus();

        // background
        g2D.setPaint(new Color(50, 75, 74));
        g2D.fillRect(0, 0, this.width, this.height);

        g2D.setPaint(new Color(137, 176, 174));
        g2D.fillRect(50, 50, this.width-100, this.height-100);

        // runner
        g2D.drawImage(runner.pic, runner.getDrawX(), runner.getDrawY(), runner.size, runner.size, null, this);

        int arrowScale = 20;
        g2D.setPaint(new Color(19, 142, 225));
        drawArrowLine(g2D, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), (int)(Math.round(runner.getX()) + runner.fuzzyVelX*arrowScale), (int)(Math.round(runner.getY())+runner.fuzzyVelY*arrowScale));

        g2D.setPaint(new Color(208, 150, 10));
        drawArrowLine(g2D, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), (int)(Math.round(runner.getX()) + runner.velX*arrowScale), (int)(Math.round(runner.getY())+runner.velY*arrowScale));

        // obstacle
        for (var obstacle : obstacles) {
            g2D.drawImage(obstacle.pic, obstacle.getDrawX(), obstacle.getDrawY(), obstacle.size, obstacle.size, null, this);
        }
        //coin
        if (coin != null){
            g2D.drawImage(coin.pic, coin.getDrawX(), coin.getDrawY(), coin.size, coin.size, null, this);
        }

        // lives
        for (int i = 0; i < runner.getLives(); i++) {
            g2D.drawImage(heartPic, 50 + i*65, 700, heartSize, heartSize, null, this);
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        int d = 5;
        int h = 5;
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xPoints = {x2, (int) xm, (int) xn};
        int[] yPoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            frame++;
            runner.move();

            if (frame % 20 == 0) runner.noiseRun();

            runner.fuzzyControl(obstacles, coin);

            for (var obstacle : obstacles) {
                if (runner.checkCollision(obstacle.getX(), obstacle.getY(), obstacle.size)) {
                    runner.lifeDelta(-1);
                    obstacle.changePosition();
                }
            }

            if (runner.checkCollision(coin.getX(), coin.getY(), coin.size)){
                runner.lifeDelta(1);
                do coin.changePosition();
                while (coin.collideWithObstacle(obstacles));
            }

        }
    }


}

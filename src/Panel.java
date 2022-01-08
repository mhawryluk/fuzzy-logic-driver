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
        g2D.drawImage(runner.pic, runner.getDrawX(), runner.getDrawY(), runner.size, runner.size, null, this);

        int arrowScale = 20;
        g2D.setPaint(new Color(19, 142, 225));
        drawArrowLine(g2D, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), (int)(Math.round(runner.getX()) + runner.fuzzyVelX*arrowScale), (int)(Math.round(runner.getY())+runner.fuzzyVelY*arrowScale), 5, 5);

        g2D.setPaint(new Color(208, 150, 10));
        drawArrowLine(g2D, (int)Math.round(runner.getX()), (int)Math.round(runner.getY()), (int)(Math.round(runner.getX()) + runner.velX*arrowScale), (int)(Math.round(runner.getY())+runner.velY*arrowScale), 5, 5);

        // chaser
        for (var chaser : chasers) {
            g2D.drawImage(chaser.pic, (int) Math.round(chaser.getX()), (int) Math.round(chaser.getY()), chaser.size, chaser.size, null, this);
        }
        //coin
        if (coin != null){
            g2D.drawImage(coin.pic, (int)Math.round(coin.getX()), (int)Math.round(coin.getY()), coin.size, coin.size, null, this);
        }

        // score
        g2D.setPaint(new Color(255, 255, 255));
        g2D.setFont(font);
        g2D.drawString("score: "+ score, 400, 750);
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
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

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
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
                if (runner.checkCollision(chaser.getX(), chaser.getY(), chaser.size)) {
                    score -= 1;
                }
            }

            if (runner.checkCollision(coin.getX(), coin.getY(), coin.size)){
                score += 1;
                coin.changePosition();
            }

//            System.out.println(runner.velX + " " + runner.velY + " " + chaser.velX + " " + chaser.velY);
        }
    }


}

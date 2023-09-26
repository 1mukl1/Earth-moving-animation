import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());
    }

    static class MyComponent extends JComponent {
        private int x = 172;
        private int y = 209;
        private double t = 0;
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Timer timer = new Timer(400, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            try {
                BufferedImage sun = ImageIO.read(new File("солнце.png"));
                BufferedImage earth = ImageIO.read(new File("земля.png"));
                g2.drawImage(sun, 236, 161, this);
                g2.drawImage(earth, x, y, this);
                timer.start();
                t += 0.01;
                System.out.println(x);
                x = 290 + (int) (120 * Math.cos(t));
                y = 200 + (int) (120 * Math.sin(t));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 200, 600, 450);
        jFrame.getContentPane().setBackground(Color.black);
        jFrame.setTitle("Движение земли вокруг солнца");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        try {
            Image image = ImageIO.read(new File("земля.png"));
            jFrame.setIconImage(image);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        jFrame.setVisible(true);
        return jFrame;
    }
        }
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import utilities.*;

public class Rotation extends JLabel {
  public Rectangle rect = new Rectangle(0, 0, 30, 30);
  public int c_angle = 0;

  @Override
  public void paintComponent (Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.rotate(Math.toRadians(c_angle), (int)(rect.getX() + rect.getWidth()/2), (int)(rect.getY() + rect.getHeight()/2));
    g2d.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getHeight(), (int)rect.getWidth());
  }
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Rotation");
    Rotation rotation = new Rotation();
    rotation.setPreferredSize(new Dimension(500,500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(rotation);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      rotation.rect.translate(1,1);
      rotation.c_angle += -5;
      frame.repaint();
      Thread.sleep(17);
    }
  }
}

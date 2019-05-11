package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Collision_test extends JLabel {
  public int rx = 0;
  public int ry = 0;
  public int height = 40;
  public int width = 40;
  public int lx1 = 0;
  public int ly1 = 400;
  public int lx2 =  400;
  public int ly2 = 400;

  @Override
  public void paint (Graphics g) {
    g.drawRect(rx,ry,height,width);
    g.drawLine(lx1,ly1,lx2,ly2);
  }

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Collisions");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Collision_test emptyLabel = new Collision_test();
    emptyLabel.setPreferredSize(new Dimension(500, 500));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.setSize(new Dimension(500,500));
    frame.pack();
    frame.setVisible(true);
    while (true) {
      emptyLabel.lx2 = frame.getContentPane().getWidth();
      emptyLabel.ly1 = frame.getContentPane().getHeight() / 2;
      emptyLabel.ly2 = frame.getContentPane().getHeight() / 2;
      if (emptyLabel.ry + 0.004 * frame.getContentPane().getHeight() >= emptyLabel.ly1-40) {
        emptyLabel.ry = 0;
      }
      emptyLabel.rx += 0.004 * frame.getContentPane().getWidth();
      emptyLabel.ry += 0.004 * frame.getContentPane().getHeight();
      frame.repaint();
      Thread.sleep(17);
      if (emptyLabel.rx >= frame.getContentPane().getWidth() && emptyLabel.ry >= frame.getContentPane().getHeight()) {
        emptyLabel.rx = 0;
        emptyLabel.ry = 0;
      } else if (emptyLabel.rx >= frame.getContentPane().getWidth()) {
        emptyLabel.rx = 0;
      } else if (emptyLabel.ry >= frame.getContentPane().getHeight()) {
        emptyLabel.ry = 0;
      }
      frame.repaint();
    }
  }
}

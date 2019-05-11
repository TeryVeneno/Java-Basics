package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frames extends JLabel {
  public int rx = 0;
  public int ry = 0;
  public int height = 40;
  public int width = 40;

  @Override
  public void paint (Graphics g) {
    g.drawRect(rx,ry,height,width);
    g.drawOval(rx+4,ry+4,height-8,width-8);
    g.drawRect(rx+10,ry+10,height-20, width-20);
  }

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Infinite Rectangle");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Frames emptyLabel = new Frames();
    emptyLabel.setPreferredSize(new Dimension(500, 500));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.setSize(new Dimension(500,500));
    frame.pack();
    frame.setVisible(true);
    while (true) {
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
    }
  }
}

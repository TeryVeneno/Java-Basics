package gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Angles extends JLabel {
  public BufferedImage img;
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

  public void load_image () {
    try {
      img = ImageIO.read(new File("gui/angles.png"));
    } catch (IOException e) {
    }
  }
  public static void main(String[] args) throws InterruptedException {
    int[] directions = new int[8];
    for (int s = 0; s < 8; s++) {
      directions[s] = 0;
    }
    directions[5] = 1;
    BufferedImage img;
    JFrame frame = new JFrame("Angles");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Angles emptyLabel = new Angles();
    emptyLabel.setPreferredSize(new Dimension(500, 500));
    emptyLabel.load_image();
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.setSize(new Dimension(500,500));
    frame.pack();
    frame.setVisible(true);
    frame.setIconImage(emptyLabel.img);
    double magnitudew =  0.004 * frame.getContentPane().getWidth();
    double magnitudeh =  0.004 * frame.getContentPane().getHeight();
    while (true) {
      magnitudew =  0.004 * frame.getContentPane().getWidth();
      magnitudeh =  0.004 * frame.getContentPane().getHeight();
      emptyLabel.lx2 = frame.getContentPane().getWidth();
      emptyLabel.ly1 = frame.getContentPane().getHeight() / 2;
      emptyLabel.ly2 = frame.getContentPane().getHeight() / 2;
      if (emptyLabel.ry - (0.004 * frame.getContentPane().getHeight()) >= emptyLabel.ly1-40 && emptyLabel.ry - (0.004 * frame.getContentPane().getHeight()) <= emptyLabel.ly1+5) {
        if (directions[0] == 1) {
          directions[0] = 0;
          directions[4] = 1;
        } else if (directions[1] == 1) {
          directions[1] = 0;
          directions[3] = 1;
        } else if (directions[3] == 1) {
          directions[3] = 0;
          directions[1] = 1;
        } else if (directions[4] == 1) {
          directions[4] = 0;
          directions[0] = 1;
        } else if (directions[5] == 1) {
          directions[5] = 0;
          directions[7] = 1;
        } else if (directions[7] == 1) {
          directions[7] = 0;
          directions[5] = 1;
        }
      }
      if (directions[0] == 1) {
        emptyLabel.ry -= magnitudeh;
      } else if (directions[1] == 1) {
        emptyLabel.rx -= magnitudew;
        emptyLabel.ry -= magnitudeh;
      } else if (directions[2] == 1) {
        emptyLabel.rx -= magnitudew;
      } else if (directions[3] == 1) {
        emptyLabel.rx -= magnitudew;
        emptyLabel.ry += magnitudeh;
      } else if (directions[4] == 1) {
        emptyLabel.ry += magnitudeh;
      } else if (directions[5] == 1) {
        emptyLabel.rx += magnitudew;
        emptyLabel.ry += magnitudeh;
      } else if (directions[6] == 1) {
        emptyLabel.rx += magnitudew;
      } else if (directions[7] == 1) {
        emptyLabel.rx += magnitudew;
        emptyLabel.ry -= magnitudeh;
      }
      frame.repaint();
      Thread.sleep(17);
      if (emptyLabel.rx >= frame.getContentPane().getWidth() && emptyLabel.ry >= frame.getContentPane().getHeight()) {
        emptyLabel.rx = 0;
        emptyLabel.ry = 0;
        frame.repaint();
        Thread.sleep(17);
      } else if (emptyLabel.rx <= -40  && emptyLabel.ry <= -40) {
        emptyLabel.rx = frame.getContentPane().getWidth();
        emptyLabel.ry = frame.getContentPane().getHeight();
        frame.repaint();
        Thread.sleep(17);
      } else if (emptyLabel.rx >= frame.getContentPane().getWidth()) {
        emptyLabel.rx = 0;
        frame.repaint();
        Thread.sleep(17);
      } else if (emptyLabel.ry >= frame.getContentPane().getHeight()) {
        emptyLabel.ry = 0;
        frame.repaint();
        Thread.sleep(17);
      } else if (emptyLabel.rx <= -40) {
        emptyLabel.rx = frame.getContentPane().getWidth();
        frame.repaint();
        Thread.sleep(17);
      } else if (emptyLabel.ry <= -40) {
        emptyLabel.ry = frame.getContentPane().getHeight();
        frame.repaint();
        Thread.sleep(17);
      }
    }
  }
}

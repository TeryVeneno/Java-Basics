package protesting;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Protest extends JLabel {
  public BufferedImage img;
  public String s = "WE'RE PROTESTING!";
  public String drawn = s;
  public int wx = 81;
  public int wy = 384;

  @Override
  public void paint (Graphics g) {
    g.setColor(Color.RED);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 120));
    g.drawString(drawn, wx, wy);
  }

  public void load_image () {
    try {
      img =  img = ImageIO.read(new File("Protesting.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Protest");
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Protest emptyLabel = new Protest();
    emptyLabel.load_image();
    emptyLabel.setBackground(Color.BLACK);
    emptyLabel.setPreferredSize(new Dimension(1000, 1000));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
    frame.setIconImage(emptyLabel.img);
    while(true) {
      emptyLabel.drawn = "";
      frame.repaint();
      Thread.sleep(100);
      for (int e = 0; e < emptyLabel.s.length(); e++) {
        emptyLabel.drawn += emptyLabel.s.charAt(e);
        frame.repaint();
        Thread.sleep(200);
        emptyLabel.drawn = "";
        emptyLabel.wx += 70;
      }
      emptyLabel.wx = 81;
      for (int r = 0; r < 8; r++) {
        if (emptyLabel.drawn.equals(emptyLabel.s)) {
          emptyLabel.drawn = "";
        } else {
          emptyLabel.drawn = emptyLabel.s;
        }
        frame.repaint();
        Thread.sleep(200);
      }
    }
  }
}

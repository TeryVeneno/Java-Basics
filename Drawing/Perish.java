package perish;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Perish {
  private static final long serialVersionUID = 1l;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Perish Drawing Studio");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Pen pen = new Pen();
    Perish_Draw area = new Perish_Draw(new Dimension(800, 800), pen);
    frame.getContentPane().add(area);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      frame.repaint();
    }
  }
}

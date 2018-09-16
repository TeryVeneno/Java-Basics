package gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import utilities.*;

public class Pong {
  public static void main(String[] args) {
    Rectangle[] rect = new Rectangle[5];
    rect[0] = new Rectangle(0, 0, 50, 25);
    rect[1] = new Rectangle(950, 0, 50, 25);
    rect[2] = new Rectangle(500, 500, 10, 10);
    rect[3] = new Rectangle(0, 0, 1000, 1);
    rect[4] = new Rectangle(0, 1000, 1000, 1);
    Objects objects = new Objects(rect);
    Coordinate r_c = new Coordinate(500, 500);
    JFrame frame = new JFrame("Pong");
    objects.setPreferredSize(new Dimension(1000,1000));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(objects);
    frame.pack();
    frame.setVisible(true);
  }
}

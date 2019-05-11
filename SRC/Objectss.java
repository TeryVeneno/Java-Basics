package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Objectss extends JLabel {
  public Rectangle[] rects;
  public String string;
  public String string2;

  public Objectss (Rectangle[] r, String s, String s2) {
    rects = r.clone();
    string = s;
    string2 = s2;
  }

  @Override
  public void paint (Graphics g) {
    for (int r = 0; r < rects.length; r++) {
      g.drawString(string, 500, 10);
      g.drawString(string2, 700, 10);
      g.drawRect((int)rects[r].getX(),(int)rects[r].getY(),(int)rects[r].getWidth(),(int)rects[r].getHeight());
    }
  }
}

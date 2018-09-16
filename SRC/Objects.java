package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Objects extends JLabel {
  Rectangle[] rects;

  public Objects (Rectangle[] r) {
    rects = r.clone();
  }

  @Override
  public void paint (Graphics g) {
    for (int r = 0; r < rects.length; r++) {
      g.drawRect((int)rects[r].getX(),(int)rects[r].getY(),(int)rects[r].getWidth(),(int)rects[r].getHeight());
    }
  }
}

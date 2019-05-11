package perish;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Pen implements Tool {
  private int last_x;
  private int last_y;

  public BufferedImage draw (Rectangle selection_area, int x, int y, int pen_size, BufferedImage area) {
    /*Graphics2D g2d = area.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.black);
    g2d.fillOval(x, y, pen_size, pen_size);*/
    return area;
  }

  public BufferedImage draw_clear(Rectangle selection_area, int x, int y, int pen_size, BufferedImage area) {
    Graphics2D g2d = area.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.black);
    g2d.fillOval(x, y, pen_size, pen_size);
    return area;
  }

  public Rectangle determine_selection_area (int x, int y, Rectangle last_sa) {
    return last_sa;
  }
}

package perish;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public interface Tool {
  public BufferedImage draw (Rectangle selection_area, int x, int y, int pen_size, BufferedImage area);
  public BufferedImage draw_clear(Rectangle selection_area, int x, int y, int pen_size, BufferedImage area);
  public Rectangle determine_selection_area (int x, int y, Rectangle last_sa);
}

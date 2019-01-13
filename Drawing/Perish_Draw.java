package perish;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Perish_Draw extends JPanel implements MouseMotionListener, MouseListener {
  private static final long serialVersionUID = 2l;

  private BufferedImage drawing_area;
  private int x = 0;
  private int y = 0;
  private Color current_color;
  private int pen_size;
  private Tool tool;
  private boolean mouse_pressed = false;
  private boolean mouse_released = false;
  private Rectangle selection_area;

  public Perish_Draw (Dimension size, Tool tool) {
    enableInputMethods(true);
    addMouseMotionListener(this);
    addMouseListener(this);
    setPreferredSize(size);
    setFocusable(true);
    this.tool = tool;
    drawing_area = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
    current_color = Color.BLACK;
    pen_size = 8;
    selection_area = new Rectangle(0,0,0,0);
  }

  @Override
  public void paintComponent (Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    if (mouse_pressed) {
      drawing_area = tool.draw_clear(selection_area, x, y, pen_size, drawing_area);
      x = (int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX());
      y = (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY());
    } else if (mouse_released) {
      drawing_area = tool.draw(selection_area, x, y, pen_size, drawing_area);
      mouse_released = false;
    }
    selection_area = tool.determine_selection_area(x, y, selection_area);
    g2d.drawImage(drawing_area, 0, 0, null);
  }

  public void clear () {
    drawing_area = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
  }

  public void change_pen_size (int pen_size) {
    this.pen_size = pen_size;
  }

  public void change_color (Color color) {
    current_color = color;
  }

  public void change_tool (Tool tool) {
    this.tool = tool;
  }

  public void mouseDragged (MouseEvent e) {
    x = e.getX();
    y = e.getY();
  }

  public void mouseMoved (MouseEvent e) {
    x = e.getX();
    y = e.getY();
  }

  public void mouseClicked (MouseEvent e) {
  }

  public void mouseEntered (MouseEvent e) {
  }

  public void mouseExited (MouseEvent e) {
  }

  public void mousePressed (MouseEvent e) {
    mouse_pressed = true;
    mouse_released = false;
  }

  public void mouseReleased (MouseEvent e) {
    mouse_pressed = false;
    mouse_released = true;
  }
}

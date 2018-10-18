package pong.menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;
import java.util.*;

public class Pong_Button extends JComponent implements MouseListener {
  private static final long serialVersionUID = 1L;

  private Dimension size = new Dimension(40, 40);
  private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
  private boolean mouse_entered;
  private boolean mouse_pressed;

  public Pong_Button (ActionListener e) {
    enableInputMethods(true);
    addMouseListener(this);
    setSize(size.width, size.height);
    setFocusable(true);
  }

  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }
}

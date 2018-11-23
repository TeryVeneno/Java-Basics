package watch;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Watch_Button extends JComponent implements MouseListener {
  static final long serialVersionUID = 1L;
  private Dimension size = new Dimension(50, 50);
  private Dimension location = new Dimension(0, 0);
  private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
  private boolean mouse_pressed;

  public Watch_Button () {
    enableInputMethods(true);
    addMouseListener(this);
    setSize(size.width, size.height);
    setFocusable(true);
  }

  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.BLACK);
    g2d.fillOval(0, 0, 50, 50);
  }

  public void mouseClicked (MouseEvent e) {
  }

  public void mouseEntered (MouseEvent e) {
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    repaint();
  }

  public void mouseExited(MouseEvent e) {
    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    repaint();
  }

  public void mousePressed (MouseEvent e) {
    mouse_pressed = true;
    notify(e);
    repaint();
  }

  public void mouseReleased (MouseEvent e) {
    mouse_pressed = false;
    repaint();
  }

  public void add_action_listener (ActionListener listener) {
    listeners.add(listener);
  }

  private void notify (MouseEvent e) {
    ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(), e.getWhen(), e.getModifiersEx());
    synchronized(listeners) {
      for (int a = 0; a < listeners.size(); a++) {
        ActionListener temp = listeners.get(a);
        temp.actionPerformed(event);
      }
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(getWidth(), getHeight());
  }

  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}

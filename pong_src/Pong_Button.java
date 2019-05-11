package pong.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;
import java.util.*;
import pong.paddle.*;
import pong.ball.*;

public class Pong_Button extends JComponent implements MouseListener {
  private static final long serialVersionUID = 1L;

  private Dimension size = new Dimension(200, 200);
  private Dimension location = new Dimension(0, 0);
  private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
  private boolean mouse_entered;
  private boolean mouse_pressed;
  private Paddle player;
  private Paddle player2;
  private Ball ball;
  private boolean started = false;
  private String text;

  public Pong_Button (String text) {
    this.text = text;
    enableInputMethods(true);
    addMouseListener(this);
    setSize(size.width, size.height);
    setFocusable(true);
  }

  public void update () {
    if (started) {
      ball.update();
      if (player.get_y() != (int)ball.get_y()) {
        if (player.get_y() - (int)ball.get_y() < 0) {
          player.translate(2);
        } else if (player2.get_y() - (int)ball.get_y() > 0) {
          player.translate(-2);
        }
      }
      if (player2.get_y() != (int)ball.get_y()) {
        if (player2.get_y() - (int)ball.get_y() < 0) {
          player2.translate(2);
        } else if (player2.get_y() - (int)ball.get_y() > 0) {
          player2.translate(-2);
        }
      }
      if (player.get_y() < -2) {
        player.translate(2);
      } else if (player.get_y() + 20 > getHeight()) {
        player.translate(-2);
      }
      if (player2.get_y() < -2) {
        player2.translate(2);
      } else if (player2.get_y() + 20 > getHeight()) {
        player2.translate(-2);
      }
      if (player.colliding(ball)) {
        ball.accelerate(ball.ret_mag(), player.calc_angle(ball));
        ball.set_x(player.get_x()+4);
      }
      if (player2.colliding(ball)) {
        ball.accelerate(ball.ret_mag(), player2.calc_angle(ball));
        ball.set_x(player2.get_x()-3);
      }
      if ((ball.get_x() < 0)) {
        ball.set_x(getWidth()/2);
        ball.set_y(getHeight()/2);
        ball.accelerate(ball.ret_mag(), 0);
      }
      if (ball.get_x() + ball.get_radius() > getWidth()) {
        ball.set_x(getWidth()/2);
        ball.set_y(getHeight()/2);
        ball.accelerate(ball.ret_mag(), 180);
      }
      if ((ball.get_y() + ball.get_radius() <= 2 || ball.get_y() + ball.get_radius() >= getHeight()-2)) {
        ball.accelerate(ball.ret_mag(), 360-ball.ret_dir());
      }
    } else {
      started = true;
      player = new Paddle(1, getHeight()/2, 20, 3, Color.GREEN,1);
      player2 = new Paddle(getWidth()-3, getHeight()/2, 20, 3, Color.RED,0);
      ball = new Ball(getWidth()/2-25, getHeight()/2, 2, Color.WHITE);
      ball.accelerate(3.2, 0);
    }
  }

  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.BLACK);
    g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, (int)Math.sqrt(getWidth()), (int)Math.sqrt(getHeight()));

    if (mouse_entered) {
      g2d.setColor(Color.BLUE);
    } else {
      g2d.setColor(Color.WHITE);
    }
    g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, (int)Math.sqrt(getWidth()), (int)Math.sqrt(getHeight()));
    g2d.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    g2d.setColor(Color.WHITE);
    g2d.drawString(text, getWidth()/3+15, 15);

    update();
    player.draw(g2d);
    player2.draw(g2d);
    ball.draw(g2d);
  }

  public void mouseClicked (MouseEvent e) {
  }

  public void mouseEntered (MouseEvent e) {
    mouse_entered = true;
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    repaint();
  }

  public void mouseExited(MouseEvent e) {
    mouse_entered = false;
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

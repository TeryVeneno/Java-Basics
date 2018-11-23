package watch;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.io.*;
import javax.swing.*;

public class Watch extends JPanel {
  static final long serialVersionUID = 1L;
  Watch_Button button = new Watch_Button();
  private boolean button_pressed;
  LocalTime lt = LocalTime.now();
  int time_char1 = 0;
  int time_char2 = 0;

  public Watch () {
    setPreferredSize(new Dimension(1000,1000));
    setMaximumSize(new Dimension(1000, 1000));
    setIgnoreRepaint(true);
    this.add(button);
    WB_Listener wbl = new WB_Listener();
    button.add_action_listener(wbl);
  }

  public void render () throws InterruptedException {
    Graphics g = this.getGraphics();
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    button.setLocation(475, 375);
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    g2d.drawString("Neopixel Watch Face", getWidth()/2-90, getHeight()/2-270);

    g2d.drawRect(getWidth()/2-250, getHeight()/2-250, 500, 500);
    g2d.setFont(new Font("Times New Roman", Font.PLAIN, 25));
    g2d.drawString("Neopixels", getWidth()/2-50, getHeight()/2-200);
    if (button_pressed) {
      lt = LocalTime.now();
      if (lt.getHour() >= 10) {
        time_char1 = (Integer.toString(lt.getHour()).charAt(0)) - '0';
        time_char2 = (Integer.toString(lt.getHour()).charAt(1)) - '0';
      } else {
        time_char1 = 0;
        time_char2 = lt.getHour();
      }
      if (time_char1 == 0) {
        g2d.setColor(Color.BLACK);
      } else if (time_char1 == 1) {
        g2d.setColor(Color.RED);
      } else if (time_char1 == 2) {
        g2d.setColor(Color.ORANGE);
      } else if (time_char1 == 3) {
        g2d.setColor(Color.YELLOW);
      } else if (time_char1 == 4) {
        g2d.setColor(Color.GREEN);
      } else if (time_char1 == 5) {
        g2d.setColor(Color.BLUE);
      } else if (time_char1 == 6) {
        g2d.setColor(Color.MAGENTA);
      } else if (time_char1 == 7) {
        g2d.setColor(Color.PINK);
      } else if (time_char1 == 8) {
        g2d.setColor(Color.CYAN);
      } else if (time_char1 == 9) {
        g2d.setColor(Color.WHITE);
      }
      g2d.fillOval(275, getHeight()/2-175, 100, 100);
      if (time_char2 == 0) {
        g2d.setColor(Color.BLACK);
      } else if (time_char2 == 1) {
        g2d.setColor(Color.RED);
      } else if (time_char2 == 2) {
        g2d.setColor(Color.ORANGE);
      } else if (time_char2 == 3) {
        g2d.setColor(Color.YELLOW);
      } else if (time_char2 == 4) {
        g2d.setColor(Color.GREEN);
      } else if (time_char2 == 5) {
        g2d.setColor(Color.BLUE);
      } else if (time_char2 == 6) {
        g2d.setColor(Color.MAGENTA);
      } else if (time_char2 == 7) {
        g2d.setColor(Color.PINK);
      } else if (time_char2 == 8) {
        g2d.setColor(Color.CYAN);
      } else if (time_char2 == 9) {
        g2d.setColor(Color.WHITE);
      }
      g2d.fillOval(625, getHeight()/2-175, 100, 100);
      Thread.sleep(1000);
      lt = LocalTime.now();
      if (lt.getMinute() >= 10) {
        time_char1 = (Integer.toString(lt.getMinute()).charAt(0)) - '0';
        time_char2 = (Integer.toString(lt.getMinute()).charAt(1)) - '0';
      } else {
        time_char1 = 0;
        time_char2 = lt.getMinute();
      }
      if (time_char1 == 0) {
        g2d.setColor(Color.BLACK);
      } else if (time_char1 == 1) {
        g2d.setColor(Color.RED);
      } else if (time_char1 == 2) {
        g2d.setColor(Color.ORANGE);
      } else if (time_char1 == 3) {
        g2d.setColor(Color.YELLOW);
      } else if (time_char1 == 4) {
        g2d.setColor(Color.GREEN);
      } else if (time_char1 == 5) {
        g2d.setColor(Color.BLUE);
      } else if (time_char1 == 6) {
        g2d.setColor(Color.MAGENTA);
      } else if (time_char1 == 7) {
        g2d.setColor(Color.PINK);
      } else if (time_char1 == 8) {
        g2d.setColor(Color.CYAN);
      } else if (time_char1 == 9) {
        g2d.setColor(Color.WHITE);
      }
      g2d.fillOval(275, getHeight()/2-175, 100, 100);
      if (time_char2 == 0) {
        g2d.setColor(Color.BLACK);
      } else if (time_char2 == 1) {
        g2d.setColor(Color.RED);
      } else if (time_char2 == 2) {
        g2d.setColor(Color.ORANGE);
      } else if (time_char2 == 3) {
        g2d.setColor(Color.YELLOW);
      } else if (time_char2 == 4) {
        g2d.setColor(Color.GREEN);
      } else if (time_char2 == 5) {
        g2d.setColor(Color.BLUE);
      } else if (time_char2 == 6) {
        g2d.setColor(Color.MAGENTA);
      } else if (time_char2 == 7) {
        g2d.setColor(Color.PINK);
      } else if (time_char2 == 8) {
        g2d.setColor(Color.CYAN);
      } else if (time_char2 == 9) {
        g2d.setColor(Color.WHITE);
      }
      g2d.fillOval(625, getHeight()/2-175, 100, 100);
      Thread.sleep(1000);
      time_char1 = 0;
      time_char2 = 0;
      g2d.setColor(Color.BLACK);
      g2d.fillOval(275, getHeight()/2-175, 100, 100);
      g2d.fillOval(625, getHeight()/2-175, 100, 100);
      button_pressed = false;
    } else {
      time_char1 = 0;
      time_char2 = 0;
      g2d.setColor(Color.BLACK);
      g2d.fillOval(275, getHeight()/2-175, 100, 100);
      g2d.fillOval(625, getHeight()/2-175, 100, 100);
    }

    g2d.setColor(Color.GRAY);
    g2d.fillRect(400, 300, 200, 200);
    g2d.drawString("Button", 475, 330);
    super.paintChildren(g);
  }

  private class WB_Listener implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      Watch_Button source = (Watch_Button)e.getSource();
      if (source == button) {
        button_pressed = true;
      }
    }
  }
}

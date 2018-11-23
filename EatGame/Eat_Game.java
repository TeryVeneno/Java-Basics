package machine_learning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Eat_Game {
  static int fps_delay = 17;
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Eat Game AI");
    Eat_Panel panel = new Eat_Panel();
    Frame_Control control = new Frame_Control();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel);
    frame.addKeyListener(control);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      panel.update(fps_delay);
      frame.repaint();
      Thread.sleep(fps_delay);
    }
  }

  public static class Frame_Control extends KeyAdapter {
    public void keyPressed (KeyEvent e) {
      if (e.getKeyCode() == e.VK_UP) {
          fps_delay += 1;
       }
       if (e.getKeyCode() == e.VK_DOWN) {
          fps_delay -= 1;
       }
    }

    public void keyReleased (KeyEvent e) {
      if (e.getKeyCode() == e.VK_UP) {
          fps_delay += 0;
       }
       if (e.getKeyCode() == e.VK_DOWN) {
          fps_delay -= 0;
       }
    }
  }
}

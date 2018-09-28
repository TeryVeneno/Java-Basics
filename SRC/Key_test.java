import java.awt.*;
import java.awt.event.*;
import gui.*;
import javax.swing.*;
import utilities.*;

public class Key_test extends KeyAdapter {
  public static int rx = 0;
  public static int ry = 0;
  public void keyPressed (KeyEvent e) {
    if (e.getKeyCode() == e.VK_UP) {
        ry = -5;
     }
     if (e.getKeyCode() == e.VK_DOWN) {
        ry = 5;
     }
     if (e.getKeyCode() == e.VK_LEFT) {
        rx = -5;
     }
     if (e.getKeyCode() == e.VK_RIGHT) {
        rx = 5;
     }
  }

  public void keyReleased (KeyEvent e) {
    if (e.getKeyCode() == e.VK_UP) {
        ry = 0;
     }
     if (e.getKeyCode() == e.VK_DOWN) {
        ry = 0;
     }
     if (e.getKeyCode() == e.VK_LEFT) {
        rx = 0;
     }
     if (e.getKeyCode() == e.VK_RIGHT) {
        rx = 0;
     }
  }

  public static void main(String[] args) throws InterruptedException {
    Rectangle[] player_objects = new Rectangle[2];
    Ran ran = new Ran();
    player_objects[0] = new Rectangle(0,0,30,30);
    player_objects[1] = new Rectangle(100, 100, 20, 20);
    Objects object = new Objects(player_objects);
    object.setPreferredSize(new Dimension(1000,1000));
    JFrame frame = new JFrame("Key Test");
    JPanel panel = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.add(object);
    panel.addKeyListener(new Key_test());
    panel.setFocusable(true);
    panel.setDoubleBuffered(true);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      object.rects[0].translate(rx, ry);
      if (object.rects[0].intersects(object.rects[1])) {
        object.rects[1].setLocation(ran.i_ran(frame.getContentPane().getWidth()-20, 0), ran.i_ran(frame.getContentPane().getHeight()-20, 0));
      }
      frame.repaint();
      Thread.sleep(17);
    }
  }
}

import java.awt.*;
import java.awt.event.*;
import gui.*;
import javax.swing.*;
import utilities.*;

public class Key_test extends KeyAdapter {
  public static int rx = 0;
  public static int ry = 0;
  public static boolean change = false;
  public void keyPressed (KeyEvent e) {
    if (e.getKeyCode() == e.VK_UP) {
        ry = 1;
     }
     if (e.getKeyCode() == e.VK_DOWN) {
        ry = -1;
     }
     if (e.getKeyCode() == e.VK_LEFT) {
        rx = -5;
     }
     if (e.getKeyCode() == e.VK_RIGHT) {
        rx = 5;
     }
     if (e.getKeyCode() == e.VK_SPACE) {
       change = true;
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
     if (e.getKeyCode() == e.VK_SPACE) {
       change = false;
     }
  }

  public static void main(String[] args) throws InterruptedException {
    Rectangle[] player_objects = new Rectangle[2];
    Ran ran = new Ran();
    Rectangle intersec = new Rectangle(1,1,1,1);
    Coordinate movements = new Coordinate(500,500);
    Coordinate movements2 = new Coordinate(0,0);
    Vectors p = new Vectors(1,270);
    Vectors p2 = new Vectors(2, 47);
    int last = 0;
    player_objects[0] = new Rectangle(500,500,30,30);
    player_objects[1] = new Rectangle(0, 0, 30, 30);
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
      p.magnitude += ry;
      p.direction += rx;
      if (p.magnitude >= 4) {
        p.magnitude = 3;
      } else if (p.magnitude < 0) {
        p.magnitude = 0;
      }
      if (change) {
        object.rects[0].setLocation(500,500);
      }
      if (object.rects[0].intersects(object.rects[1])) {
         p.direction = -1*p2.direction;
         p2.direction = -1*p.direction;
         p.magnitude = p2.magnitude;
         p2.magnitude = last;
      }
      last = (int)p.magnitude;
      movements.x = (int)object.rects[0].getX();
      movements.y = (int)object.rects[0].getY();
      movements2.x = (int)object.rects[1].getX();
      movements2.y = (int)object.rects[1].getY();
      object.rects[0].setLocation(Vectors.x_move(p,movements), Vectors.y_move(p,movements));
      object.rects[1].setLocation(Vectors.x_move(p2,movements2),Vectors.y_move(p2,movements2));
      movements2.x = (int)object.rects[1].getX();
      movements2.y = (int)object.rects[1].getY();
      movements.x = (int)object.rects[0].getX();
      movements.y = (int)object.rects[0].getY();
      frame.repaint();
      Thread.sleep(17);
      if (object.rects[0].getX() >= frame.getContentPane().getWidth() && object.rects[0].getY() >= frame.getContentPane().getHeight()) {
        object.rects[0].setLocation(0,0);
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[0].getX() <= -40  && object.rects[0].getY() <= -40) {
        object.rects[0].setLocation(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[0].getX() >= frame.getContentPane().getWidth()) {
        object.rects[0].setLocation(0, (int)object.rects[0].getY());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[0].getY() >= frame.getContentPane().getHeight()) {
        object.rects[0].setLocation((int)object.rects[0].getX(), 0);
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[0].getX() <= -40) {
        object.rects[0].setLocation(frame.getContentPane().getWidth(), (int)object.rects[0].getY());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[0].getY() <= -40) {
        object.rects[0].setLocation((int)object.rects[0].getX(), frame.getContentPane().getHeight());
        frame.repaint();
        Thread.sleep(17);
      }
      /*



      */
      if (object.rects[1].getX() >= frame.getContentPane().getWidth() && object.rects[1].getY() >= frame.getContentPane().getHeight()) {
        object.rects[1].setLocation(0,0);
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[1].getX() <= -40  && object.rects[1].getY() <= -40) {
        object.rects[1].setLocation(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[1].getX() >= frame.getContentPane().getWidth()) {
        object.rects[1].setLocation(0, (int)object.rects[0].getY());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[1].getY() >= frame.getContentPane().getHeight()) {
        object.rects[1].setLocation((int)object.rects[0].getX(), 0);
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[1].getX() <= -40) {
        object.rects[1].setLocation(frame.getContentPane().getWidth(), (int)object.rects[0].getY());
        frame.repaint();
        Thread.sleep(17);
      } else if (object.rects[1].getY() <= -40) {
        object.rects[1].setLocation((int)object.rects[0].getX(), frame.getContentPane().getHeight());
        frame.repaint();
        Thread.sleep(17);
      }
    }
  }
}

package gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import utilities.*;

public class Collide {

  public Coordinate move (Vectors v, Coordinate c) {
    int x_change = c.x;
    int y_change = c.y;
    if (v.direction == 0) {
      y_change -= v.magnitude;
    } else if (v.direction == 1) {
      x_change -= v.magnitude;
      y_change -= v.magnitude;
    } else if (v.direction == 2) {
      x_change -= v.magnitude;
    } else if (v.direction == 3) {
      x_change -= v.magnitude;
      y_change += v.magnitude;
    } else if (v.direction == 4) {
      y_change += v.magnitude;
    } else if (v.direction == 5) {
      x_change += v.magnitude;
      y_change += v.magnitude;
    } else if (v.direction == 6) {
      x_change += v.magnitude;
    } else if (v.direction == 7) {
      x_change += v.magnitude;
      y_change -= v.magnitude;
    }
    Coordinate r = new Coordinate(x_change, y_change);
    return r;
  }

  public int redirect (int direction) {
    int dir = direction;
    if (direction == 0) {
      dir = 4;
    } else if (direction == 1) {
      dir = 3;
    } else if (direction == 3) {
      dir = 1;
    } else if (direction == 4) {
      dir = 0;
    } else if (direction == 5) {
      dir = 7;
    } else if (direction == 7) {
      dir = 5;
    }
    return dir;
  }

  public static void main(String[] args) throws InterruptedException {
    Vectors mag_r = new Vectors(1, 5);
    int count = 15;
    Coordinate r_c = new Coordinate(0,0);
    Rectangle rects = new Rectangle(0,0,40,40);
    Rectangle lines = new Rectangle(0,400,400,1);
    Collide collide = new Collide();
    JFrame frame = new JFrame("Collide");
    Objects objects = new Objects(rects, lines);
    objects.setPreferredSize(new Dimension(500,500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(objects);
    frame.pack();
    frame.setVisible(true);
    while(true) {
      objects.line.setLocation(0, frame.getContentPane().getHeight()/2);
      objects.line.setSize(frame.getContentPane().getWidth(), 1);
      if (objects.rect.intersects(lines)) {
        mag_r.direction = collide.redirect(mag_r.direction);
      }
      r_c = collide.move(mag_r, r_c);
      objects.rect.setLocation(r_c.x, r_c.y);
      frame.repaint();
      Thread.sleep(17);
      if (objects.rect.getX() >= frame.getContentPane().getWidth() && objects.rect.getY() >= frame.getContentPane().getHeight()) {
        r_c.x = 0;
        r_c.y = 0;
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      } else if (objects.rect.getX() <= -40  && objects.rect.getY() <= -40) {
        r_c.x = frame.getContentPane().getWidth();
        r_c.y = frame.getContentPane().getHeight();
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      } else if (objects.rect.getX() >= frame.getContentPane().getWidth()) {
        r_c.x = 0;
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      } else if (objects.rect.getY() >= frame.getContentPane().getHeight()) {
        r_c.y = 0;
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      } else if (objects.rect.getX() <= -40) {
        r_c.x = frame.getContentPane().getWidth();
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      } else if (objects.rect.getY() <= -40) {
        r_c.y = frame.getContentPane().getHeight();
        objects.rect.setLocation(r_c.x, r_c.y);
        frame.repaint();
        Thread.sleep(17);
      }
    }
  }
}

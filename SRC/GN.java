package machine_learning;

import java.awt.*;
import java.awt.event.*;
import gui.*;
import javax.swing.*;
import utilities.*;
import java.util.*;

public class GN extends KeyAdapter {
  public static int rx = 5;
  public static int ry = 5;
  public static int time = 2;
  public static long end = System.currentTimeMillis() + time*1000;

  public static void movements (double[] d) {
    if (d[0] >= 0.3) {
      ry = -5;
    }
    if (d[1] >= 0.3) {
      ry = 5;
    }
    if (d[2] >= 0.3) {
      rx = -5;
    }
    if (d[3] >= 0.3) {
      rx = 5;
    }
  }

  public void keyPressed (KeyEvent e) {
    if (e.getKeyCode() == e.VK_UP) {
        time += 1;
        end = System.currentTimeMillis() + time*1000;
     }
     if (e.getKeyCode() == e.VK_DOWN) {
        time -= 1;
        end = System.currentTimeMillis() + time*1000;
     }
  }

  public static void main(String[] args) throws InterruptedException {
    Rectangle[] player_objects = new Rectangle[6];
    Ran ran = new Ran();
    double[] input = new double[8];
    int max_val = 0;
    long start = 0;
    int place = 0;
    long o_start = 0;
    int generations = 0;
    int distance = 0;
    int[] fitness = new int[100];
    int food_count = 0;
    double[]  d_hold = new double[1];
    int[] listed = new int[50];
    double[] choices = new double[4];
    Brain[] creatures = new Brain[100];
    Brain[] holder = new Brain[50];
    boolean not_listed = false;
    int death = 0;
    for (int c = 0 ; c < 100; c++) {
      creatures[c] = new Brain(2, 5, 4, 1, 0.03, input, d_hold);
    }
    player_objects[0] = new Rectangle(400,100,30,30);
    player_objects[1] = new Rectangle(600, 400, 20, 20);
    player_objects[2] = new Rectangle(0, -1, 1000, 1);
    player_objects[3] = new Rectangle(-1, 0, 1, 1000);
    player_objects[4] = new Rectangle(999, 0, 1, 1000);
    player_objects[5] = new Rectangle(0, 728, 1000, 1);
    Objectss object = new Objectss(player_objects, Integer.toString(generations), "Best");
    object.setPreferredSize(new Dimension(1000,1000));
    JFrame frame = new JFrame("Genetic Algorithm");
    JPanel panel = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.add(object);
    panel.addKeyListener(new GN());
    panel.setFocusable(true);
    panel.setDoubleBuffered(true);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      for (int s = 0; s < 100; s++) {
        start = System.currentTimeMillis();
        o_start = System.currentTimeMillis();
        while (death == 0) {
          object.string2 = "";
          input[0] = object.rects[0].getX();
          input[1] = object.rects[0].getY();
          input[2] = object.rects[0].getX() - object.rects[1].getX();
          input[3] = object.rects[0].getY() - object.rects[1].getY();
          input[4] = object.rects[0].getY() - object.rects[2].getY();
          input[5] = object.rects[0].getX() - object.rects[3].getX();
          input[6] = object.rects[0].getX() - object.rects[4].getX();
          input[7] = object.rects[0].getY() - object.rects[5].getY();
          creatures[s].change_i(input);
          choices = creatures[s].respond();
          movements(choices);
          object.rects[0].translate(rx, ry);
          distance += rx;
          distance += ry;
          if (object.rects[0].intersects(object.rects[1])) {
            object.rects[1].setLocation(ran.i_ran(frame.getContentPane().getWidth()-20, 0), ran.i_ran(frame.getContentPane().getHeight()-20, 0));
            food_count++;
            start = System.currentTimeMillis();
          }
          if ((System.currentTimeMillis() - start) >= 8000) {
            death = 1;
          }
          if (object.rects[0].intersects(object.rects[2])) {
            death = 1;
          }
          if (object.rects[0].intersects(object.rects[3])) {
            death = 1;
          }
          if (object.rects[0].intersects(object.rects[4])) {
            death = 1;
          }
          if (object.rects[0].intersects(object.rects[5])) {
            death = 1;
          }
          frame.repaint();
          Thread.sleep(17);
        }
        death = 0;
        fitness[s] = food_count*10000 + ((int)(System.currentTimeMillis() - o_start) * 7) + distance*10;
        food_count = 0;
        distance = 0;
        object.rects[0].setLocation(400, 100);
        object.rects[1].setLocation(600, 400);
        rx = 5;
        ry = 5;
      }

      for (int s = 0; s < 100; s++) {
        if (fitness[s] > max_val) {
          max_val = fitness[s];
          place = s;
        }
      }
      max_val = 0;
      start = System.currentTimeMillis();
      for (int s = 0; s < 20; s++) {
        object.string2 = "Notice!!!!!!!!!!!!!!!!!!!!";
        frame.repaint();
        Thread.sleep(100);
        object.string2 = "";
        frame.repaint();
        Thread.sleep(50);
      }
      while (death == 0) {
        object.string2 = "Best";
        input[0] = object.rects[0].getX();
        input[1] = object.rects[0].getY();
        input[2] = object.rects[0].getX() - object.rects[1].getX();
        input[3] = object.rects[0].getY() - object.rects[1].getY();
        input[4] = object.rects[0].getY() - object.rects[2].getY();
        input[5] = object.rects[0].getX() - object.rects[3].getX();
        input[6] = object.rects[0].getX() - object.rects[4].getX();
        input[7] = object.rects[0].getY() - object.rects[5].getY();
        creatures[place].change_i(input);
        choices = creatures[place].respond();
        movements(choices);
        object.rects[0].translate(rx, ry);
        if (object.rects[0].intersects(object.rects[1])) {
          object.rects[1].setLocation(ran.i_ran(frame.getContentPane().getWidth()-20, 0), ran.i_ran(frame.getContentPane().getHeight()-20, 0));
          food_count++;
          start = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - start) >= 8000) {
          death = 1;
        }
        if (object.rects[0].intersects(object.rects[2])) {
          death = 1;
        }
        if (object.rects[0].intersects(object.rects[3])) {
          death = 1;
        }
        if (object.rects[0].intersects(object.rects[4])) {
          death = 1;
        }
        if (object.rects[0].intersects(object.rects[5])) {
          death = 1;
        }
        frame.repaint();
        Thread.sleep(17);
      }
      rx = 5;
      ry = 5;
      for (int f = 0; f < 50; f++) {
        for (int f2 = 0; f2 < 100; f2++) {
          for (int s = 0; s < 50; s++) {
            if (listed[s] == f2) {
              not_listed = false;
              break;
            } else {
              not_listed = true;
            }
          }
          if (fitness[f2] > max_val && not_listed) {
            max_val = fitness[f2];
            place = f2;
          }
        }
        holder[f] = creatures[place];
        listed[f] = place;
        max_val = 0;
      }
      for (int f = 0; f < 50; f++) {
        listed[f] = -1;
      }
      for (int s = 0; s < 25; s++) {
        creatures[s] = holder[s].deep_copy();
        creatures[s].mutate();
        creatures[s+25] = holder[s].deep_copy();
        creatures[s+25].mutate();
        creatures[s+50] = holder[s].deep_copy();
        creatures[s+50].mutate();
        creatures[s+75] = holder[s].deep_copy();
        creatures[s+75].mutate();
      }
      generations++;
      object.string = Integer.toString(generations);
    }
  }
}

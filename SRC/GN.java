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
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);
    boolean correct = false;
    int pop = 0;
    System.out.println("Enter the layers: ");
    int layers = input1.nextInt();
    System.out.println("Enter the neurons: ");
    int neurons = input2.nextInt();
    while (!correct) {
      System.out.println("Enter the population: ");
      pop = input3.nextInt();
      if ((pop / 4)*4 != pop) {
      } else {
        correct = true;
      }
    }
    Rectangle[] player_objects = new Rectangle[(pop*2)+4];
    Ran ran = new Ran();
    double[] input = new double[6];
    int[] rxs = new int[pop];
    int[] rys = new int[pop];
    int max_val = 0;
    long[] start = new long[pop];
    long sim = 0;
    int place = 0;
    long[] o_start = new long[pop];
    int generations = 0;
    int[] distance = new int[pop];
    int[] fitness = new int[pop];
    int[] food_count = new int[pop];
    double[]  d_hold = new double[1];
    int[] listed = new int[pop/2];
    double[] choices = new double[4];
    Brain[] creatures = new Brain[pop];
    Brain[] holder = new Brain[pop/2];
    boolean not_listed = false;
    int[] death = new int[pop];
    boolean all_dead = false;
    for (int c = 0 ; c < pop; c++) {
      creatures[c] = new Brain(3, 6, 4, 1, 0.03, input, d_hold);
    }
    for (int s = 0; s < pop; s++) {
      player_objects[s] = new Rectangle(400,100,30,30);
    }
    for (int s = pop; s < pop*2; s++) {
      player_objects[s] = new Rectangle(600, 400, 20, 20);
    }
    for (int r = 0; r < pop; r++) {
      rxs[r] = rx;
      rys[r] = ry;
    }
    player_objects[pop*2] = new Rectangle(0, -1, 1000, 1);
    player_objects[pop*2+1] = new Rectangle(-1, 0, 1, 1000);
    player_objects[pop*2+2] = new Rectangle(999, 0, 1, 1000);
    player_objects[pop*2+3] = new Rectangle(0, 728, 1000, 1);
    Objectss object = new Objectss(player_objects, "Generation: " + Integer.toString(generations), "");
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
      for (int s = 0; s < pop; s++) {
        start[s] = System.currentTimeMillis();
        o_start[s] = System.currentTimeMillis();
      }
      while (!all_dead) {
        sim = System.currentTimeMillis();
        for (int c = 0; c < pop; c++) {
          if (death[c] != 1) {
            rx = rxs[c];
            ry = rys[c];
            input[0] = object.rects[c].getX() - object.rects[c+pop].getX();
            input[1] = object.rects[c].getY() - object.rects[c+pop].getY();
            input[2] = object.rects[c].getY() - object.rects[c+pop].getY();
            input[3] = object.rects[c].getX() - object.rects[c+pop].getX();
            input[4] = object.rects[c].getX() - object.rects[c+pop].getX();
            input[5] = object.rects[c].getY() - object.rects[c+pop].getY();
            creatures[c].change_i(input);
            choices = creatures[c].respond();
            movements(choices);
            rxs[c] = rx;
            rys[c] = ry;
            object.rects[c].translate(rx, ry);
            if (object.rects[c].intersects(object.rects[c+pop])) {
              object.rects[c+pop].setLocation(ran.i_ran(979, 0), ran.i_ran(708, 0));
              food_count[c]++;
              start[c] = System.currentTimeMillis();
            }
            if ((System.currentTimeMillis() - start[c]) >= 12000) {
              death[c] = 1;
            }
            if (System.currentTimeMillis() - sim >= 60000) {
              all_dead = true;
              c = pop;
            }
            if (object.rects[c].intersects(object.rects[pop*2])) {
              death[c] = 1;
            }
            if (object.rects[c].intersects(object.rects[pop*2+1])) {
              death[c] = 1;
            }
            if (object.rects[c].intersects(object.rects[pop*2+2])) {
              death[c] = 1;
            }
            if (object.rects[c].intersects(object.rects[pop*2+3])) {
              death[c] = 1;
            }
            frame.repaint();
            Thread.sleep(17);
          } else {
            object.rects[c].setLocation(3000,3000);
            object.rects[c+pop].setLocation(3000,3000);
          }
        }
        for (int d = 0; d < pop; d++) {
          if (death[d] != 1) {
            break;
          } else if (d == pop-1) {
            all_dead = true;
          }
        }
      }
      for (int d = 0; d < pop; d++) {
        death[d] = 0;
      }
      all_dead = false;
      for (int f = 0; f < pop; f++) {
        fitness[f] = food_count[f]*18000 + (int)(System.currentTimeMillis() - o_start[f]);
        food_count[f] = 0;
      }
      for (int r = 0; r < pop; r++) {
        rxs[r] = 5;
        rys[r] = 5;
      }
      rx = 5;
      ry = 5;
      for (int l = 0; l < pop/4; l++) {
        for (int s = 0; s < pop; s++) {
          not_listed = true;
          if (listed[l] == s) {
            not_listed = false;
          }
          if (fitness[s] > max_val && not_listed) {
            max_val = fitness[s];
            place = s;
          }
        }
        listed[l] = place;
        holder[l] = creatures[place];
      }
      for (int s = 0; s < pop; s++) {
        object.rects[s].setLocation(3000, 3000);
        object.rects[s+pop].setLocation(3000, 3000);
      }
      object.rects[listed[0]].setLocation(400,100);
      object.rects[listed[0]+pop].setLocation(600,400);
      max_val = 0;
      object.string2 = "";
      for (int s = 0; s < 20; s++) {
        object.string2 = "Notice!!!!!!!!!!!!!!!!!!!!";
        frame.repaint();
        Thread.sleep(100);
        object.string2 = "";
        frame.repaint();
        Thread.sleep(50);
      }
      start[listed[0]] = System.currentTimeMillis();
      while (death[place] != 1) {
        object.string2 = "Best";
        input[0] = object.rects[listed[0]].getX() - object.rects[listed[0]+pop].getX();
        input[1] = object.rects[listed[0]].getY() - object.rects[listed[0]+pop].getY();
        input[2] = object.rects[listed[0]].getY() - object.rects[pop*2].getY();
        input[3] = object.rects[listed[0]].getX() - object.rects[pop*2+1].getX();
        input[4] = object.rects[listed[0]].getX() - object.rects[pop*2+2].getX();
        input[5] = object.rects[listed[0]].getY() - object.rects[pop*2+3].getY();
        creatures[listed[0]].change_i(input);
        choices = creatures[listed[0]].respond();
        movements(choices);
        object.rects[listed[0]].translate(rx, ry);
        if (object.rects[listed[0]].intersects(object.rects[listed[0]+pop])) {
          object.rects[listed[0]+pop].setLocation(ran.i_ran(979, 0), ran.i_ran(708, 0));
          start[listed[0]] = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - start[listed[0]]) >= 12000) {
          death[listed[0]] = 1;
        }
        if (object.rects[listed[0]].intersects(object.rects[pop*2])) {
          death[listed[0]] = 1;
        }
        if (object.rects[listed[0]].intersects(object.rects[pop*2+1])) {
          death[listed[0]] = 1;
        }
        if (object.rects[listed[0]].intersects(object.rects[pop*2+2])) {
          death[listed[0]] = 1;
        }
        if (object.rects[listed[0]].intersects(object.rects[pop*2+3])) {
          death[listed[0]] = 1;
        }
        frame.repaint();
        Thread.sleep(17);
      }
      rx = 5;
      ry = 5;
      death[listed[0]] = 0;
      for (int s = 0; s < pop; s++) {
        object.rects[s].setLocation(400, 100);
        object.rects[s+pop].setLocation(600, 400);
      }
      for (int f = 0; f < pop/2; f++) {
        listed[f] = -1;
      }
      for (int s = 0; s < pop/4; s++) {
        creatures[s] = holder[s].deep_copy();
        creatures[s].mutate();
        creatures[s+pop/4] = holder[s].deep_copy();
        creatures[s+pop/4].mutate();
        creatures[s+pop/2] = holder[s].deep_copy();
        creatures[s+pop/2].mutate();
        creatures[s+((pop/4)*3)] = holder[s].deep_copy();
        creatures[s+((pop/4)*3)].mutate();
      }
      generations++;
      object.string = "Generation: " + Integer.toString(generations);
      object.string2 = "";
    }
  }
}

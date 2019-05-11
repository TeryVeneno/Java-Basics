package machine_learning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import java.awt.RenderingHints;
import java.util.*;
import utilities.*;
import machine_learning.neurons.*;
import machine_learning.networks.*;

public class Eat_Panel extends JPanel {
  private static final long serialVersionUID = 1l;

  Rectangle agent_hit = new Rectangle(300, 300, 50, 50);
  Rectangle food = new Rectangle(250, 250, 100, 100);
  Ran ran = new Ran();
  Network brain;
  int choice = 0;
  int fps_delay = 17;
  double[] desire = new double[4];
  int limit = 0;

  public Eat_Panel () {
    setPreferredSize(new Dimension(500,500));
    setMaximumSize(new Dimension(500, 500));
    setIgnoreRepaint(true);
    for (int i = 0; i < 4; i++) {
      desire[i] = 0;
    }
    brain = new Network(5, 10, 4, 4, 0.11, 0.8, Neuron.MEMORY_LENGTH_3, 100);
  }

  @Override
  public void paint (Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g2d.setColor(Color.RED);
    g2d.fillRect((int)food.getX(), (int)food.getY(), 100, 100);
    g2d.setColor(Color.GREEN);
    g2d.fillRoundRect((int)agent_hit.getX(), (int)agent_hit.getY(), 50, 50, (int)Math.sqrt(50), (int)Math.sqrt(50));
    g2d.setColor(Color.BLACK);
    g2d.drawString(Integer.toString(1000/fps_delay), getWidth()/2-10, 10);
    g2d.dispose();
  }

  public void update (int fps_delay) {
    ArrayList<Double> inputs = new ArrayList<Double>(8);
    inputs.add(food.getX() / 100);
    inputs.add(food.getY() / 100);
    inputs.add((agent_hit.getX() - food.getX()) / 100);
    inputs.add((agent_hit.getY() - food.getY()) / 100);
    inputs.add((agent_hit.getX() - getWidth()-25) / 100);
    inputs.add((agent_hit.getX() - 0) / 100);
    inputs.add((agent_hit.getY() - getHeight()-25) / 100);
    inputs.add((agent_hit.getY() - 0) / 100);
    ArrayList<Double> responses = new ArrayList<Double>(brain.respond(inputs));
    if (agent_hit.intersects(food)) {
      food.setLocation(ran.i_ran(25, getWidth()-25), ran.i_ran(25, getHeight())-25);
      for (int i = 0; i < 4; i++) {
        desire[i] = 0;
      }
      desire[choice] = 1;
      if (limit >= 0) {
        brain.think(10, desire, 10);
      }
    }
    if (agent_hit.getX() <= 0) {
      for (int i = 0; i < 4; i++) {
        desire[i] = 1;
      }
      desire[choice] = 0;
      if (limit >= 15000) {
        brain.think(10, desire, -1);
      }
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getX()+50 >= getWidth()) {
      for (int i = 0; i < 4; i++) {
        desire[i] = 1;
      }
      desire[choice] = 0;
      if (limit >= 15000) {
        brain.think(10, desire, -1);
      }
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getY() <= 0) {
      for (int i = 0; i < 4; i++) {
        desire[i] = 1;
      }
      desire[choice] = 0;
      if (limit >= 5000) {
        brain.think(10, desire, -1);
      }
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getY()+50 >= getHeight()) {
      for (int i = 0; i < 4; i++) {
        desire[i] = 1;
      }
      desire[choice] = 0;
      if (limit >= 5000) {
        brain.think(10, desire, -1);
      }
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    }/* else {
      for (int i = 0; i < 4; i++) {
        desire[i] = 1;
      }
      brain.think(1, desire, 0);
    }*/
    if (responses.get(0) >= 0.4) {
      agent_hit.translate(0, -10);
      choice = 0;
    } else if (responses.get(1) >= 0.4) {
      agent_hit.translate(-10, 0);
      choice = 1;
    } else if (responses.get(2) >= 0.4) {
      agent_hit.translate(0, 10);
      choice = 2;
    } else if (responses.get(3) >= 0.4) {
      agent_hit.translate(10, 0);
      choice = 3;
    } else {
      choice = ran.i_ran(0, 3);
      if (choice == 0) {
        agent_hit.translate(0, -10);
      } else if (choice == 1) {
        agent_hit.translate(-10, 0);
      } else if (choice == 2) {
        agent_hit.translate(0, 10);
      } else if (choice == 3) {
        agent_hit.translate(10, 0);
      }
    }
    System.out.println(responses.toString());
    this.fps_delay = fps_delay;
    limit++;
  }
}

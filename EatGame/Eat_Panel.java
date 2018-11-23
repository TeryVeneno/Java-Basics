package machine_learning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import java.awt.RenderingHints;
import java.util.*;
import utilities.*;

public class Eat_Panel extends JPanel implements Action_Definer {
  private static final long serialVersionUID = 1l;

  Rectangle agent_hit = new Rectangle(280, 280, 50, 50);
  Rectangle food = new Rectangle(300, 300, 25, 25);
  Ran ran = new Ran();
  Reinforcement agent;
  int choice = 0;
  int fps_delay = 17;
  int limit = 0;

  public Eat_Panel () {
    setPreferredSize(new Dimension(500,500));
    setMaximumSize(new Dimension(500, 500));
    setIgnoreRepaint(true);
    agent = new Reinforcement(1000, 4, 0.08, 0.8, 0, 100000, Sensor.SINGLE_SENSOR, 10000, this);
  }

  public int[] pos_actions (int length, int width) {
    int[] poses = new int[4];
    for (int s  = 0; s < 4; s++) {
      poses[s] = 1;
    }
    return poses;
  }

  public double transpose (double input) {
    return (1.0/(0.5*input))*(1.0/(0.5*input));
  }

  public double[] next_state(int action) {
    Rectangle copy_hit = new Rectangle(agent_hit);
    Rectangle food_copy = new Rectangle(food);
    if (action == 0) {
      copy_hit.translate(0, -10);
    } else if (action == 1) {
      copy_hit.translate(-10, 0);
    } else if (action == 2) {
      copy_hit.translate(0, 10);
    } else if (action == 3) {
      copy_hit.translate(10, 0);
    }
    double[] inputs = new double[10];
    inputs[0] = copy_hit.getX();
    inputs[1] = copy_hit.getY();
    inputs[2] = food_copy.getX();
    inputs[3] = food_copy.getY();
    inputs[4] = copy_hit.getX() - food.getX()*0.2;
    inputs[5] = copy_hit.getY() - food.getY()*0.2;
    inputs[6] = copy_hit.getX() - getWidth()-25;
    inputs[7] = copy_hit.getX() - 0;
    inputs[8] = copy_hit.getY() - getHeight()-25;
    inputs[9] = copy_hit.getY() - 0;
    return inputs;
  }

  @Override
  public void paint (Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g2d.setColor(Color.RED);
    g2d.fillRect((int)food.getX(), (int)food.getY(), 25, 25);
    g2d.setColor(Color.GREEN);
    g2d.fillRoundRect((int)agent_hit.getX(), (int)agent_hit.getY(), 50, 50, (int)Math.sqrt(50), (int)Math.sqrt(50));
    g2d.setColor(Color.BLACK);
    g2d.drawString(Integer.toString(1000/fps_delay), getWidth()/2-10, 10);
    g2d.dispose();
  }

  public void update (int fps_delay) {
    if (agent_hit.intersects(food)) {
      food.setLocation(ran.i_ran(25, getWidth()-25), ran.i_ran(25, getHeight())-25);
      agent.train(50, choice);
    }
    if (agent_hit.getX() <= 0) {
      agent.train(-1, choice);
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getX()+50 >= getWidth()) {
      agent.train(-1, choice);
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getY() <= 0) {
      agent.train(-1, choice);
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else if (agent_hit.getY()+50 >= getHeight()) {
      agent.train(-1, choice);
      do {
        agent_hit.setLocation(ran.i_ran(0, getWidth()-25), ran.i_ran(0, getHeight()-25));
      } while (agent_hit.intersects(food));
    } else {
      //agent.train(0, choice);
    }
    double[] inputs = new double[10];
    inputs[0] = agent_hit.getX();
    inputs[1] = agent_hit.getY();
    inputs[2] = food.getX();
    inputs[3] = food.getY();
    inputs[4] = agent_hit.getX() - food.getX()*0.2;
    inputs[5] = agent_hit.getY() - food.getY()*0.2;
    inputs[6] = agent_hit.getX() - getWidth()-25;
    inputs[7] = agent_hit.getX() - 0;
    inputs[8] = agent_hit.getY() - getHeight()-25;
    inputs[9] = agent_hit.getY() - 0;
    agent.receive(inputs);
    agent.set_c();
    choice = agent.choose();
    //System.out.println(choice);
    //System.out.println("State: " + agent.ret_c());
    if (choice == 0) {
      agent_hit.translate(0, -10);
    } else if (choice == 1) {
      agent_hit.translate(-10, 0);
    } else if (choice == 2) {
      agent_hit.translate(0, 10);
    } else if (choice == 3) {
      agent_hit.translate(10, 0);
    }
    this.fps_delay = fps_delay;
    /*double[][] q = agent.ret_q();
    for (int s = 0; s < q.length; s++) {
      System.out.println(s + "" + Arrays.toString(q[s]));
    }*/
    limit++;
  }
}

package machine_learning;

import utilities.*;
import java.util.*;

public class Reinforcement {
  private double[][] Q;
  private int c_state;
  private int states;
  private int actions;
  private double learn_rate;
  private double eager;
  private int ite;
  private int limit;
  private int type;
  private Random rand = new Random(System.currentTimeMillis());
  private Sensor sensor;
  private Action_Definer definer;

  public Reinforcement (int states, int actions, double learn_r, double eagerness, int start, int ran, int type, int factor, Action_Definer definer) {
    this.states = states;
    this.actions = actions;
    this.type = type;
    this.definer = definer;
    Q = new double[states][actions];
    for (int s = 0; s < Q.length; s++) {
      for (int a = 0; a < Q[s].length; a++) {
        Q[s][a] = 0.1;
      }
    }
    sensor = new Sensor(type, factor, definer);
    ite = 0;
    limit = ran;
    learn_rate = learn_r;
    eager = eagerness;
    c_state = start;
  }

  public int choose () {
    int choice = next_a();
    return choice;
  }

  public void train (double reward, int action) {
    sensor.receive(definer.next_state(action));
    int next_s = sensor.send_data();
    if (next_s >= states) {
      next_s = states-1;
    }
    Q[c_state][action] = Q[c_state][action] + (learn_rate * (reward + eager * max(next_s) - Q[c_state][action]));
  }

  public double max (int state) {
    int[] pos_actions = definer.pos_actions(conv(state)[0], conv(state)[1]);
    double max_val = Double.MIN_VALUE;
    for (int s = 0; s < Q[0].length; s++) {
      if (max_val < Q[state][s] && pos_actions[s] == 1) {
        max_val = Q[state][s];
      }
    }
    return max_val;
  }

  public int next_a () {
    int[] pos_actions = definer.pos_actions(conv(c_state)[0], conv(c_state)[1]);
    double max_val = -20;
    double least_val = 0.1;
    int next = 0;
    int ran = rand.nextInt(actions);
    Integer not = null;
    if (ite > limit) {
      for (int s = 0; s < Q[0].length; s++) {
        boolean pos = pos_actions[s] == 1;
        boolean test = max_val < Q[c_state][s];
        if (pos) {
          if (test) {
            max_val = Q[c_state][s];
            next = s;
          }
        } else if ((least_val > Q[c_state][s]) && (pos_actions[s] == 1)) {
          least_val = Q[c_state][s];
          not = s;
        }
        if (max_val == 0.1) {
          if (not != null) {
            while (next == not) {
              ran = rand.nextInt(Q[0].length);
              if (pos_actions[ran] != 0) {
                next = ran;
              }
            }
          }
        }
      }
    } else if (ite <= limit) {
      do {
        ran = rand.nextInt(actions);
        if (pos_actions[ran] == 1) {
          next = ran;
        }
      } while (pos_actions[ran] != 1);
      ite++;
    }
    return next;
  }

  public void set_c () {
    int next_s = sensor.send_data();
    if (next_s >= states) {
      c_state = states-1;
    } else {
      c_state = next_s;
    }
  }

  public double get_q_val (int state, int action) {
    return Q[state][action];
  }

  public double[][] ret_q () {
    return Q;
  }

  public int ret_c () {
    return c_state;
  }

  public int con (int length, int width) {
    int[][] holder = new int[(int)Math.sqrt(states)][(int)Math.sqrt(states)];
    int count = 0;
    for (int i = 0; i < Math.sqrt(states); i++) {
      for (int o = 0; o < Math.sqrt(states); o++) {
        holder[i][o] = count;
        count++;
      }
    }
    return holder[length][width];
  }

  public int[] conv (int state) {
    int[] ret = new int[2];
    int[] length = new int[states];
    int[] width = new int[states];
    int x, y = 0;
    for (int i = 0; i < states; i++) {
      if (i % Math.sqrt(states) > 0) {
        y = (int)(i/Math.sqrt(states));
        x = (int)(i%Math.sqrt(states));
      } else {
        y = (int)(i/Math.sqrt(states));
        x = 0;
      }
      length[i] = y;
      width[i] = x;
    }
    ret[0] = length[state];
    ret[1] = width[state];
    return ret;
  }

  public void receive (double[][] input) {
    sensor.receive(input);
  }
  public void receive (double[] input) {
    sensor.receive(input);
  }

  public void change_limit (int amount) {
    limit += amount;
  }

  public void set_limit (int amount) {
    limit = amount;
  }
}

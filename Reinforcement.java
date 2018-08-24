package machine_learning;

import utilities.*;
import java.util.*;

public class Reinforcement {
  private double[][] Q;
  private int c_state;
  private double learn_rate;
  private double eager;
  int ite;
  int limit;
  private Random rand = new Random(System.currentTimeMillis());

  public Reinforcement (int states, int actions, double learn_r, double eagerness, int start, int ran) {
    Q = new double[states][actions];
    for (int s = 0; s < Q.length; s++) {
      for (int a = 0; a < Q[s].length; a++) {
        Q[s][a] = 0.1;
      }
    }
    ite = 0;
    limit = ran;
    learn_rate = learn_r;
    eager = eagerness;
    c_state = start;
  }

  public int choose (int[] pos_actions) {
    int choice = next_s(pos_actions);
    return choice;
  }

  public void train (int reward, int[] pos_actions, int action) {
    Q[c_state][action] = Q[c_state][action] + (learn_rate * (reward + eager * max(pos_actions) - Q[c_state][action]));
  }

  public double max (int[] pos_actions) {
    double max_val = Double.MIN_VALUE;
    for (int s = 0; s < Q[0].length; s++) {
      if (max_val < Q[c_state][s] && pos_actions[s] == 1) {
        max_val = Q[c_state][s];
      }
    }
    return max_val;
  }

  public int next_s (int[] pos_actions) {
    double max_val = -20;
    double least_val = Double.MIN_VALUE;
    int next = 0;
    int ran = 0;
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
      while (pos_actions[ran] != 1) {
        ran = rand.nextInt(Q[0].length);
        if (pos_actions[ran] == 1) {
          next = ran;
        }
      }
      ite++;
    }
    return next;
  }

  public void set_c (int state) {
    c_state = state;
  }

  public double[][] ret_q () {
    return Q;
  }
}

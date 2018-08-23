package machine_learning;

import java.util.*;

public class Reinforcement {
  private double[][] Q;
  private int c_state;
  private double learn_rate;
  private double eager;

  public Reinforcement (int states, int actions, double learn_r, double eagerness, int start) {
    Q = new double[states][actions];
    for (int s = 0; s < Q.length; s++) {
      for (int a = 0; a < Q[s].length; a++) {
        Q[s][a] = 0.1;
      }
    }
    learn_rate = learn_r;
    eager = eagerness;
    c_state = start;
  }

  public int choose (int[] pos_actions) {
    int choice = next_s(pos_actions);
    return choice;
  }

  public void train (int reward, int[] pos_actions) {
    int action = choose(pos_actions);
    Q[c_state][action] = Q[c_state][action] + learn_rate * (reward + eager * max(pos_actions) - Q[c_state][action]);
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
    double max_val = Double.MIN_VALUE;
    int next = 0;
    for (int s = 0; s < Q[0].length; s++) {
      if (max_val < Q[c_state][s] && pos_actions[s] == 1) {
        max_val = Q[c_state][s];
        next = s;
      }
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

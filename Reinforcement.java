package machine_learning;

import java.util.*;

public class Reinforcement {
  private double[][] Qs;
  private int c_state;
  private double learn_rate;
  private double eager;

  public Reinforcement (int states, int actions, double learn_r, double eagerness, int start) {
    Qs = new double[states][actions];
    learn_rate = learn_r;
    eager = eagerness;
    c_state = start;
  }

  public int choose (int[] pos_actions) {
    c_state = next_s(pos_actions);
    return c_state;
  }

  public void train (int next, int reward, int[] pos_actions) {
    Qs[c_state][next] = Qs[c_state][next] + learn_rate * (reward + eager * max(pos_actions) - Qs[c_state][next]);
  }

  public double max (int[] pos_actions) {
    double max_val = Double.MIN_VALUE;
    if (Q == null) {
      for (int s = 0; s < pos_actions.length; ) {
        if (max_val < Qs[c_state][pos_actions[s]]) {
          max_val = Qs[c_state][pos_actions[s]];
        }
      }
    }
    return max_val;
  }

  public int next_s (int[] pos_actions) {
    double max_val = Double.MIN_VALUE;
    int next = 0;
    if (Q == null) {
      for (int s = 0; s < pos_actions.length; ) {
        if (max_val < Qs[c_state][pos_actions[s]]) {
          max_val = Qs[c_state][pos_actions[s]];
          next = pos_actions[s];
        }
      }
    } else if (Qs == null) {
      for (int s = 0; s < pos_actions.length; ) {
        if (max_val < Q[pos_actions[s]]) {
          max_val = Q[pos_actions[s]];
          next = pos_actions[s];
        }
      }
    }
    return next;
  }

  public void set_c (int state) {
    c_state = state;
  }
}

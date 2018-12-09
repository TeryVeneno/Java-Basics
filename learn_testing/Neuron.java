package machine_learning.neurons;
import java.util.*;
import utilities.*;

public class Neuron {
  private double[] stm;
  private ArrayList<Double> weights;
  private ArrayList<Integer> connections;
  private ArrayList<Double> inputs;
  private int total = 0;
  private double cur_output = 0;
  private double learn_rate;
  private double eager;
  private int last_mem_pos = 0;
  public final static int MEMORY_LENGTH_1 = 10;
  public final static int MEMORY_LENGTH_2 = 100;
  public final static int MEMORY_LENGTH_3 = 1000;
  Ran ran = new Ran();

  public Neuron (int amount, ArrayList<Integer> c, int memory_size, double learn_r, double eagerness) {
    learn_rate = learn_r;
    eager = eagerness;
    weights = new ArrayList<Double>(amount);
    for (int s = 0; s < weights.size(); s++) {
      weights.set(s, ran.d_ran(2, -2));
    }
    connections = new ArrayList<Integer>(c);
    stm = new double[memory_size];
    for (int s = 0; s < stm.length; s++) {
      stm[s] = 0;
    }
  }

  public Neuron (ArrayList<Double> w) {
    inputs = new ArrayList<Double>(weights.size());
    weights = new ArrayList<Double>(w);
  }

  public double respond (ArrayList<Double> input) {
    total = 0;
    inputs = new ArrayList<Double>(input);
    inputs.add(stm[(int)cur_output]);
    last_mem_pos = (int)cur_output;
    if (weights.size() != inputs.size()) {
      for (int s = weights.size()-1; s > inputs.size(); s--) {
        weights.remove(s);
      }
      weights.trimToSize();
      for (int s = weights.size(); s < inputs.size(); s++) {
        weights.add(ran.d_ran(5, -5));
      }
    }
    for (int s = 0; s < inputs.size(); s++) {
      total += weights.get(s) * inputs.get(s);
    }
    cur_output = sig(total);
    return cur_output;
  }

  public double sig (double input) {
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public void train (double error_reward, double total_error) {
    int val = (int)cur_output*stm.length;
    if (val >= stm.length) {
      val -= 1;
    }
    double temp = stm[val];
    //stm[val] = temp + (learn_rate * (error_reward + eager * (cur_output) - temp));
    //stm[val] = temp + (learn_rate * total_error * cur_output * (1-cur_output));
    //stm[val] = error_reward*total_error;
    //stm[val] = error_reward/total_error;
    stm[val] = total_error/error_reward;
    //stm[val] = error_reward;
    //stm[val] = total_error;
    for (int s = 0; s < weights.size(); s++) {
      //weights.set(s, weights.get(s) + (learn_rate * total_error * eager * inputs.get(s) * cur_output * (1-cur_output)));
      weights.set(s, weights.get(s) + (learn_rate * (total_error + eager * (cur_output) - temp)));
    }
  }

  public int get_w_l () {
    return weights.size();
  }

  public int get_i_l () {
    return inputs.size();
  }

  public double get_w (int index) {
    return weights.get(index);
  }

  public double i_ret(int index) {
    return inputs.get(index);
  }

  public ArrayList<Double> i_get_w () {
    return weights;
  }

  public boolean check_connections (int connect) {
    for (int c = 0; c < connections.size(); c++) {
      if (connections.get(c) == connect) {
        return true;
      }
    }
    return false;
  }

  public double read_memory (int index) {
    return stm[index];
  }

  public double[] ret_memory () {
    return stm;
  }

  public double send_memory () {
    return stm[(int)cur_output];
  }

  public double ret_output () {
    return cur_output;
  }
}

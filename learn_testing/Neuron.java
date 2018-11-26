package machine_learning.neurons;
import java.util.*;
import utilities.*;

public class Neuron {
  private double[] stm;
  private ArrayList<Double> weights;
  private ArrayList<Integer> connections;
  private ArrayList<Double> inputs;
  private int total = 0;

  public Neuron (int amount, ArrayList<Integer> c, int memory_size) {
    weights = ArrayList<Double>(amount);
    for (int s = 0; s < weights.size(); s++) {
      weights.set(s, ran.d_ran(7, -7));
    }
    connections = c.clone();
    stm = new double[memory_size];
  }

  public Neuron (ArrayList<Double> w) {
    inputs = new ArrayList<Double>(weights.size());
    weights = w.clone();
  }

  public double respond (ArrayList<Integer> input) {
    total = 0;
    inputs = input.clone();
    if (weights.size() != inputs.size()) {
      for (int s = weights.size()-1; s > inputs.size(); s--) {
        weights.remove(s);
      }
      weights.trimToSize();
    }
    for (int s = 0; s < inputs.size(); ) {
      total += weights.get(s) * inputs.get(s);
    }
    return sig(total);
  }

  public double sig (double input) {
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public void train (double new_val, int index, int error_reward) {
    weights.set(index, weights.get(index)+new_val);
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
}

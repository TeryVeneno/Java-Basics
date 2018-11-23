package machine_learning;
import java.util.*;
import utilities.*;

public class Sigmoid {

  private double[] weights;
  private double total;
  private double[] inputs;
  private Ran ran = new Ran();

  public Sigmoid (int amount) {
    total = 0;
    inputs = new double[amount];
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length; s++) {
      weights[s] = ran.d_ran(7, -7);
    }
  }

  public Sigmoid (double w[]) {
    total = 0;
    inputs = new double[w.length];
    weights = w.clone();
  }

  public double output (double input[]) {
    total = 0;
    inputs = input.clone();
    for (int s = 0; s < inputs.length; s++) {
      total += inputs[s] * weights[s];
    }
    total = sig(total);
    return total;
  }

  public double i_ret(int index) {
    return inputs[index];
  }

  public void train (double new_val, int index) {
    weights[index] = weights[index] + new_val;
  }

  public double sig (double input) {
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public double get_w (int index) {
    return weights[index];
  }

  public  double[] i_get_w () {
    return weights;
  }

  public double get_t () {
    return total;
  }

  public int get_w_l () {
    return weights.length;
  }

  public int get_i_l () {
    return inputs.length;
  }

  public void randomize_w () {
    for (int w = 0; w < weights.length; w++) {
      if (ran.percent_chance(0.25,0.5)) {
        if (ran.percent_chance(0.10,0.5)) {
          weights[w] = ran.d_ran(5,-5);
        } else {
          weights[w] = weights[w] + ran.d_ran(1,-1);
        }
      }
    }
  }
}

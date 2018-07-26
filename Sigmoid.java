package machine_learning;
import java.util.*;
import utilities.*;

public class Sigmoid {

  double[] weights;
  double total;
  double bias;
  double cur_output;
  double[] inputs;
  Ran ran = new Ran();
  Random rand = new Random();

  public Sigmoid (int amount) {
    total = 0;
    inputs = new double[amount];
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length; s++) {
      weights[s] = rand.nextDouble();
    }
    bias = ran.d_ran(-4, -5);
  }

  public double output (double input[]) {
    total = 0;
    inputs = input.clone();
    for (int s = 0; s < inputs.length; s++) {
      total += inputs[s] * weights[s];
    }
    total += bias;
    total = sig(total);
    cur_output = total;
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
}

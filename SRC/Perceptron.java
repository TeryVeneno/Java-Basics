package machine_learning;
import java.util.*;
import utilities.*;

public class Perceptron {

  private double[] weights;
  private double[] inputs;
  private double bias;
  private double total;
  private double cur_output;
  private Ran ran = new Ran();
  private Random rand = new Random();

  public Perceptron (int amount) {
    total = 0;
    inputs = new double[amount];
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length; s++) {
      weights[s] = rand.nextDouble();;
    }
    bias = ran.d_ran(0, -1);
  }

  public int output (double[] input) {
    total = 0;
    inputs = input.clone();
    for (int s = 0; s < weights.length; s++) {
      total += inputs[s] * weights[s];
    }
    total += bias;
    if (total >= 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public void train (double new_val, int index) {
    weights[index] = weights[index] + new_val;
  }

  public double i_ret(int index) {
    return inputs[index];
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

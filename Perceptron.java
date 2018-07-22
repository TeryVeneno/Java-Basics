package machine_learning;
import java.util.*;
import utilities.*;

public class Perceptron {

  double[] weights;
  double[] inputs;
  double bias;
  double total;
  double learn_rate;
  double cur_output;
  Ran ran = new Ran();

  public Perceptron (double learn) {
    inputs = ins.clone();
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length; s++) {
      weights[s] = ran.d_ran(1, -1);
    }
    bias = ran.d_ran(1, -2);
    learn_rate = learn;
  }

  public int output (double ins) {
    total = 0;
    for (int s = 0; s < inputs.length; s++) {
      total += ins[s] * weights[s];
    }
    total += bias;
    if (total >= 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public void train (double error) {
    total = 0;
    for (int s = 0; s < weights.length; s++) {
      weights[s] = weights[s] + learn_rate * error * inputs[s];
      weights[s] = weights[s];
    }
  }

  public double format (double formatted) {
    return Double.parseDouble(String.format("%.4g%n", formatted));
  }
}

package machine_learning;
import java.util.*;
import utilities.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Perceptron {

  double[] weights;
  int[] inputs;
  double bias;
  double total;
  double learn_rate;
  double cur_output;
  Ran ran = new Ran();

  public Perceptron (int[] ins, double learn) {
    inputs = ins.clone();
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length - 1; s++) {
      weights[s] = Double.parseDouble(String.format("%.2g%n", ran.d_ran(1, -1)));;
    }
    bias = Double.parseDouble(String.format("%.2g%n", ran.d_ran(1, -1)));
    learn_rate = learn;
  }

  public double output () {
    for (int s = 0; s < inputs.length - 1; s++) {
      total += inputs[s] * weights[s];
    }
    total = Double.parseDouble(String.format("%.2g%n", total));
    total += bias;
    total = Double.parseDouble(String.format("%.2g%n", total));
    if (total >= 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public void train (double error) {
    total = 0;
    for (int s = 0; s < weights.length - 1; s++) {
      weights[s] = weights[s] + learn_rate * error * inputs[s];
      weights[s] = Double.parseDouble(String.format("%.2g%n", weights[s]));
    }
  }
}

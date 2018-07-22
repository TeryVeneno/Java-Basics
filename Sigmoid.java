package machine_learning;
import java.util.*;
import utilities.*;

public class Sigmoid {

  double[] weights;
  double[] inputs;
  double total;
  double bias;
  double cur_output;
  Ran ran = new Ran();

  public Sigmoid () {
    inputs = ins.clone();
    weights = new double[inputs.length];
    for (int s = 0; s < inputs.length; s++) {
      weights[s] =  ran.d_ran(1, -1);
    }
    bias = ran.d_ran(1, -1);
  }

  public double output () {
    for (int s = 0; s < inputs.length; s++) {
      total += inputs[s] * weights[s];
    }
    total = sig(total);
    total += bias;
    cur_output = total;
    return total;
  }

  public void train_hidden (double new_val, int index) {
    weights[index] = weights[index] + new_val;
  }

  public double sig (double input) {
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public double get_w (int index) {
    return weights[index];
  }

  public double format (int limit, double formatted) {
    return Double.parseDouble(String.format(("%." + limit +"g%n"), formatted));
  }
}

package machine_learning;
import java.util.*;
import utilities.*;

public class Brain {
  Perceptron[][] p_array;
  Sigmoid[][] s_array;
  double[] feed;
  double[] forward;
  double[] inputs;
  double learn_rate;
  int lays;
  int ns;
  int ch;
  double desire[];

  public Brain (int layers, int neurons, int choice, double learn_r, double inp[], double[] desired) {
    feed = new double[neurons];
    forward = new double[neurons];
    inputs = inp.clone();
    desire = desired.clone();
    learn_rate = learn_r;
    ch = choice;
    lays = layers - 1;
    ns = neurons - 1;
    if (choice == 0) {
      p_array = new Perceptron[layers][neurons];
      for (int l = 0; l < lays; l++) {
        for (int n = 0; n < ns; n++) {
          p_array[l][n] = new Perceptron(learn_rate);
        }
      }
    } else if (choice == 1) {
      s_array = new Sigmoid[layers][neurons];
      for (int s = 0; s < ns+1; s++) {
        s_array[0][s] = new Sigmoid(inputs.length);
      }
      for (int l = 1; l < layers; l++) {
        for (int n = 0; n < ns +1; n++) {
          s_array[l][n] = new Sigmoid(neurons);
        }
      }
    }
  }

  public String respond () {
    if (ch == 0) {
      for (int n = 0; n < ns +1; n++) {
        feed[n] = p_array[0][n].output(inputs);
        forward[n] = feed[n];
      }
      for (int l = 1; l < lays +1; l++) {
        for (int n = 0; n < ns +1; n++) {
          feed[n] = p_array[l][n].output(forward);
        }
        if (l == lays) {
          return Arrays.toString(feed);
        }
        forward = feed.clone();
      }
    } else if (ch == 1) {
      for (int n = 0; n < ns +1; n++) {
        feed[n] = s_array[0][n].output(inputs);
        forward[n] = feed[n];
      }
      for (int l = 1; l < lays +1; l++) {
        for (int n = 0; n < ns +1; n++) {
          feed[n] = s_array[l][n].output(forward);
        }
        if (l == lays) {
          return Arrays.toString(feed);
        }
        for (int n = 0; n < ns +1; n++) {
          forward[n] = feed[n];
        }
      }
    }
    return Arrays.toString(feed);
  }

  public double sig_prime(double input) {
    return input / sig(input);
  }

  public double sig (double input) {
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public void train_o () {
    double[] vals = new double[ns+1];
    for (int n = 0; n < ns + 1; n++) {
      vals[n] = sig_prime(s_array[lays][n].get_t()) * (desire[n] - feed[n]) * learn_rate;
      vals[n] = vals[n] / forward[n];
    }
    for (int n = 0; n < ns + 1; n++) {
      for (int w = 0; w < ns + 1; w++) {
        s_array[lays][n].train(vals[w], w);
      }
    }
  }

  public void train () {
    double val = 0;
    double[] vals = new double[ns+1];
    double[] valss = new double[inputs.length];
    for (int n = 0; n < ns; n++) {
      vals[n] = sig_prime(s_array[lays][n].get_t()) * (desire[n] - feed[n]);
      val += vals[n];
    }
    for (int l = 0; l < lays; l++) {
      for (int n = 0; n < ns; n++) {
        for (int w = 0; w < s_array[l][n].get_w_l() -1; w++) {
          valss[w] = val / s_array[l][n].get_w(w);
          valss[w] = valss[w] * sig_prime(s_array[l][n].get_t()) * learn_rate;
          if (s_array[l][n].i_ret(w) != 0) {
            valss[w] = valss[w] / s_array[l][n].i_ret(w);
          }
          s_array[l][n].train(valss[w], w);
        }
      }
    }
  }

  public void improve () {
    train_o();
    train();
  }
}

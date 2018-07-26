package machine_learning;
import java.util.*;
import utilities.*;

public class Brain {
  Perceptron[][] p_array;
  Sigmoid[][] s_array;
  double[][] feed;
  double[] inputs;
  double learn_rate;
  int lays;
  int ns;
  int ch;
  double desire[];

  public Brain (int layers, int neurons, int choice, double learn_r, double inp[], double[] desired) {
    feed = new double[layers][neurons];
    inputs = inp.clone();
    desire = desired.clone();
    learn_rate = learn_r;
    ch = choice;
    lays = layers - 1;
    ns = neurons - 1;
    if (choice == 0) {
      p_array = new Perceptron[layers][neurons];
      for (int s = 0; s < ns+1; s++) {
        p_array[0][s] = new Perceptron(inputs.length);
      }
      for (int l = 0; l < lays+1; l++) {
        for (int n = 0; n < ns+1; n++) {
          p_array[l][n] = new Perceptron(neurons);
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
      for (int n = 0; n < ns +1; n++) {
        feed[0][n] = s_array[0][n].output(inputs);
      }
      for (int l = 1; l < lays +1; l++) {
        for (int n = 0; n < ns +1; n++) {
          feed[l][n] = s_array[l][n].output(feed[l-1]);
        }
      }
      return Arrays.toString(feed[lays]);
  }

  public String respond (int choice) {
    for (int n = 0; n < ns +1; n++) {
      feed[0][n] = p_array[0][n].output(inputs);
    }
    for (int l = 1; l < lays +1; l++) {
      for (int n = 0; n < ns +1; n++) {
        feed[l][n] = p_array[l][n].output(feed[l-1]);
      }
    }
    return Arrays.toString(feed[lays]);
  }

  public void improve () {
    double error = 0;
    double train_val = 0;
    for (int n = ns; n > -1; n--) {
      for (int w = s_array[lays][n].get_w_l() -1; w > -1; w--) {
        error = desire[n] - feed[lays][n];
        train_val = learn_rate * error * s_array[lays][n].i_ret(w) *
        feed[lays][n] * (1-feed[lays][n]);
        s_array[lays][n].train(train_val, w);
      }
    }
    double total_error = 0;
    train_val = 0;
    for (int n = ns; n > -1; n--) {
      total_error += desire[n] - feed[lays][n];
    }
    for (int l = lays -1; l > -1; l--) {
      for (int n = ns -1; n > -1; n--) {
        for (int w = s_array[l][n].get_w_l() -1; w > -1; w--) {
          train_val = learn_rate * total_error * s_array[l][n].i_ret(w) *
          feed[l][n] * (1-feed[l][n]);
          s_array[l][n].train(train_val, w);
        }
      }
    }
  }

  public void improve (int choice) {
    double error = 0;
    double train_val = 0;
    for (int n = ns; n > -1; n--) {
      for (int w = p_array[lays][n].get_w_l() -1; w > -1; w--) {
        error = desire[n] - feed[lays][n];
        train_val = learn_rate * error * p_array[lays][n].i_ret(w);
        p_array[lays][n].train(train_val, w);
      }
    }
    double total_error = 0;
    train_val = 0;
    for (int n = ns; n > -1; n--) {
      total_error += desire[n] - feed[lays][n];
    }
    for (int l = lays -1; l > -1; l--) {
      for (int n = ns -1; n > -1; n--) {
        for (int w = p_array[l][n].get_w_l() -1; w > -1; w--) {
          train_val = learn_rate * total_error * p_array[l][n].i_ret(w);
          p_array[l][n].train(train_val, w);
        }
      }
    }
  }

  public void think (int cycles) {
    for (int s = 0; s <= cycles; s++) {
      respond();
      improve();
    }
  }

  public void think (int cycles, int choice) {
    for (int s = 0; s <= cycles; s++) {
      respond(choice);
      improve(choice);
    }
  }
}

package machine_learning;
import java.util.*;
import utilities.*;

public class Brain {
  private Perceptron[][] p_array;
  private Sigmoid[][] s_array;
  private double[][] feed;
  private double[] inputs;
  private double learn_rate;
  private int lays;
  private int ns;
  private int ch;
  private int o_ns;
  private double desire[];

  public Brain (int layers, int neurons, int choice, double learn_r, double inp[], double[] desired) {
    feed = new double[layers][neurons+1];
    inputs = new double[inp.length+1];
    for (int s = 0; s < inp.length; s++) {
      inputs[s] = inp[s];
    }
    inputs[inputs.length-1] = 1;
    desire = desired.clone();
    learn_rate = learn_r;
    ch = choice;
    lays = layers;
    ns = neurons;
    o_ns = neurons;
    if (choice == 0) {
      p_array = new Perceptron[layers][neurons];
      for (int s = 0; s < ns; s++) {
        p_array[0][s] = new Perceptron(inputs.length);
      }
      for (int l = 1; l < layers; l++) {
        for (int n = 0; n < ns; n++) {
          p_array[l][n] = new Perceptron(neurons+1);
        }
      }
    } else if (choice == 1) {
      s_array = new Sigmoid[layers][neurons];
      for (int s = 0; s < ns; s++) {
        s_array[0][s] = new Sigmoid(inputs.length);
      }
      for (int l = 1; l < layers; l++) {
        for (int n = 0; n < ns; n++) {
          s_array[l][n] = new Sigmoid(neurons+1);
        }
      }
    }
  }

  public Brain (int layers, int neurons, int o_neurons, int choice, double learn_r, double inp[], double[] desired) {
    feed = new double[layers][neurons+1];
    inputs = new double[inp.length+1];
    for (int s = 0; s < inp.length; s++) {
      inputs[s] = inp[s];
    }
    desire = desired.clone();
    learn_rate = learn_r;
    ch = choice;
    lays = layers;
    ns = neurons;
    o_ns = o_neurons;
    if (choice == 0) {
      p_array = new Perceptron[layers][neurons];
      for (int s = 0; s < ns; s++) {
        p_array[0][s] = new Perceptron(inputs.length);
      }
      for (int l = 1; l < layers-1; l++) {
        for (int n = 0; n < ns; n++) {
          p_array[l][n] = new Perceptron(neurons+1);
        }
      }
      for (int n = 0; n < o_ns; n++) {
        p_array[lays-1][n] = new Perceptron(neurons+1);
      }
    } else if (choice == 1) {
      s_array = new Sigmoid[layers][neurons];
      for (int s = 0; s < ns; s++) {
        s_array[0][s] = new Sigmoid(inputs.length);
      }
      for (int l = 1; l < layers-1; l++) {
        for (int n = 0; n < ns; n++) {
          s_array[l][n] = new Sigmoid(neurons+1);
        }
      }
      for (int n = 0; n < o_ns; n++) {
        s_array[lays-1][n] = new Sigmoid(neurons+1);
      }
    }
  }


  public double[] respond () {
      for (int n = 0; n < ns; n++) {
        feed[0][n] = s_array[0][n].output(inputs);
      }
      feed[0][ns] = 1;
      for (int l = 1; l < lays-1; l++) {
        for (int n = 0; n < ns; n++) {
          feed[l][n] = s_array[l][n].output(feed[l-1]);
        }
        feed[l][ns] = 1;
      }
      for (int n = 0; n < o_ns; n++) {
        feed[lays-1][n] = s_array[lays-1][n].output(feed[lays-2]);
      }
      return feed[lays-1];
  }

  public double[] respond (int choice) {
    for (int n = 0; n < ns; n++) {
      feed[0][n] = p_array[0][n].output(inputs);
    }
    feed[0][ns] = 1;
    for (int l = 1; l < lays-1; l++) {
      for (int n = 0; n < ns; n++) {
        feed[l][n] = p_array[l][n].output(feed[l-1]);
      }
      feed[l][ns] = 1;
    }
    for (int n = 0; n < o_ns; n++) {
      feed[lays-1][n] = p_array[lays-1][n].output(feed[lays-2]);
    }
    return feed[lays-1];
  }

  public void improve () {
    double error = 0;
    double train_val = 0;
    for (int n = o_ns-1; n > -1; n--) {
      for (int w = s_array[lays-1][n].get_w_l() -1; w > -1; w--) {
        error = desire[n] - feed[lays-1][n];
        train_val = learn_rate * error * s_array[lays-1][n].i_ret(w) *
        feed[lays-1][n] * (1-feed[lays-1][n]);
        s_array[lays-1][n].train(train_val, w);
      }
    }
    double total_error = 0;
    train_val = 0;
    for (int n = o_ns-1; n > -1; n--) {
      total_error += desire[n] - feed[lays-1][n];
    }
    for (int l = lays -2; l > -1; l--) {
      for (int n = ns -2; n > -1; n--) {
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
    for (int n = o_ns-1; n > -1; n--) {
      for (int w = p_array[lays-1][n].get_w_l() -1; w > -1; w--) {
        error = desire[n] - feed[lays-1][n];
        train_val = learn_rate * error * p_array[lays-1][n].i_ret(w);
        p_array[lays-1][n].train(train_val, w);
      }
    }
    double total_error = 0;
    train_val = 0;
    for (int n = o_ns-1; n > -1; n--) {
      total_error += desire[n] - feed[lays-1][n];
    }
    for (int l = lays -2; l > -1; l--) {
      for (int n = ns -2; n > -1; n--) {
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

  public void change (double[] inp, double[] desired, double learn_r) {
    inputs = new double[inp.length+1];
    for (int s = 0; s < inp.length; s++) {
      inputs[s] = inp[s];
    }
    inputs[inputs.length-1] = 1;
    desire = desired.clone();
    learn_rate = learn_r;
  }

  public void change_i (double[] inp) {
    inputs = new double[inp.length+1];
    for (int s = 0; s < inp.length; s++) {
      inputs[s] = inp[s];
    }
    inputs[inputs.length-1] = 1;
  }

  public void change_d (double[] desired) {
    desire = desired.clone();
  }

  public void change_r (double learn_r) {
    learn_rate = learn_r;
  }

  public void mutate () {
    for (int l = 0; l < lays-1; l++) {
      for (int n = 0; n < ns; n++) {
        s_array[l][n].randomize_w();
      }
    }
    for (int s = 0; s < o_ns; s++) {
      s_array[lays-1][s].randomize_w();
    }
  }

  public void set_array (Sigmoid[][] s) {
    s_array = s.clone();
  }

  public Sigmoid[][] ret_array () {
    return s_array;
  }

  public Brain deep_copy () {
    Brain brain = new Brain(lays, ns, o_ns, ch, learn_rate, inputs, desire);
    brain.set_array(s_array);
    return brain;
  }
}

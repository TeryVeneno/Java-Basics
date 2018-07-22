package machine_learning;
import utilities.*;

public class Brain {
  Perceptron[][] p_array;
  Sigmoid[][] s_array;

  public Neural_Network (int layers, int neurons, int choice, double learn_rate, double inputs[]) {
    if (choice == 0) {
      p_array = new Perceptron[layers][neurons];
      for (int l = 0; l < layers; l++) {
        for (int n = 0; n < neurons; n++) {
          p_array[l][n] = new Perceptron(inputs, learn_rate)
        }
      }
    } else if (choice == 1) {
      s_array = new Sigmoid[layers][neurons];
      for (int l = 0; l < layers; l++) {
        for (int n = 0; n < neurons; n++) {
          s_array[l][n] = new Sigmoid(inputs, learn_rate)
        }
      }
    }
  }

  public double respond () {
    if (choice == 0) {
      for (Perceptron per : p_array) {
        per.output()
      }
    }
  }
}

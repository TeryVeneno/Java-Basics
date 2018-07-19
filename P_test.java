import machine_learning.Perceptron;
import utilities.Ran;
import java.util.*;

public class P_test {
  public static void main(String[] args) throws IllegalArgumentException {
    double outp;
    double error;
    Ran ran = new Ran();
    Random rand = new Random(System.currentTimeMillis());
    double d = 1;
    int[] inputs = new int[6];
    inputs[0] = 1;
    for (int s = 1; s < inputs.length; s++) {
      inputs[s] = rand.nextInt(2);
    }
    Perceptron p = new Perceptron(inputs, 0.03);
    outp = p.output();
    if (outp == d) {
      System.out.println("The Perceptron has finished learning and its output " + outp + " is the same as desired_output of " + d + ".");
    } else {
      while (outp != d) {
        error = (d - outp);
        p.train(error);
        outp = p.output();
        if (outp == d) {
          System.out.println("The Perceptron has finished learning and its output " + outp + " is the same as desired_output of " + d + ".");
        }
      }
    }
  }
}

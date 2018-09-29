import machine_learning.Sigmoid;
import utilities.Ran;
import java.util.*;

public class S_test {

  public static double format (double formatted) {
    return Double.parseDouble(String.format("%.9g%n", formatted));
  }

  public static void main(String[] args) {
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
    Sigmoid s = new Sigmoid(inputs, 0.3);
    s.output();
    for (int i = 0; i < 10000; i++) {
      s.train(d - s.output());
    }
    System.out.println("Training finished. System may now be able to predict desired output.");
    System.out.println("Output: " + s.output());
  }
}

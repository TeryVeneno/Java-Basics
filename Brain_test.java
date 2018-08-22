import machine_learning.Brain;
import java.util.*;
import utilities.Ran;
import java.util.Random;

public class Brain_test {
  public static void main(String[] args) {
    Random rand = new Random(System.currentTimeMillis());
    double[] inputs = new double[6];
    double[] desired = new double[10];
    desired[0] = 1;
    desired[1] = 0;
    desired[2] = 1;
    desired[3] = 0;
    desired[4] = 1;
    desired[5] = 0;
    desired[6] = 1;
    desired[7] = 0;
    desired[8] = 1;
    desired[9] = 0;
    inputs[0] = 1;
    for (int s = 1; s < inputs.length; s++) {
      inputs[s] = rand.nextInt(2);
    }
    Brain brain = new Brain(20, 10, 1, 0.3, inputs, desired);
    System.out.println(Arrays.toString(brain.respond()));
    brain.think(10000);
    System.out.println(Arrays.toString(brain.respond()));
  }
}

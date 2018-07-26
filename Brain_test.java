import machine_learning.Brain;
import utilities.Ran;
import java.util.Random;

public class Brain_test {
  public static void main(String[] args) {
    Random rand = new Random(System.currentTimeMillis());
    double[] inputs = new double[6];
    double[] desired = new double[30];
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
    desired[10] = 1;
    desired[11] = 0;
    desired[12] = 1;
    desired[13] = 0;
    desired[14] = 1;
    desired[15] = 0;
    desired[16] = 1;
    desired[17] = 0;
    desired[18] = 1;
    desired[19] = 0;
    desired[20] = 1;
    desired[21] = 0;
    desired[22] = 1;
    desired[23] = 0;
    desired[24] = 1;
    desired[25] = 0;
    desired[26] = 1;
    desired[27] = 0;
    desired[28] = 1;
    desired[29] = 0;
    inputs[0] = 1;
    for (int s = 1; s < inputs.length; s++) {
      inputs[s] = rand.nextInt(2);
    }
    Brain brain = new Brain(30, 30, 1, 0.3, inputs, desired);
    System.out.println(brain.respond());
    brain.think(10000);
    System.out.println(brain.respond());
  }
}

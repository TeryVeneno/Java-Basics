package machine_learning;
import java.util.Scanner;
import utilities.Ran;

public class Perceptron {

  static int perceptron (int in1, int in2, int in3, int w1, int w2, int w3, int bis) {
     return ((in1 * w1) + (in2 * w2)
      + (in3 * w3)) + bis;
  }

  public static void main(String[] args) throws InterruptedException {
    Ran rand = new Ran();
    Scanner p_input = new Scanner(System.in);
    int iterations = 0;
    int checker;
    int weight1 = rand.ran(10, 1);
    int weight2 = rand.ran(10, 1);
    int weight3 = rand.ran(10, 1);
    int output;
    System.out.print("Enter the output what the output should be(only 1 or 0): ");
    int e_output = p_input.nextInt();
    System.out.print("Enter the first input(only 1 or 0): ");
    int input1 = p_input.nextInt();
    System.out.print("Enter the second input(only 1 or 0): ");
    int input2 = p_input.nextInt();
    System.out.print("Enter the third input(only 1 or 0): ");
    int input3 = p_input.nextInt();
    System.out.print("Enter the bias: ");
    int bias = p_input.nextInt();

    checker = perceptron(input1, input2, input3, weight1, weight2, weight3, bias);
    if (checker >= 0) {
      output = 1;
    } else {
      output = 0;
    }
    System.out.println("\nWeight1 is: " + weight1 + ".");
    System.out.println("Weight2 is: " + weight2 + ".");
    System.out.println("Weight3 is: " + weight3 + ".\n");
    iterations++;
    System.out.println("The number of iterations for the program is: "
    + iterations + ".");

    while (output != e_output) {
      iterations++;
      weight1 += ((e_output - output) * input1);
      weight2 += ((e_output - output) * input2);
      weight3 += ((e_output - output) * input3);
      System.out.println("\nWeight1 is: " + weight1 + ".");
      System.out.println("Weight2 is: " + weight2 + ".");
      System.out.println("Weight3 is: " + weight3 + ".\n");
      System.out.println("The number of iterations for the program is: "
      + iterations + ".");
      checker = perceptron(input1, input2, input3, weight1, weight2, weight3, bias);
      if (checker >= 0) {
        output = 1;
      } else {
        output = 0;
      }
      Thread.sleep(3000);
    }
    System.out.println("The perceptron's output is " + output +
    " and that is the same as the desired output of " + e_output + ". So the program has finished.");
  }
}

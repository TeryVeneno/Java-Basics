import java.util.Scanner;

public class Calculator {

  static double add (double nm1, double nm2) {
    return nm1 + nm2;
  }

  static double subtract (double nm1, double nm2) {
    return nm1 - nm2;
  }

  static double multiply (double nm1, double nm2) {
    return nm1 * nm2;
  }

  static double divide (double nm1, double nm2) {
    return nm1 / nm2;
  }

  static double mod (double nm1, double nm2) {
    return nm1 % nm2;
  }

  static double ex (double nm1, double nm2) {
    return Math.pow(nm1, nm2);
  }
  public static void main(String[] args) {
    String operator;
    double number, number2;
    Scanner input_op = new Scanner(System.in);
    Scanner input_number = new Scanner(System.in);
    while (true) {
      System.out.println("Available operators: \n\n" + "+ (add), - (subtract), / ( divide), * (multiply), % ( mod), ^ (exponent)\n");
      System.out.print("First number please: ");
      number = input_number.nextDouble();
      System.out.print("Next number please: ");
      number2 = input_number.nextDouble();
      System.out.print("Enter your operator: ");
      operator = input_op.next();
      if (operator.equals("+")) {
        System.out.println(add(number, number2) + "\n");
      } else if (operator.equals("-")) {
        System.out.println(subtract(number, number2) + "\n");
      } else if (operator.equals("*")) {
        System.out.println(multiply(number, number2) + "\n");
      } else if (operator.equals("/")) {
        System.out.println(divide(number, number2) + "\n");
      } else if (operator.equals("%")) {
        System.out.println(mod(number, number2) + "\n");
      } else if (operator.equals("^")) {
        System.out.println(ex(number,number2) + "\n");
      } else {
        System.out.println("This calculator can't do that operation.\n");
      }
    }
  }
}

import java.util.Scanner;

public class Scanner_test {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    /*double number, number1, sum;
    System.out.print("Enter the first number: ");
    number = input.nextDouble();
    System.out.print("Enter the second number: ");
    number1 = input.nextDouble();
    sum = number + number1;
    System.out.println("The sum is: " + sum);*/
    String name;
    System.out.print("Enter your name: ");
    name = input.nextLine();
    System.out.println("Your name is: " + name);
  }
}

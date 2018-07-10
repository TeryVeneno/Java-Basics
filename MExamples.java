import java.util.Scanner;

public class MExamples {
  static double tip_calc (double amount) {
    return amount * .15;
  }

  static double discount (double amount, double percent) {
    return amount - (amount * (percent/100));
  }
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    /*double tip, bill;
    System.out.print("Enter the amount of your bill: ");
    bill =  input.nextDouble();
    tip = tip_calc(bill);
    System.out.println("Your tip should be " + tip + ".");*/
    double amount, disc;
    System.out.print("Enter the original cost: ");
    amount = input.nextDouble();
    System.out.print("Enter the discount as whole number: ");
    disc = input1.nextDouble();
    System.out.println("Your discount is " + discount(amount, disc));
  }
}

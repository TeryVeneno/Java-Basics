import java.util.Scanner;

public class While_loop {
  public static void main(String[] args) {
    /*int count = 1;
    while (count <= 10) {
      System.out.println(count);
      count++;*/

    /*double balance = 5000;
    double rate = 1.02;
    int year = 1;
    while (year <= 10) {
      balance *= rate;
      System.out.print("After year " + year + ", the balance is ");
      System.out.printf("balance = %.2f",balance);
      System.out.println(".");
      year++;
    }

    System.out.print("After ten years the balance is : ");
    System.out.printf("balance = %.2f",balance);
    System.out.println(".");*/

    /*Scanner input = new Scanner(System.in);
    int grade, total, count;
    double average = 0.0;
    total = 0;
    count = 0;
    System.out.print("Enter a grade (-1 to quit): ");
    grade = input.nextInt();
    while (grade != -1) {
      total += grade;
      count++;
      System.out.print("Enter a grade (-1 to quit): ");
      grade = input.nextInt();
    }
    average = total / count;
    System.out.println("The average grade is: " + average + ".");*/

    double balance = 5000;
    double rate = 1.02;
    int years = 0;
    while (balance <= 100000) {
      balance *= rate;
      years++;
    }
    System.out.println("It will take " + years + " years to reach 100000.");
  }
}

import java.util.Scanner;

public class If_else {
  public static void main(String[] args) {
    /*int hours_worked;
    Scanner input = new Scanner(System.in);
    System.out.print("Enter hours worked: ");
    hours_worked = input.nextInt();
    double rate = 25.00;
    double gross_pay;
    if (hours_worked > 40) {
      gross_pay = (40 * rate) + ((hours_worked - 40) * (rate* 1.5));
    } else {
      gross_pay = hours_worked * rate;
    }
    System.out.println("The gross pay is: " + gross_pay);*/

    // nested if or else-ifs statements
    int grade;
    String letter_grade = "";
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a grade: ");
    grade = input.nextInt();

    /*if (grade >= 90) {
      if (grade >= 95) {
        letter_grade = "A+";
        System.out.println("Your letter grade is: " + letter_grade);
      }
      if (grade >= 93) {
        letter_grade = "A";
        System.out.println("Your letter grade is: " + letter_grade);
      } else {
        letter_grade = "A-";
        System.out.println("Your letter grade is: " + letter_grade);
      }

    } else {
      System.out.println("Sorry, you didn't make an A.");
    }*/

    if (grade >= 90) {
      letter_grade = "A";
    } else if (grade >= 80) {
      letter_grade = "B";
    } else if (grade >= 70) {
      letter_grade = "C";
    } else if (grade >= 60) {
      letter_grade = "D";
    } else if (grade >= 0) {
      letter_grade = "F";
    } else {
      System.out.println("Didn't recognize input.");
    }
    System.out.println("A grade of " + grade + " is a " + letter_grade + ".");
  }
}

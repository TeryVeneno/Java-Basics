import java.util.Scanner;

public class Methods {

  /*static int square (int number) {
      return number * number;
  }*/

  static double ftoc(double temp) {
    double celsius;
    celsius = (temp - 32.0) * (5.0 / 9.0);
    return celsius;
  }

  static double ctof(double temp) {
    double fahrenheit;
    fahrenheit = temp * (9 / 5) + 32;
    return fahrenheit;
  }

  static double convert(double temp, String type) {
    if (type.equals("C")) {
      return ftoc(temp);
    } else {
      return ctof(temp);
    }
  }

  public static void main(String[] args) {
    /*int num = 12;
    System.out.println(num + " squared equals " + square(num));*/

    Scanner input = new Scanner(System.in);
    Scanner input_temp = new Scanner(System.in);
    double temperature;
    String type;
    System.out.print("Enter a temperature to convert: ");
    temperature = input_temp.nextDouble();
    System.out.print("Enter type to convert to (C or F): ");
    type = input.next();
    System.out.println("The converted temperature is " + convert(temperature, type));
    /*double temp, ftemp, ctemp;
    System.out.print("Enter a temperature in Fahrenheit: ");
    ftemp = input.nextDouble();
    ctemp = ftoc(ftemp);
    System.out.println(ftemp + " F is equal to " + ctemp + " C.");
    System.out.print("Enter a temperature in celsius: ");
    ctemp = input.nextDouble();
    ftemp = ctof(ctemp);
    System.out.println(ctemp + " C is equal to " + ftemp + " F.");*/

  }
}

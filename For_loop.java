import java.util.Scanner;

public class For_loop {
  public static void main(String[] args) {
    /*for ( int s = 1; s <= 5; s++) {
      System.out.println("Hello World!");
    }*/

    /*for (int s = 1; s <= 100; s += 2) {
      System.out.print(s);
    }*/

    /*int sum = 0;
    for (int s = 1; s <= 10; s++) {
      sum += s;
    }
    System.out.println("The sum of 1 to 10 is " + sum + ".");*/

    /*double balance = 5000;
    double rate = 1.02;
    int s = 1;
    for (; s <= 10; ) {
      balance *= rate;
      s++;
    }
    System.out.println("The balance after ten years is " + balance + ".");*/

    Scanner input = new Scanner(System.in);
    String sentence = "";
    int vowels = 0;
    System.out.print("Enter a sentence: ");
    sentence = input.nextLine();
    for (int pos = 0; pos < sentence.length(); pos++) {
      if (sentence.charAt(pos) == 'a') {
        vowels++;
      } else if (sentence.charAt(pos) == 'e') {
        vowels++;
      } else if (sentence.charAt(pos) == 'i') {
        vowels++;
      }  else if (sentence.charAt(pos) == 'o') {
        vowels++;
      } else if (sentence.charAt(pos) == 'u') {
        vowels++;
      }
    }
    System.out.println("There are " + vowels + " vowels in the sentence.");
  }
}

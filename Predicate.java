import java.util.Scanner;

public class Predicate {
  static boolean is_even(int number) {
    if (number % 2 == 0) {
      return true;
    } else {
      return false;
    }
  }

  static boolean is_vowel(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    /*Scanner input = new Scanner(System.in);
    int num;
    System.out.print("Enter a number: ");
    num = input.nextInt();
    if (is_even(num)) {
      System.out.println(num + " is even.");
    } else {
      System.out.println(num + " is odd.");
    }*/

    String sentence;
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a sentence: ");
    sentence = input.nextLine();
    for (int s = 0; s < sentence.length(); s++) {
      if (is_vowel(sentence.charAt(s))) {
        System.out.print(sentence.charAt(s));
      }
    }
    System.out.println();
  }
}

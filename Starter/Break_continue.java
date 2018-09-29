import java.util.Scanner;

public class Break_continue {
  public static void main(String[] args) {
    /*String spaces = "there are spaces in this string";
    int num_spaces = 0;
    for (int s = 0; s < spaces.length(); s++) {
      if (spaces.charAt(s) != ' ') {
        continue;
      }
      num_spaces++;
    }
    System.out.println("There are " + num_spaces + " spaces.");*/

    Scanner input = new Scanner(System.in);
    int number = 7;
    int guess;
    while (true) {
      System.out.print("Enter your guess: ");
      guess = input.nextInt();
      if (guess == number) {
        break;
      }
    }
  }
}

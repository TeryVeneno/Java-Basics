import java.util.Scanner;

public class Guess_game {
  public static void main(String[] args) {
    String name = "";
    String answer = "Watson";
    Scanner input = new Scanner(System.in);
    System.out.println("Let's play a guessing game. You get 3 tries.");
    System.out.print("What was the name of the computer that played jeopardy? ");
    name = input.nextLine();
    if (name.equals(answer)) {
      System.out.println("That's right!");
    } else {
      System.out.print("Sorry guess again: ");
      name = input.nextLine();
      if (name.equals(answer)) {
        System.out.println("That's right!");
      } else {
        System.out.print("Still not right. One more guess: ");
        name = input.nextLine();
        if (name.equals(answer)) {
          System.out.println("That's right!");
        } else {
          System.out.println("Wrong again. The answer is Watson.");
        }
      }
    }
  }
}

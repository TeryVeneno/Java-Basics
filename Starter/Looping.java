import java.util.Scanner;

public class Looping {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String answer = "Watson";
    String response = "";
    int tries = 0;
    while (tries <= 3) {
      System.out.print("Enter the name of the computer that played Jeopardy: ");
      response = input.nextLine();
      tries++;
      if (response.equals("Watson")) {
        System.out.println("That's right!");
        break;
      } else if (tries == 3) {
        System.out.println("Sorry. The answer is Watson.");
        break;
      } else {
        System.out.println("Sorry. Try again.");
      }
    }
  }
}

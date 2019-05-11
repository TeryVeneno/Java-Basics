import java.util.*;
import java.io.*;

public class Name_create {
  public static void main(String[] args) throws InterruptedException, IOException {
    Scanner input = new Scanner(System.in);
    String temp = "";
    String temp2 = "";
    String temp3 = "";
    boolean go = true;
    System.out.print("Enter a root: ");
    temp = input.nextLine();
    while (true) {
      if (go) {
        System.out.print("Enter the add-on: ");
        temp2 = input.nextLine();
        System.out.println(temp2 + temp);
      }
      System.out.print("Start Over, T or F: ");
      temp3 = input.nextLine();
      if (temp3.equals("T")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        break;
      }
    }
    input.close();
  }
}

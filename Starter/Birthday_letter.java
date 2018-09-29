import java.util.Scanner;

public class Birthday_letter {
  public static void main(String[] args) {
    Scanner input_number = new Scanner(System.in);
    Scanner input_string = new Scanner(System.in);
    String name, present, your_name;
    int age;
    System.out.print("What is the name of the gift giver?: ");
    name = input_string.nextLine();
    System.out.print("What is the present they gave you?: ");
    present = input_string.nextLine();
    System.out.print("How old were you on your birthday?: ");
    age = input_number.nextInt();
    System.out.print("What is your name?: ");
    your_name = input_string.nextLine();
    System.out.print("\n\n");
    System.out.println("Dear " + name + ", ");
    System.out.print("\n");
    System.out.print("Thank you for the " + present + ". ");
    System.out.println("I really like it. I can't believe ");
    System.out.print("I'm already " + age + " years old, but " );
    System.out.println("it doesn't feel that much different than ");
    System.out.println("being " + (age-1) + ". ");
    System.out.print("\n");
    System.out.println("Sincerely, ");
    System.out.print("\n");
    System.out.println(your_name);
  }
}

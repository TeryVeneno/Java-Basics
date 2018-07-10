public class Pass {
  static int square(int num) {
    num *= num;
    return num;
  }

  public static void main(String[] args) {
    int number = 12;
    System.out.println("number squared: " + square(number));
    System.out.println("original number: " + number);
  }
}

public class Check {
  public static void main(String[] args) {
    int s = 2;
      int i = "TFQ".hashCode() % 3000;
      int p = "ARA".hashCode() % 3000;
      for (int a = 0; a <= p; a++)
         s = (s ^ a) % i;
      System.out.println(s);
  }
}

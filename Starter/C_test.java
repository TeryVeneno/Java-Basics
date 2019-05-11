import utilities.Converter;

public class C_test {
  public static void main(String[] args) {
    Converter convert = new Converter(8);
    System.out.println(convert.convert_bd("00101101"));
    System.out.println(convert.convert_db(45));
  }
}

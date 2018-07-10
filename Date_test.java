public class Date_test {
  public static void main(String[] args) {
    Date today = new Date(6,20,18);
    Date tomorrow = new Date(6,18);
    System.out.println("today is: " + today.to_string());
    System.out.println("tomorrow is: " + tomorrow.to_string());
    tomorrow.set_day(21);
    System.out.println("tomorrow is: " + tomorrow.to_string());
    System.out.println("today is again: " + today.get_month() + "/"
    + today.get_day() + "/" + today.get_year());
  }
}

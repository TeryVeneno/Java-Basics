public class Date {
  public int month;
  public int day;
  public int year;

  public Date(int m, int d, int y) {
    month = m;
    day = d;
    year = y;
  }

  public Date(int m, int y) {
    month = m;
    day = 0;
    year = y;
  }

  public Date() {
    month = 0;
    day = 0;
    year = 0;
  }

  public String to_string() {
    return month + "/" + day + "/" + year;
  }

  public int get_month() {
    return month;
  }

  public int get_day() {
    return day;
  }

  public int get_year() {
    return year;
  }

  public void set_month(int m) {
    month = m;
  }

  public void set_day(int d) {
    day = d;
  }

  public void set_year(int y) {
    year = y;
  }

  public void set_date(int m, int d, int y) {
    month = m;
    day = d;
    year = y;
  }
}

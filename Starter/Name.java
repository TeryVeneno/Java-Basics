public class Name {
  private String first;
  private String middle;
  private String last;

  public Name(String f, String m, String l) {
    first = f;
    middle = m;
    last = l;
  }

  public Name(String f, String l) {
    first = f;
    middle = "";
    last = l;
  }

  public Name(String l) {
    first = "";
    middle = "";
    last = l;
  }

  public Name() {
    first = "";
    middle = "";
    last = "";
  }

  public String toString() {
    return first + " " + middle + " " + last;
  }

  public String get_first() {
    return first;
  }

  public String get_middle() {
    return middle;
  }

  public String get_last() {
    return last;
  }

  public void set_first(String fname) {
    first = fname;
  }

  public void set_middle(String mname) {
    middle = mname;
  }

  public void set_last(String lname) {
    last = lname;
  }

  public void set_name(String f, String m, String l) {
    first = f;
    middle = m;
    last = l;
  }
}

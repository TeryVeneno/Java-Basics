public class Chapter5 {
  public class Name {
    private String first;
    private String middle;
    private String last;

    // displays name
    public String display_name() {
      return first + " " + middle + " " + last;
    }

    // gets initials
    public String get_initials () {
      return first.substring(0,1) + middle.substring(0,1) + last.substring(0,1);
    }
  }

  public static void main(String[] args) {

  }
}

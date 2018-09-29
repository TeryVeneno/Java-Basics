public class Name_test {
  public static void main(String[] args) {
    Name my_name = new Name ("Omateolashi", "Simon", "Asenime");
    Name your_name = new Name ("Bob", "Bobert");
    /*Name a_name = new Name ("Merr");
    Name some_name = newName();
    System.out.println("my_name: " + my_name.toString());
    System.out.println("your_name: " + your_name.toString());*/
    Name some_name = new Name();
    System.out.println("my_name first name: " + my_name.get_first());
    your_name.set_last("God");
    System.out.println("your_name: " + your_name.toString());
    some_name.set_name("Ichigo", "Ywhach", "Kurosaki");
    System.out.println("some_name: " + some_name.toString());
  }
}

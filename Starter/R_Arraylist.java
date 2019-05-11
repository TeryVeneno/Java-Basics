import java.util.*;

public class R_Arraylist {

  /*static void display (ArrayList arr) {
    for (int s = 0; s < arr.size(); s++) {
      System.out.print(arr.get(s) + " ");
    }
  }*/

  public static void main(String[] args) {
     int number;
    Random ran = new Random(System.currentTimeMillis());
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    for (int s = 0; s < 100 ; s++) {
       number = ran.nextInt(101);
       numbers.add(number);
    }

    /*if (numbers.indexOf(100) >= 0) {
      System.out.println("100 was found at position " + numbers.indexOf(100));
    }

    if (numbers.contains(100)) {
      System.out.println(("100 was found at position " + numbers.indexOf(100)));
    } else {
      System.out.println("100 was not found in numbers.");
    }*/

    int min = 0;
    for (int s = 0; s < numbers.get(min); s++) {
      if (numbers.get(s) < numbers.get(min)) {
        min = s;
      }
    }
    System.out.println("The minimum value in numbers is " + numbers.get(min));
  }
}

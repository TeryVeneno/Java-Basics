import java.util.ArrayList;

public class Array_list {

  static void display(ArrayList arr) {
    for (int s = 0; s < arr.size(); s++) {
      System.out.print(arr.get(s) + " ");
    }
    System.out.println();
  }

  static void change(ArrayList<Integer> arr, int amount) {
    int value;
    for (int s = 0; s < arr.size(); s++) {
      value = arr.get(s);
      arr.set(s, value + amount);
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    for (int s = 1; s < 11; s++) {
      numbers.add(s);
    }
    display (numbers);
    change (numbers, 5);
    display (numbers);

    /*double average;
    int total = 0;
    ArrayList<Integer> grades = new ArrayList<Integer>();
    grades.add(78);
    grades.add(84);
    grades.add(90);
    for (int s = 0; s < grades.size(); s++) {
      total += grades.get(s);
    }
    average = total / grades.size();
    System.out.println("The average is " + average + ".");
    System.out.println(grades);

    int grade1, grade2, grade3, total;
    double average
    grade1 = 78;
    grade2 = 84;
    grade3 = 90;
    total = grade1 + grade 2 + grade3;
    average = total / 3;*/
  }
}

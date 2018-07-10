import java.util.ArrayList;

public class Array_list {
  public static void main(String[] args) {
    double average;
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

    /*int grade1, grade2, grade3, total;
    double average
    grade1 = 78;
    grade2 = 84;
    grade3 = 90;
    total = grade1 + grade 2 + grade3;
    average = total / 3;*/

  }
}

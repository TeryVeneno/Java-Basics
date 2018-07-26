package utilities;
import java.util.Random;

public class Ran {
  Random rand = new Random(System.currentTimeMillis());
  String s_total;
  public Ran () {

  }

  public int i_ran (int max, int min) {
    return (int)(rand.nextDouble() * (max - min)) + min;
  }

  public double d_ran (double max, double min) {
    return (rand.nextDouble() * (max - min)) + min;
  }

  public String s_ran (int limit) {
    for (int s = 0; s < limit; s++) {
      s_total += (char) i_ran(100, 0);
    }
    return s_total;
  }
}

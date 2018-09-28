package utilities;
import java.util.*;
import java.text.*;
import java.math.RoundingMode;

public class Ran {
  Random rand = new Random();
  private String s_total;
  DecimalFormat df = new DecimalFormat("#.#");
  public Ran () {
    df.setRoundingMode(RoundingMode.CEILING);
  }

  public int i_ran (double max, double min) {
    return (int)((rand.nextDouble() * (max - min)) + min);
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

  public boolean percent_chance (double p1, double p2) {
    double percent1 = Double.parseDouble(df.format(1 - p1));
    double percent2 = Double.parseDouble(df.format(1 - p2));
    double chance = Double.parseDouble(df.format(rand.nextDouble()));
    if (chance >= percent1) {
      return true;
    } else {
      return false;
    }
  }
}

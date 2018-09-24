package machine_learning;
import utilities.*;

public class Gene {
  public int connection;
  public int weight;
  private boolean enabled;
  private int innov;
  private String type;
  Ran ran = new Ran();

  public Gene (int con, Double w, boolean e, int i, String t) {
    connection = con
    weights = w
    enabled = e;
    innov = i;
    type = t;
  }

  public Gene (int con, boolean e, int i, String t) {
    connection = con
    weight = ran.d_ran(1, -1);
    enabled = e;
    innov = i;
    type = t;
  }


  public void disable () {
    enabled = false;
  }

  public void enable () {
    enabled = true;
  }

  public boolean usable () {
    return enabled;
  }

  public void set_weight (double val) {
    weight = val
  }

  public boolean equals (Gene g) {
    if (g.innov == innov) {
      return true;
    }
  }
}

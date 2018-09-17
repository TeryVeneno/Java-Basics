package machine_learning;
import utilities.*;

public class Gene {
  public ArrayList<Integer> connections;
  public ArrayList<Integer> pull_from;
  public ArrayList<Double> weights;
  private boolean enabled;
  private int innov;
  private String type;
  Ran ran = new Ran();

  public Gene (ArrayList<Integer> con, ArrayList<Double> w, ArrayList<Integer> p, boolean e, int i, String t) {
    connection = con.clone();
    weights = w.clone();
    pull_from = p.clone();
    enabled = e;
    innov = i;
    type = t;
  }

  public Gene (ArrayList<Integer> con, ArrayList<Integer> p, boolean e, int i, String t) {
    connection = con.clone();
    pull_from = p.clone();
    weights = new  ArrayList<Double>();
    for (int w = 0; w < p.size(); w++) {
      w.add(ran.d_ran(0.9, -0.9));
    }
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

  public void set_weight (int index, double val) {
    weights.get(index) = val;
  }

  public boolean equals (Gene g) {
    if (g.innov == innov) {
      return true;
    }
  }
}

public class Gene {
  public int connection;
  public int weight;
  private boolean enabled;
  private int innov;
  private String type;

  public Gene (int con, int w, boolean e, int i, String t) {
    connection = con;
    weight = w;
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
}

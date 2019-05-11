package utilities;
import gui.*;

public class Vectors {
  private double magnitude;
  private double direction;

  public Vectors (double mag, int dir) {
    magnitude = mag;
    direction = dir;
  }

  public static int x_update (Vectors v, double x) {
    int x_change = (int)x;
    x_change += (int)(Math.cos((Math.toRadians(v.direction)))*v.magnitude);
    return x_change;
  }

  public static int y_update (Vectors v, double y) {
    int y_change = (int)y;
    y_change += (int)(Math.sin((Math.toRadians(v.direction)))*v.magnitude);
    return y_change;
  }

  public double get_mag () {
    return magnitude;
  }

  public double get_dir () {
    return direction;
  }

  public void set_mag (double mag) {
    magnitude = mag;
  }

  public void set_dir (double dir) {
    direction = dir;
  }

  public void change (double val) {
    magnitude += val;
  }
}

package utilities;
import gui.*;

public class Vectors {
  public double magnitude;
  public double direction;

  public Vectors (double mag, int dir) {
    magnitude = mag;
    direction = dir;
  }

  public static Coordinate move (Vectors v, Coordinate c) {
    double x_change = c.x;
    double y_change = c.y;
    x_change = Math.cos((v.direction))*v.magnitude;
    y_change = Math.sin((v.direction))*v.magnitude;
    Coordinate r = new Coordinate((int)x_change, (int)y_change);
    return r;
  }
}

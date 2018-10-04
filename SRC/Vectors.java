package utilities;
import gui.*;

public class Vectors {
  public double magnitude;
  public double direction;

  public Vectors (double mag, int dir) {
    magnitude = mag;
    direction = dir;
  }

  public static int x_move (Vectors v, Coordinate c) {
    int x_change = c.x;
    x_change += (int)(Math.cos((Math.toRadians(v.direction)))*v.magnitude);
    return x_change;
  }

  public static int y_move (Vectors v, Coordinate c) {
    int y_change = c.y;
    y_change += (int)(Math.sin((Math.toRadians(v.direction)))*v.magnitude);
    return y_change;
  }
}

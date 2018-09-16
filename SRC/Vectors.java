package utilities;
import gui.*;

public class Vectors {
  public double magnitude;
  public int direction;

  public Vectors (double mag, int dir) {
    magnitude = mag;
    direction = dir;
  }

  public static int redirect (int direction) {
    int dir = direction;
    if (direction == 0) {
      dir = 4;
    } else if (direction == 1) {
      dir = 3;
    } else if (direction == 3) {
      dir = 1;
    } else if (direction == 4) {
      dir = 0;
    } else if (direction == 5) {
      dir = 7;
    } else if (direction == 7) {
      dir = 5;
    }
    return dir;
  }

  public static Coordinate move (Vectors v, Coordinate c) {
    int x_change = c.x;
    int y_change = c.y;
    if (v.direction == 0) {
      y_change -= v.magnitude;
    } else if (v.direction == 1) {
      x_change -= v.magnitude;
      y_change -= v.magnitude;
    } else if (v.direction == 2) {
      x_change -= v.magnitude;
    } else if (v.direction == 3) {
      x_change -= v.magnitude;
      y_change += v.magnitude;
    } else if (v.direction == 4) {
      y_change += v.magnitude;
    } else if (v.direction == 5) {
      x_change += v.magnitude;
      y_change += v.magnitude;
    } else if (v.direction == 6) {
      x_change += v.magnitude;
    } else if (v.direction == 7) {
      x_change += v.magnitude;
      y_change -= v.magnitude;
    }
    Coordinate r = new Coordinate(x_change, y_change);
    return r;
  }
}

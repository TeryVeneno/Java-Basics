package gui;

public class Coordinate {
  public int x;
  public int y;

  public Coordinate (int xs, int ys) {
    x = xs;
    y = ys;
  }

  public static String to_string (Coordinate c) {
    String ret = "";
    ret += c.x;
    ret += ", ";
    ret += c.y;
    return ret;
  }
}

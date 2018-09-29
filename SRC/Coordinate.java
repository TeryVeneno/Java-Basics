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

  public static String to_string (Coordinate[] cs) {
    String ret = "";
    for (int c = 0; c < cs.length; c++) {
      if (c == 0) {
        ret += cs[c].x;
        ret += ", ";
        ret += cs[c].y;
      } else {
        ret += ", ";
        ret += cs[c].x;
        ret += ", ";
        ret += cs[c].y;
      }
    }
    return ret;
  }
}

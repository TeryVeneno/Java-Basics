package utilities;
import java.text.*;
import java.math.*;
import java.util.*;

public class Formatting {


  static DecimalFormat df = new DecimalFormat();
  static DecimalFormat dff = new DecimalFormat();

  public Formatting (String kind, String kind2) {
    df.applyPattern(kind);
    dff.applyPattern(kind2);
  }

  public Formatting () {
    df.applyPattern(".#");
    dff.applyPattern(".##");
  }

  static public double form (double formatted) {
    df.setRoundingMode(RoundingMode.CEILING);
    return Double.parseDouble(df.format(formatted));
  }

  static public double form (double formatted, int choice) {
    dff.setRoundingMode(RoundingMode.CEILING);
    return Double.parseDouble(dff.format(formatted));
  }

  static public double[] form (double[] formatted) {
    double[] f_ret = formatted.clone();
    for (int i = 0; i < f_ret.length; i++) {
      f_ret[i] = form((f_ret[i] * 100), 0);
    }
    return f_ret;
  }

  static public String reverse (String reversed) {
    String ret_reverse = "";
    for (int r = reversed.length() -1; r >= 0; r--) {
      ret_reverse = ret_reverse + reversed.charAt(r);
    }
    return ret_reverse;
  }
}

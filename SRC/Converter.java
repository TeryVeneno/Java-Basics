package utilities;

public class Converter extends Formatting {

  private int places;

  public Converter (int place) {
    places = place;
  }

  public Converter () {
  }

  public int convert_bd (String binary) {
    int total = 0;
    int place = 1;
    for (int p = 0; p < places-1; p++) {
      place *= 2;
    }
    String checker;
    for (int p = 0; p < binary.length(); p++) {
      checker = "";
      checker += binary.charAt(p);
      if (checker.equals("1")) {
        total += place;
        place /= 2;
      } else {
        place /= 2;
      }
    }
    return total;
  }

  public String convert_db (int decimal) {
    int start = 1;
    int place = places;
    for (int p = 0; p < places-1; p++) {
      start *= 2;
    }
    String total = "";
    while (place > 0) {
      if (decimal >= start) {
        decimal -= start;
        start /= 2;
        total += "1";
        place--;
      } else {
        start /= 2;
        total += "0";
        place--;
      }
    }
    return total;
  }
}

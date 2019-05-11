package utilities;

public class Time_Con extends Converter {
  String convert_to;
  String convert_from;

  public Time_Con (String con_to, String con_from) {
    convert_to = con_to;
    convert_from = con_from;
  }

  public Time_Con () {
    convert_to = "minutes";
    convert_from = "seconds";
  }

  public long convert (long value) {
    if (convert_to.equals("hours") && convert_from.equals("minutes")) {
      return value / 60;
    } else if (convert_to.equals("minutes") && convert_from.equals("hours")) {
      return value * 60;
    } else if (convert_to.equals("minutes") && convert_from.equals("seconds")) {
      return value / 60;
    } else if (convert_to.equals("seconds") && convert_from.equals("minutes")) {
      return value * 60;
    } else if (convert_to.equals("seconds") && convert_from.equals("miliseconds")) {
      return value / 1000;
    } else if (convert_to.equals("miliseconds") && convert_from.equals("seconds")) {
      return value * 1000;
    } else if (convert_to.equals("miliseconds") && convert_from.equals("microseconds")) {
      return value / 1000;
    } else if (convert_to.equals("microseconds") && convert_from.equals("miliseconds")) {
      return value * 1000;
    } else if (convert_to.equals("microseconds") && convert_from.equals("nanoseconds")) {
      return value / 1000;
    } else if (convert_to.equals("nanoseconds") && convert_from.equals("microseconds")) {
      return value * 1000;
    }
    return value;
  }
}

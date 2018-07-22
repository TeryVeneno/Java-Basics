public class Formatting {
  public double format (int limit, double formatted) {
    return Double.parseDouble(String.format(("%." + limit +"g%n"), formatted));
  }
}

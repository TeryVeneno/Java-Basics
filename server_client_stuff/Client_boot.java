package communication.client;

public class Client_boot {
  private static String ip = "localhost";
  private static int port = 1234;

  public static void main(String[] args) {
    Client.start();
    Client.send("Hello Network. This is a test.", ip, port);
  }
}

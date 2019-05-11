package communication.server;

public class Server_boot {

  public Server_boot (int port) {
    new Server(port);
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Server_boot >> Server has failed to boot. Invalid Parameters. Please enter a port.");
      return;
    }
    int port = Integer.parseInt(args[0]);
    new Server_boot(port);
  }
}

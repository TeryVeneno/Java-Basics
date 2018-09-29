package communication.server;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class Server {
  private int port;
  private DatagramSocket socket;
  private boolean running = false;

  private void receive () {
    Thread receiver = new Thread("Receiver") {
      public void run () {
        try {
          while (running) {
            byte[] r_data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(r_data, r_data.length);
            socket.receive(packet);
            String message = new String(r_data);
            System.out.println(packet.getAddress().getHostAddress() + ":" + packet.getPort() + " >> " + message);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }; receiver.start();
  }

  public Server (int port) {
    try {
      this.port = port;
      socket = new DatagramSocket(port);
      running = true;
      receive();
      System.out.println("Server >> Started on Port " + port);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

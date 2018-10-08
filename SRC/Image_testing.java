import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;

public class Image_testing extends JPanel {
  BufferedImage img;

  @Override
  public void paint (Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

  public Image_testing () {
    try {
      img = ImageIO.read(new File("Resources/black hole.jpg"))
    } catch(IOException e) {
    }
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Image_testing");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(500,500));
    frame.pack();
    frame.setVisible(true);
  }
}

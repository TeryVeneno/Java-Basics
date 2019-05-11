import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
import machine_learning.*;

public class Image_testing extends JLabel {
  BufferedImage img;
  int w = 10;
  int h = 10;
  int type = BufferedImage.TYPE_INT_ARGB;
  BufferedImage image = new BufferedImage(w, h, type);

  public void paint (Graphics g) {
    g.drawImage(image, 0, 0, null);
  }

  public Image_testing () {
    try {
      img = ImageIO.read(new File("Resources/black hole.jpg"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void print_pixel_ARGB(int pixel) {
    int alpha = (pixel >> 24) & 0xff;
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
  }

  public void check () {
    int w = img.getWidth();
    int h = img.getHeight();
    System.out.println("Width: "+w+"\n" + "Height: "+h);
    for (int s = 0; s < h; s++) {
      for (int r = 0; r < w; r++) {
        int pixel = img.getRGB(r,s);
        System.out.println("Pixel: " + pixel);
        System.out.println("[" +s+"]["+r+"]- ");
        print_pixel_ARGB(pixel);
      }
    }
  }

  public void print_ai () {
    int temp = 0;
    int red, green, blue = 0;
    int w = img.getWidth();
    int h = img.getHeight();
    double[] inputs = new double[w*h];
    double[] desire = new double[300];
    double[] res = new double[300];
    int average = 0;
    for (int s = 0; s < w; s++) {
      for (int r = 0; r < h; r++) {
        int pixel = img.getRGB(s,r);
        inputs[s * h + r] = pixel;
      }
    }
    for (int s = 0; s < 100; s++) {
      average = 0;
      for (int r = 0; r < (w*h)/100; r++) {
        average += inputs[s * ((w*h)/100) + r];
      }
      average /= ((w*h)/100);
      double holder1 = ((average >> 16) & 0xff);
      double holder2 = ((average >> 8) & 0xff);
      double holder3 = ((average) & 0xff);
      desire[s*3] = holder1/1000;
      desire[s*3+1] = holder2/1000;
      desire[s*3+2] = holder3/1000;
      System.out.println(holder1);
      System.out.println(holder2);
      System.out.println(holder3);
      System.out.println(holder1/1000);
      System.out.println(holder2/1000);
      System.out.println(holder3/1000);
      System.out.println(desire[s*3]);
      System.out.println(desire[s*3+1]);
      System.out.println(desire[s*3+2]);
    }
    Brain brain = new Brain(3, 30, 300, 1, 0.03, inputs, desire);
    brain.think(10000);
    res = brain.respond().clone();
    System.out.println(Arrays.toString(res));
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {
        red = (int)(res[(x*10+y)*3]*1000);
        green = (int)(res[(x*10+y)*3+1]*1000);
        blue = (int)(res[(x*10+y)*3+2]*1000);
        if (red > 255) {
          red = 255;
        }
        if (green > 255) {
          green = 255;
        }
        if (blue > 255) {
          blue = 255;
        }
        temp = new Color(red, green, blue).getRGB();
        image.setRGB(x,y, temp);
        System.out.println((temp >> 16) & 0xff);
        System.out.println(((temp >> 8) & 0xff));
        System.out.println(((temp) & 0xff));
      }
    }
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Image_testing");
    Image_testing test = new Image_testing();
    test.print_ai();
    test.setPreferredSize(new Dimension(500,500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(test);
    frame.pack();
    frame.setVisible(true);
    frame.repaint();
  }
}

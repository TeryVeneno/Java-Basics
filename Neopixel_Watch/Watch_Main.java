package watch;
,mimport java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.io.*;
import javax.swing.*;

public class Watch_Main {
  public static void main(String[] args) throws InterruptedException {
    Watch watch = new Watch();
    JFrame frame = new JFrame("Neopixel Watch");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(watch);
    frame.setSize(new Dimension(500,500));
    frame.pack();
    frame.setVisible(true);
    while (true) {
      watch.render();
      Thread.sleep(17);
    }
  }
}

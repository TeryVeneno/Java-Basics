package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frames {
  public static void main(String[] args) {
    JFrame frame = new JFrame("FrameDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel emptyLabel = new JLabel("");
    emptyLabel.setPreferredSize(new Dimension(175, 100));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }
}

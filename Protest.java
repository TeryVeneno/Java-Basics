import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Protest extends JLabel {
  public String s = "WE'RE PROTESTING!";
  public String drawn = s;

  @Override
  public void paint (Graphics g) {
    g.drawString(drawn, 683, 384);
  }
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Protest");
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Protest emptyLabel = new Protest();
    Font labelFont = emptyLabel.getFont();
    emptyLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 120));
    emptyLabel.setPreferredSize(new Dimension(1000, 1000));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
    while(true) {
      if (drawn.equals(s)) {
        emptyLabel.drawn = "";
      } else {
        emptyLabel.drawn = s;
      }
      frame.repaint();
      Thread.sleep(17);
    }
  }
}

package pong.menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class Start_Menu extends JPanel {
  JButton play1;
  JButton play2;
  public boolean not_showing = false;
  public boolean pvp = false;

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.WHITE);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
    g.drawString("Pong", getWidth()/2-30, 100);
    play1.setSize(120,60);
    play2.setSize(120,60);
    play1.setLocation(getWidth()/2-60, 200);
    play2.setLocation(getWidth()/2-60, 300);
  }

  public Start_Menu () {
    setPreferredSize(new Dimension(1000, 1000));
    setMaximumSize(new Dimension(1000, 1000));
    play1 = new JButton("1 Player");
    play2 = new JButton("2 Players");
    this.add(play1);
    this.add(play2);
    MenuListener menu = new MenuListener();
    play1.addActionListener(menu);
    play2.addActionListener(menu);
  }

  private class MenuListener implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      JButton source = (JButton)e.getSource();
      if (source == play1) {
        // some code to start player vs AI
      }
      if (source == play2) {
        // player vs player
        pvp = true;
        System.out.println("started");
      }
    }
  }
}

package pong.menu;
import pong.board.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Game_Panel extends JPanel {
  Pong_Board board;
  public boolean stopped = false;

  public Game_Panel (Pong_Board board) {
    setPreferredSize(new Dimension(1000, 1000));
    setMaximumSize(new Dimension(1000, 1000));
    this.board = board;
    this.addKeyListener(new QuitHandler());
  }

  private class QuitHandler extends KeyAdapter {
    public void keyPressed (KeyEvent e) {
      if (e.getKeyCode() == e.VK_Q) {
        board.quit();
        stopped = true;
      }
    }
  }
}

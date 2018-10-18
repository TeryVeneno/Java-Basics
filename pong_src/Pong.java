package pong.main;
import javax.swing.*;
import pong.board.Pong_Board;
import java.awt.*;
import pong.menu.*;
public class Pong {
  public static void main(String[] args) throws InterruptedException {
    Pong_Board board = new Pong_Board();
    Start_Menu menu = new Start_Menu();
    JPanel slides = new JPanel(new CardLayout());
    JFrame frame = new JFrame("Pong");
    slides.add(menu, "Menu");
    slides.add(board, "Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(slides);
    frame.pack();
    frame.setVisible(true);
    CardLayout cl = (CardLayout) slides.getLayout();
    cl.show(slides, "Menu");
    while (true) {
      if (board.stopped == true) {
        board.stopped = false;
        cl.show(slides, "Menu");
        frame.repaint();
      }
      System.out.println(menu.pvp);
      if (menu.pvp) {
        menu.pvp = false;
        cl.show(slides, "Game");
        board.init();
      }
    }
  }
}

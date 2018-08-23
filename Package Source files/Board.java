package utilities;

import java.util.*;

public class Board {
  private char[][] board;

  public Board (int columns, int rows) {
    board = new char[columns][rows];
    for (int c = 0; c < columns; c++) {
      for (int r = 0; r < rows; r++) {
        board[c][r] = ' ';
      }
    }
  }

  public Board (char[][] template) {
    board = template.clone();
  }

  public static void whitespace () {
    int count = 0;
    while (count < 27) {
      System.out.println();
      count++;
    }
  }

  public void show_board () {
    String show = "|";
    String wall = "";
    for (int s = 0; s < board.length +2; s++) {
      wall += "-";
    }
    System.out.println(wall);
    for (int c = 0; c < board.length; c++) {
      for (int r = 0; r < board[c].length; r++) {
        show += board[c][r];
      }
      show += "|";
      System.out.println(show);
      show = "|";
    }
    System.out.println(wall);
  }

  public void update_board (int column, int row, char value) {
    board[column][row] = value;
  }
  public char[][] ret_board () {
    return board;
  }
}

import utilities.Board;

public class B_test {

  public static void whitespace () {
    int count = 0;
    while (count < 27) {
      System.out.println();
      count++;
    }
  }
  public static void main(String[] args) throws InterruptedException {
    Board board = new Board (3,3);
    int count = 0;
    int c2 = 0;
    while (count < 3) {
      whitespace();
      if (count != 0) {
        board.update_board(c2, count-1, ' ');
      } else if (count == 0) {
        if (c2 != 0) {
          board.update_board(c2-1, count+2, ' ');
        } else if (c2 == 0) {
          board.update_board(c2, count, ' ');
        }
      }
      board.update_board(c2, count, '0');
      board.show_board();
      if (count == 2 && c2 == 0) {
        c2++;
        count = 0;
      } else if (count == 2 && c2 == 1) {
        c2++;
        count = 0;
      } else {
        count++;
      }
      Thread.sleep(400);
    }
  }
}

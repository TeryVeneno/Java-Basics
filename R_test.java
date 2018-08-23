import utilities.*;
import java.util.*;
import machine_learning.Reinforcement;

public class R_test {
  static char[][] maze = new char[3][3];
  static char[] maz = new char[9];
  static public void set () {
    maze[0][0] = '0';
    maze[0][1] = '0';
    maze[0][2] = '0';
    maze[1][0] = 'F';
    maze[1][1] = '0';
    maze[1][2] = '0';
    maze[2][0] = '0';
    maze[2][1] = '0';
    maze[2][2] = 'X';
    maz[0] = '0';
    maz[1] = '0';
    maz[2] = '0';
    maz[3] = 'F';
    maz[4] = '0';
    maz[5] = '0';
    maz[6] = '0';
    maz[7] = '0';
    maz[8] = 'X';
  }

  public static int[] pos_actions (int length, int width) {
    int[] poses = new int[4];
    for (int s = 0; s < poses.length; s++) {
      if (s == 0) {
        if ((length-1) >= 0) {
          poses[s] = 1;
        }
      } else if (s == 1) {
        if ((width-1) >= 0) {
          poses[s] = 1;
        }
      } else if (s == 2) {
        if ((length+1) <= 2) {
          poses[s] = 1;
        }
      } else if (s == 3) {
        if ((width+1) <= 2) {
          poses[s] = 1;
        }
      }
    }
    return poses;
  }

  public static int con (int length, int width) {
    int[][] holder = new int[3][3];
    int count = 0;
    for (int i = 0; i < 3; i++) {
      for (int o = 0; o < 3; o++) {
        holder[i][o] = count;
        count++;
      }
    }
    return holder[length][width];
  }

  public static int[] conv (int state) {
    int[] ret = new int[2];
    int[] length = new int[9];
    int[] width = new int[9];
    int count = 0;
    for (int i = 0; i < 9; i++) {
      length[i] = count;
      if (i == 2) {
        count = 1;
      } else if (i == 5) {
        count = 2;
      }
    }
    count = 0;
    for (int i = 0; i < 9; i++) {
      width[i] = count;
      if (i == 2) {
        count = 0;
      } else if (i == 5) {
        count = 0;
      } else {
        count++;
      }
    }
    ret[0] = length[state];
    ret[1] = width[state];
    return ret;
  }

  public static void main(String[] args) throws InterruptedException {
    set();
    int[] take = new int[2];
    int choice = 0;
    int next = 0;
    Board board = new Board(maze);
    Random rand = new Random(System.currentTimeMillis());
    int ran = rand.nextInt(9);
    Reinforcement r = new Reinforcement(9, 4, 0.1, 0.8, ran);
    take = conv(ran);
    board.update_board(take[0], take[1], 'a');
    for (int s = 0; s < 25; s++) {
      board.show_board();
      if (maze[take[0]][take[1]] == 'F') {
        r.train(1, pos_actions(take[0], take[1]));
        r.set_c(rand.nextInt(9));
      } else if (maze[take[0]][take[1]] == 'X') {
        r.train(-1, pos_actions(take[0], take[1]));
        choice = r.choose(pos_actions(take[0], take[1]));
        if (choice == 0) {
          next = con(take[0], take[1]);
          next -= 3;
          take = conv(next);
        } else if (choice == 1) {
          next = con(take[0], take[1]);
          next -= 1;
          take = conv(next);
        } else if (choice == 2) {
          next = con(take[0], take[1]);
          next += 3;
          take = conv(next);
        } else if (choice == 3) {
          next = con(take[0], take[1]);
          next += 1;
          take = conv(next);
        }
        r.set_c(next);
      } else {
        choice = r.choose(pos_actions(take[0], take[1]));
        if (choice == 0) {
          next = con(take[0], take[1]);
          next -= 3;
          take = conv(next);
        } else if (choice == 1) {
          next = con(take[0], take[1]);
          next -= 1;
          take = conv(next);
        } else if (choice == 2) {
          next = con(take[0], take[1]);
          next += 3;
          take = conv(next);
        } else if (choice == 3) {
          next = con(take[0], take[1]);
          next += 1;
          take = conv(next);
        }
        r.train(0, pos_actions(take[0], take[1]));
        r.set_c(next);
      }
      board.update_board(take[0], take[1], 'a');
      Thread.sleep(400);
    }
  }
}

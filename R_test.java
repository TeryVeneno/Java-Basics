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
    poses[0] = 0;
    poses[1] = 0;
    poses[2] = 0;
    poses[3] = 0;
    int l1 = length-1;
    int l2 = length+1;
    int w1 = width-1;
    int w2 = width+1;
    for (int s = 0; s < poses.length; s++) {
      if (s == 0) {
        if (l1 >= 0) {
          poses[s] = 1;
        }
      }
      if (s == 1) {
        if (w1 >= 0) {
          poses[s] = 1;
        }
      }
      if (s == 2) {
        if (l2 <= 2) {
          poses[s] = 1;
        }
      }
      if (s == 3) {
        if (w2 <= 2) {
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
    Board board = new Board(maze.clone());
    Random rand = new Random(System.currentTimeMillis());
    int ran = rand.nextInt(9);
    Reinforcement r = new Reinforcement(9, 4, 0.1, 0.8, ran, 150);
    take = conv(ran);
    board.update_board(take[0], take[1], 'a');
    for (int s = 0; s < 10000; s++) {
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
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
      if (maze[take[0]][take[1]] == 'X') {
        r.train(-2, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(2, pos_actions(take[0], take[1]), choice);
        ran = rand.nextInt(9);
        take = conv(ran);
        r.set_c(ran);
      } else {
        r.train(0, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
      }
      board.update_board(take[0], take[1], 'a');
    }
    for (int s = 0; s < 50; s++) {
      board.whitespace();
      board.show_board();
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
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
      if (maze[take[0]][take[1]] == 'X') {
        r.train(-1, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, pos_actions(take[0], take[1]), choice);
        ran = rand.nextInt(9);
        take = conv(ran);
        r.set_c(ran);
      } else {
        r.train(0, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
      }
      board.update_board(take[0], take[1], 'a');
      Thread.sleep(1000);
    }
    double[][] q = r.ret_q().clone();
    for (int s = 0; s < q.length; s++) {
      System.out.println(s + "" + Arrays.toString(q[s]));
    }
  }
}

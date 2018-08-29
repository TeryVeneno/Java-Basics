import java.io.IOException;
import java.util.*;
import machine_learning.Reinforcement;
import utilities.*;

public class Cat_Mouse {
  static char[][] maze = new char[5][5];
  static char[] maz = new char[25];
  static public void set () {
    for (int l = 0; l < 5; l++) {
      for (int w = 0; w < 5; w++) {
        maze[l][w] = ' ';
      }
    }
    maze[2][2] = 'F';
    for (int s = 0; s < 25; s++) {
      maz[s] = '0';
    }
    maz[12] = 'F';
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
        if (l2 <= 4) {
          poses[s] = 1;
        }
      }
      if (s == 3) {
        if (w2 <= 4) {
          poses[s] = 1;
        }
      }
    }
    return poses;
  }

  public static int[] cat_s (int mouse_pos1, int mouse_pos2, int cat_pos1, int cat_pos2) {
    int[] holder = new int[2];
    holder[0] = cat_pos1 - mouse_pos1;
    holder[1] = cat_pos2 - mouse_pos2;;
    return holder;
  }

  public static int con (int length, int width) {
    int[][] holder = new int[5][5];
    int count = 0;
    for (int i = 0; i < 5; i++) {
      for (int o = 0; o < 5; o++) {
        holder[i][o] = count;
        count++;
      }
    }
    return holder[length][width];
  }

  public static int[] conv (int state) {
    int[] ret = new int[2];
    int[] length = new int[25];
    int[] width = new int[25];
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

  public static void main(String[] args) throws IOException, InterruptedException {
    set();
    int[] take = new int[2];
    int[] movements = new int[2];
    int[] last_pos = new int[2];
    int[] take2 = new int[2];
    int choice = 0;
    int next = 0;
    int last;
    char[][] holder = new char[5][5];
    Board board = new Board(maze);
    Random rand = new Random(System.currentTimeMillis());
    int ran = rand.nextInt(25);
    Reinforcement r = new Reinforcement(25, 4, 0.1, 0.8, ran, -1);
    take = conv(ran);
    last = ran;
    ran = rand.nextInt(25);
    take2 = conv(0);
    board.update_board(take[0], take[1], 'C');
    board.update_board(take2[0], take2[1], 'A');
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    for (int s = 0; s < 10000; s++) {
      choice = r.choose(pos_actions(take[0], take[1]));
      if (choice == 0) {
        next = con(take[0], take[1]);
        next -= 5;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 1) {
        next = con(take[0], take[1]);
        next -= 1;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 2) {
        next = con(take[0], take[1]);
        next += 5;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 3) {
        next = con(take[0], take[1]);
        next += 1;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      }
      holder = board.ret_board().clone();
      if (holder[take[0]][take[1]] == 'C') {
        r.train(-1, pos_actions(take[0], take[1]), choice);
        ran = rand.nextInt(25);
        take = conv(ran);
        r.set_c(ran);
        take2 = conv(0);
        board.update_board(take[0], take[1], 'A');
        board.update_board(take2[0], take2[1], 'C');
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, pos_actions(take[0], take[1]), choice);
        take2 = conv(0);
        ran = rand.nextInt(25);
        take = conv(ran);
        r.set_c(ran);
        board.update_board(take[0], take[1], 'A');
        board.update_board(take2[0], take2[1], 'C');
      } else {
        r.train(0, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
        board.update_board(take[0], take[1], 'A');
        movements = cat_s(take[0], take[1], take2[0], take2[1]);
        if (movements[0] < 0 && take2[0] > 0) {
          take2[0] = -1 + take2[0];
        } else if (movements[0] > 0 && take2[0] < 5) {
          take2[0] = 1 + take2[0];
        } else {
          if (movements[1] < 0 && take2[1] > 0) {
            take2[1] = -1 + take2[1];
          } else if (movements[1] > 0 && take2[1] < 5) {
            take2[1] = 1 + take2[1];
          }
        }
        holder = board.ret_board().clone();
        if (holder[take2[0]][take2[1]] == 'A') {
          r.set_c(last);
          r.train(-1, pos_actions(last_pos[0], last_pos[1]), choice);
          ran = rand.nextInt(25);
          take = conv(ran);
          r.set_c(ran);
          take2 = conv(0);
          board.update_board(take[0], take[1], 'A');
        }
        board.update_board(take2[0], take2[1], 'C');
      }
      last = next;
    }

    for (int s = 0; s < 50; s++) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      board.show_board();
      choice = r.choose(pos_actions(take[0], take[1]));
      if (choice == 0) {
        next = con(take[0], take[1]);
        next -= 5;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 1) {
        next = con(take[0], take[1]);
        next -= 1;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 2) {
        next = con(take[0], take[1]);
        next += 5;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      } else if (choice == 3) {
        next = con(take[0], take[1]);
        next += 1;
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        board.update_board(take2[0], take2[1], maze[take2[0]][take2[1]]);
        take = conv(next);
      }
      holder = board.ret_board().clone();
      if (holder[take[0]][take[1]] == 'C') {
        r.train(-1, pos_actions(take[0], take[1]), choice);
        ran = rand.nextInt(25);
        take = conv(ran);
        r.set_c(ran);
        take2 = conv(0);
        board.update_board(take[0], take[1], 'A');
        board.update_board(take2[0], take2[1], 'C');
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, pos_actions(take[0], take[1]), choice);
        ran = rand.nextInt(25);
        take = conv(ran);
        r.set_c(ran);
        board.update_board(take[0], take[1], 'A');
        board.update_board(take2[0], take2[1], 'C');
      } else {
        r.train(0, pos_actions(take[0], take[1]), choice);
        r.set_c(next);
        board.update_board(take[0], take[1], 'A');
        movements = cat_s(take[0], take[1], take2[0], take2[1]);
        if (movements[0] < 0 && take2[0] > 0) {
          take2[0] = -1 + take2[0];
        } else if (movements[0] > 0 && take2[0] < 5) {
          take2[0] = 1 + take2[0];
        } else {
          if (movements[1] < 0 && take2[1] > 0) {
            take2[1] = -1 + take2[1];
          } else if (movements[1] > 0 && take2[1] < 5) {
            take2[1] = 1 + take2[1];
          }
        }
        holder = board.ret_board().clone();
        if (holder[take2[0]][take2[1]] == 'A') {
          r.set_c(last);
          r.train(-1, pos_actions(last_pos[0], last_pos[1]), choice);
          ran = rand.nextInt(25);
          take = conv(ran);
          r.set_c(ran);
          take2 = conv(0);
          board.update_board(take[0], take[1], 'A');
        }
        board.update_board(take2[0], take2[1], 'C');
      }
      last = next;
      Thread.sleep(300);
    }
    double[][] q = r.ret_q().clone();
    for (int s = 0; s < q.length; s++) {
      System.out.println(s + "" + Arrays.toString(q[s]));
    }
    System.out.println(Arrays.toString(conv(9)));
  }
}

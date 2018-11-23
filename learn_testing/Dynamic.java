package machine_learning;

import utilities.*;
import java.util.*;
import java.io.IOException;

public class Dynamic {
  static public char[][] maze = new char[3][3];
  static public char[] maz = new char[9];
  static public double[] m = new double[9];
  static public double[][] ma = new double[3][3];
  static public int[] take = new int[2];
  static public void set () {
    maze[0][0] = 'F';
    maze[0][1] = ' ';
    maze[0][2] = ' ';
    maze[1][0] = ' ';
    maze[1][1] = ' ';
    maze[1][2] = ' ';
    maze[2][0] = ' ';
    maze[2][1] = ' ';
    maze[2][2] = 'X';
    maz[0] = 'F';
    maz[1] = ' ';
    maz[2] = ' ';
    maz[3] = ' ';
    maz[4] = ' ';
    maz[5] = ' ';
    maz[6] = ' ';
    maz[7] = ' ';
    maz[8] = 'X';
    ma[0][0] = 2;
    ma[0][1] = 0;
    ma[0][2] = 0;
    ma[1][0] = 0;
    ma[1][1] = 0;
    ma[1][2] = 0;
    ma[2][0] = 0;
    ma[2][1] = 0;
    ma[2][2] = -2;
    m[0] = 2;
    m[1] = 0;
    m[2] = 0;
    m[3] = 0;
    m[4] = 0;
    m[5] = 0;
    m[6] = 0;
    m[7] = 0;
    m[8] = -2;
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

  public static void main(String[] args) throws InterruptedException, IOException {
    set();
    Choices choices = new Choices();
    int choice = 0;
    int next = 0;
    Board board = new Board(maze.clone());
    Random rand = new Random(System.currentTimeMillis());
    int ran = rand.nextInt(9);
    double[] desire = new double[6];
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    ma[take[0]][take[1]] = 100;
    Reinforcement r = new Reinforcement(9, 4, 0.03, 0.7, ran, 500, Sensor.ARRAY_SENSOR, 10, choices);
    r.receive(ma);
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    for (int s = 0; s < 500; s++) {
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      ma[take[0]][take[1]] = m[con(take[0], take[1])];
      if (s == 249) {
        maze[0][0] = 'X';
        maze[2][2] = 'F';
        maz[0] = 'X';
        maz[8] = 'F';
        ma[0][0] = -2;
        ma[2][2] = 2;
        m[0] = -2;
        m[8] = 2;
        board.update_board(0, 0, maze[0][0]);
        board.update_board(2, 2, maze[2][2]);
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ma[take[0]][take[1]] = m[con(take[0], take[1])];
        ran = rand.nextInt(9);
        take = conv(ran);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      }
      choice = r.choose();
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
        r.train(-1, choice);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, choice);
        ran = rand.nextInt(9);
        take = conv(ran);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else {
        r.train(0, choice);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      }
      board.update_board(take[0], take[1], 'A');
    }
    /*

    space

    */
    maze[0][0] = 'F';
    maze[2][2] = 'X';
    maz[0] = 'F';
    maz[8] = 'X';
    ma[0][0] = 2;
    ma[2][2] = -2;
    m[0] = 2;
    m[8] = -2;
    board.update_board(0, 0, maze[0][0]);
    board.update_board(2, 2, maze[2][2]);
    board.update_board(take[0], take[1], maze[take[0]][take[1]]);
    ma[take[0]][take[1]] = m[con(take[0], take[1])];
    ran = rand.nextInt(9);
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    ma[take[0]][take[1]] = 1;
    r.receive(ma);
    r.set_c();
    for (int s = 0; s < 20; s++) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      if (maze[take[0]][take[1]] == 'F') {
        board.show_board();
        Thread.sleep(150);
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ma[take[0]][take[1]] = m[con(take[0], take[1])];
        ran = rand.nextInt(9);
        take = conv(ran);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
        System.out.println(r.ret_c());
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        board.update_board(take[0], take[1], 'A');
      }
      board.show_board();
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      ma[take[0]][take[1]] = m[con(take[0], take[1])];
      System.out.println(r.ret_c());
      choice = r.choose();
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
        r.train(-1, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else {
        r.train(0, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      }
      Thread.sleep(300);
    }
    /*

    space

    */
    maze[0][0] = 'X';
    maze[2][2] = 'F';
    maz[0] = 'X';
    maz[8] = 'F';
    ma[0][0] = -2;
    ma[2][2] = 2;
    m[0] = -2;
    m[8] = 2;
    board.update_board(0, 0, maze[0][0]);
    board.update_board(2, 2, maze[2][2]);
    board.update_board(take[0], take[1], maze[take[0]][take[1]]);
    ma[take[0]][take[1]] = m[con(take[0], take[1])];
    ran = rand.nextInt(9);
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    ma[take[0]][take[1]] = 1;
    r.receive(ma);
    r.set_c();
    for (int s = 0; s < 20; s++) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      if (maze[take[0]][take[1]] == 'F') {
        board.show_board();
        Thread.sleep(150);
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ma[take[0]][take[1]] = m[con(take[0], take[1])];
        ran = rand.nextInt(9);
        take = conv(ran);
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
        System.out.println(r.ret_c());
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        board.update_board(take[0], take[1], 'A');
      }
      board.show_board();
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      ma[take[0]][take[1]] = m[con(take[0], take[1])];
      System.out.println(r.ret_c());
      choice = r.choose();
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
        r.train(-1, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else if (maze[take[0]][take[1]] == 'F') {
        r.train(1, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      } else {
        r.train(0, choice);
        board.update_board(take[0], take[1], 'A');
        ma[take[0]][take[1]] = 1;
        r.receive(ma);
        r.set_c();
      }
      Thread.sleep(300);
    }
    double[][] q = r.ret_q().clone();
    for (int s = 0; s < q.length; s++) {
      System.out.println(s + "" + Arrays.toString(q[s]));
    }
  }

  private static class Choices implements Action_Definer {
    public int[] pos_actions (int length, int width) {
      int[] poses = new int[4];
      poses[0] = 0;
      poses[1] = 0;
      poses[2] = 0;
      poses[3] = 0;
      int l1 = take[0]-1;
      int l2 = take[0]+1;
      int w1 = take[1]-1;
      int w2 = take[1]+1;
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

    public double transpose (double val) {
      return 1.0 / (1.0 + Math.exp((-1 * val)));
    }
  }
}

/*if ((int)((sensor.respond()[0]*10 + sensor2.respond()[0]*10 + sensor3.respond()[0]*10)/32.8) >= 9) {
  r.set_c(8);
} else {
  r.set_c((int)((sensor.respond()[0]*10 + sensor2.respond()[0]*10 + sensor3.respond()[0]*10)/2.8));
}*/

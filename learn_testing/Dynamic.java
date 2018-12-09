package machine_learning;

import utilities.*;
import machine_learning.networks.*;
import machine_learning.neurons.*;
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
  }

  public static int[] pos_actions (int length, int width) {
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
    int choice = 0;
    int next = 0;
    int[] pose = new int [4];
    double[] desire = new double[4];
    double max_val = 0;
    ArrayList<Double> input = new ArrayList<Double>(4);
    ArrayList<Double> responses = new ArrayList<Double>(4);
    Board board = new Board(maze.clone());
    Random rand = new Random(System.currentTimeMillis());
    int ran = rand.nextInt(9);
    Network ai = new Network(5, 10, 4, 4, 0.11, 0.8, Neuron.MEMORY_LENGTH_2, 0);
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    for (int s = 0; s < 3000; s++) {
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      if (s >= 999) {
        maze[0][0] = 'X';
        maze[2][2] = 'F';
        maz[0] = 'X';
        maz[8] = 'F';
        board.update_board(0, 0, maze[0][0]);
        board.update_board(2, 2, maze[2][2]);
        /*board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ran = rand.nextInt(9);
        take = conv(ran);*/
        input.clear();
        input.add((double)con(take[0], take[1]));
        input.add(8.0);
        input.add(0.0);
      } else {
        input.clear();
        input.add((double)con(take[0], take[1]));
        input.add(0.0);
        input.add(8.0);
      }
      responses.clear();
      responses.addAll(ai.respond(input));
      pose = pos_actions(take[0], take[1]);
      max_val = -1;
      for (int t = 0; t < 4; t++) {
        if (responses.get(t) > max_val && pose[t] == 1) {
          max_val = responses.get(t);
          choice = t;
        }
      }
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
        for (int t = 0; t < 4; t++) {
          desire[t] = 1;
        }
        desire[choice] = -1;
        ai.think(10, desire, -10);
      } else if (maze[take[0]][take[1]] == 'F') {
        for (int t = 0; t < 4; t++) {
          desire[t] = -1;
        }
        desire[choice] = 1;
        ai.think(10, desire, 10);
        ran = rand.nextInt(9);
        take = conv(ran);
      } else {
        for (int t = 0; t < 4; t++) {
          desire[t] = 0.5;
        }
        ai.think(1, desire, 0);
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
    max_val = 0;
    board.update_board(0, 0, maze[0][0]);
    board.update_board(2, 2, maze[2][2]);
    board.update_board(take[0], take[1], maze[take[0]][take[1]]);
    ran = rand.nextInt(9);
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    for (int s = 0; s < 20; s++) {
      input.clear();
      input.add((double)con(take[0], take[1]));
      input.add(0.0);
      input.add(8.0);
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      if (maze[take[0]][take[1]] == 'F') {
        board.show_board();
        Thread.sleep(150);
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ran = rand.nextInt(9);
        take = conv(ran);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        board.update_board(take[0], take[1], 'A');
      }
      board.show_board();
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      responses.clear();
      responses.addAll(ai.respond(input));
      pose = pos_actions(take[0], take[1]);
      max_val = -1;
      for (int t = 0; t < 4; t++) {
        if (responses.get(t) > max_val && pose[t] == 1) {
          max_val = responses.get(t);
          choice = t;
        }
      }
      System.out.println(responses.toString());
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
        for (int t = 0; t < 4; t++) {
          desire[t] = 1;
        }
        desire[choice] = -1;
        ai.think(1, desire, -1);
        board.update_board(take[0], take[1], 'A');
      } else if (maze[take[0]][take[1]] == 'F') {
        for (int t = 0; t < 4; t++) {
          desire[t] = -1;
        }
        desire[choice] = 1;
        ai.think(1, desire, 1);
        ran = rand.nextInt(9);
        take = conv(ran);
        board.update_board(take[0], take[1], 'A');
      } else {
        for (int t = 0; t < 4; t++) {
          desire[t] = 1;
        }
        ai.think(1, desire, 0);
        board.update_board(take[0], take[1], 'A');
      }
      board.update_board(take[0], take[1], 'A');
      Thread.sleep(300);
    }
    /*

    space

    */
    maze[0][0] = 'X';
    maze[2][2] = 'F';
    maz[0] = 'X';
    maz[8] = 'F';
    board.update_board(0, 0, maze[0][0]);
    board.update_board(2, 2, maze[2][2]);
    board.update_board(take[0], take[1], maze[take[0]][take[1]]);
    ran = rand.nextInt(9);
    take = conv(ran);
    board.update_board(take[0], take[1], 'A');
    for (int s = 0; s < 20; s++) {
      input.clear();
      input.add((double)con(take[0], take[1]));
      input.add(8.0);
      input.add(0.0);
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      if (maze[take[0]][take[1]] == 'F') {
        board.show_board();
        Thread.sleep(150);
        board.update_board(take[0], take[1], maze[take[0]][take[1]]);
        ran = rand.nextInt(9);
        take = conv(ran);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        board.update_board(take[0], take[1], 'A');
      }
      board.show_board();
      board.update_board(take[0], take[1], maze[take[0]][take[1]]);
      responses.clear();
      responses.addAll(ai.respond(input));
      pose = pos_actions(take[0], take[1]);
      max_val = -1;
      for (int t = 0; t < 4; t++) {
        if (responses.get(t) > max_val && pose[t] == 1) {
          max_val = responses.get(t);
          choice = t;
        }
      }
      System.out.println(responses.toString());
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
        for (int t = 0; t < 4; t++) {
          desire[t] = 1;
        }
        desire[choice] = -1;
        ai.think(1, desire, -1);
        board.update_board(take[0], take[1], 'A');
      } else if (maze[take[0]][take[1]] == 'F') {
        for (int t = 0; t < 4; t++) {
          desire[t] = -1;
        }
        desire[choice] = 1;
        ai.think(1, desire, 1);
        ran = rand.nextInt(9);
        take = conv(ran);
        board.update_board(take[0], take[1], 'A');
      } else {
        for (int t = 0; t < 4; t++) {
          desire[t] = 1;
        }
        ai.think(1, desire, 0);
        board.update_board(take[0], take[1], 'A');
      }
      board.update_board(take[0], take[1], 'A');
      Thread.sleep(300);
    }
    ai.print_all_mem();
  }

  /*private static class Choices implements Action_Definer {
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
  }*/
}

/*if ((int)((sensor.respond()[0]*10 + sensor2.respond()[0]*10 + sensor3.respond()[0]*10)/32.8) >= 9) {
  r.set_c(8);
} else {
  r.set_c((int)((sensor.respond()[0]*10 + sensor2.respond()[0]*10 + sensor3.respond()[0]*10)/2.8));
}*/

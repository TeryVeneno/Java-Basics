package pong.board;
import pong.ball.Ball;
import pong.paddle.Paddle;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.*;
import utilities.Vectors;

public class Pong_Board extends Canvas {
  private static final long serialVersionUID = 1L;
  private BufferStrategy strategy;
  private Graphics2D g2d;
  private final int max_f_rate = 60;
  private int frame_rate;
  private Paddle player;
  private Paddle player2;
  private Ball ball;
  private int p1m = 0;
  private int p2m = 0;
  private int score1 = 0;
  private int score2 = 0;
  public boolean stopped = false;
  private boolean win1 = false;
  private boolean win2 = false;
  private boolean restart = false;

  public void init () {
    start_game();
  }

  public Pong_Board () {
    setPreferredSize(new Dimension(1000,1000));
    setIgnoreRepaint(true);
    Paddle_Handler ph = new Paddle_Handler();
    addKeyListener(ph);
  }

  public void render (int drawing) {
    do {
      if (strategy == null || strategy.contentsLost()) {
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        this.g2d = (Graphics2D) strategy.getDrawGraphics();
      }
      this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      this.g2d.setColor(Color.BLACK);
      this.g2d.fillRect(0,0, getWidth(), getHeight());

      player.draw(this.g2d);
      player2.draw(this.g2d);
      ball.draw(this.g2d);

      this.g2d.setColor(Color.WHITE);
      this.g2d.fillRect(getWidth()/2-10, 0, 10, getHeight());
      this.g2d.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      this.g2d.drawString("FPS: " + frame_rate, getWidth()/2,15);
      this.g2d.setFont(new Font("Times New Roman", Font.PLAIN, 30));
      this.g2d.drawString(Integer.toString(score1), getWidth()/4, 30);
      this.g2d.drawString(Integer.toString(score2), (getWidth()/4)*3, 30);
      if (drawing < 4) {
        this.g2d.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        this.g2d.drawString(Integer.toString(drawing+1), getWidth()/2-70, getHeight()/2);
        this.g2d.drawString(Integer.toString(drawing+1), getWidth()/2+50, getHeight()/2);
      } else if (drawing == 5) {
        this.g2d.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        if (win1) {
          this.g2d.drawString("Player1 wins.", getWidth()/2-300, getHeight()/2-80);
          this.g2d.drawString("Press Q to return to the main menu.", getWidth()/2-300, getHeight()/2-60);
          this.g2d.drawString("Press R to restart.", getWidth()/2-300, getHeight()/2-40);
        } else {
          this.g2d.drawString("Player2 wins.", getWidth()/2-300, getHeight()/2-80);
          this.g2d.drawString("Press Q to return to the main menu.", getWidth()/2-300, getHeight()/2-60);
          this.g2d.drawString("Press R to restart.", getWidth()/2-300, getHeight()/2-40);
        }
      }
      if (!strategy.contentsLost()) {
        strategy.show();
      }
    } while (strategy.contentsLost());
  }

  public void update () {
    ball.update();
    player.translate(p1m);
    player2.translate(p2m);
    if (player.get_y() < -7 || player.get_y() + 100 > getHeight()-12) {
      player.translate(-p1m);
    }
    if (player2.get_y() < -7 || player2.get_y() + 100 > getHeight()-12) {
      player2.translate(-p2m);
    }
    if (player.colliding(ball)) {
      ball.accelerate(ball.ret_mag(), player.calc_angle(ball));
      ball.set_x(player.get_x()+36);
    }
    if (player2.colliding(ball)) {
      ball.accelerate(ball.ret_mag(), player2.calc_angle(ball));
      ball.set_x(player2.get_x()-16);
    }
    if ((ball.get_x() < 5)) {
      ball.set_x(getWidth()/2);
      ball.set_y(getHeight()/2);
      ball.accelerate(ball.ret_mag(), 0);
      score2++;
    }
    if (ball.get_x() + ball.get_radius() > getWidth()+5) {
      ball.set_x(getWidth()/2);
      ball.set_y(getHeight()/2);
      ball.accelerate(ball.ret_mag(), 180);
      score1++;
    }
    if ((ball.get_y() + ball.get_radius() <= 10 || ball.get_y() + ball.get_radius() >= getHeight()-10)) {
      ball.accelerate(ball.ret_mag(), 360-ball.ret_dir());
    }
    if (score1 == 10) {
      win1 = true;
      score1 = 0;
      score2 = 0;
    }
    if (score2 == 10) {
      win2 = true;
      score1 = 0;
      score2 = 0;
    }
  }

  public void start_again () {
    restart = false;
    start_game();
  }

  public void start_game () {
    player = new Paddle(0, getHeight()/2, 100, 30, Color.GREEN,1);
    player2 = new Paddle(getWidth()-30, getHeight()/2, 100, 30, Color.RED,0);
    ball = new Ball(getWidth()/2, getHeight()/2, 10, Color.WHITE);
    score1 = 0;
    score2 = 0;
    ball.accelerate(12, 0);
    long old = System.currentTimeMillis();
    long elapsed = 0;
    long current = old;
    long total = 0;
    int frames = 0;
    render(4);
    while (total != 4) {
      if (current-old >= 1000 && total == 0) {
        render(0);
        total++;
      } else if (current-old >= 2000 && total == 1) {
        render(1);
        total++;
      } else if (current-old >= 3000 && total == 2) {
        render(2);
        total++;
      } else if (current-old >= 4000 && total == 3) {
        total++;
      }
      current = System.currentTimeMillis();
    }
    old = System.currentTimeMillis();
    current = old;
    total = 0;
    while (true) {
      current = System.currentTimeMillis();
      elapsed = (current - old);
      total += elapsed;
      if (total >= 1000) {
        frame_rate = frames;
        frames = 0;
        total = 0;
      }
      if (!win1 && !win2) {
        update();
        render(4);
      } else {
        render(5);
      }
      if (restart) {
        win1 = false;
        win2 = false;
        start_again();
      }
      try {
        Thread.sleep(fps_delay(max_f_rate));
      } catch(Exception e) {
        e.printStackTrace();
      }

      if (stopped == true) {
        break;
      }
      old = current;
      frames++;
    }
  }

  private int fps_delay (int desired_fps) {
    return 1000/desired_fps;
  }

  private class Paddle_Handler extends KeyAdapter {
    public void keyPressed (KeyEvent e) {
      if (e.getKeyCode() == e.VK_W) {
        p1m = -10;
      }
      if (e.getKeyCode() == e.VK_S) {
        p1m = 10;
      }
      if (e.getKeyCode() == e.VK_UP) {
        p2m = -10;
      }
      if (e.getKeyCode() == e.VK_DOWN) {
        p2m = 10;
      }
      if (e.getKeyCode() == e.VK_Q) {
        stopped = true;
      }
      if (e.getKeyCode() == e.VK_R) {
        restart = true;
      }
    }

    public void keyReleased (KeyEvent e) {
      if (e.getKeyCode() == e.VK_W) {
        p1m = 0;
      }
      if (e.getKeyCode() == e.VK_S) {
        p1m = 0;
      }
      if (e.getKeyCode() == e.VK_UP) {
        p2m = 0;
      }
      if (e.getKeyCode() == e.VK_DOWN) {
        p2m = 0;
      }
    }
  }
}

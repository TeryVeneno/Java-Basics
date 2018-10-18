package pong.paddle;
import pong.ball.Ball;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

public class Paddle {
  private int x;
  private int y;
  private int height;
  private int width;
  private Color paddle_color;
  private int orientation;
  Rectangle hit;

  public Paddle (int x, int y, int height, int width, Color c, int orient) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    paddle_color = c;
    orientation = orient;
    hit = new Rectangle(x,y,width,height);
  }

  public boolean colliding (Ball b) {
    if (hit.intersects(b.get_hit())) {
      return true;
    }
    return false;
  }

  public int calc_angle (Ball b) {
    Rectangle temp = hit.intersection(b.get_hit());
    if (orientation == 1) {
      return (int)((((y + height/2) - temp.getY())/(height/2)) * 75);
    } else {
      return 180-(int)((((y + height/2) - temp.getY())/(height/2)) * 75);
    }
  }

  public void draw (Graphics2D g) {
    g.setColor(paddle_color);
    g.fillRoundRect(x, y, width, height, (int)Math.sqrt(width), (int)Math.sqrt(height));
  }

  public int get_x () {
    return x;
  }

  public int get_y () {
    return y;
  }

  public void set_x (int x) {
  this.x = x;
  hit.setLocation(x,y);
  }

  public void set_y (int y) {
    this.y = y;
    hit.setLocation(x,y);
  }

  public void translate (int cy) {
    y += cy;
    hit.setLocation(x,y);
  }
}

package pong.ball;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import utilities.Vectors;

public class Ball {
  private double x;
  private double y;
  private double radius;
  private Color c;
  private Vectors velocity;
  private Rectangle hit;

  public Ball (double x, double y, double radius, Color c) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    velocity = new Vectors(0, 0);
    this.c = c;
    hit = new Rectangle((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
  }

  public double get_radius () {
    return radius;
  }

  public void draw (Graphics2D g) {
    g.setColor(c);
    g.fillOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
  }

  public double get_x () {
    return x;
  }

  public double get_y () {
    return y;
  }

  public void accelerate (double mag_change, double direction) {
    velocity.set_mag(mag_change);
    velocity.set_dir(direction);
  }

  public Vectors get_velocity () {
    return velocity;
  }

  public void update() {
    x = Vectors.x_update(velocity, x);
    y = Vectors.y_update(velocity, y);
    hit.setLocation((int)x,(int)y);
  }

  public double ret_dir () {
    return velocity.get_dir();
  }

  public double ret_mag () {
    return velocity.get_mag();
  }

  public void set_x (double x) {
    this.x = x;
    hit.setLocation((int)x,(int)y);
  }

  public void set_y (double y) {
    this.y = y;
    hit.setLocation((int)x,(int)y);
  }

  public Rectangle get_hit () {
    return hit;
  }
}

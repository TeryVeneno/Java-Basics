package render_engine;
import render_engine.polygons.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class Viewer3D extends KeyAdapter {
  static double heading_val = 180;
  static double pitch_val = 0;
  static Vertex3D camera_rotation = new Vertex3D(1, 1, 1);
  static Vertex3D camera = new Vertex3D(0, 0, 100);
  static final int DEPTH_OF_FIELD = 800;
  static Vertex3D xyz = new Vertex3D(0, 0, 0);
  static int already_drawn = 0;

  public void keyPressed (KeyEvent e) {
    if (e.getKeyCode() == e.VK_UP) {
      pitch_val -= 2;
    }
    if (e.getKeyCode() == e.VK_DOWN) {
      pitch_val += 2;
    }
    if (e.getKeyCode() == e.VK_RIGHT) {
      heading_val -= 2;
    }
    if (e.getKeyCode() == e.VK_LEFT) {
      heading_val += 2;
    }
    if (e.getKeyCode() == e.VK_W) {
      camera.z -= 10;
    }
    if (e.getKeyCode() == e.VK_S) {
      camera.z += 10;
    }
    if (e.getKeyCode() == e.VK_A) {
      camera.x -= 5;
    }
    if (e.getKeyCode() == e.VK_D) {
      camera.x += 5;
    }
    if (e.getKeyCode() == e.VK_Q) {
      camera.y -= 5;
    }
    if (e.getKeyCode() == e.VK_E) {
      camera.y += 5;
    }
    if (e.getKeyCode() == e.VK_1) {
       xyz.x = -5;
    }
    if (e.getKeyCode() == e.VK_2) {
      xyz.x = 5;
    }
    if (e.getKeyCode() == e.VK_3) {
      xyz.y = -5;
    }
    if (e.getKeyCode() == e.VK_4) {
      xyz.y = 5;
    }
    if (e.getKeyCode() == e.VK_5) {
      xyz.z = -5;
    }
    if (e.getKeyCode() == e.VK_6) {
      xyz.z = 5;
    }
    if (e.getKeyCode() == e.VK_7) {
      camera_rotation.x += 1;
    }
    if (e.getKeyCode() == e.VK_8) {
      camera_rotation.x -= 1;
    }
    if (e.getKeyCode() == e.VK_9) {
      camera_rotation.y += 1;
    }
    if (e.getKeyCode() == e.VK_0) {
      camera_rotation.y -= 1;
    }
    if (e.getKeyCode() == e.VK_MINUS) {
      camera_rotation.z += 1;
    }
    if (e.getKeyCode() == e.VK_EQUALS) {
      camera_rotation.z -= 1;
    }
  }

  public void keyReleased (KeyEvent e) {
    if (e.getKeyCode() == e.VK_1) {
       xyz.x = 0;
    }
    if (e.getKeyCode() == e.VK_2) {
      xyz.x = 0;
    }
    if (e.getKeyCode() == e.VK_3) {
      xyz.y = 0;
    }
    if (e.getKeyCode() == e.VK_4) {
      xyz.y = 0;
    }
    if (e.getKeyCode() == e.VK_5) {
      xyz.z = 0;
    }
    if (e.getKeyCode() == e.VK_6) {
      xyz.z = 0;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("3D");
    Container pane = frame.getContentPane();
    pane.setLayout(new BorderLayout());
    ArrayList<Triangle3D> triangle = new ArrayList<Triangle3D>(4);
    triangle.add(new Triangle3D(new Vertex3D(100, 100, 100), new Vertex3D(-100, -100, 100), new Vertex3D(-100, 100, -100), Color.WHITE));
    triangle.add(new Triangle3D(new Vertex3D(100, 100, 100), new Vertex3D(-100, -100, 100), new Vertex3D(100, -100, -100), Color.GREEN));
    triangle.add(new Triangle3D(new Vertex3D(-100, 100, -100), new Vertex3D(100, -100, -100), new Vertex3D(100, 100, 100), Color.RED));
    triangle.add(new Triangle3D(new Vertex3D(-100, 100, -100), new Vertex3D(100, -100, -100), new Vertex3D(-100, -100, 100), Color.CYAN));

    ArrayList<Triangle3D> tringle = new ArrayList<Triangle3D>(12);
    /*tringle.add(new Triangle3D(new Vertex3D(300 , 300, 300), new Vertex3D(100, 100, 300), new Vertex3D(100, 300, 100), Color.WHITE));
    tringle.add(new Triangle3D(new Vertex3D(300, 300, 300), new Vertex3D(100, 100, 300), new Vertex3D(300, 100, 100), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(100, 300, 100), new Vertex3D(300, 100, 100), new Vertex3D(300, 300, 300), Color.RED));
    tringle.add(new Triangle3D(new Vertex3D(100, 300, 100), new Vertex3D(300, 100, 100), new Vertex3D(100, 100, 300), Color.CYAN));*/

    tringle.add(new Triangle3D(new Vertex3D(300 , 300, 10), new Vertex3D(400, 300, 10), new Vertex3D(300, 400, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300, 400, 10), new Vertex3D(400, 400, 10), new Vertex3D(400, 300, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300 , 300, 110), new Vertex3D(400, 300, 110), new Vertex3D(300, 400, 110), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300, 400, 110), new Vertex3D(400, 400, 110), new Vertex3D(400, 300, 110), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300 , 300, 110), new Vertex3D(300, 300, 10), new Vertex3D(300, 400, 110), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300, 400, 110), new Vertex3D(300, 400, 10), new Vertex3D(300, 300, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(400 , 300, 110), new Vertex3D(400, 300, 10), new Vertex3D(400, 400, 110), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(400, 400, 110), new Vertex3D(400, 400, 10), new Vertex3D(400, 300, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300 , 400, 110), new Vertex3D(400, 400, 110), new Vertex3D(300, 400, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300, 400, 10), new Vertex3D(400, 400, 10), new Vertex3D(400, 400, 110), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300 , 300, 110), new Vertex3D(400, 300, 110), new Vertex3D(300, 300, 10), Color.GREEN));
    tringle.add(new Triangle3D(new Vertex3D(300, 300, 10), new Vertex3D(400, 300, 10), new Vertex3D(400, 300, 110), Color.GREEN));

    ArrayList<Triangle3D> triangles = triangle;
    ArrayList<Triangle3D> tringles = tringle;

    JPanel render_panel = new JPanel() {
      private static final long serialVersionUID = 1l;
      Vertex3D light = new Vertex3D(0, 0, 1);
      public void paintComponent (Graphics g) {
        Mesh3D tetrahedron = new Mesh3D(triangles, getWidth()*getHeight(), 800);
        Mesh3D cube = new Mesh3D(tringles, getWidth()*getHeight(), 800);
        cube.translate(xyz);
        Vertex3D cube_pos = new Vertex3D(0, 0, 0);
        Vertex3D triangle_pos = new Vertex3D(0, 0, 0);
        for (Triangle3D t : tringles) {
          cube_pos.add(t.p1);
          cube_pos.add(t.p2);
          cube_pos.add(t.p3);
        }
        cube_pos.divide(36);
        for (Triangle3D t : triangle) {
          triangle_pos.add(t.p1);
          triangle_pos.add(t.p2);
          triangle_pos.add(t.p3);
        }
        triangle_pos.divide(36);
        Vertex3D use_cam = new Vertex3D(camera);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.WHITE);
        g2d.drawString("Position of Cube: "+" X: "+Double.toString(cube_pos.x)+", Y: "+Double.toString(cube_pos.y)+", Z: "+Double.toString(cube_pos.z), 0, 10);
        g2d.drawString("Position of Tetrahedron: "+" X: "+Double.toString(triangle_pos.x)+", Y: "+Double.toString(triangle_pos.y)+", Z: "+Double.toString(triangle_pos.z), 0, 30);
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        tetrahedron.change_both(pitch_val, heading_val);
        cube.change_both(pitch_val, heading_val);
        Matrix3D camera_angle = Matrix3D.multiply(Matrix3D.x_rotate(camera_rotation.x), Matrix3D.multiply(Matrix3D.y_rotate(camera_rotation.y), Matrix3D.z_rotate(camera_rotation.z)));
        if (triangle_pos.z - camera.z > cube_pos.z - camera.z) {
          image = cube.render(use_cam, light, image, camera_angle);
          image = tetrahedron.render(use_cam, light, image, camera_angle);
        } else {
          image = tetrahedron.render(use_cam, light, image, camera_angle);
          image = cube.render(use_cam, light, image, camera_angle);
        }
        g2d.drawImage(image, 0, 0, null);
      }
    };
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    Viewer3D control = new Viewer3D();
    frame.addKeyListener(control);
    frame.setVisible(true);
    pane.add(render_panel, BorderLayout.CENTER);
    while (true) {
      frame.repaint();
      Thread.sleep(17);
    }
  }
}

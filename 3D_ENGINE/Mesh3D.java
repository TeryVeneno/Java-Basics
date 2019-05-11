package render_engine.polygons;

import render_engine.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.*;

public class Mesh3D {
  private static final long serialVersionUID = 1l;

  ArrayList<Triangle3D> mesh;
  ArrayList<Triangle3D> original;
  double[] z_buffer;
  double pitch_val = 0;
  double heading_val = 0;
  int DOF;
  Vertex3D scale;
  double z_post;

  public Mesh3D (ArrayList<Triangle3D> mesh_data, int size_buffer, int dof, Vertex3D scale) {
    mesh = new ArrayList<Triangle3D>(mesh_data.size());
    original = new ArrayList<Triangle3D>(mesh_data.size());
    for (Triangle3D t : mesh_data) {
      mesh.add(t.clone());
      original.add(t.clone());
    }
    z_buffer = new double[size_buffer];
    for (int z = 0; z < size_buffer; z++) {
      z_buffer[z] = Double.NEGATIVE_INFINITY;
    }
    DOF = dof;
    this.scale = new Vertex3D(scale);

    for (Triangle3D t : mesh) {
      t.p1.multiply(scale);
      t.p2.multiply(scale);
      t.p3.multiply(scale);
    }
  }

  public BufferedImage render (Vertex3D camera, Vertex3D light, BufferedImage environment, Matrix3D camera_rotation) {
    for (Triangle3D t : mesh) {
      Vertex3D p1 = camera_rotation.transform(t.p1);
      Vertex3D p2 = camera_rotation.transform(t.p2);
      Vertex3D p3 = camera_rotation.transform(t.p3);
      p1.subtract(camera);
      p2.subtract(camera);
      p3.subtract(camera);
      p1.x = (DOF * p1.x / -p1.z) + environment.getWidth()/2;
      p1.y = environment.getHeight()/2 - (DOF * p1.y / -p1.z);
      p2.x = (DOF * p2.x / -p2.z) + environment.getWidth()/2;
      p2.y = environment.getHeight()/2 - (DOF * p2.y / -p2.z);
      p3.x = (DOF * p3.x / -p3.z) + environment.getWidth()/2;
      p3.y = environment.getHeight()/2 - (DOF * p3.y / -p3.z);

      Vertex3D ab = new Vertex3D(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
      Vertex3D ac = new Vertex3D(p3.x - p1.x, p3.y - p1.y, p3.z - p1.z);
      Vertex3D norm = new Vertex3D (
        ab.y * ac.z - ab.z * ac.y,
        ab.z * ac.x - ab.x * ac.z,
        ab.x * ac.y - ab.y * ac.x
      );
      double normal_length = Math.sqrt(Math.pow(norm.x,2)+Math.pow(norm.y,2)+Math.pow(norm.z, 2));
      norm.x /= normal_length;
      norm.y /= normal_length;
      norm.z /= normal_length;
      int vals = 0;
      if (norm.x*light.x != 0) {
        vals += 1;
      }
      if (norm.y*light.y != 0) {
        vals += 1;
      }
      if (norm.z*light.z != 0) {
        vals += 1;
      }
      if (vals == 0) {
        vals = 1;
      }
      double angle_cos = (Math.abs(norm.x)*light.x+Math.abs(norm.y)*light.y+Math.abs(norm.z)*light.z)/vals;

      int min_x = (int)Math.max(0, Math.ceil(Math.min(p1.x, Math.min(p2.x, p3.x))));
      int max_x = (int)Math.min(environment.getWidth()-1, Math.floor(Math.max(p1.x, Math.max(p2.x, p3.x))));
      int min_y = (int)Math.max(0, Math.ceil(Math.min(p1.y, Math.min(p2.y, p3.y))));
      int max_y = (int)Math.min(environment.getHeight()-1, Math.floor(Math.max(p1.y, Math.max(p2.y, p3.y))));
      double triangle_area = (p1.y-p3.y)*(p2.x-p3.x)+(p2.y-p3.y)*(p3.x-p1.x);
      for (int y = min_y; y <= max_y; y++) {
        for (int x = min_x; x <= max_x; x++) {
          double b1 = ((y-p3.y)*(p2.x-p3.x)+(p2.y-p3.y)*(p3.x-x))/triangle_area;
          double b2 = ((y-p1.y)*(p3.x-p1.x)+(p3.y-p1.y)*(p1.x-x))/triangle_area;
          double b3 = ((y-p2.y)*(p1.x-p2.x)+(p1.y-p2.y)*(p2.x-x))/triangle_area;
          if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
            double depth = b1 * p1.z + b2 * p2.z + b3 * p3.z;
            int z_index = y * environment.getWidth() + x;
            if (z_buffer[z_index] < depth) {
              environment.setRGB(x, y, Mesh3D.getShade(t.color, angle_cos).getRGB());
              z_buffer[z_index] = depth;
            }
          }
        }
      }
    }
    return environment;
  }

  public BufferedImage wire_frame_render (Vertex3D camera, BufferedImage environment, Matrix3D camera_rotation) {
    for (Triangle3D t : mesh) {
      Vertex3D p1 = camera_rotation.transform(t.p1);
      Vertex3D p2 = camera_rotation.transform(t.p2);
      Vertex3D p3 = camera_rotation.transform(t.p3);
      p1.subtract(camera);
      p2.subtract(camera);
      p3.subtract(camera);
      p1.x = (DOF * p1.x / -p1.z) + environment.getWidth()/2;
      p1.y = environment.getHeight()/2 - (DOF * p1.y / -p1.z);
      p2.x = (DOF * p2.x / -p2.z) + environment.getWidth()/2;
      p2.y = environment.getHeight()/2 - (DOF * p2.y / -p2.z);
      p3.x = (DOF * p3.x / -p3.z) + environment.getWidth()/2;
      p3.y = environment.getHeight()/2 - (DOF * p3.y / -p3.z);

      Graphics2D g2d = environment.createGraphics();
      g2d.setColor(Color.GREEN);
      Path2D path = new Path2D.Double();
      path.moveTo(p1.x, p1.y);
      path.lineTo(p2.x, p2.y);
      path.lineTo(p3.x, p3.y);
      path.closePath();
      g2d.draw(path);
    }
    return environment;
  }

  public static Color getShade (Color color, double shade) {
    double red_linear = Math.pow(color.getRed(), 2.4) * shade;
    double green_linear = Math.pow(color.getGreen(), 2.4) * shade;
    double blue_linear = Math.pow(color.getBlue(), 2.4) * shade;

    int red = (int) Math.pow(red_linear, 1/2.4);
    int green = (int) Math.pow(green_linear, 1/2.4);
    int blue = (int) Math.pow(blue_linear, 1/2.4);

    return new Color(red, green, blue);
  }

  public static ArrayList<Triangle3D> inflate (ArrayList<Triangle3D> tringles) {
    ArrayList<Triangle3D> result = new ArrayList<Triangle3D>();
    for (Triangle3D t : tringles) {
      Vertex3D m1 = new Vertex3D((t.p1.x+t.p2.x)/130, (t.p1.y+t.p2.y)/130, (t.p1.z+t.p2.z)/130);
      Vertex3D m2 = new Vertex3D((t.p2.x+t.p3.x)/130, (t.p2.y+t.p3.y)/130, (t.p2.z+t.p3.z)/130);
      Vertex3D m3 = new Vertex3D((t.p1.x+t.p3.x)/130, (t.p1.y+t.p3.y)/130, (t.p1.z+t.p3.z)/130);
      result.add(new Triangle3D(t.p1, m1, m3, t.color));
      result.add(new Triangle3D(t.p2, m1, m2, t.color));
      result.add(new Triangle3D(t.p3, m2, m3, t.color));
      result.add(new Triangle3D(m1, m2, m3, t.color));
    }
    for (Triangle3D t : result) {
      for (Vertex3D v : new Vertex3D[] {t.p1, t.p2, t.p3}) {
        double l = Math.sqrt(Math.pow(v.x,2)+Math.pow(v.y,2)+Math.pow(v.z, 2)) / Math.sqrt(700000);
        v.x /= l;
        v.y /= l;
        v.z /= l;
      }
    }
    return result;
  }

  public void change_dof (int dof) {
    DOF = dof;
  }

  public void translate (Vertex3D xyz) {
    for (int s = 0; s < mesh.size(); s++) {
      mesh.get(s).p1.add(xyz);
      mesh.get(s).p2.add(xyz);
      mesh.get(s).p3.add(xyz);
      original.get(s).p1.add(xyz);
      original.get(s).p2.add(xyz);
      original.get(s).p3.add(xyz);
    }
  }

  public void change_size (Vertex3D scale) {
    mesh = new ArrayList<Triangle3D>(original);
    for (Triangle3D t : mesh) {
      t.p1.multiply(scale);
      t.p2.multiply(scale);
      t.p3.multiply(scale);
    }
  }

  public Vertex3D ret_scale () {
    return scale;
  }

  public double ret_post_z (Vertex3D camera, Matrix3D camera_rotation) {
    for (Triangle3D t : mesh) {
      Vertex3D p1 = camera_rotation.transform(t.p1);
      Vertex3D p2 = camera_rotation.transform(t.p2);
      Vertex3D p3 = camera_rotation.transform(t.p3);
      p1.subtract(camera);
      p2.subtract(camera);
      p3.subtract(camera);
      z_post += p1.z;
      z_post += p2.z;
      z_post += p3.z;
    }
    z_post /= mesh.size()*3;
    return z_post;
  }
}
//dof = DEPTH OF FIELD

package render_engine;

import render_engine.polygons.*;

public class Matrix3D {
  public double[] values;

  public Matrix3D (double[] vals) {
    values = vals;
  }

  public Matrix3D multiply (Matrix3D other) {
    double[] result = new double[9];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        for (int s = 0; s < 3; s++) {
          result[r * 3 + c] += this.values[r * 3 + s] * other.values[s * 3 + c];
        }
      }
    }
    return new Matrix3D(result);
  }

  public static Matrix3D multiply (Matrix3D one, Matrix3D two) {
    double[] result = new double[9];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        for (int s = 0; s < 3; s++) {
          result[r * 3 + c] += one.values[r * 3 + s] * two.values[s * 3 + c];
        }
      }
    }
    return new Matrix3D(result);
  }

  public Vertex3D transform (Vertex3D input) {
    return new Vertex3D (input.x * values[0] + input.y * values[3] + input.z * values[6],
                        input.x * values[1] + input.y * values[4] + input.z * values[7],
                        input.x * values[2] + input.y * values[5] + input.z * values[8]);
  }

  public static Matrix3D x_rotate (double pitch) {
    pitch = Math.toRadians(pitch);
    Matrix3D p_transform = new Matrix3D(new double[]{
      1, 0, 0,
      0, Math.cos(pitch), Math.sin(pitch),
      0, -Math.sin(pitch), Math.cos(pitch)
    });
    return p_transform;
  }

  public static Matrix3D y_rotate (double yaw) {
    yaw = Math.toRadians(yaw);
    Matrix3D y_transform = new Matrix3D(new double[]{
      Math.cos(yaw), 0, -Math.sin(yaw),
      0, 1, 0,
      Math.sin(yaw), 0, Math.cos(yaw)
    });
    return y_transform;
  }

  public static Matrix3D z_rotate (double roll) {
    roll = Math.toRadians(roll);
    Matrix3D r_transform = new Matrix3D(new double[]{
      Math.cos(roll), Math.sin(roll), 0,
      -Math.sin(roll), Math.cos(roll), 0,
      0, 0, 1,
    });
    return r_transform;
  }

  public void negate () {
    for (int s = 0; s < values.length; s++) {
      values[s] = -values[s];
    }
  }
}

package render_engine.polygons;

public class Vertex3D {
  public double x, y, z;

  public Vertex3D (double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vertex3D (Vertex3D v) {
    x = v.x;
    y = v.y;
    z = v.z;
  }

  public void subtract (Vertex3D v) {
    this.x -= v.x;
    this.y -= v.y;
    this.z -= v.z;
  }

  public void subtract (int val) {
    this.x -= val;
    this.y -= val;
    this.z -= val;
  }

  public void add (Vertex3D v) {
    this.x += v.x;
    this.y += v.y;
    this.z += v.z;
  }

  public void add (int val) {
  this.x += val;
  this.y += val;
  this.z += val;
  }

  public void divide (int val) {
    this.x /= val;
    this.y /= val;
    this.z /= val;
  }

  public void divide (Vertex3D v) {
    this.x /= v.x;
    this.y /= v.y;
    this.z /= v.z;
  }

  public void multiply (Vertex3D v) {
    this.x *= v.x;
    this.y *= v.y;
    this.z *= v.z;
  }

  public void multiply (int val) {
    this.x *= val;
    this.y *= val;
    this.z *= val;
  }

  public Vertex3D addition (Vertex3D v) {
    return new Vertex3D(this.x + v.x, this.y + v.y, this.z + v.z);
  }

  public Vertex3D subtraction (Vertex3D v) {
    return new Vertex3D(this.x - v.x, this.y - v.y, this.z - v.z);
  }

  public Vertex3D multiplication (Vertex3D v) {
    return new Vertex3D(this.x * v.x, this.y * v.y, this.z * v.z);
  }

  public Vertex3D division (Vertex3D v) {
    return new Vertex3D(this.x / v.x, this.y / v.y, this.z / v.z);
  }

  public Vertex3D addition (int val) {
    return new Vertex3D(this.x + val, this.y + val, this.z + val);
  }

  public Vertex3D subtraction (int val) {
    return new Vertex3D(this.x - val, this.y - val, this.z - val);
  }

  public Vertex3D multiplication (int val) {
    return new Vertex3D(this.x * val, this.y * val, this.z * val);
  }

  public Vertex3D division (int val) {
    return new Vertex3D(this.x / val, this.y / val, this.z / val);
  }

  public void positize () {
    x = Math.sqrt(Math.pow(x,2));
    y = Math.sqrt(Math.pow(y,2));
    z = Math.sqrt(Math.pow(z,2));
  }

  public void negatize () {
    x = -Math.sqrt(Math.pow(x,2));
    y = -Math.sqrt(Math.pow(y,2));
    z = -Math.sqrt(Math.pow(z,2));
  }

  public String toString () {
    return x + ", " + y + ", " + z;
  }
}

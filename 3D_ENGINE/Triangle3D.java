package render_engine.polygons;
import java.awt.*;

public class Triangle3D {
  public Vertex3D p1, p2, p3;
  public Color color;

  public Triangle3D (Vertex3D v1, Vertex3D v2, Vertex3D v3, Color color) {
    p1 = new Vertex3D(v1);
    p2 = new Vertex3D(v2);
    p3 = new Vertex3D(v3);
    this.color = color;
  }

  public String toString () {
    return "[ " + p1 + " ]" + ", " + "[ " + p2 + " ]" + "[ " + p3 + " ]";
  }

  public Triangle3D clone () {
    return new Triangle3D(p1,p2,p3,color);
  }
}

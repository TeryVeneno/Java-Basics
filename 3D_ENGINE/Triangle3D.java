package render_engine.polygons;
import java.awt.*;

public class Triangle3D {
  public Vertex3D p1, p2, p3;
  public Color color;

  public Triangle3D (Vertex3D v1, Vertex3D v2, Vertex3D v3, Color color) {
    p1 = v1;
    p2 = v2;
    p3 = v3;
    this.color = color;
  }
}

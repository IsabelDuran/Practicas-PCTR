public class Punto {
  double x, y; // Variables de instancia

  public Punto(double x, double y) { // El constructor
    this.x= x; this.y= y;
  }
  public void moverEn(double dx, double dy) { // Un método
    this.x+= dx; this.y+= dy;
  }
  public String toString() { // Otro método
    return "("+this.x+","+this.y+")";
  }
}

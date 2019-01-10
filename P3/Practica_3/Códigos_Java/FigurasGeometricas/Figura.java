/**
 * Clase que modela las figuras geométricas.
 *
 * @author Natalia Partera
 * @version 3.0
 */

interface CalculosFigura {
  void CalcularArea();
  void CalcularPerimetro();
}

public class Figura {
  //Atributos
  protected double perimetro = 0.0;
  protected double area = 0.0;

  //Constructor nulo
  public Figura() {}

  //Métodos observadores
  public double Area() {return area;}

  public double Perimetro() {return perimetro;}

  public void VerDatos() {
    System.out.println("La figura mide " + perimetro + " metros de perímetro.");
    System.out.println("La figura tiene un área de " + area + " m^2.");
  }
/*
  //Métodos modificadores
  protected void CalcularArea() {}

  protected void CalcularPerimetro() {}
*/
}

/**
 * Clase que modela cuadrados.
 *
 * @author Natalia Partera
 * @version 2.0
 */

public class Cuadrado extends Rectangulo {
  //Constructor nulo
  public Cuadrado() {}
  //Constructor
  public Cuadrado(double lad) {
    super(lad, lad);
  }

  //Métodos observadores
  public double Lado() {
    return Base();
  }

  public void VerDatos() {
    System.out.println("Un lado cualquiera del cuadrado mide " + Lado() + " metros.");
    System.out.println("El cuadrado mide " + perimetro + " metros de perímetro.");
    System.out.println("Y tiene un área de " + area + " m^2.");
  }
}

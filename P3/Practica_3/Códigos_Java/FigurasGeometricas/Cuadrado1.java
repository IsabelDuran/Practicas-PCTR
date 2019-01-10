/**
 * Clase que modela cuadrados.
 *
 * @author Natalia Partera
 * @version 1.0
 */

public class Cuadrado extends Figura {
  //Atributos
  private double lado;

  //Constructor nulo
  public Cuadrado() {}
  //Constructor
  public Cuadrado(double lad) {
    super();
    lado = lad;
  }

  //Métodos observadores
  public double Lado() {
    return lado;
  }

  //Métodos modificadores
  public void CalcularArea() {
    area = lado * lado;
  }

  public void CalcularPerimetro() {
    perimetro = 4 * lado;
  }

}

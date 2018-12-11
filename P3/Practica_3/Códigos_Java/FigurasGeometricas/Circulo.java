/**
 * Clase que modela círculos.
 *
 * @author Natalia Partera
 * @version 3.0
 */

public class Circulo extends Figura implements CalculosFigura {
  //Atributos
  private double PI = 3.1416;
  private double radio;

  //Constructor nulo
  public Circulo() {}
  //Constructor
  public Circulo(double rad) {
    super();
    radio = rad;
  }

  //Métodos observadores
  public double Radio() {
    return radio;
  }

  public void VerDatos() {
    System.out.println("El radio del círculo mide " + radio + " metros.");
    System.out.println("El círculo mide " + perimetro + " metros de perímetro.");
    System.out.println("Y tiene un área de " + area + " m^2.");
  }

  //Métodos modificadores
  public void CalcularArea() {
    area = PI * radio * radio;
  }

  public void CalcularPerimetro() {
    perimetro = 2 * PI * radio;
  }

}

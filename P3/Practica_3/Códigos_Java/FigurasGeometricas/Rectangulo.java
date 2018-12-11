/**
 * Clase que modela rectángulos.
 *
 * @author Natalia Partera
 * @version 3.0
 */

public class Rectangulo extends Figura implements CalculosFigura {
  //Atributos
  private double base, altura;

  //Constructor nulo
  public Rectangulo() {}
  //Constructor
  public Rectangulo(double b, double a) {
    super();
    base = b;
    altura = a;
  }

  //Métodos observadores
  public double Base() {
    return base;
  }

  public double Altura() {
    return altura;
  }

  public void VerDatos() {
    System.out.println("La base del rectangulo mide " + base + " metros y la altura, " + altura + " metros.");
    System.out.println("El rectángulo mide " + perimetro + " metros de perímetro.");
    System.out.println("Y tiene un área de " + area + " m^2.");
  }

  //Métodos modificadores
  public void CalcularArea() {
    area = base * altura;
  }

  public void CalcularPerimetro() {
    perimetro = 2 * base + 2 * altura;
  }

}

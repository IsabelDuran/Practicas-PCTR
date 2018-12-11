/**
 * Clase que modela polígonos regulares.
 *
 * @author Natalia Partera
 * @version 3.0
 */

public class Poligono extends Figura implements CalculosFigura{
  //Atributos
  private double apotema, lado;
  private int numLados;

  //Constructor nulo
  public Poligono() {}
  //Constructores
  public Poligono(int nLados) {
    super();
    numLados = nLados;
  }

  public Poligono(int nLados, double lad) {
    super();
    numLados = nLados;
    lado = lad;
  }

  public Poligono(int nLados, double lad, double apot) {
    super();
    numLados = nLados;
    lado = lad;
    apotema = apot;
  }

  //Métodos observadores
  public int NumLados() {
    return numLados;
  }

  public double Apotema() {
    return apotema;
  }

  public double Lado() {
    return lado;
  }

  public void VerDatos() {
    System.out.println("El polígono tiene " + numLados + ". Cada lado mide " + lado + " metros y su apotema mide " + apotema + " metros.");
    System.out.println("El polígono mide " + perimetro + " metros de perímetro.");
    System.out.println("Y tiene un área de " + area + " m^2.");
  }

  //Métodos modificadores
  public void Apotema(double apot) {
    apotema = apot;
  }

  public void Lado (double lad) {
    lado = lad;
  }

  public void CalcularArea() {
    if(Perimetro() == 0)
      CalcularPerimetro();
    if(apotema == 0)
      System.out.println("No se ha podido calcular el área: es necesario inicializar el valor de la apotema.");
    else
      area = (Perimetro() * apotema) / 2;
  }

  public void CalcularPerimetro() {
    perimetro = numLados * lado;
  }

}

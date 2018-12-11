/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 1.0
 */

import java.util.*;

public class UsaSubFiguras 
{
  public static void main (String args[]) 
  {
    Scanner teclado = new Scanner(System.in);
    Rectangulo rectangulo;
    Circulo circulo;
    Poligono hexagono;
    Cuadrado cuadrado;

    System.out.println("Inicializando rectangulo ...");
    System.out.print("Introduzca la base del rectángulo:");
    double base = teclado.nextDouble();
    System.out.print("Introduzca la altura del rectángulo:");
    double altura = teclado.nextDouble();
    rectangulo = new Rectangulo(base, altura);
 
    System.out.println("Inicializando círculo ...");
    System.out.print("Introduzca el radio del círculo:");
    double radio = teclado.nextDouble();
    circulo = new Circulo(radio);

    System.out.println("Inicializando hexágono ...");
    System.out.print("Introduzca la longitud de un lado del hexágono:");
    double lado = teclado.nextDouble();
    System.out.print("Introduzca el valor de la apotema del hexágono:");
    double apotema = teclado.nextDouble();
    hexagono = new Poligono(6, lado, apotema);

    System.out.println("Inicializando cuadrado ...");
    System.out.print("Introduzca el lado del cuadrado:");
    double ladoC = teclado.nextDouble();
    cuadrado = new Cuadrado(ladoC);

    System.out.println("Ahora calculamos y mostramos el área y el perímetro de cada figura.");
    rectangulo.CalcularPerimetro();
    rectangulo.CalcularArea();
    circulo.CalcularPerimetro();
    circulo.CalcularArea();
    hexagono.CalcularPerimetro();
    hexagono.CalcularArea();
    cuadrado.CalcularPerimetro();
    cuadrado.CalcularArea();

    System.out.println("Rectangulo: perímetro = " + rectangulo.Perimetro() + "; área = " + rectangulo.Area());
    System.out.println("Círculo: perímetro = " + circulo.Perimetro() + "; área = " + circulo.Area());
    System.out.println("Hexágono: perímetro = " + hexagono.Perimetro() + "; área = " + hexagono.Area());
    System.out.println("Cuadrado: perímetro = " + cuadrado.Perimetro() + "; área = " + cuadrado.Area());
  }
}

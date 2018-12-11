/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 1.0
 */

import java.util.*;

public class UsaFiguras 
{
  public static void main (String args[]) 
  {
    Rectangulo[] rectangulos;
    Scanner teclado = new Scanner(System.in);

    System.out.println("Introduzca el número de rectángulos que desea: ");
    int numRect = teclado.nextInt();
    rectangulos = new Rectangulo[numRect];
    for(int i = 0; i < numRect; ++i) {
      System.out.print("Introduzca la base del rectángulo:");
      double base = teclado.nextDouble();
      System.out.print("Introduzca la altura del rectángulo:");
      double altura = teclado.nextDouble();
      rectangulos[i] = new Rectangulo(base, altura);
    }
    System.out.println("Ahora calculamos y mostramos el área y el perímetro de cada rectángulo.");
    for(int i = 0; i < numRect; ++i) {
      rectangulos[i].CalcularPerimetro();
      rectangulos[i].CalcularArea();
      System.out.println("Rectangulo " + (i+1) + ": perímetro = " + rectangulos[i].Perimetro() + "; área = " + rectangulos[i].Area());
    }
  }
}

/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 1.0
 */

import java.util.*;

public class UsaFigurasStack
{
  public static void main (String args[]) 
  {
    Stack<Rectangulo> figuras;
    figuras = new Stack();
    Scanner teclado = new Scanner(System.in);
    String opc;
    do {
      System.out.println("Nuevo rectángulo.");
      System.out.print("Introduzca la base del rectángulo:");
      double base = teclado.nextDouble();
      System.out.print("Introduzca la altura del rectángulo:");
      double altura = teclado.nextDouble();
      Rectangulo r = new Rectangulo(base, altura);
      r.CalcularPerimetro();
      r.CalcularArea();
      figuras.push(r);
      System.out.print("¿Desea introducir otro rectángulo? (S/N) ");
      opc = teclado.next();
    } while (opc.compareToIgnoreCase("s") == 0 || opc.compareToIgnoreCase("si") == 0);

    System.out.println("Ahora mostramos el área y el perímetro de cada figura.");
    while(!figuras.empty()) {
      System.out.println("Sacamos los elementos de la pila y mostramos su perímetro y su área.");
      Figura f = figuras.pop();
      System.out.println("Figura: perímetro = " + f.Perimetro() + "; área = " + f.Area());
    }
  }
}

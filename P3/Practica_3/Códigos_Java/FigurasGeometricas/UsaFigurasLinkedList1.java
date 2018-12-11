/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 1.0
 */

import java.util.*;

public class UsaFigurasLinkedList
{
  public static void main (String args[]) 
  {
    LinkedList<Rectangulo> figuras;
    figuras = new LinkedList();
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
      if(!figuras.add(r)) {
        System.err.println("Error al introducir elemento en el ArrayList");
      }
      System.out.print("¿Desea introducir otro rectángulo? (S/N) ");
      opc = teclado.next();
    } while (opc.compareToIgnoreCase("s") == 0 || opc.compareToIgnoreCase("si") == 0);

    System.out.println("Ahora mostramos el área y el perímetro de cada figura.");
    for(int i = 0; i < figuras.size(); ++i) {
      System.out.println("Figura " + (i+1) + ": perímetro = " + figuras.get(i).Perimetro() + "; área = " + figuras.get(i).Area());
    }
  }
}

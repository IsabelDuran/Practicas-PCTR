/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 2.0
 */

import java.util.*;

public class UsaFigurasLinkedList
{
  public static void main (String args[]) 
  {
    LinkedList<Figura> figuras;
    figuras = new LinkedList();
    Scanner teclado = new Scanner(System.in);
    String opc;
    int i = 0;
    do {
      System.out.println("Nueva figura.");
      switch(i % 4) {
        case 0: //Circulo
          System.out.print("Introduzca el radio del círculo (en metros):");
          double radio = teclado.nextDouble();
          Circulo circ = new Circulo(radio);
          circ.CalcularPerimetro();
          circ.CalcularArea();
          if(!figuras.add(circ))
            System.err.println("Error al introducir elemento en el ArrayList");
          break;
        case 1: //Cuadrado
          System.out.print("Introduzca el lado del cuadrado (en metros): ");
          double ladoC = teclado.nextDouble();
          Cuadrado cuad = new Cuadrado(ladoC);
          cuad.CalcularPerimetro();
          cuad.CalcularArea();
          if(!figuras.add(cuad))
            System.err.println("Error al introducir elemento en el ArrayList");
          break;
        case 2: //Rectángulo
          System.out.print("Introduzca la base del rectángulo (en metros): ");
          double base = teclado.nextDouble();
          System.out.print("Introduzca la altura del rectángulo (en metros): ");
          double altura = teclado.nextDouble();
          Rectangulo r = new Rectangulo(base, altura);
          r.CalcularPerimetro();
          r.CalcularArea();
          if(!figuras.add(r))
            System.err.println("Error al introducir elemento en el ArrayList");
          break;
        case 3: //Polígono
          System.out.print("Introduzca el número de lados del polígono: ");
          int numLados = teclado.nextInt();
          System.out.print("Introduzca la longitud de un lado del polígono (en metros): ");
          double ladoP = teclado.nextDouble();
          System.out.print("Introduzca el valor de la apotema del polígono (en metros): ");
          double apotema = teclado.nextDouble();
          Poligono p = new Poligono(numLados, ladoP, apotema);
          p.CalcularPerimetro();
          p.CalcularArea();
          if(!figuras.add(p))
            System.err.println("Error al introducir elemento en el ArrayList");
          break;
      }
      System.out.print("¿Desea introducir otra figura? (S/N) ");
      opc = teclado.next();
      ++i;
    } while (opc.compareToIgnoreCase("s") == 0 || opc.compareToIgnoreCase("si") == 0);

    System.out.println("Ahora mostramos el área y el perímetro de cada figura.");
    for(int j = 0; j < figuras.size(); ++j) {
      System.out.println("Figura " + (j+1) + ": perímetro = " + figuras.get(j).Perimetro() + "; área = " + figuras.get(j).Area());
    }
  }
}

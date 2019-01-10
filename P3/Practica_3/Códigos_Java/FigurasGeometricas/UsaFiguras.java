/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 4.0
 */

import java.util.*;

public class UsaFiguras 
{
  public static void main (String args[]) 
  {
    Figura[] figuras;
    Scanner teclado = new Scanner(System.in);

    System.out.print("Introduzca el número de figuras que desea: ");
    int numFig = teclado.nextInt();
    figuras = new Figura[numFig];
    for(int i = 0; i < numFig; ++i) {
			switch(i%4) {
        case 0: //Circulo
          System.out.print("Introduzca el radio del círculo (en metros):");
          double radio = teclado.nextDouble();
          Circulo circ = new Circulo(radio);
          circ.CalcularPerimetro();
          circ.CalcularArea();
          figuras[i] = circ;
          break;
        case 1: //Cuadrado
          System.out.print("Introduzca el lado del cuadrado (en metros): ");
          double ladoC = teclado.nextDouble();
          Cuadrado cuad = new Cuadrado(ladoC);
          cuad.CalcularPerimetro();
          cuad.CalcularArea();
          figuras[i] = cuad;
          break;
        case 2: //Rectángulo
          System.out.print("Introduzca la base del rectángulo (en metros): ");
          double base = teclado.nextDouble();
          System.out.print("Introduzca la altura del rectángulo (en metros): ");
          double altura = teclado.nextDouble();
          Rectangulo r = new Rectangulo(base, altura);
          r.CalcularPerimetro();
          r.CalcularArea();
          figuras[i] = r;
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
          figuras[i] = p;
          break;
      }
    }
    System.out.println("Ahora mostramos el área y el perímetro de cada figura.");
    for(int i = 0; i < numFig; ++i)
      figuras[i].VerDatos();
  }
}

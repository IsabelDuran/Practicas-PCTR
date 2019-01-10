/**
 * Programa que hace uso de figuras geométricas.
 *
 * @author Natalia Partera
 * @version 3.0
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
          figuras[i] = new Circulo(radio);
          break;
        case 1: //Cuadrado
          System.out.print("Introduzca el lado del cuadrado (en metros): ");
          double ladoC = teclado.nextDouble();
          figuras[i] = new Cuadrado(ladoC);
          break;
        case 2: //Rectángulo
          System.out.print("Introduzca la base del rectángulo (en metros): ");
          double base = teclado.nextDouble();
          System.out.print("Introduzca la altura del rectángulo (en metros): ");
          double altura = teclado.nextDouble();
          figuras[i] = new Rectangulo(base, altura);
          break;
        case 3: //Polígono
          System.out.print("Introduzca el número de lados del polígono: ");
          int numLados = teclado.nextInt();
          System.out.print("Introduzca la longitud de un lado del polígono (en metros): ");
          double ladoP = teclado.nextDouble();
          System.out.print("Introduzca el valor de la apotema del polígono (en metros): ");
          double apotema = teclado.nextDouble();
          figuras[i] = new Poligono(numLados, ladoP, apotema);
          break;
      }
    }
    System.out.println("Ahora calculamos y mostramos el área y el perímetro de cada figura.");
    for(int i = 0; i < numFig; ++i) {
      figuras[i].CalcularPerimetro();
      figuras[i].CalcularArea();
      figuras[i].VerDatos();
      //System.out.println("Figura " + (i+1) + ": perímetro = " + figuras[i].Perimetro() + "; área = " + figuras[i].Area());
    }
  }
}

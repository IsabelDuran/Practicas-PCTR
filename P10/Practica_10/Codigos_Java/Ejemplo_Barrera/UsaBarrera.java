  /**
   * Programa que muestra el uso de una barrera cíclica para sincronizar.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;
  
  public class UsaBarrera {
    public static void main (String[] args) {
      int numHilos = 10;

      System.out.println("¡Oh! Hay una enorme piedra en el camino. Debemos moverla " +
        "para continuar, pero pesa demasiado. Tenemos que esperar a que más gente " +
        "nos ayude a moverla.");
      CyclicBarrier piedra = new CyclicBarrier(numHilos);

      for (int i = 0; i <  numHilos; ++i) {
        new Persona(piedra).start();
        System.out.println("Ha llegado una persona más.");
      }

    }
  }

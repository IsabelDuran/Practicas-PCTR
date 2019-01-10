  /**
   * Programa que muestra el funcionamiento de la clase Semaphore.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.*;
  import java.util.concurrent.*;

  public class EjerSemaphore {
    public static void main (String[] args) throws InterruptedException{
      Scanner teclado = new Scanner(System.in);
      String seguir = "s";

      System.out.println("Introduzca con cuántos permisos comenzará el semáforo:");
      int perm = teclado.nextInt();
      Semaphore sem = new Semaphore(perm);

      while(seguir.contentEquals("s") || seguir.contentEquals("S")) {
        System.out.println("El semáforo tiene " + sem.availablePermits() + 
        " permisos.");
        System.out.println("Indique cuántos permisos solicitar: ");
        perm = teclado.nextInt();
        sem.tryAcquire(perm);
        System.out.println("El semáforo tiene " + sem.availablePermits() + 
        " permisos.");
        System.out.println("Indique cuántos permisos ceder: ");
        perm = teclado.nextInt();
        sem.release(perm);
        System.out.println("El semáforo tiene " + sem.availablePermits() + 
        " permisos.");
        System.out.println("Si quiere continuar, introduzca ''S'' ó ''s'': ");
        seguir = teclado.next();
      }
    }
  }

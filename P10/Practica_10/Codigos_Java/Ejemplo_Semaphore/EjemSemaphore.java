  /**
   * Programa que muestra el funcionamiento de la clase Semaphore.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;

  public class EjemSemaphore {
    public static void main (String[] args) throws InterruptedException{
      Semaphore sem = new Semaphore (3);
      System.out.println("El semáforo empieza con " + sem.availablePermits() + 
        " permisos.");
      sem.acquire();
      System.out.println("Tras pedir un permiso, quedan " + sem.availablePermits() + 
        " disponibles.");
      sem.tryAcquire(4);
      System.out.println("Intenta pedir 4 permisos. Tras ello quedan " + 
        sem.availablePermits() + ".");
      sem.tryAcquire(2);
      System.out.println("Intenta pedir 2 permisos. Tras ello quedan disponibles " + 
        sem.availablePermits() + " permisos.");
      sem.release();
      System.out.println("Se cede un permiso. Ahora quedan " + sem.availablePermits() 
        + " disponibles.");
      sem.acquire(2);
      System.out.println("Pide dos permisos. Quedan " + sem.availablePermits());
      System.out.println("Fin del programa");
    }
  }

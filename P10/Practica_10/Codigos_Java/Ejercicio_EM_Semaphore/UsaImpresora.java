  /**
   * Programa que controla una cola de impresión usando semáforos.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;

  class Hilo extends Thread {
    final Impresora impresora;
    Semaphore semaforo;
    int numTarea;

    public Hilo(Impresora i, Semaphore sem, int num) {
      impresora = i;
      semaforo = sem;
      numTarea = num;
    }
 
    public void run() {
      try {
        semaforo.acquire();
        System.out.println("La tarea " + numTarea + " obtiene el permiso.");
        impresora.imprimir();
        System.out.println("La tarea " + numTarea + " ha sido impresa por completo.");
        semaforo.release();
      } catch(InterruptedException ex) {}
    }
  }

  public class UsaImpresora {
    public static void main (String[] args) {
      Impresora imp = new Impresora();
      Semaphore s = new Semaphore(1);

      for(int i= 1; i <= 10; ++i) {
        Hilo h = new Hilo(imp, s, i);
        h.start();
      }

    }
  }

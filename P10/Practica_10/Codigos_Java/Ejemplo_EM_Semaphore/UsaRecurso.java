  /**
   * Programa que muestra el control de la exclusión mutua con semáforos.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;

  class Hilo extends Thread {
    final Recurso r;
    Semaphore s;
    int incremento;

    public Hilo(Recurso rec, Semaphore sem, int suma) {
      r = rec;
      s = sem;
      incremento = suma;
    }
 
    public void run() {
      while(r.valor() < r.maximo()) {
        try {
          s.acquire();
          if(r.valor() < r.maximo()) {
            r.aumentar(incremento);
            System.out.print(r.valor() + " - ");
          }
          s.release();
        } catch(InterruptedException ex) {}
      }
    }
  }

  public class UsaRecurso {
    public static void main (String[] args) {
      Recurso r = new Recurso(200);
      Semaphore s = new Semaphore(1);

      for(int i= 1; i <= 5; ++i) {
        Hilo h = new Hilo(r, s, i);
        h.start();
      }

    }
  }

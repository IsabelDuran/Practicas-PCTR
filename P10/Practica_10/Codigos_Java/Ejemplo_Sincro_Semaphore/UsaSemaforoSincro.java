  /**
   * Programa que sincroniza dos hilos entre sí usando semáforos.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;

  class HiloAumentador extends Thread {
    Semaphore s;
    int cont;

    public HiloAumentador(Semaphore semaforo) {
      s = semaforo;
    }

    public void cederPermiso(Semaphore s) {
      s.release();
    }

    public void run() {
      while(cont < 100) {
        ++cont;
        System.out.print(cont + " - ");
      }
      System.out.println();
      cederPermiso(s);
    }
  }

  class HiloDecrementador extends Thread {
    Semaphore s;
    int cont = 100;

    public HiloDecrementador(Semaphore semaforo) {
      s = semaforo;
    }
 
    public void pedirPermiso(Semaphore s) {
      try {
        s.acquire();
      } catch(InterruptedException ex) {}
    }

    public void run() {
      while(cont != 0) {
        pedirPermiso(s);
        while(cont > 0) {
          --cont;
          System.out.print(cont + " - ");
        }
        System.out.println();
      }
    }
  }

  public class UsaSemaforoSincro {
    public static void main (String[] args) {
      Semaphore s = new Semaphore(0);

      HiloAumentador hAumen = new HiloAumentador(s);
      HiloDecrementador hDecre = new HiloDecrementador(s);
      hDecre.start();
      hAumen.start();
    }
  }

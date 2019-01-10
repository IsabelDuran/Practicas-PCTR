  /**
   * Programa que muestra el uso de los cerrojos.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  class Hilo extends Thread {
    Recurso r;
    int incremento;

    public Hilo() {}

    public Hilo(Recurso rec, int suma) {
      r = rec;
      incremento = suma;
    }
 
    public void run() {
      while(r.valor() < r.maximo()) {
        r.aumentar(incremento);
      }
    }
  }

  public class UsaRecurso {
    public static void main (String[] args) {
      Recurso r = new Recurso(200);

      for(int i= 1; i <= 5; ++i) {
        Hilo h = new Hilo(r, i);
        h.start();
      }
    }
  }

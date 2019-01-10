  /**
   * Clase que se queda a la espera de poder pasar por la barrera cíclica.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;

  public class Persona extends Thread {
    final CyclicBarrier piedra;
    boolean bloqueado = true;

    public Persona(CyclicBarrier barrera) {
      piedra = barrera;
    }

    public void run() {
      while(bloqueado) {
        try {
          piedra.await();
        } catch(BrokenBarrierException ex) {}
          catch(InterruptedException ex) {}
        bloqueado = false;
      }
      System.out.println("¡Hemos pasado!");
    }
  }


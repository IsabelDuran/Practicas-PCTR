  /**
   * Programa que prueba el funcionamiento del monitor.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  class Hilo implements Runnable {
    private Monitor monitor;

    public Hilo(Monitor mon) {
      monitor = mon;
    }

    public void run() {
      for(;;)
        monitor.comprobar();
    }
  }

  public class PruebaMonitor {
    //Programa principal
    public static void main (String[] args) {
      Monitor monitor;
      monitor = new Monitor();

      Thread hilo1 = new Thread(new Hilo(monitor));
      Thread hilo2 = new Thread(new Hilo(monitor));
      Thread hilo3 = new Thread(new Hilo(monitor));

      hilo1.start();
      hilo2.start();
      hilo3.start();
    }
  }

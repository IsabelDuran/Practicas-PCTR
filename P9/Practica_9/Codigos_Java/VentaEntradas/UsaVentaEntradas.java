  /**
   * Programa que prueba el funcionamiento de la clase VentaEntradas.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  class Hilo implements Runnable {
    private VentaEntradas entradas;

    public Hilo(VentaEntradas ent) {
      entradas = ent;
    }

    public void run() {
      for(int i = 0; i < 20; ++i)
        entradas.venta();
    }
  }

  public class UsaVentaEntradas {
    //Programa principal
    public static void main (String[] args) {
      VentaEntradas entradas;
      entradas = new VentaEntradas();

      Thread hilo1 = new Thread(new Hilo(entradas));
      Thread hilo2 = new Thread(new Hilo(entradas));
      Thread hilo3 = new Thread(new Hilo(entradas));

      hilo1.start();
      hilo2.start();
      hilo3.start();
    }
  }

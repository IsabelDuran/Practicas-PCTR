  /**
   * Programa que muestra el uso de los cerrojos.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  class Cliente extends Thread {
    LineaCaja linea;

    public Cliente(LineaCaja lc) {
      linea = lc;
    }
 
    public void run() {
      for(int i = 1; i <= 10; ++i) {
        try {
          linea.llegaCliente();
        } catch (InterruptedException ex) {}
      }
    }
  }

  class Cajero extends Thread {
    LineaCaja linea;

    public Cajero(LineaCaja lc) {
      linea = lc;
    }

    public void run() {
      while(true) {
        try {
          linea.atenderCliente();
        } catch (InterruptedException ex) {}
      }
    }
  }

  public class UsaLineaCaja {
    public static void main (String[] args) {
      LineaCaja lc = new LineaCaja();
      Cajero cajero = new Cajero(lc);
      cajero.start();

      for(int i= 0; i < 10; ++i) {
        Cliente cliente = new Cliente(lc);
        cliente.start();
      }
    }
  }

  /**
   * Clase Impresora.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  public class Impresora {
    //Atributos
    private final ReentrantLock cerrojo = new ReentrantLock();

    //Constructores
    public Impresora() {}

    //Métodos
    public void imprimir(String documento) {
      cerrojo.lock();
      try {
        System.out.println("Comienza la impresión de ''" + documento + "''.");
        for(int i = 0; i < 100000; ++i) {}
        System.out.println("Documento ''" + documento + "'' impreso.");
      } finally {
        cerrojo.unlock();
      }
    }
  }

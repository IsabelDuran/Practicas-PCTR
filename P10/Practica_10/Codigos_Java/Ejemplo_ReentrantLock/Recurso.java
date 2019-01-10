  /**
   * Clase Recurso.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  public class Recurso {

    //Atributos privados
    private int cont;
    private int max = 100;
    private final ReentrantLock cerrojo = new ReentrantLock();

    //Constructores
    public Recurso() {}

    public Recurso(int n) {
      max = n;
    }

    //Métodos
    public void aumentar(int i) {
      cerrojo.lock();
      try {
        cont = cont + i;
        System.out.print(cont + " - ");
      } finally {
        cerrojo.unlock();
      }
    }

    public int valor() {
      int v = -1;
      cerrojo.lock();
      try {
        v = cont;
      } finally {
        cerrojo.unlock();
        return v;
      }
    }

    public int maximo() {
      return max;
    }
  }

  /**
   * Programa que muestra el funcionamiento de las variables atómicas.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.atomic.*;

  public class UsaVarAtomicas {
    public static void main (String[] args) {
      AtomicInteger ent = new AtomicInteger(20);
      AtomicLong lar = new AtomicLong(123456);
      AtomicLongArray arr = new AtomicLongArray(20);

      for(int i= 0; i < 5; ++i) {
        VarAtomicas h = new VarAtomicas(ent, lar, arr);
        h.start();
      }

    }
  }

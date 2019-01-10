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
    private final Condition noVacia = cerrojo.newCondition();
    private final Condition noLlena = cerrojo.newCondition();

    private final String[] colaImpresion = new String[10];
    private int tareasEnCola = 0;

    //Constructores
    public Impresora() {}

    //Métodos
    public void mandarImprimir(String documento) throws InterruptedException {
      cerrojo.lock();
      try {
        while(tareasEnCola == (colaImpresion.length - 1))
          noLlena.await();
        colaImpresion[tareasEnCola] = documento;
        System.out.println("Se pone en cola un nuevo documento: " + documento);
        ++tareasEnCola;
        noVacia.signal();
      } finally {
        cerrojo.unlock();
      }
    }

    public void imprimir() throws InterruptedException {
      cerrojo.lock();
      try {
        while(tareasEnCola == 0)
          noVacia.await();
        String doc = colaImpresion[0];
        System.out.println("Comienza la impresión de ''" + doc + "''.");
        for(int i = 0; i < 100000; ++i) {}
        System.out.println("Documento ''" + doc + "'' impreso.");
        //Se elimina el documento impreso de la cola de impresión
        int j = 1;
        for(; colaImpresion[j] != null; ++j) {
          colaImpresion[j-1] = colaImpresion[j];
        }
        colaImpresion[j] = null;
        --tareasEnCola;
        noLlena.signal();
      } finally {
        cerrojo.unlock();
      }
    }
  }

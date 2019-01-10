  /**
   * Clase Impresora.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;
  import java.util.concurrent.locks.*;

  public class Impresora {
    //Atributos
    private final ReentrantLock cerrojo = new ReentrantLock();
    private final Condition noVacia = cerrojo.newCondition();
    private final Condition noLlena = cerrojo.newCondition();

    private final ArrayBlockingQueue<String> colaImpresion;
    private int tareasEnCola = 0;

    //Constructores
    public Impresora() {
      colaImpresion = new ArrayBlockingQueue<String>(10);
    }

    //Métodos
    public void mandarImprimir(String documento) throws InterruptedException {
      cerrojo.lock();
      try {
        while(colaImpresion.size() == 10)
          noLlena.await();
        colaImpresion.put(documento);
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
        String doc = colaImpresion.take();
        System.out.println("Comienza la impresión de ''" + doc + "''.");
        for(int i = 0; i < 100000; ++i) {}
        System.out.println("Documento ''" + doc + "'' impreso.");
        --tareasEnCola;
        noLlena.signal();
      } finally {
        cerrojo.unlock();
      }
    }
  }

  /**
   * Clase LineaCaja.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;
  import java.util.concurrent.locks.*;

  public class LineaCaja {

    //Atributos privados
    private final ReentrantLock cerrojo = new ReentrantLock();
    private final Condition noVacia = cerrojo.newCondition();
    private final Condition noLlena = cerrojo.newCondition();

    private final LinkedBlockingQueue<Integer> colaClientes;
    private int clientesEnCola = 0;
    private Integer ultimoCliente = 0;

    //Constructores
    public LineaCaja() {
      colaClientes = new LinkedBlockingQueue<Integer>(50);
    }

    //Métodos
    public void llegaCliente() throws InterruptedException {
      cerrojo.lock();
      try {
        while(colaClientes.size() == 50)
          noLlena.await();
        colaClientes.put(ultimoCliente);
        System.out.println("Ha llegado un nuevo cliente: " + ultimoCliente.toString());
        ++ultimoCliente;
        ++clientesEnCola;
        noVacia.signal();
      } finally {
        cerrojo.unlock();
      }
    }

    public void atenderCliente() throws InterruptedException {
      cerrojo.lock();
      try {
        while(clientesEnCola == 0)
          noVacia.await();
        int cliente = colaClientes.take();
        System.out.println("Se atiende al cliente " + cliente);
        --clientesEnCola;
        noLlena.signal();
      } finally{
        cerrojo.unlock();
      }
    }

  }

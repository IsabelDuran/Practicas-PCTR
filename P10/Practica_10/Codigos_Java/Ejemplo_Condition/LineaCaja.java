  /**
   * Clase LineaCaja.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  public class LineaCaja {

    //Atributos privados
    private final ReentrantLock cerrojo = new ReentrantLock();
    private final Condition noVacia = cerrojo.newCondition();
    private final Condition noLlena = cerrojo.newCondition();

    private final int[] colaClientes = new int[50];
    private int clientesEnCola = 0;
    private int ultimoCliente = 0;

    //Constructores
    public LineaCaja() {}

    //Métodos
    public void llegaCliente() throws InterruptedException {
      cerrojo.lock();
      try {
        while(clientesEnCola == (colaClientes.length - 1))
          noLlena.await();
        colaClientes[clientesEnCola] = ultimoCliente;
        System.out.println("Ha llegado un nuevo cliente: " + ultimoCliente);
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
        int cliente = colaClientes[0];
        System.out.println("Se atiende al cliente " + cliente);
        //Los clientes avanzan una posición en la cola.
        int i = 1;
        for(; colaClientes[i] != 0; ++i) {
          colaClientes[i-1] = colaClientes[i];
        }
        colaClientes[i] = 0;
        --clientesEnCola;
        noLlena.signal();
      } finally{
        cerrojo.unlock();
      }
    }

  }

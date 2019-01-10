  /**
   * Clase VentaEntradas que emula la venta concurrente de entradas.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  public class VentaEntradas {

    //Atributo privado
    private int numeracion;
    private boolean libre;

    //Constructor de la clase
    public VentaEntradas() {
      numeracion = 0;
      libre = true;
    }

    //Método que simula la venta de entradas
    public synchronized void venta() {
      while(!libre) {
        try {
          wait();
        } catch (InterruptedException e) {
          return ;
        }
      }      
      libre = false;
      System.out.println("[Inicio de la venta]");
      ++numeracion;
      //Para emular el tiempo que pudiera tardar en imprimir una entrada o en 
      //solicitar y/o registrar otros datos, usamos el siguiente bucle
      for(int i = 0; i < 10000; ++i) {}

      System.out.println("Ha sido vendida la entrada núm. " + numeracion);
      libre = true;
      System.out.println("[Fin de la venta]");
      notifyAll();
    }
  }

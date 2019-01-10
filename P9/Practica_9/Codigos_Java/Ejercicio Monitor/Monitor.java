  /**
   * Clase Monitor.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  public class Monitor {

    //Atributo privado
    private int hilos;
    private boolean despertar, despertando;

    //Constructor de la clase
    public Monitor() {
      hilos = 0;
      despertar = false;
      despertando = false;
    }

    //Método que despierta a todos los hilos si ya han sido suspendidos 2 previamente
    public synchronized void comprobar() {
      if (despertando) {
        if (hilos == 0) {
          despertando = false;
          System.out.println("Todos los hilos están despiertos.");
        }
        else
          notifyAll();
      }
      else {

      if (hilos == 2) {
        System.out.println("Un hilo despierta a los demás.");
        despertar = true;
        despertando = true;
        notifyAll();
      }
      else {
        System.out.println("Un hilo ha sido suspendido.");
        ++hilos;
        despertar = false;
        while(!despertar) {
          try {
            wait();
          } catch (InterruptedException e) {
            return ;
          }
        }
        System.out.println("Un hilo se despierta.");
        --hilos;
        }
      }
    }
  }

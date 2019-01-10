  /**
   * Clase Ascensor.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  public class Ascensor {

    //Atributo privado
    private int piso;
    private boolean parado;

    //Constructor de la clase
    public Ascensor() {
      piso = 0;
      parado = true;
    }

    //Método que si dada la posición del ascensor y el piso desde el que le llaman,
    //controla si el ascensor sube o baja
    public synchronized void llamar(int p, String mensaje) {
      while(!parado) {
        try {
          wait();
        } catch (InterruptedException e) {
          return ;
        }
      }
      if(piso > p) {
        bajar(p, mensaje);
      }
      else if(piso < p) {
        subir(p, mensaje);
      }
    }

    //Método modificador que simula la subida del ascensor. Muestra un mensaje cuando
    //el ascensor ha llegado al destino.
    public synchronized void subir(int p, String mensaje) {
      while(!parado) {
        try {
          wait();
        } catch (InterruptedException e) {
          return ;
        }
      }
      parado = false;
      while (piso != p) {
        ++piso;
        System.out.println("Ascensor en el piso " + piso);
      }
      parado = true;
      System.out.println(mensaje);
      notifyAll();
    }

    //Método modificador que simula la bajada del ascensor. Muestra un mensaje cuando
    //el ascensor ha llegado al destino.
    public synchronized void bajar(int p, String mensaje) {
      while(!parado) {
        try {
          wait();
        } catch (InterruptedException e) {
          return ;
        }
      }
      parado = false;
      while (piso != p) {
        --piso;
        System.out.println("Ascensor en el piso " + piso);
      }
      parado = true;
      System.out.println(mensaje);
      notifyAll();
    }

    //Método observador que muestra el piso
    public synchronized void mostrarPiso() {
      System.out.println("El ascensor está en el piso " + piso);
    }

    //Método observador que devuelve el piso
    public synchronized int piso() {
      return piso;
    }

  }

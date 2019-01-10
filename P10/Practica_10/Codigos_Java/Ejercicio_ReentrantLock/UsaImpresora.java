  /**
   * Programa que controla una cola de impresión usando cerrojos.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.locks.*;

  class Tarea extends Thread {
    final Impresora impresora;
    String nombre;

    public Tarea(Impresora i, String nom) {
      impresora = i;
      nombre = nom;
    }
 
    public void run() {
      impresora.imprimir(nombre);
    }
  }

  public class UsaImpresora {
    public static void main (String[] args) {
      Impresora imp = new Impresora();
      int numTareas = 10;
      String[] tareas = new String[numTareas];

      for(int i = 0; i < numTareas; ++i) {
        tareas[i] = "Apuntes de PCTR: guión " + (i+1);
      }

      for(int j= 0; j < numTareas; ++j) {
        Tarea t = new Tarea(imp, tareas[j]);
        t.start();
      }

    }
  }

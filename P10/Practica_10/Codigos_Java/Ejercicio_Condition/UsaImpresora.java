  /**
   * Programa que controla una cola de impresión usando cerrojos y condiciones.
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
      try {
        impresora.mandarImprimir(nombre);
      } catch (InterruptedException ex) {}
    }
  }

  class Imprimiendo extends Thread {
    final Impresora impresora;

    public Imprimiendo(Impresora i) {
      impresora = i;
    }
 
    public void run() {
      while(true) {
        try {
          impresora.imprimir();
        } catch (InterruptedException ex) {}
      }
    }
  }

  public class UsaImpresora {
    public static void main (String[] args) {
      Impresora imp = new Impresora();
      Imprimiendo daemonion = new Imprimiendo(imp);
      daemonion.start();

      int numTareas = 20;
      String[] tareas = new String[numTareas];

      int i = 0;
      for(; i < numTareas/2; ++i) {
        tareas[i] = "Apuntes de PCTR: guión " + (i+1);
      }
      for(; i < numTareas; ++i) {
        tareas[i] = "Apuntes de POO: guión " + (i+1-10);
      }

      for(int j= 0; j < numTareas; ++j) {
        Tarea t = new Tarea(imp, tareas[j]);
        t.start();
      }

    }
  }

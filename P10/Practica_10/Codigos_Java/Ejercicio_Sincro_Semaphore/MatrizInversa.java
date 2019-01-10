  /**
   * Programa que sincroniza dos hilos entre sí usando semáforos.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;
  import java.util.concurrent.atomic.*;

  class HiloAdjunto extends Thread {
    final Matriz matriz;
    Semaphore semAdj;
    final Matriz adjuntos;

    public HiloAdjunto(Matriz m, Semaphore sem, Matriz adj) {
      matriz = m;
      semAdj = sem;
      adjuntos = adj;
    }

    public void cederPermiso(Semaphore semAdj) {
//      System.out.println("HiloAdjunto cede el permiso a HiloTraspuesta.");
      semAdj.release();
    }

    public void run() {
//      System.out.println("HiloAdjunto comienza su ejecución.");
//      System.out.println("La matriz es:");
//      matriz.imprimirMatriz();
//      System.out.println("La matriz de adjuntos inicial es:");
//      adjuntos.imprimirMatriz();
      for(int i = 0; i < matriz.dimension(); ++i) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(matriz.dimension());
        for(int j = 0; j < matriz.dimension(); ++j) {
          fila.set(j, matriz.componente2(i, j).determinante2());
        }
        adjuntos.inicializarFila(i, fila);
      }
//      System.out.println("HiloAdjunto ha calculado como matriz de adjuntos:");
//      adjuntos.imprimirMatriz();
      cederPermiso(semAdj);
    }
  }

  class HiloTraspuesta extends Thread {
    Semaphore semAdj;
    Semaphore semDet;
    final Matriz adjuntos;
    final Matriz traspuesta;

    public HiloTraspuesta(Matriz adj, Matriz trasp, Semaphore semA, Semaphore semD) {
      adjuntos = adj;
      traspuesta = trasp;
      semAdj = semA;
      semDet = semD;
    }

    public void pedirPermiso(Semaphore semAdj) {
      try {
        semAdj.acquire();
//        System.out.println("HiloTraspuesta adquiere el permiso de HiloAdjunto.");
      } catch(InterruptedException ex) {}
    }

    public void cederPermiso(Semaphore semDet) {
//      System.out.println("HiloTraspuesta cede el permiso a HiloDeterminante.");
      semDet.release();
    }

    public void run() {
      pedirPermiso(semAdj);
//      System.out.println("HiloTraspuesta comienza su ejecución.");
//      System.out.println("La matriz de adjuntos es:");
//      adjuntos.imprimirMatriz();
      adjuntos.trasponer(traspuesta);
//      System.out.println("HiloTraspuesta ha calculado como matriz traspuesta:");
//      traspuesta.imprimirMatriz();
      cederPermiso(semDet);
    }
  }

  class HiloDeterminante extends Thread {
    final Matriz matriz;
    final Matriz traspuesta;
    final Matriz inversa;
    Semaphore semDet;
    float determinante;

    public HiloDeterminante(Matriz m, Matriz tras, Matriz inv, Semaphore sem) {
      matriz = m;
      traspuesta = tras;
      inversa = inv;
      semDet = sem;
    }

    public void pedirPermiso(Semaphore semDet) {
      try {
        semDet.acquire();
//        System.out.println("HiloDeterminante adquiere el permiso de HiloTraspuesta.");
      } catch(InterruptedException ex) {}
    }

    public void run() {
      pedirPermiso(semDet);
//      System.out.println("HiloDeterminante comienza su ejecución.");
//      System.out.println("La matriz traspuesta es:");
//      traspuesta.imprimirMatriz();
//      System.out.println("La matriz de inversa inicial es:");
//      inversa.imprimirMatriz();
      determinante = matriz.determinante3();
//      System.out.println("El determinante de la matriz M es: " + determinante);
      traspuesta.dividirMatriz(inversa, determinante);
//      System.out.println("HiloDeterminante ha calculado como matriz inversa:");
//      inversa.imprimirMatriz();
      
    }
  }

  public class MatrizInversa {
    public static void main (String[] args) throws InterruptedException {
      Semaphore semAdj = new Semaphore(0);
      Semaphore semDet = new Semaphore(0);
      Matriz matriz = new Matriz(3);
      Matriz adjuntos = new Matriz(3);
      Matriz traspuesta = new Matriz(3);
      float determinante;
      Matriz inversa = new Matriz(3);
      matriz.inicializarMatrizAleatoria();

      System.out.println("La matriz inicial es: ");
      matriz.imprimirMatriz();
      HiloAdjunto hAdj = new HiloAdjunto(matriz, semAdj, adjuntos);
//      adjuntos.imprimirMatriz();
      HiloTraspuesta hTras = new HiloTraspuesta(adjuntos, traspuesta, semAdj, semDet);
//      traspuesta.imprimirMatriz();
      HiloDeterminante hDet = new HiloDeterminante(matriz, traspuesta, inversa, semDet);
      hAdj.start();
      hTras.start();
      hDet.start();
      hDet.join();
//      System.out.println("HiloDeterminante ha terminado.");
      System.out.println("La matriz inversa es: ");
      inversa.imprimirMatriz();
    }
  }

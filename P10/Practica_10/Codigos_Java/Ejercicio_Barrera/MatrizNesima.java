  /**
   * Programa que calcula la n-ésima potencia de una matriz.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.*;
  import java.util.concurrent.atomic.*;
  
  class Hilo extends Thread {
    final CyclicBarrier potencia;
    final Matriz matriz;
    final Matriz nEsima;
    int n, fila, columna;

    public Hilo(CyclicBarrier barrera, Matriz m, Matriz esima, int pot, int f) {
      potencia = barrera;
      matriz = m;
      nEsima = esima;
      n = pot;
      fila = f;
    }

    public void run() {
      if(n == 0)
      {
        nEsima.inicializarMatrizDe1();
      }
      else if (n > 1) {
        for(int i = 1; i < n; ++i) {
          try {
            potencia.await();
          } catch(BrokenBarrierException ex) {}
            catch(InterruptedException ex) {}
          AtomicReferenceArray<Float> filaElem = nEsima.multiplicaFilaPorColumna(matriz, fila);
          try {
            potencia.await();
          } catch(BrokenBarrierException ex) {}
            catch(InterruptedException ex) {}
          nEsima.asignar(filaElem, fila);
        }
      }
    }
  }

  public class MatrizNesima {
    public static void main (String[] args) throws InterruptedException {
      int dimension = 3;
      Matriz matriz = new Matriz(dimension);
      Matriz m_esima = new Matriz(dimension);
      int n = 3;
      int numHilos = dimension;
      matriz.inicializarMatrizAleatoria();
      m_esima.copiarMatriz(matriz);

      System.out.println("La matriz M es:");
      matriz.imprimirMatriz();

      CyclicBarrier barrera = new CyclicBarrier(numHilos);

      Hilo h1 = new Hilo(barrera, matriz, m_esima, n, 0);
      Hilo h2 = new Hilo(barrera, matriz, m_esima, n, 1);
      Hilo h3 = new Hilo(barrera, matriz, m_esima, n, 2);
      
      h1.start();
      h2.start();
      h3.start();
      h1.join();
      h2.join();
      h3.join();
      System.out.println("La matriz elevada a " + n + " es:");
      m_esima.imprimirMatriz();
      
    }
  }

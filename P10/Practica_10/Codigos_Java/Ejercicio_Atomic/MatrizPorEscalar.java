  /**
   * Programa que multiplica una matriz por un escalar.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.atomic.*;

  class Hilo extends Thread {
    final Matriz matriz;
    int fila;
    int cte;

    public Hilo(Matriz m, int f, int c) {
      matriz = m;
      fila = f;
      cte = c;
    }

    public void run() {
      matriz.multiplicarFilaPorEscalar(fila, cte);
    }
  }

  public class MatrizPorEscalar {
    public static void main (String[] args) throws InterruptedException {
      int dimension = 4;
      Matriz m = new Matriz(dimension);

      m.inicializarMatrizAleatoria();
      m.imprimirMatriz();

      int constante = 2;
      Hilo h1 = new Hilo(m, 0, constante);
      h1.start();
      Hilo h2 = new Hilo(m, 1, constante);
      h2.start();
      Hilo h3 = new Hilo(m, 2, constante);
      h3.start();
      Hilo h4 = new Hilo(m, 3, constante);
      h4.start();
      h1.join();
      h2.join();
      h3.join();
      h4.join();
      System.out.println("Matriz m por escalar " + constante + ":");
      m.imprimirMatriz();
    }
  }

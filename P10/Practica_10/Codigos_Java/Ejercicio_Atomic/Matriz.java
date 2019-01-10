  /**
   * Clase Matriz.
   * [Ejercicio]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.*;
  import java.util.concurrent.atomic.*;

  public class Matriz extends Thread {
    //Atributos privados
    private AtomicReferenceArray<AtomicLongArray> matriz;
    private int dim;

    //Constructor de la clase
    public Matriz(int dimension) {
      matriz = new AtomicReferenceArray(dimension);
      dim = dimension;

      for(int i = 0; i < dim; ++i) {
        AtomicLongArray fila = new AtomicLongArray(dim);
        matriz.set(i, fila);
      }
    }

    public void inicializarMatriz() {
      for(int i = 0; i < dim; ++i) {
        AtomicLongArray fila = new AtomicLongArray(dim);
        for(int j = 0; j < dim; ++j){
          fila.set(j, i+j);
        }
        matriz.set(i, fila);
      }
    }

    public void inicializarMatrizAleatoria() {
      Random aleat = new Random();
      for(int i = 0; i < dim; ++i) {
        AtomicLongArray fila = new AtomicLongArray(dim);
        for(int j = 0; j < dim; ++j){
          fila.set(j, aleat.nextInt(100));
        }
        matriz.set(i, fila);
      }
    }

    public void imprimirMatriz() {
      for(int i = 0; i < dim; ++i) {
        for(int j = 0; j < dim; ++j){
          System.out.print(" " + (matriz.get(i)).get(j) + " ");
        }
        System.out.println();
      }
    }

    public void multiplicarFilaPorEscalar(int f, int k) {
      if(k >=0 && k < dim) {
        AtomicLongArray fila =new AtomicLongArray(dim);
        for(int i = 0; i < dim; ++i) {
          fila.set(i, matriz.get(f).get(i));
        }
        for(int j = 0; j < dim; ++j) {
          fila.set(j, fila.get(j) * k);
        }
        matriz.set(f, fila);
      }
    }

    public int dimension() {
      return dim;
    }
  }

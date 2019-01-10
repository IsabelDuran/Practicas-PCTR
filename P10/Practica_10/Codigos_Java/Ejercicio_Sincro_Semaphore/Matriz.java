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
    private AtomicReferenceArray<AtomicReferenceArray<Float>> matriz;
    private int dim;

    //Constructor de la clase
    public Matriz(int dimension) {
      matriz = new AtomicReferenceArray<AtomicReferenceArray<Float>>(dimension);
      dim = dimension;

      for(int i = 0; i < dim; ++i) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        matriz.set(i, fila);
      }
    }

    public void inicializarMatriz() {
      for(int i = 0; i < dim; ++i) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        for(int j = 0; j < dim; ++j){
          Integer num = new Integer(i+j);
          fila.set(j, num.floatValue());
        }
        matriz.set(i, fila);
      }
    }

    public void inicializarFila(int f, AtomicReferenceArray<Float> fila) {
      matriz.set(f, fila);
    }

    public void inicializarMatrizAleatoria() {
      Random aleat = new Random();
      for(int i = 0; i < dim; ++i) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        for(int j = 0; j < dim; ++j){
          Integer num = new Integer(aleat.nextInt(20));
          fila.set(j, num.floatValue());
        }
        matriz.set(i, fila);
      }
    }

    public void imprimirMatriz() {
      for(int i = 0; i < dim; ++i) {
        for(int j = 0; j < dim; ++j){
          System.out.print(" " + matriz.get(i).get(j) + " ");
        }
        System.out.println();
      }
    }

    public void multiplicarFilaPorEscalar(int f, float k) {
      if(k >=0 && k < dim) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        for(int i = 0; i < dim; ++i) {
          fila.set(i, matriz.get(f).get(i));
        }
        for(int j = 0; j < dim; ++j) {
          fila.set(j, fila.get(j) * k);
        }
        matriz.set(f, fila);
      }
    }

    public void dividirMatriz(Matriz mat, float k) {
      for(int i = 0; i < dim; ++i) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        for(int j = 0; j < dim; ++j) {
          fila.set(j, matriz.get(i).get(j) / k);
        } 
        mat.inicializarFila(i, fila);
      }
    }

    public int dimension() {
      return dim;
    }

    public Matriz componente2(int f, int c) {
      Matriz mat = new Matriz(2);

      int indexI = 0;
      for(int i = 0; i < dim; ++i) {
        if(i != f) {
          AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim-1);
          int indexJ = 0;
          for(int j = 0; j < dim; ++j) {  
            if(j != c) {
              fila.set(indexJ, matriz.get(i).get(j));
              ++indexJ;
            }
          }
          mat.inicializarFila(indexI, fila);
          ++indexI;
        }
      }

      return mat;
    }

    public float determinante2() {
      float det = matriz.get(0).get(0) * matriz.get(1).get(1) - 
        matriz.get(0).get(1) * matriz.get(1).get(0);
      return det;
    }

    public float determinante3() {
      float det = matriz.get(0).get(0) * matriz.get(1).get(1) * matriz.get(2).get(2) + 
        matriz.get(1).get(0) *matriz.get(2).get(1) * matriz.get(0).get(2) + 
        matriz.get(0).get(1) * matriz.get(1).get(2) * matriz.get(2).get(0) - (
        matriz.get(0).get(2) * matriz.get(1).get(1) * matriz.get(2).get(0) + 
        matriz.get(1).get(2) * matriz.get(2).get(1) * matriz.get(0).get(0) + 
        matriz.get(1).get(0) * matriz.get(0).get(1) * matriz.get(2).get(2));
      return det;
    }

    public void trasponer(Matriz tras) {
      for(int j = 0; j < dim; ++j) {
        AtomicReferenceArray<Float> fila = new AtomicReferenceArray<Float>(dim);
        for(int i = 0; i < dim; ++i) {
          fila.set(i, matriz.get(i).get(j));
        }
        tras.inicializarFila(j, fila);
      }
    }
  }

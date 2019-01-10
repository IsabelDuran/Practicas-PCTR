  /**
   * Clase Recurso.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  public class Recurso {

    //Atributos privados
    private int cont;
    private int max = 100;

    //Constructores
    public Recurso() {}

    public Recurso(int n) {
      max = n;
    }

    //Métodos
    public void aumentar(int i) {
      cont = cont + i;
    }

    public int valor() {
      return cont;
    }

    public int maximo() {
      return max;
    }
  }

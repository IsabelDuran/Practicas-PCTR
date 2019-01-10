  /**
   * Clase Contador.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  public class Contador {

    //Atributo privado
    private int cont;

    //Constructor de la clase
    public Contador() { }

    //Método modificador que aumenta la variable
    public synchronized void aumentar() {
      //Para controlar el inicio de la ejecución del método, mostramos un mensaje
      System.out.println("Aumentando...");
      cont++;
      //Para controlar el fin de la ejecución del método, mostramos un mensaje
      System.out.println("Aumentado.");
    }

    //Método observador que devuelve el valor de la variable
    public synchronized int mostrarValor() {
      //Para controlar el inicio de la ejecución del método, mostramos un mensaje
      System.out.println("Mostrando...");
      System.out.println("Contador: " + cont);
      //Para controlar el fin de la ejecución del método, mostramos un mensaje
      System.out.println("Mostrado.");
    }

    //Método observador que devuelve el valor de la variable
    public synchronized int valor() {
      //Para controlar la ejecución del método, mostramos un mensaje
      System.out.println("Devolviendo.");
      return cont;
    }

  }

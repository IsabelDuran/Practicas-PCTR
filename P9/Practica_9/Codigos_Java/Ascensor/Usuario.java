  /**
   * Clase Usuario.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  public class Usuario extends Thread {

    //Atributo privado
    private String nombre;
    private int piso;
    private Ascensor ascensor;
    private int[] pisos;

    //Constructor de la clase
    public Usuario(Ascensor a, String n) {
      nombre = n;
      piso = 0;
      ascensor = a;
    }

    //Constructor de la clase
    public Usuario(Ascensor a, String n, int p, int[] lp) {
      nombre = n;
      piso = p;
      ascensor = a;
      pisos = lp;
    }

    public void VerPisos() {
      for(int i = 0; i < pisos.length; ++i)
        System.out.println(nombre + " va al piso " + pisos[i]);
    }

    //Método que llama al ascensor para ir de un piso a otro
    public void llamarAscensor(int destino) {
      System.out.println("El usuario " + nombre + " está esperando en el piso " + piso + " para ir al piso " + destino);
      String mensaje1 = "El usuario " + nombre + " se ha montado en el ascensor en el piso " + piso;
      ascensor.llamar(piso, mensaje1);
      String mensaje2 = "El usuario " + nombre + " ha llegado al piso " +  destino;
      ascensor.llamar(destino, mensaje2);
      piso = destino;
    }

    //Método run
    public void run() {
      for(int i = 0; i < pisos.length; ++i) {
        llamarAscensor(pisos[i]);
      }
    }

  }

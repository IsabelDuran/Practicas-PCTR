  /**
   * Programa que prueba el funcionamiento de Ascensor.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  class SubirAscensor extends Thread{
    private Ascensor ascensor;

    public SubirAscensor(Ascensor asc) {
      ascensor = asc;
    }

    public void run() {
      for(;;)
        ascensor.subir(ascensor.piso() + 3, "Sube 3 pisos.");
    }
  }

  class BajarAscensor extends Thread{
    private Ascensor ascensor;

    public BajarAscensor(Ascensor asc) {
      ascensor = asc;
    }

    public void run() {
      for(;;)
        ascensor.bajar(ascensor.piso() - 2, "Baja 2 pisos.");
    }
  }

  public class PruebaAscensor {
    //Programa principal
    public static void main (String[] args) {
      Ascensor ascensor;
      ascensor = new Ascensor();

      new SubirAscensor(ascensor).start();
      new SubirAscensor(ascensor).start();
      new SubirAscensor(ascensor).start();
      new BajarAscensor(ascensor).start();
      new BajarAscensor(ascensor).start();
    }

  }

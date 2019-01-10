  /**
   * Programa que muesta el funcionamiento de Ascensor.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  public class UsaAscensor {
    
    static void inicializarPisos(int[] p1, int[] p2, int[] p3) {
      p1[0] = 0;
      p1[1] = 2;
      p1[2] = 1;
      p1[3] = 3;
      p1[4] = 0;
      p1[5] = 5;
      p1[6] = 7;
      p1[7] = 4;
      p1[8] = 6;
      p1[9] = 0;
      p2[0] = 3;
      p2[1] = 1;
      p2[2] = 6;
      p2[3] = 3;
      p2[4] = 8;
      p2[5] = 0;
      p2[6] = 5;
      p2[7] = 2;
      p3[0] = 8;
      p3[1] = 4;
      p3[2] = 6;
      p3[3] = 0;
      p3[4] = 5;
      p3[5] = 1;
      p3[6] = 7;
      p3[7] = 2;
      p3[8] = 8;
      p3[9] = 0;
      p3[10] = 3;
      p3[11] = 5;
    }

    //Programa principal
    public static void main (String[] args) {
      Ascensor ascensor;
      ascensor = new Ascensor();

      int[] p1 = new int[10];
      int[] p2 = new int[8];
      int[] p3 = new int[12];
      UsaAscensor.inicializarPisos(p1, p2, p3);

      Usuario manolita = new Usuario(ascensor, "Manolita", 0, p1);
      Usuario pepe = new Usuario(ascensor, "Pepe", 3, p2);
      Usuario juan = new Usuario(ascensor, "Juan", 8, p3);
      
      manolita.start();
      pepe.start();
      juan.start();
    }

  }

public class UsaClases {
  public static void main (String[] args) {
    Superclase super1;
    Subclase sub1;

    super1 = new Superclase(3);
    super1.VerDatos();

    sub1 = new Subclase(5, 1);
    sub1.VerDatos();
    sub1.OperacionDatos();
  }
}

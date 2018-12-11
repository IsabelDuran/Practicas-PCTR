public class Subclase extends Superclase {
  private int dato5;  

  public Subclase(int d1, int d5) {
    super(d1);
    dato5 = d5;
  }

  public void VerTodosDatos() {
    VerDatos();
    System.out.println("dato5 = " + dato5);
  }

  public void OperacionDatos() {
    int suma;
    suma = dato1 + dato2 + dato4 + dato5;
    System.out.println("La suma de dato1, dato2, dato4 y dato5 es:");
    System.out.println(dato1 + " + " + dato2 + " + " + dato4 + " + " + dato5 + " = " + suma);
  }
}

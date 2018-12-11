  public class Superclase {
    protected int dato1, dato2;
    private int dato3;
    public int dato4;

    protected Superclase(int d) {
      dato1 = d + 1;
      dato2 = d + 2;
      dato3 = d + 3;
      dato4 = d + 4;
    }

    public void VerDatos() {
      System.out.println("dato1 = " + dato1 + "; dato2 = " + dato2 + "; dato3 = " + 
        dato3 + "; dato4 = " + dato4);
    }

    protected void OperacionDatos() {}
  }

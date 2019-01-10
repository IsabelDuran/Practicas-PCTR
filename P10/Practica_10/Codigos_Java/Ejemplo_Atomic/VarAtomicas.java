  /**
   * Clase VarAtomicas.
   * [Ejemplo]
   * @author Natalia Partera
   * @version 1.0
   */

  import java.util.concurrent.atomic.*;

  public class VarAtomicas extends Thread {

    //Atributos privados
    private AtomicBoolean condicion;
    private AtomicInteger entero;
    private AtomicInteger indice;
    private AtomicLong largo;
    private AtomicLongArray multiplicaciones;

    //Constructor de la clase
    public VarAtomicas(AtomicInteger at, AtomicLong al, AtomicLongArray ala) {
      condicion = new AtomicBoolean(true);
      entero = at;
      indice = new AtomicInteger(0);
      largo = al;
      multiplicaciones = ala;
    }

    //Método run
    public void run() {
      while(condicion.get()) {
          System.out.println(this.getName() + ", multiplicaciones[" + (indice.get()) +
            "] = " + multiplicaciones.getAndSet(indice.get(), 
            largo.get() * entero.get()));
        if(indice.get() == (multiplicaciones.length() - 1)) {
          condicion.set(false);
        }
        else {
          System.out.println(this.getName() + ": Se ha multiplicado por " + 
            entero.getAndDecrement());
          indice.incrementAndGet();
        }
      }
    }

  }

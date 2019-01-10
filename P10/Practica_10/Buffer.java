//ADAPTAR ESTE MONITOR AL API DE ALTO NIVEL DE JAVA
//UTILIZAR CERROJOS DE CLASE REENTRANTLOCK Y VARIABLES CONDITION 

    public class Buffer {        
      private int numSlots = 0;
      private double[] buffer = null;
      private int putIn = 0, takeOut = 0;
      private int cont = 0;
      
      public Buffer(int numSlots) {
        this.numSlots = numSlots;
        buffer = new double[numSlots];
      }
      
      public synchronized void insertar (double valor) {
        while (cont == numSlots)
          try {
            wait();
          } catch (InterruptedException e) {
            System.err.println("wait interrumpido");
          }
        buffer[putIn] = valor;
        putIn = (putIn + 1) % numSlots;
        cont++;                   
        notifyAll();  
      }
      
      public synchronized double extraer () {
        double valor;
        while (cont == 0)
          try {
            wait();
          } catch (InterruptedException e) {
            System.err.println("wait interrumpido");
          }
        valor = buffer[takeOut];
        takeOut = (takeOut + 1) % numSlots;
        cont--;                           
        notifyAll();
        return valor;
      }
    }//Buffer
    








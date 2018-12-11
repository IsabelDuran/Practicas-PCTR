/**
 * @(#)tryTwo.java
 * @author  A.T.
 * @version 1.00 2012/11/9
 * Pre-solucion al control de la e.m. con variables comunes mediante flag de señalización
 * No preserva la e.m. sobre el recurso común.
 */

public class tryTwo
  extends Thread
{
	private int tipoHilo;
	
	private static volatile int nVueltas = 1000000;
	private static volatile int n = 0;
	private static volatile boolean C1 = false;
	private static volatile boolean C2 = false;


    public tryTwo(int tipoHilo)
    {this.tipoHilo=tipoHilo;}
    public void run()
    {
      switch(tipoHilo){
        case 1:{for(int i=0; i<nVueltas; i++){
        	      while(C2==true);
        	      C1 = true;
        	      n++;
        	      C1 = false;
        	      
        	    }
        	    break;}
        case 2: {for(int i=0; i<nVueltas;i++){
        	      while(C1==true);
        	      C2 = true;
        	      n--;
        	      C2 = false;
                }
        	    }break;
      }
    }

    public static void main(String[] args)
      throws InterruptedException
    {
      tryTwo h1 = new tryTwo(1);
      tryTwo h2 = new tryTwo(2);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(n);
    }
}

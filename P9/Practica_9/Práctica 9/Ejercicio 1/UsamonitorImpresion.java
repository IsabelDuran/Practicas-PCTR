import java.util.*;
import java.util.concurrent.*;

public class UsamonitorImpresion implements Runnable
{
    private static monitorImpresion impresora;
    private int hilo;
    
    UsamonitorImpresion(int hilo)
    {
      this.hilo = hilo;
    } 

    public void run()
    {
       try
       {
       	 int imp;
       	 imp = impresora.pedir_impresoras();
       	 System.out.println("El proceso "+ hilo + " esta usando la impresora "+ imp);    
       	 impresora.dejar_impresoras(imp);

       } catch (InterruptedException e) {} 
    }

	public static void main(String[] args) throws InterruptedException
	{
      impresora = new monitorImpresion();
      
      double cB = 0.5;
      double Nt = (double)Runtime.getRuntime().availableProcessors()/(1-cB);
      int nt = (int)Math.round(Nt);
      ExecutorService hilos = Executors.newFixedThreadPool(nt);
      

      System.out.println("Num. hebras: "+nt);

      for(int i = 0; i < nt; i++)
      	hilos.execute(new UsamonitorImpresion(i));

      hilos.shutdown();
      hilos.awaitTermination(1,TimeUnit.DAYS);

      System.out.println("FIN");

	}
}
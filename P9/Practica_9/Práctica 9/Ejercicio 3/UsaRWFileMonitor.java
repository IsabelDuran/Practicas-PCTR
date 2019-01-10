import java.util.*;
import java.util.concurrent.*;
import java.io.*; //añadir para las excepciones de fichero

public class UsaRWFileMonitor implements Runnable
{
	static RWFileMonitor monitor;
    int id;

    UsaRWFileMonitor(int id)
    {
      this.id = id;
    }

    public void run()
    {
     // try
     // {
    	for(int i = 0; i < 1; i++)
    	{
    		switch(id)
    		{
    			case 0: monitor.StartRead();
    			        try
                        {
                          monitor.fichero.seek(0);
                          System.out.println("El hilo "+ this.id + " ha leido: "+ monitor.fichero.readLine());
                        } catch(IOException e)
                          {
                            System.out.println("Error en la escritura");
                          }
    			        monitor.EndRead();
    			break;
                case 1: monitor.StartWrite();
                        try
    					{
      						monitor.fichero.writeBytes(" Escribe el hilo "+id + "\n");
    					} catch(IOException e)
      						{
        						System.out.println("Error en la escritura");
      						}
                        monitor.EndWrite();
                break;
    		}
    	}
     // } catch(InterruptedException e) { System.out.println("ERROR"); }

    }

	public static void main(String[] args) throws Exception
	{
       monitor = new RWFileMonitor();

       ExecutorService hilos = Executors.newFixedThreadPool(2);

       for(int i=0; i < 2; i++)
       	hilos.execute(new UsaRWFileMonitor(i));

       hilos.shutdown();
       hilos.awaitTermination(1,TimeUnit.DAYS);

       System.out.println("FINALIZADO");
	}
}
import java.util.concurrent.*;
import java.util.Random;

public class UsamonitorCadena implements Runnable
{
     
    public static monitorCadena_1 b1 = new monitorCadena_1();
    public static monitorCadena_2 b2 = new monitorCadena_2();
    public int hilo;

    UsamonitorCadena(int hilo)
    {
    	this.hilo = hilo;
    }

    public void run() 
    {
       try
       {
       	 for(int i = 0; i < 100; i++)
       	 {
       	 	switch(hilo)
       	 	{
       	 		case 0: int[][] m = new int [5][5];
                    for(int j = 0; j < m.length; j++)
       	 	        	{
       	 	        		for(int k = 0; k < m[j].length; k++)
       	 	        		{
       	 	        			m[j][k] = (int)Math.floor(Math.random()*(1-10)+10); //Numero aleatorio sin decimales entre 1 y 10
       	 	        		}
       	 	         	
       	 	        	}

       	 	        	System.out.println("CASO 0");
       	 	       	  	//mostrar(m);


       	 	         	b1.insertar(m); //
       	 		break;
       	 		case 1: int[][] aux = new int[5][5];
                    int[][] b = b1.extraer(); //
                    System.out.println("CASO 1");
                   
       	 	        	for(int x = 0; x < b.length; x++)
                     		for(int y = 0; y < b[x].length; y++)
                     			aux[y][x] = b[x][y];
                    // mostrar(aux);

                    	b2.insertar(aux);
       	 		break;
       	 		case 2: int[][] c = b2.extraer(); //
       	 	    	    int prod = 1;
                    System.out.println("CASO 2");
                      
       	 	        	for(int z = 0; z < c.length; z++)
       	 	        		prod*= c[z][z];
                //    mostrar(c);

       	 	        	System.out.println("\nResultado: "+prod);
       	 		break;
       	 	}
         }
       } catch(InterruptedException e) { System.out.println("ERROR"); }
    }

	public static void main(String[] args) throws Exception
	{
       ExecutorService matrices = Executors.newFixedThreadPool(3);

       for(int i=0; i < 3; i++)
       	matrices.execute(new UsamonitorCadena(i));

       matrices.shutdown();
       matrices.awaitTermination(1,TimeUnit.DAYS);

       System.out.println("FINALIZADO");
	}

	public void mostrar(int[][] m)
	{
		for(int i = 0; i < m.length; i++)
		{

			for(int j = 0; j < m.length; j++)
			{
			   System.out.print(m[i][j] + "|");
			}
			System.out.println();
		}
	}

}
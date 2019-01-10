/*
   Monitor Teórico

 */
public class monitorImpresion
{
   private static int impresoras;
   private static boolean [] impresorasLibres;

   monitorImpresion()
   {
   	 impresoras = 3;
   	 impresorasLibres = new boolean[3];

   	 for(int i = 0; i < impresoras; i++)
   	 	impresorasLibres[i] = true;
   }

   synchronized int pedir_impresoras() throws InterruptedException
   {
   	 int i = 0;

     while(impresoras == 0)
     	wait();
   
     while(!impresorasLibres[i])
     	i++;
  
     impresorasLibres[i] = false;
     impresoras--;

     return i;
   }

   synchronized void dejar_impresoras(int n) throws InterruptedException
   {
      impresorasLibres[n] = true;
      impresoras++;
      notifyAll();
      
      System.out.println("La impresora "+ n + " esta ahora libre.");  
   }

}
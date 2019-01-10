import java.util.ArrayList;

public class monitorCadena_2 
{
  private ArrayList<int[][]> buffer_2;

  monitorCadena_2()
  {
    buffer_2 = new ArrayList<int[][]>();
  }

  public synchronized void insertar(int[][] m) throws InterruptedException
  {
       while(buffer_2.size() == 50)
        wait();
       
       buffer_2.add(m);
       notifyAll();
  }

    public synchronized int[][] extraer() throws InterruptedException
    {
       while(buffer_2.size() == 0)
        wait();
       
       int[][] aux = buffer_2.get(0);
       buffer_2.remove(0);

       notifyAll();
       return aux;
    }

}
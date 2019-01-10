import java.util.ArrayList;

public class monitorCadena_1
{
    private ArrayList<int[][]> buffer_1;

	monitorCadena_1()
	{
       buffer_1 = new ArrayList<int[][]>();
	}

	public synchronized void insertar(int[][] m) throws InterruptedException
	{
       while(buffer_1.size() == 100)
       	wait();
       
       buffer_1.add(m);
       notifyAll();
	}

    public synchronized int[][] extraer() throws InterruptedException
    {
       while(buffer_1.size() == 0)
       	wait();
       
       int[][] aux = buffer_1.get(0);
       buffer_1.remove(0);

       notifyAll();
       return aux;
    }

}
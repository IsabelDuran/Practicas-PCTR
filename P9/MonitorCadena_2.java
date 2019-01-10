package buffer;

import java.util.ArrayList;

public class MonitorCadena_2 {
	ArrayList<int[][]> buffer_2 = new ArrayList<>();
	private static int cont = 0;
	private static final int numSlots = 50;

	public synchronized void insertar(int[][] matriz) throws InterruptedException {
		while (cont == numSlots) {
			try {
				wait();
			} catch (InterruptedException ee) {
				throw ee;
			}
		}
		cont++;
		buffer_2.add(matriz);
		notifyAll();
	}
	
	  public synchronized int[][] extraer () {
		  int[][] matriz;
		  while(buffer_2.size() == 0) {
			  try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		  cont--;
		  matriz = buffer_2.get(0);
		  buffer_2.remove(0);
		  notifyAll();
		  return matriz;
	  }
}

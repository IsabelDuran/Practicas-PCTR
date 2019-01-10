package buffer;

import java.util.ArrayList;

public class MonitorCadena_1 {
	ArrayList<int[][]> buffer_1 = new ArrayList<>();
	private static int cont = 0;
	private static final int numSlots = 100;

	public synchronized void insertar(int[][] matriz) throws InterruptedException {
		while (cont == numSlots) {
			try {
				wait();
			} catch (InterruptedException ee) {
				throw ee;
			}
		}
		cont++;
		buffer_1.add(matriz);
		notifyAll();
	}
	
	  public synchronized int[][] extraer () {
		  int[][] matriz;
		  while(buffer_1.size() == 0) {
			  try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		  cont--;
		  matriz = buffer_1.get(0);
		  buffer_1.remove(0);
		  notifyAll();
		  return matriz;
	  }

}

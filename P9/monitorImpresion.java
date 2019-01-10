package monitorimpresion;

public class monitorImpresion {
	private int impresoras;
	private boolean[] libre = new boolean[Runtime.getRuntime().availableProcessors()];

	
	public monitorImpresion() {
		impresoras = Runtime.getRuntime().availableProcessors();
		for(int i = 0; i < impresoras; i++)
			libre[i] = true;
	}
	
	public synchronized int take_print() throws InterruptedException {
		while(impresoras == 0) {
			try {
				wait();
			}catch(InterruptedException e) {throw e;}
		}
		int n = 0;
		while(!libre[n])
			if(n < 11)
				n++;
		libre[n] = false;
		impresoras--;
		return n;
	}
	
	public synchronized void drop_print(int n) {
		libre[n] = true;
		impresoras++;
		notifyAll();
	}
}

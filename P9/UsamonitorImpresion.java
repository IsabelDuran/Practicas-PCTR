package monitorimpresion;

import java.util.ArrayList;

public class UsamonitorImpresion implements Runnable {
	public static monitorImpresion monitor = new monitorImpresion();
	private int hilo;

	public UsamonitorImpresion(int h) {
		hilo = h;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				monitor.take_print();
				monitor.drop_print(hilo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		int nHilos = Runtime.getRuntime().availableProcessors();
		ArrayList<Thread> misHilos = new ArrayList();
		
		for (int i = 0; i < nHilos; i++) {
			misHilos.add(new Thread(new UsamonitorImpresion(i)));
			misHilos.get(i).start();
		}
		
		for(Thread hilo : misHilos) {
			hilo.join();
		}
	}
}

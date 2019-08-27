import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
public class aRendimiento {
	public static final int nTareas = 100;
	public static ArrayList<Integer> nIteraciones = new ArrayList<>();
	public static ArrayList<Long> tiemposSyn = new ArrayList<>();
	public static ArrayList<Long> tiemposRL = new ArrayList<>();
	public static ArrayList<Long> tiemposAI = new ArrayList<>();
	
	public static Object cerreojoSyn = new Object();
	public static ReentrantLock cerrojoRL = new ReentrantLock();
	
	public static int contadorSyn = 0;
	public static int contadorRL = 0;
	public static AtomicInteger contadorAI = new AtomicInteger(0);

	public static ExecutorService ejecutor;

	private static void addIteraciones() {
		for (int i = 1000; i <= 10000; i += 1000)
			nIteraciones.add(i);
	}

	private static void rendimientoSyn(int nIter) {
		ejecutor = Executors.newCachedThreadPool();
		for(int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j <  nIter; j++) {
						synchronized (cerreojoSyn) {
							contadorSyn++;
						}
					}
					
				}
			});
		}
		ejecutor.shutdown();
		while(!ejecutor.isTerminated());
	}
	
	private static void rendimientoAI(int nIter) {
		ejecutor = Executors.newCachedThreadPool();
		for(int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j <  nIter; j++)
						contadorAI.incrementAndGet();
					
				}
			});
		}
		ejecutor.shutdown();
		while(!ejecutor.isTerminated());
		
	}

	private static void rendimientoRL(int nIter) {
		ejecutor = Executors.newCachedThreadPool();
		for(int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j <  nIter; j++) {
						cerrojoRL.lock();
						try {
							contadorRL++;
						} finally {
							cerrojoRL.unlock();
						}
					}
					
				}
			});
		}
		ejecutor.shutdown();
		while(!ejecutor.isTerminated());
		
	}

	private static void imprimirTiempos() {
		System.out.println("Iteraciones		Synchronized		ReentrantLock		AtomicInteger");
		for (int i = 0; i < nIteraciones.size(); i++)
			System.out.println(
					nIteraciones.get(i) + "			" + tiemposSyn.get(i) + "			" + tiemposRL.get(i) + "			" + tiemposAI.get(i));

	}
	
	public static void main(String[] args) {
		long inicioSyn, finSyn, inicioRL, finRL, inicioAI, finAI;
		addIteraciones();

		for (int nIter : nIteraciones) {

			inicioSyn = System.currentTimeMillis();
			rendimientoSyn(nIter);
			finSyn = System.currentTimeMillis();
			
			tiemposSyn.add(finSyn - inicioSyn);
			
			inicioRL = System.currentTimeMillis();
			rendimientoRL(nIter);
			finRL = System.currentTimeMillis();
			
			tiemposRL.add(finRL - inicioRL);
			
			inicioAI = System.currentTimeMillis();
			rendimientoAI(nIter);
			finAI = System.currentTimeMillis();
			
			tiemposAI.add(finAI - inicioAI);
		}
		
		imprimirTiempos();

	}

}

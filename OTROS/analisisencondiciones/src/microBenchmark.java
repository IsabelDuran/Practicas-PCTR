import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
public class microBenchmark {
	public static AtomicInteger contadorAT = new AtomicInteger(0);
	public static ReentrantLock cerrojo = new ReentrantLock();
	public static ExecutorService ejecutor;
	public static int contadorRT = 0;

	public static ArrayList<Integer> nIteraciones = new ArrayList<>();
	public static ArrayList<Long> tiemposAT = new ArrayList<>();
	public static ArrayList<Long> tiemposRT = new ArrayList<>();

	private static void rendimientoAT(int nTareas) {
		ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		ejecutor.execute(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < nTareas; i++)
					contadorAT.incrementAndGet();

			}
		});

		ejecutor.shutdown();
		while (!ejecutor.isTerminated())
			;

	}

	private static void rendimientoRL(int nTareas) {
		ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		ejecutor.execute(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < nTareas; i++) {
					cerrojo.lock();
					try {
						contadorRT++;

					} finally {
						cerrojo.unlock();
					}
				}

			}
		});

		ejecutor.shutdown();
		while (!ejecutor.isTerminated())
			;

	}

	/**
	 * 
	 */
	private static void addTareas() {
		for (int i = 1000; i <= 1000000; i *= 10)
			nIteraciones.add(i);
	}

	private static void imprimirTiempos() {
		System.out.println("Iteraciones		AtomicInteger		ReentrantLock");
		for (int i = 0; i < nIteraciones.size(); i++)
			System.out.println(
					nIteraciones.get(i) + "			" + tiemposAT.get(i) + "			" + tiemposRT.get(i));

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long inicioAT, finAT, tiempoAT, inicioRL, finRL, tiempoRL;

		addTareas();

		for (int nTareas : nIteraciones) {
			contadorAT = new AtomicInteger(0);
			inicioAT = System.currentTimeMillis();
			rendimientoAT(nTareas);
			finAT = System.currentTimeMillis();
			tiempoAT = finAT - inicioAT;

			tiemposAT.add(tiempoAT);

			contadorRT = 0;

			inicioRL = System.currentTimeMillis();
			rendimientoRL(nTareas);
			finRL = System.currentTimeMillis();
			tiempoRL = finRL - inicioRL;

			tiemposRT.add(tiempoRL);

		}
		imprimirTiempos();
	}

}

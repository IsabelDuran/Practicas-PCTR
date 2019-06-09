package analisisRendimiento;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class aRendimiento {
	public static final int nTareas = 100;

	public static ReentrantLock cerrojoRL = new ReentrantLock();
	public static Object cerrojoSyn = new Object();
	public static AtomicInteger atomicInt = new AtomicInteger(0);

	public static ArrayList<Integer> tareas = new ArrayList<>();
	public static ArrayList<Long> tiemposSyn = new ArrayList<>();
	public static ArrayList<Long> tiemposRL = new ArrayList<>();
	public static ArrayList<Long> tiemposAI = new ArrayList<>();

	public static int contador = 0;

	public static void addTareas() {
		for (int i = 10000; i <= 100000; i += 1000) {
			tareas.add(i);
		}
	}

	private static void contadorSyn(Integer nIteraciones) {
		synchronized (cerrojoSyn) {
			for (int i = 0; i < nIteraciones; i++)
				contador++;
		}

	}

	private static void rendimientoSyn(Integer nIteraciones) {
		ExecutorService ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {

				@Override
				public void run() {
					contadorSyn(nIteraciones);
				}
			});
		}
		ejecutor.shutdown();
		while (!ejecutor.isTerminated());

	}

	private static void contadorRL(Integer nIteraciones) {
		cerrojoRL.lock();
		try {
			contador++;
		} finally {
			cerrojoRL.unlock();
		}

	}

	private static void rendimientoRL(Integer nIteraciones) {
		ExecutorService ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {

				@Override
				public void run() {
					contadorRL(nIteraciones);
				}
			});
		}
		ejecutor.shutdown();
		while (!ejecutor.isTerminated());
	}
	
	private static void contadorAI(Integer nIteraciones) {
		atomicInt.incrementAndGet();
	}

	private static void rendimientoAI(Integer nIteraciones) {
		ExecutorService ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < nTareas; i++) {
			ejecutor.execute(new Runnable() {

				@Override
				public void run() {
					contadorAI(nIteraciones);
				}
			});
		}
		ejecutor.shutdown();
		while (!ejecutor.isTerminated());

	}

	public static void main(String[] args) {
		long startSyn, finSyn, startRL, finRL, startAI, finAI;
		addTareas();
		for (Integer nIteraciones : tareas) {
			// Medición de tiempos utilizando Synchronized
			startSyn = System.currentTimeMillis();
			rendimientoSyn(nIteraciones);
			finSyn = System.currentTimeMillis();
			tiemposSyn.add(finSyn - startSyn);

			contador = 0; // Devolvemos el contador a 0

			// Medición de tiempos utilizando ReentrantLock
			startRL = System.currentTimeMillis();
			rendimientoRL(nIteraciones);
			finRL = System.currentTimeMillis();
			tiemposRL.add(finRL - startRL);
			
			// Medición de tiempos utilizando AtomicInteger
			startAI = System.currentTimeMillis();
			rendimientoAI(nIteraciones);
			finAI = System.currentTimeMillis();
			tiemposAI.add(finAI - startAI);
		}
		
		System.out.println("Iter/Tarea Synchronized ReentrantLock AtomicInteger");
		for(int i = 0; i < tareas.size(); i++) {
			System.out.print(tareas.get(i) + "\t\t" + tiemposSyn.get(i) + "\t\t" + tiemposRL.get(i) + "\t\t" + tiemposAI.get(i));
			System.out.println();
		}
		
	}

}

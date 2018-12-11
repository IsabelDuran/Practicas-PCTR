package lamport;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author isa
 *
 */
public class algoLamport {
	public static final int iteraciones = 200000;
	public static volatile int nProc = 4;
	public static volatile int inCS = 0;
	public Executor executor;
	public static volatile boolean[] eleccion = new boolean[nProc];
	public static volatile int[] prioridad = new int[nProc];

	/**
	 * @author isa
	 *
	 */
	public class P implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			for (int i = 0; i < iteraciones; i++) {
				eleccion[0] = true;
				prioridad[0] = Math.max(prioridad[0], Math.max(prioridad[1], Math.max(prioridad[2], prioridad[3])));
				eleccion[0] = false;

				for (int j = 0; j < nProc; j++) {
					while (eleccion[j] && j != 0) {
					}
					while (prioridad[j] != 0
							&& (prioridad[0] > prioridad[j] || prioridad[0] == prioridad[j] && 0 > j)) {
					}
				}
				inCS++;
				System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                prioridad[0] = 0;
			}
		}}

		/**
		 * @author isa
		 *
		 */
		public class Q implements Runnable {
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				for (int i = 0; i < iteraciones; i++) {
					eleccion[1] = true;
					prioridad[1] = Math.max(prioridad[0], Math.max(prioridad[1], Math.max(prioridad[2], prioridad[3])));
					eleccion[1] = false;

					for (int j = 0; j < nProc; j++) {
						while (eleccion[j] && j != 1) {
						}
						while (prioridad[j] != 1
								&& (prioridad[1] > prioridad[j] || prioridad[1] == prioridad[j] && 1 > j)) {
						}
					}
					inCS++;
					System.out.println("Number of processes in critical section: "
	                        + inCS);
	                inCS--;
	                prioridad[1] = 0;
				}
			}
		}

		/**
		 * @author isa
		 *
		 */
		public class R implements Runnable {
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				for (int i = 0; i < iteraciones; i++) {
					eleccion[2] = true;
					prioridad[2] = Math.max(prioridad[0], Math.max(prioridad[1], Math.max(prioridad[2], prioridad[3])));
					eleccion[2] = false;

					for (int j = 0; j < nProc; j++) {
						while (eleccion[j] && j != 2) {
						}
						while (prioridad[j] != 2
								&& (prioridad[2] > prioridad[j] || prioridad[2] == prioridad[j] && 2 > j)) {
						}
					}
					inCS++;
					System.out.println("Number of processes in critical section: "
	                        + inCS);
	                inCS--;
	                prioridad[2] = 0;
				}
			}

		}

		/**
		 * @author isa
		 *
		 */
		public class S implements Runnable {
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				for (int i = 0; i < iteraciones; i++) {
					eleccion[3] = true;
					prioridad[3] = Math.max(prioridad[0], Math.max(prioridad[1], Math.max(prioridad[2], prioridad[3])));
					eleccion[3] = false;

					for (int j = 0; j < nProc; j++) {
						while (eleccion[j] && j != 3) {
						}
						while (prioridad[j] != 3
								&& (prioridad[3] > prioridad[j] || prioridad[3] == prioridad[j] && 3 > j)) {
						}
					}
					inCS++;
					System.out.println("Number of processes in critical section: "
	                        + inCS);
	                inCS--;
	                prioridad[3] = 0;
				}
			}
		}
	

	/**
	 * @param nProc
	 */
	public algoLamport() {
		for (int i = 0; i < eleccion.length; i++)
			eleccion[i] = false;
		for (int i = 0; i < prioridad.length; i++)
			prioridad[i] = 0;

		executor = Executors.newFixedThreadPool(nProc);
		executor.execute(new P());
		executor.execute(new Q());
		executor.execute(new R());
		executor.execute(new S());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new algoLamport();
	}
}

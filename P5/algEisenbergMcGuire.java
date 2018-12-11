package eidenberg;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author isa
 *
 */
public class algEisenbergMcGuire {
	public static volatile int inCS = 0;
	public static volatile int turno = 0;
	public Executor executor;

	public static enum flag {
		ILDE, WANTIN, INCS
	};

	public static volatile flag[] pstate = new flag[2];

	/**
	 * @author isa
	 *
	 */
	class P implements Runnable {
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				int index = turno;
				do {
					pstate[0] = flag.WANTIN;
					while (index != 0) {
						if (pstate[0] != flag.ILDE)
							index = turno;
						else
							index = (index + 1) % 2;
					}

					pstate[0] = flag.INCS;
					index = 0;
					while ((index < 2) && (index == 0) || pstate[0] != flag.INCS) {
						index++;
					}
				} while (index >= 2 && turno == 0 || pstate[0] == flag.ILDE);
				
				turno = 0;
				inCS++;
				//SECCIÓN CRÍTICA
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                index = (index + 1) % 2;
                while(pstate[index] == flag.ILDE)
                	index = (index + 1) % 2;
                
                turno = index;
                pstate[0] = flag.ILDE;
				
			}
		}
	}

	/**
	 * @author isa
	 *
	 */
	class Q implements Runnable {
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				int index = turno;

				do {
					pstate[1] = flag.WANTIN;
					while (index != 1) {
						if (pstate[1] != flag.ILDE)
							index = turno;
						else
							index = (index + 1) % 2;
					}

					pstate[1] = flag.INCS;
					index = 1;
					while ((index < 2) && (index == 1) || pstate[1] != flag.INCS) {
						index++;
					}
				} while (index >= 2 && turno == 1 || pstate[1] == flag.ILDE);
				
				turno = 1;
				inCS++;
				//SECCIÓN CRÍTICA
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                
                index = (index + 1) % 2;
                while(pstate[index] == flag.ILDE)
                	index = (index + 1) % 2;
                
                turno = index;
                pstate[1] = flag.ILDE;
				
			}
		}
	}

	/**
	 * 
	 */
	public algEisenbergMcGuire() {
		pstate[0] = flag.ILDE;
		pstate[1] = flag.ILDE;
		executor = Executors.newFixedThreadPool(2);
		executor.execute(new P());
		executor.execute(new Q());

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new algEisenbergMcGuire();
	}

}

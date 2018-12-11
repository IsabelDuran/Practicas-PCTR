import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class escalaVectorParalelo implements Runnable {
	private static int[] vectorP = new int[100000000];
	private int inicio, fin;
	private static int n = 5;
	private static double factorEscala;

	/**
	 * @param factorEscala
	 */
	public static void setFactorEscala(double factorEscala) {
		escalaVectorParalelo.factorEscala = factorEscala;
	}

	/**
	 * @param inicio
	 * @param fin
	 */
	public escalaVectorParalelo(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		for (int i = inicio; i < fin; i++) {
			vectorP[i] *= factorEscala;
		}
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		double factorEsc = sc.nextDouble();
		escalaVectorParalelo.setFactorEscala(factorEsc);
		sc.close();
		
		ArrayList<Thread> misHilos = new ArrayList<>();		
		int tamTarea = vectorP.length / n;
		int inicio = 0;
		
		for (int i = 0; i < n - 1; i++) {
			misHilos.add(new Thread(new escalaVectorParalelo(inicio, inicio + tamTarea)));
			inicio += tamTarea;	
		}
		
		misHilos.add(new Thread(new escalaVectorParalelo(inicio, vectorP.length - 1)));
		
		for(Thread i : misHilos)
			i.start();
		for(Thread i : misHilos)
			i.join();
		System.out.println("Finalizado");
	}
}

package matvector;


/**
 * @author isa
 *
 */
public class prodMatConcurrente extends Thread {
	static double[][] matrizA, matrizB, matrizResultado;
	
	public int fila;
	
	/**
	 * @param fila
	 */
	public prodMatConcurrente(int fila) {
		this.fila = fila;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		for(int i = 0; i < matrizResultado[0].length; i++)
			for(int j = 0; j < matrizResultado[0].length; j++)
				matrizResultado[fila][i] += matrizA[fila][j] * matrizB[j][fila];
	}
}
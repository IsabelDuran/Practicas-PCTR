package matvector;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class usaProdMat {

	/**
	 * @param matriz
	 */
	public static void imprimeMatriz(double[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.println("");
			for (int j = 0; j < matriz.length; j++)
				System.out.print(matriz[i][j] + " ");
		}
	}

	/**
	 * @param tamMatriz
	 * @return
	 */
	public static double[][] crearMatriz(int tamMatriz) {
		Scanner sc = new Scanner(System.in);

		double[][] matriz = new double[tamMatriz][tamMatriz];

		System.out.println("Introduce los datos de la Matriz:");
		for (int i = 0; i < tamMatriz; i++) {
			for (int j = 0; j < tamMatriz; j++)
				matriz[i][j] = sc.nextDouble();

		}
		return matriz;
	}
	
	/**
	 * @param tamMatriz
	 * @return
	 */
	public static double[][] crearMatrizAleatoria(int tamMatriz) {
		double[][] matriz = new double[tamMatriz][tamMatriz];

		for (int i = 0; i < tamMatriz; i++) {
			for (int j = 0; j < tamMatriz; j++)
				matriz[i][j] = Math.random() * 10;

		}
		return matriz;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el tamaño de la Matriz:");
		int n = 1000;
		double[][] matrizA = crearMatrizAleatoria(n);
		double[][] matrizB = crearMatrizAleatoria(n);
		
		prodMatConcurrente.matrizA = matrizA;
		prodMatConcurrente.matrizB = matrizB;
		prodMatConcurrente.matrizResultado = new double[n][n];

		long start = System.currentTimeMillis();
		ArrayList<Thread> misHilos = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			misHilos.add(new Thread(new prodMatConcurrente(i)));
	
		for(Thread i : misHilos)
			i.start();

		for(Thread i : misHilos)
			i.join();
		
		long end = System.currentTimeMillis();
		
		System.out.println("El tiempo es: " + (end - start));
		
		
	}
}

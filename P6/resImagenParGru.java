package resimagen;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author isa
 *
 */
public class resImagenParGru implements Runnable {
	public static int tam;
	public static int[][] matriz;
	public int inicio;
	public int fin;
	public static Executor executor;

	/**
	 * @param f
	 */
	public resImagenParGru(int i, int f) {
		inicio = i;
		fin = f;
	}

	/**
	 * @param n
	 */
	private static void randomMatrix(int n) {
		tam = n;
		matriz = new int[tam][tam];
		matriz = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matriz[i][j] = (int) (Math.random() * 20);
	}

	/**
	 * @param mat
	 */
	public static void imprimeMatriz(int[][] mat) {
		for (int[] vec : mat) {
			System.out.println();
			for (int el : vec)
				System.out.print(el + " ");
		}
		System.out.println("\n");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = inicio; i < fin; i++)
			for (int j = 0; j < matriz[inicio].length; j++)
				if (i != 0 && j != 0 && i != (tam - 1) && j != (tam - 1))
					matriz[i][j] = 4 * matriz[i][j] - matriz[i + 1][j] - matriz[i][j + 1] - matriz[i - 1][j] - matriz[i][j - 1];
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Tamaño de la matriz: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		randomMatrix(n);

		int nHilos = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(nHilos);

		int tamanoTarea = n / nHilos;
		int inicio = 0;

		long start = System.nanoTime();
		for (int i = 0; i < nHilos; i++) {
			executor.execute(new resImagenParGru(inicio, inicio + tamanoTarea));
			inicio += tamanoTarea;
		}

		long end = System.nanoTime();

		System.out.println("El tiempo es: " + (end - start) / 1.0e9);
	}

}

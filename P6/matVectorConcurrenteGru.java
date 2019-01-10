package matvector;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author isa
 *
 */
public class matVectorConcurrenteGru implements Runnable {
	public static int matriz[][];
	public static int vector[];
	public static int vectorResultado[];
	public static Executor executor;

	public int inicio, fin;

	/**
	 * @param i
	 * @param f
	 */
	public matVectorConcurrenteGru(int i, int f) {
		inicio = i;
		fin = f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int resultado = 0;
		for (int i = inicio; i < fin; i++) {
			for (int j = 0; j < matriz[inicio].length; j++)
				resultado += matriz[inicio][j] * vector[inicio];
			vectorResultado[inicio] = resultado;
		}

	}

	/**
	 * @param n
	 */
	private static void randomMatrix(int n) {
		matriz = new int[n][n];
		vector = new int[n];
		for (int i = 0; i < n; i++) {
			vector[i] = (int) (Math.random() * 3);
			for (int j = 0; j < n; j++)
				matriz[i][j] = (int) (Math.random() * 3);
		}
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

	/**
	 * @param vec
	 */
	public static void imprimeVector(int[] vec) {
		for (int elto : vec)
			System.out.print(elto + " ");
		System.out.println("\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int nHilos = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(nHilos);

		Scanner sc = new Scanner(System.in);
		System.out.println("Tamaño de la matriz:");
		int n = sc.nextInt();
		randomMatrix(n);
		vectorResultado = new int[n];

		int tamanoTarea = n / nHilos;
		int inicio = 0;

		for (int i = 0; i < nHilos; ++i) {
			executor.execute(new matVectorConcurrenteGru(inicio, inicio + tamanoTarea));
			inicio += tamanoTarea;
		}
		
		imprimeVector(vectorResultado);

	}

}

package resimagen;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class resImagenParFin extends Thread {
	public static int tam;
	public static int[][] matriz;
	public int fila;

	/**
	 * @param f
	 */
	public resImagenParFin(int f) {
		fila = f;
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
		for (int j = 0; j < tam; j++)
			if (fila != 0 && j != 0 && fila != (tam - 1) && j != (tam - 1))
				matriz[fila][j] = 4 * matriz[fila][j] - matriz[fila + 1][j] - matriz[fila][j + 1] - matriz[fila - 1][j]
						- matriz[fila][j - 1];
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
		
		ArrayList<Thread> misHilos = new ArrayList<>();

		long start = System.nanoTime();
		for (int i = 0; i < n; ++i)
			misHilos.add(new resImagenParFin(i));

		for (Thread i : misHilos)
			i.start();
		for (Thread i : misHilos)
			i.join();
	
		long end = System.nanoTime();

		System.out.println("El tiempo es: " + (end - start) / 1.0e9);
	}

}

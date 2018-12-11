package resimagen;

import java.util.Scanner;

/**
 * @author isa
 *
 */
public class resImagen {
	static int tam;
	static int[][] matriz;

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

	/**
	 * 
	 */
	public static void calcularResaltado() {
		for (int i = 0; i < tam; i++)
			for (int j = 0; j < tam; j++)
				if (i != 0 && j != 0 && i != (tam - 1) && j != (tam - 1))
					matriz[i][j] = 4 * matriz[i][j] - matriz[i + 1][j] - matriz[i][j + 1] - matriz[i - 1][j] - matriz[i][j - 1];
			
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Tamaño de la matriz: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		randomMatrix(n);
		
		long start = System.nanoTime();
		calcularResaltado();
		long end = System.nanoTime();
		
		System.out.println("El tiempo es: " + (end - start)/1.0e9);
	}
}

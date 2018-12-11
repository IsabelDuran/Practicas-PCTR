package matvector;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class matVectorConcurrente implements Runnable {

	public static int n = 12;
	public static int matriz[][];
	public static int vector[];
	public static int vectorResultado[];

	public int fila;

	/**
	 * @param fila
	 */
	public matVectorConcurrente(int fila) {
		this.fila = fila;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		int resultado = 0;
		for (int i = 0; i < n; i++)
			resultado += matriz[fila][i] * vector[i];

		vectorResultado[fila] = resultado;
	}

	/**
	 * 
	 */
	private static void randomData() {
		n = (int) (Math.random() * 2) + 1;

		matriz = new int[n][n];
		vector = new int[n];
		for (int i = 0; i < n; i++) {
			vector[i] = (int) (Math.random() * 3);
			for (int j = 0; j < n; j++)
				matriz[i][j] = (int) (Math.random() * 3);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		vectorResultado = new int[n];
		
		System.out.println("1.Introducir datos");
		System.out.println("2.Generar datos aleatoriamente");
		int op = sc.nextInt();

		switch (op) {
		case 1:
			setData();
			break;
		case 2:
			randomData();
			break;
		}


		ArrayList<Thread> misHilos = new ArrayList<>();

		for (int i = 0; i < n; ++i)
			misHilos.add(new Thread(new matVectorConcurrente(i)));

		for (Thread i : misHilos)
			i.start();
		for (Thread i : misHilos)
			i.join();

		System.out.println("Matriz");
		imprimeMatriz(matriz);
		System.out.println("Vector");

		imprimeVector(vector);
		System.out.println("Resultado");

		imprimeVector(vectorResultado);

	}

	/**
	 * 
	 */
	public static void setData() {
		Scanner sc = new Scanner(System.in);
		int valor;
		System.out.println("Introduce un tamaño:");
		n = sc.nextInt();

		matriz = new int[n][n];
		vector = new int[n];

		System.out.println("Introduce los datos de la Matriz:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				valor = sc.nextInt();
				matriz[i][j] = valor;
			}
		}

		System.out.println("Introduce los datos del VectorIni:");
		for (int i = 0; i < n; i++) {
			vector[i] = sc.nextInt();
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

}

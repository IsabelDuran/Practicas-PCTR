package acGreenbergHastings;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class acGreenbergHastings implements Runnable {
	public static Scanner sc = new Scanner(System.in);
	public static ThreadPoolExecutor ejecutor;
	public static int[][] reticulaPrimera;
	public static int[][] reticulaSegunda;
	public static int n;
	public static int m;
	public static int t;

	int inicio, fin;

	public acGreenbergHastings(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public void run() {
		for (int fila = inicio; fila < fin; fila++)
			for (int columna = 0; columna < n; columna++)
				comprobarVecindad(fila, columna);

	}

	private void comprobarVecindad(int fila, int columna) {
		int[][] matCeldasAdyacentes = new int[3][3];
		rellenarCeldasAdyacentes(matCeldasAdyacentes, fila, columna);

		if (reticulaPrimera[fila][columna] == 0)
			reticulaSegunda[fila][columna] = 1;
		else if (reticulaPrimera[fila][columna] == 1)
			reticulaSegunda[fila][columna] = 2;
		else if(reticulaPrimera[fila][columna] == 2 && numeroVecinasExcitadas(matCeldasAdyacentes) >= 1)
			reticulaSegunda[fila][columna] = 0;
		else
			reticulaSegunda[fila][columna] = 2;

	}

	private int numeroVecinasExcitadas(int[][] matCeldasAdyacentes) {
		int nVecinas = 0;
		for (int i = 0; i < matCeldasAdyacentes.length; i++) {
			for (int j = 0; j < matCeldasAdyacentes.length; j++) {
				if(matCeldasAdyacentes[i][j] == 0)
					nVecinas++;
			}
		}
		return nVecinas;
	}

	private void rellenarCeldasAdyacentes(int[][] matCeldasAdyacentes, int fila, int columna) {
		for (int i = 0; i < matCeldasAdyacentes.length; i++) {
			for (int j = 0; j < matCeldasAdyacentes.length; j++) {
				matCeldasAdyacentes[i][j] = 0;
			}
		}

		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if ((fila + i) >= 0 && (fila + i) < n && (columna + j) >= 0 && (columna + j) < n)
					matCeldasAdyacentes[i + 1][j + 1] = reticulaPrimera[fila + i][columna + j];

	}

	private static void imprimirMatriz(int[][] reticula) {
		for (int i = 0; i < reticula.length; i++) {
			for (int j = 0; j < reticula.length; j++) {
				System.out.print(reticula[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void rellenarMatrizManual() {
		System.out.println("Introduzca 0 para una célula en estado EXCITADO");
		System.out.println("Introduzca 1 para una célula en estado REFRACTARIO");
		System.out.println("Introduzca 0 para una célula en estado ESTABLE");
		for (int i = 0; i < reticulaPrimera.length; i++) {
			for (int j = 0; j < reticulaPrimera.length; j++) {
				System.out.println("Rellenando fila " + i + " columa " + j);
				reticulaPrimera[i][j] = sc.nextInt();
			}
		}

	}

	private static void rellenarMatrizAleatoria() {
		Random rd = new Random();
		for (int i = 0; i < reticulaPrimera.length; i++) {
			for (int j = 0; j < reticulaPrimera.length; j++) {
				reticulaPrimera[i][j] = rd.nextInt(3);
			}
		}

	}

	private static void imprimirMenu() {
		System.out.println("ELija que opción desea");
		System.out.println("1.Introducir datos aleatorios en la reticula");
		System.out.println("2.Introducir datos manualmente");

	}

	public static void main(String[] args) throws InterruptedException {
		int cb = 0;
		System.out.println("Introduzca el tamaño de la reticula");
		n = sc.nextInt();
		System.out.println("Introduzca el número de tareas");
		m = sc.nextInt();
		System.out.println("Introduzca el número de etapas");
		t = sc.nextInt();

		reticulaPrimera = new int[n][n];
		reticulaSegunda = new int[n][n];

		imprimirMenu();
		int op = sc.nextInt();

		if (op == 1)
			rellenarMatrizAleatoria();
		else {
			rellenarMatrizManual();
			System.out.println("Su reticula inicial es");
			imprimirMatriz(reticulaPrimera);
		}

		long start = System.nanoTime();
		for (int i = 0; i < t; i++) {
			int ini = 0;
			int nPasos = n / (m / (1 - cb));
			ejecutor = new ThreadPoolExecutor(m, m, 1000, TimeUnit.DAYS, new ArrayBlockingQueue<>(10000));
			for (int j = 0; j < m - 1; j++) {
				ejecutor.execute(new acGreenbergHastings(ini, ini + nPasos));
				ini += nPasos;
			}

			ejecutor.execute(new acGreenbergHastings(ini, n));
			ejecutor.shutdown();
			ejecutor.awaitTermination(1000, TimeUnit.DAYS);

			reticulaPrimera = reticulaSegunda;
		}
		long end = System.nanoTime();

		System.out.println("La ejecución para " + t + " etapas ha durado " + (end - start) + " microsegundos.");
		if (op == 2)
			imprimirMatriz(reticulaSegunda);

	}

}

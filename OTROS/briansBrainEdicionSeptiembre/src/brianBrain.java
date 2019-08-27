import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author isa
 *
 */
public class brianBrain implements Runnable {
	public static int tamReticula;
	public static long nPasos;
	public static int nHilos;
	public static int[][] reticulaGenPrimera;
	public static int[][] reticulaGenSegunda;
	public static ThreadPoolExecutor ejecutor;
	public static CyclicBarrier barrera;

	public int inicio, fin;

	/**
	 * @param inicio
	 * @param fin
	 */
	public brianBrain(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public void run() {
		for (int fila = inicio; fila < fin; fila++)
			for (int columna = 0; columna < tamReticula; columna++)
				comprobarVecinos(fila, columna);

		try {
			barrera.await();
			reticulaGenPrimera = reticulaGenSegunda;
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void comprobarVecinos(int fila, int columna) {
		int[][] matAux = new int[3][3];
		celdasAdyacentes(matAux, fila, columna);
		
		if(reticulaGenPrimera[fila][columna] == 2)
			reticulaGenSegunda[fila][columna] = 0;
		else if(reticulaGenPrimera[fila][columna] == 1)
				reticulaGenSegunda[fila][columna] = 2;
			else if(reticulaGenPrimera[fila][columna] == 0 && numVecinasDisparando(matAux) == 2)
				reticulaGenSegunda[fila][columna] = 1;
			else reticulaGenSegunda[fila][columna] = 0;
		
	}

	private int numVecinasDisparando(int[][] matAux) {
		int nVecinasDisp = 0;
		for (int i = 0; i < matAux.length; i++) {
			for (int j = 0; j < matAux.length; j++) {
				if(matAux[i][j] == 1)
					nVecinasDisp++;
			}
		}
		return nVecinasDisp;
	}

	private void celdasAdyacentes(int[][] matAux, int fila, int columna) {
		for (int i = 0; i < matAux.length; i++)
			for (int j = 0; j < matAux.length; j++) {
				matAux[i][j] = 0;
			}

		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				if ((fila + i) > 0 && (fila + i) < matAux.length && (columna + j) > 0 && (columna + j) < matAux.length)
					matAux[fila + i][columna + j] = reticulaGenPrimera[fila + i][columna + j];
			}

	}

	/**
	 * 
	 */
	private static void imprimirMenu() {
		System.out.println("Elije como desea rellenar la retícula");
		System.out.println("1.De forma aleatoria");
		System.out.println("2.Introduciendo datos manualmente");

	}

	/**
	 * @return
	 */
	private static int leerTeclado() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	/**
	 * 
	 */
	private static void inicializarReticulaAleatoria() {
		inicializarMatrices();
		Random rd = new Random();

		for (int i = 0; i < tamReticula; i++)
			for (int j = 0; j < reticulaGenPrimera.length; j++)
				reticulaGenPrimera[i][j] = rd.nextInt(3);

	}

	private static void inicializarReticulaManual() {
		System.out.println("Va a rellenar su retícula de tamaño " + tamReticula + "x" + tamReticula);
		inicializarMatrices();

		for (int i = 0; i < reticulaGenPrimera.length; i++) {
			for (int j = 0; j < reticulaGenPrimera.length; j++) {
				System.out.println("Introduzca un número del 0 al 2");
				reticulaGenPrimera[i][j] = leerTeclado();
			}
		}

	}

	public static void inicializarMatrices() {
		reticulaGenPrimera = new int[tamReticula][tamReticula];
		reticulaGenSegunda = new int[tamReticula][tamReticula];
	}

	private static void imprimirMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void ejecutarTareas() {
		barrera = new CyclicBarrier(nHilos);
		for (int i = 0; i < nPasos; i++) {
			ejecutor = new ThreadPoolExecutor(nHilos, nHilos, 100000, TimeUnit.DAYS, new ArrayBlockingQueue<>(1000000));
			int inicio = 0;
			int nTareas = tamReticula / nHilos;
			for (int j = 0; j < nHilos - 1; j++)
				ejecutor.execute(new brianBrain(inicio, inicio + nTareas));
			ejecutor.execute(new brianBrain(inicio, tamReticula));
			ejecutor.shutdown();
			while (!ejecutor.isTerminated())
				;
		}
	}

	public static void main(String[] args) {
		int op;
		imprimirMenu();
		op = leerTeclado();
		switch (op) {
		case 1:
			tamReticula = 800;
			nPasos = 10000000000L;
			nHilos = Runtime.getRuntime().availableProcessors();
			inicializarReticulaAleatoria();
			long tiempoTareas;
			long inicioTareas = System.currentTimeMillis();
			ejecutarTareas();
			long finTareas = System.currentTimeMillis();
			tiempoTareas = finTareas - inicioTareas;
			nHilos = nHilos * 2;
			long inicioDobleTareas = System.currentTimeMillis();
			ejecutarTareas();
			long finDobleTareas = System.currentTimeMillis();
			long tiempoDobleTareas = finDobleTareas - inicioDobleTareas;

			System.out.println("El speedup es " + (double) tiempoTareas / tiempoDobleTareas);

			break;

		case 2:
			System.out.println("Introduce el tamaño de la retícula");
			tamReticula = leerTeclado();
			System.out.println("Introduce el número de pasos");
			nPasos = leerTeclado();
			System.out.println("Introduce el número de tareas");
			nHilos = leerTeclado();
			inicializarReticulaManual();
			System.out.println("Su matriz inicial es:");
			imprimirMatriz(reticulaGenPrimera);
			ejecutarTareas();
			imprimirMatriz(reticulaGenSegunda);
		}

	}

}

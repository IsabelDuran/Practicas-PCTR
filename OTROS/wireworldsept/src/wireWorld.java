import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class wireWorld implements Runnable {
	public static Scanner sc = new Scanner(System.in);
	public static int[][] reticulaInicial;
	public static int[][] reticulaFinal;
	public static int n; // Numero generaciones
	public static int d; // Tam. retícula
	public static int m; // Número de tareas

	int inicio, fin;

	public wireWorld(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public void run() {
		for (int fila = inicio; fila < fin; fila++)
			for (int columna = 0; columna < reticulaInicial.length; columna++)
				aplicarVecindad(fila, columna);

	}

	private void aplicarVecindad(int fila, int columna) {
		int[][] matAux = new int[3][3];
		celdasAdyacentes(matAux, fila, columna);
		
		if(reticulaInicial[fila][columna] == 0)
			reticulaFinal[fila][columna] = 0;
		else if(reticulaInicial[fila][columna] == 1)
			reticulaFinal[fila][columna] = 2;
		else if(reticulaInicial[fila][columna] == 2)
			reticulaFinal[fila][columna] = 3;
		else if(reticulaInicial[fila][columna] == 3 && (vecinosFrenteElectron(matAux) == 1 || vecinosFrenteElectron(matAux) == 2))
			reticulaFinal[fila][columna] = 1;
		else
			reticulaFinal[fila][columna] = 3;
			

	}

	private int vecinosFrenteElectron(int[][] matAux) {
		int nVecinosFrenteElectron = 0;
		for (int i = 0; i < matAux.length; i++) {
			for (int j = 0; j < matAux.length; j++) {
				if(matAux[i][j] == 1)
					nVecinosFrenteElectron++;
			}
		}
		return nVecinosFrenteElectron;
	}

	private void celdasAdyacentes(int[][] matAux, int fila, int columna) {
		for (int i = 0; i < matAux.length; i++) {
			for (int j = 0; j < matAux.length; j++) {
				matAux[i][j] = 0;
			}
		}
		
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <= 1; j++)
				if((fila + i) >= 0 && (fila + i) < reticulaInicial.length && (columna + j) >= 0 && (columna + j) < reticulaInicial.length)
					matAux[i + 1][j + 1] = reticulaInicial[fila + i][columna + j];
		
	}

	private static void rellenarMatrizAutomaticamente() {
		Random rd = new Random();
		for (int i = 0; i < reticulaInicial.length; i++)
			for (int j = 0; j < reticulaInicial.length; j++)
				reticulaInicial[i][j] = rd.nextInt(4);

	}

	private static void rellenarMatrizManual() {
		for (int i = 0; i < reticulaInicial.length; i++)
			for (int j = 0; j < reticulaInicial.length; j++) {
				System.out.println("Rellenando fila " + i + " columna " + j);
				System.out.println("0 Vacio");
				System.out.println("1 Frente electrón");
				System.out.println("2 Cola electrón");
				System.out.println("3 Conductor");
				reticulaInicial[i][j] = sc.nextInt();
			}

	}

	private static void imprimirNuevaGeneracion(int[][] reticula) {
		for (int i = 0; i < reticula.length; i++) {
			for (int j = 0; j < reticula.length; j++) {
				System.out.print(reticula[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		int nHilos = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor ejecutor;
		System.out.println("Bienvenido a WireWorld");
		System.out.println("Por favor introduce el número de generaciones que desea tener");
		n = sc.nextInt();
		System.out.println("Introduzca el tamaño de la retícula");
		d = sc.nextInt();
		reticulaInicial = new int[d][d];
		reticulaFinal = new int[d][d];
		System.out.println("Introduzca el número de tareas que desea realizar");
		m = sc.nextInt();
		System.out.println("¿Cómo desea que se rellene la retícula?");
		System.out.println("1.De forma automática");
		System.out.println("2.De forma manual");
		int opcion = sc.nextInt();

		if (opcion == 1)
			rellenarMatrizAutomaticamente();
		else
			{
			rellenarMatrizManual();
			imprimirNuevaGeneracion(reticulaInicial);
			System.out.println();}
		
		int ini = 0;
		int nPasos = d / m;
		
		for(int i = 0; i < n; i++) {
			ejecutor = new ThreadPoolExecutor(nHilos, nHilos, 10000, TimeUnit.DAYS, new ArrayBlockingQueue<>(10000));
			for(int j = 0; j < m - 1; j++) {
				ejecutor.execute(new wireWorld(ini, ini + nPasos));
				ini += nPasos;
			}
			
			ejecutor.execute(new wireWorld(ini, d));
			
			ejecutor.shutdown();
			ejecutor.awaitTermination(10000, TimeUnit.DAYS);
			
			reticulaInicial =  reticulaFinal;
			if(opcion == 2){
			System.out.println("Generacion " + (i + 1));
				imprimirNuevaGeneracion(reticulaInicial);
				System.out.println();}
		}
		
		
	}

}

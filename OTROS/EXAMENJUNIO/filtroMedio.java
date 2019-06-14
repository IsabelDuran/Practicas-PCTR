import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author isa
 *
 */
public class filtroMedio implements Runnable{
	public static final int f = 1;
	public static int n;
	public static int m;
	public static int[][] matrizInicial;
	public static int[][] matrizFinal;
	
	int inicio, fin;
	
	/**
	 * @param inicio
	 * @param fin
	 */
	public filtroMedio(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}


	/**
	 *
	 */
	@Override
	public void run() {
		for (int fila = inicio; fila < fin; fila++)
			for (int columna = 0; columna < matrizInicial.length; columna++)
				aplicarTransformacion(fila, columna);
	}
	
	/**
	 * @param fila
	 * @param columna
	 */
	private void aplicarTransformacion(int fila, int columna) {
		int[][] matAuxiliar = new int[3][3];
		celdasAdyacentes(matrizInicial, matAuxiliar, fila, columna);
		
		double valorFuncionFiltron = ((double) 1 / Math.pow((2 * f) + 1, 2));

		matrizFinal[fila][columna] = (int) (valorFuncionFiltron * (matAuxiliar[0][0] + matAuxiliar[0][1] + matAuxiliar[0][2] + matAuxiliar[1][0]
																	+ matAuxiliar[1][1] + matAuxiliar[1][2] + matAuxiliar[2][0] 
																	+ matAuxiliar[2][1] + matAuxiliar[2][2]));
	}
	

	/**
	 * @param matIn
	 * @param matOut
	 * @param fila
	 * @param columna
	 */
	static void celdasAdyacentes(int[][] matIn, int[][] matOut, int fila, int columna) {
		for (int i = 0; i < matOut.length; i++)
			for (int j = 0; j < matOut.length; j++)
				matOut[i][j] = 0;

		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				if ((fila + i) >= 0 && (fila + i) < matIn.length && (columna + j) >= 0 && (columna + j) < matIn.length)
					matOut[i + 1][j + 1] = matIn[fila + i][columna + j];
			}
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
	private static void inicializarMatrizManualmente() {
		matrizInicial = new int[n][n];
		matrizFinal = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				System.out.println("Introduzca el dato de la fila " + i + " columna " + j);
				matrizInicial[i][j] = leerTeclado();
			}
	}

	/**
	 * 
	 */
	private static void inicializarMatrizDatosAleatorios() {
		Random rn = new Random();
		matrizInicial = new int[n][n];
		matrizFinal = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matrizInicial[i][j] = rn.nextInt(256);

	}
	
	/**
	 * @param matriz
	 */
	private static void imprimirMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++)
				System.out.print(matriz[i][j] + " ");

			System.out.println();
		}
	}
	
	
	/**
	 * 
	 */
	private static void menuUsuario() {
		System.out.println("Por favor, elija su opción");
		System.out.println("1.Introducir datos de forma aleatoria");
		System.out.println("2.Introducir datos de forma manual");
	}
	
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		long start, end;

		System.out.println("Por favor introduzca el tamaño de la matriz");
		n = leerTeclado();
		System.out.println("Por favor introduzca el número de hebras");
		m = leerTeclado();

		int inicio = 0, nTareas;

		nTareas = n / m;		//El total de tareas es el tamaño de la matriz entre el número de hilos
		
		menuUsuario();
		
		int opcion = leerTeclado();

		switch (opcion) {
		case 1:
			inicializarMatrizDatosAleatorios();
			break;

		case 2:
			inicializarMatrizManualmente();
			System.out.println("Su matriz inicial es: ");
			imprimirMatriz(matrizInicial);
			break;
		}
		
		
		start = System.currentTimeMillis();
				
		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(m, m, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(m));
		
		for(int i = 0; i < m - 1; i++) {
			ejecutor.execute(new filtroMedio(inicio, inicio + nTareas));
			inicio += nTareas;
		}
		
		ejecutor.execute(new filtroMedio(inicio, matrizInicial.length));
		
		ejecutor.shutdown();
		while(!ejecutor.isTerminated());
		
		end = System.currentTimeMillis();
		
		if(opcion == 2) {
			System.out.println("La matriz resultante es: ");
			imprimirMatriz(matrizFinal);
		}
		
		System.out.println("El calculo ha tomado un total de " + (end - start) + " milisegundos.");
		
	}


}

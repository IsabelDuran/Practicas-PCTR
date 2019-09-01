import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class acIncendioForestal implements Runnable {
	public static CyclicBarrier barrera;
	public static ThreadPoolExecutor ejecutor;
	public static int[][] reticulaInicial;
	public static int[][] reticulaFinal;
	public static final int tam = 400;
	public static int m; // Arboles
	public static int n; // Focos fuego
	public static int g; // Numero generaciones

	int inicio, fin;

	public acIncendioForestal(int i, int f) {
		inicio = i;
		fin = f;
	}

	public void run() {
		for (int fila = inicio; fila < fin; fila++)
			for (int columna = 0; columna < tam; columna++)
				comprobarVecindad(fila, columna);
		try {
			barrera.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void comprobarVecindad(int fila, int columna) {
		int[][] matAux = new int[3][3];
		matAux = celdasAdyacentes(matAux, fila, columna);

		if (reticulaInicial[fila][columna] == 0)
			reticulaFinal[fila][columna] = 0;
		else if (reticulaInicial[fila][columna] == 2)
			reticulaFinal[fila][columna] = 0;
		else if (reticulaInicial[fila][columna] == 1 && vecinosIndendiados(matAux) > 0)
			reticulaFinal[fila][columna] = 2;
		else
			reticulaFinal[fila][columna] = 1;

	}

	public int vecinosIndendiados(int[][] matAux) {
		int n = 0;
		for (int i = 0; i < matAux.length; i++)
			for (int j = 0; j < matAux.length; j++)
				if (matAux[i][j] == 2)
					n++;
		return n;
	}

	public int[][] celdasAdyacentes(int[][] matAux, int fila, int columna) {
		for (int i = 0; i < matAux.length; i++)
			for (int j = 0; j < matAux.length; j++)
				matAux[i][j] = 0;

		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if ((fila + i) >= 0 && (fila + i) < tam && (columna + j) >= 0 && (columna + j) < tam)
					matAux[i + 1][j + 1] = reticulaInicial[fila + i][columna + j];

		return matAux;
	}

	private static void inicializarReticula() {
		Random rd = new Random();

		for (int i = 0; i < tam; i++)
			for (int j = 0; j < tam; j++)
				reticulaInicial[i][j] = 0;

		int fila, col;
		for (int i = 0; i < m; i++) {
			fila = rd.nextInt(400);
			col = rd.nextInt(400);
			if (reticulaInicial[fila][col] == 0)
				reticulaInicial[fila][col] = 1;
			else
				i--;
		}


		for (int i = 0; i < n; i++) {
			fila = rd.nextInt(400);
			col = rd.nextInt(400);
			if (reticulaInicial[fila][col] == 0 && reticulaInicial[fila][col] != 1)
				reticulaInicial[fila][col] = 2;
			else
				i--;
		}
	}

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		int nHilos = Runtime.getRuntime().availableProcessors();
		barrera = new CyclicBarrier(nHilos);
		ejecutor = new ThreadPoolExecutor(nHilos, nHilos, 10000, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(1000));
		m = Integer.parseInt(args[0]);
		n = Integer.parseInt(args[1]);
		g = Integer.parseInt(args[2]);

		reticulaInicial = new int[tam][tam];
		reticulaFinal = new int[tam][tam];

		inicializarReticula();
		int ini = 0;
		int nPasos = tam / nHilos;

		Long start = System.currentTimeMillis();
		for (int j = 0; j < g; j++) {
			for (int i = 0; i < nHilos - 1; i++) {
				ejecutor.execute(new acIncendioForestal(ini, ini + nPasos));
				ini += nPasos;
			}

			ejecutor.execute(new acIncendioForestal(ini, tam));

		}
		ejecutor.shutdown();
		ejecutor.awaitTermination(1000, TimeUnit.DAYS);
		Long end = System.currentTimeMillis();

		System.out.println("Se han tardado " + (end - start) + " milisegundos.");

	}
}

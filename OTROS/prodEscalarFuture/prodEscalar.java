import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author isa
 *
 */
public class prodEscalar implements Callable<Float> {
	static int tam = 10000;
	static ArrayList<Float> vector1 = new ArrayList<>(tam);
	static ArrayList<Float> vector2 = new ArrayList<>(tam);
	static Float Res = new Float(0);

	int inicio;
	int fin;

	/**
	 * @param i
	 * @param f
	 */
	public prodEscalar(int i, int f) {
		inicio = i;
		fin = f;
	}

	/**
	 * 
	 */
	private static void inicializarVectores() {
		Random ran = new Random();
		for (int i1 = 0; i1 < tam; i1++) {
			vector1.add(i1, Float.valueOf(ran.nextFloat()));
			vector2.add(i1, Float.valueOf(ran.nextFloat()));
		}
	}

	/**
	 *
	 */
	@Override
	public Float call() throws Exception {
		Float res = new Float(0);
		for (int i = inicio; i < fin; i++)
			res = res + (vector1.get(i) * vector2.get(i));

		return res;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ArrayList<Future<Float>> vecFutures = new ArrayList<Future<Float>>();
		inicializarVectores();

		Scanner sc = new Scanner(System.in);
		int nHilos;
		System.out.println("Introduce el número de hilos que desee crear: ");
		nHilos = sc.nextInt();

		ExecutorService executor = Executors.newFixedThreadPool(nHilos);
		
		/*Dividimos el número de tareas*/
		int inicio = 0;
		int nTareas = tam / nHilos;

		for (int i = 0; i < nHilos - 1; i++) {
			vecFutures.add(executor.submit(new prodEscalar(inicio, inicio + nTareas)));
			inicio += nTareas;
		}
		
		vecFutures.add(executor.submit(new prodEscalar(inicio, tam)));

		executor.shutdown();
		
		while (!executor.isTerminated()){}

		for (Future<Float> iter : vecFutures) {
			Res += iter.get();
		}

		System.out.println("El resultado del producto escalar es " + Res);
		sc.close();
	}

}

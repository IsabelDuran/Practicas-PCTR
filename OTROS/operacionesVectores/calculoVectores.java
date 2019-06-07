import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class calculoVectores implements Runnable {
	public static int tam = 1000000;
	public static Semaphore semK = new Semaphore(1);

	public static Semaphore mutex1 = new Semaphore(1);
	public static Semaphore barrera1 = new Semaphore(0);

	public static Semaphore mutex2 = new Semaphore(1);
	public static Semaphore barrera2 = new Semaphore(0);

	public static Semaphore mutex3 = new Semaphore(1);
	public static Semaphore barrera3 = new Semaphore(0);

	public static long[] vectorA = new long[tam];
	public static long[] vectorB = new long[tam];
	public static long[] vectorC = new long[tam];
	public static long[] vectorSumaAB = new long[tam];
	public static long[] vectorMultABC = new long[tam];
	public static long[] vectorRes = new long[tam];
	public static int contador1 = 0, contador2 = 0, contador3 = 0, nHilos = Runtime.getRuntime().availableProcessors();
	int inicio, fin;
	public static double sumaProd = 0, k;

	public calculoVectores(int i, int f) {
		inicio = i;
		fin = f;
	}

	@Override
	public void run() {
		try {
			ejecutarOperacionesVectoriales();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ejecutarOperacionesVectoriales() throws InterruptedException {
		sumaVectores(inicio, fin);

		mutex1.acquire();
		contador1++;
		mutex1.release();

		if (contador1 == nHilos)
			barrera1.release();

		barrera1.acquire();
		barrera1.release();

		multiplicaVectores(inicio, fin);

		sumaVectores(inicio, fin);

		mutex2.acquire();
		contador2++;
		mutex2.release();

		if (contador2 == nHilos)
			barrera2.release();

		barrera2.acquire();
		barrera2.release();

		sumaProductos(inicio, fin);

		mutex3.acquire();
		contador3++;
		mutex3.release();

		if (contador3 == nHilos) {
			barrera3.release();
			normaEuclidea();
		}

		barrera3.acquire();
		barrera3.release();

		productoEscalar(inicio, fin);
	}

	private static void productoEscalar(int inicio, int fin) {
		for (int i = inicio; i < fin; i++)
			vectorRes[i] = (long) (vectorA[i] * k);
	}

	private static void normaEuclidea() throws InterruptedException {
		semK.acquire();
		k = Math.sqrt(sumaProd);

	}

	private static void sumaVectores(int inicio, int fin) {
		for (int i = inicio; i < fin; i++)
			vectorSumaAB[i] = vectorA[i] + vectorB[i];

	}

	private static void multiplicaVectores(int inicio, int fin) {
		for (int i = inicio; i < fin; i++)
			vectorMultABC[i] = vectorSumaAB[i] * vectorC[i];
	}

	private static void sumaProductos(int inicio, int fin) {
		for (int i = inicio; i < fin; i++)
			sumaProd += Math.pow(vectorMultABC[i], 2);
	}

	private static void inicializarVectores() {
		Random rd = new Random();
		for (int i = 0; i < tam; i++) {
			vectorA[i] = rd.nextInt(10);
			vectorB[i] = rd.nextInt(10);
			vectorC[i] = rd.nextInt(10);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		inicializarVectores();
		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nHilos, nHilos, 10000, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(1));

		int nTareas = tam / nHilos, ini = 0;

		for (int i = 0; i < nHilos - 1; i++) {
			ejecutor.execute(new calculoVectores(ini, ini + nTareas));
			ini += nTareas;
		}

		ejecutor.execute(new calculoVectores(ini, tam - 1));
		ejecutor.shutdown();
		ejecutor.awaitTermination(10000, TimeUnit.DAYS);

		System.out.println(vectorRes[1]);

	}

}

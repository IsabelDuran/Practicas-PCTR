import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class perfectNumbers implements Callable<Integer> {
	public static int n;
	public static int nHilos;

	int inicio, fin;

	public perfectNumbers(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public Integer call() throws Exception {
		Integer nPerfectos = new Integer(0);
		for(int i = inicio; i <= fin; i++) {
			if(esPerfecto(i))
				nPerfectos++;
		}
		return nPerfectos;
	}

	private boolean esPerfecto(int n) {
		int suma = 0;
		for(int i = 1; i < n; i++) {
			if(n % i == 0)
				suma += i;
		}
		
		if(suma == n)
			return true;
		else
			return false;
	}

	private static void mostrarMenu() {
		System.out.println("Introduce el número hasta el cual quieres buscar números perfectos");
		n = leerTeclado();
		System.out.println("Introduce el número de hilos a utilizar");
		nHilos = leerTeclado();
	}

	private static int leerTeclado() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Integer nPerfectos = new Integer(0);
		mostrarMenu();
		ExecutorService ejecutor = Executors.newFixedThreadPool(nHilos);

		ArrayList<Future<Integer>> arrayFuture = new ArrayList<>();

		int ini = 1;
		int nTareas = n / nHilos;

		for (int i = 0; i < nHilos - 1; i++) {
			arrayFuture.add(ejecutor.submit(new perfectNumbers(ini, ini + (nTareas - 1))));
			ini += nTareas;
		}
		arrayFuture.add(ejecutor.submit(new perfectNumbers(ini, n)));
		
		ejecutor.shutdown();
		
		for(Future<Integer> i : arrayFuture)
			nPerfectos += i.get();
		
		System.out.println("Hasta el número " + n + " hay " + nPerfectos + " números perfectos");
		
	}

}

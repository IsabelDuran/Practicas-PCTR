import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class pruebaLife implements Runnable {
	public int filaInicio;
	public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
	public static ExecutorService executor;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Life.nextGen(filaInicio);
			Life.status();

			try {
				cyclicBarrier.await();

			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public pruebaLife(int i) {
		filaInicio = i;
	}

	public static void main(String[] args) throws InterruptedException {
		executor = Executors.newFixedThreadPool(10);
		System.out.println("El mundo creado ha sido: ");
		celLife mundo = new celLife(50);
		celLife.imprimirMundo();

		for (int i = 0; i < 10; i++)
			executor.execute(new pruebaLife(i));

		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.DAYS);

	}

}

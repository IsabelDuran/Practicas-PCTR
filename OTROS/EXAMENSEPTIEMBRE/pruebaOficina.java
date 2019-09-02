

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class pruebaOficina {
	public static void main(String[] args) throws InterruptedException {
		Oficina oficina = new Oficina();
		ExecutorService ejecutor = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 50; i++) {
			ejecutor.execute(new Empleado(i, oficina));
			ejecutor.execute(new Limpiador(i, oficina));
		}

		ejecutor.shutdown();
		ejecutor.awaitTermination(10000, TimeUnit.DAYS);

		System.out.println("Fin");
	}
}

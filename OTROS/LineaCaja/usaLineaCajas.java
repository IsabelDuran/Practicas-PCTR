import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaLineaCajas {
	public static void main(String[] args) {
		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(20, 20, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20));
		lineaCajas cajas = new lineaCajas();
		
		for(int i = 0; i < 20; i++)
			ejecutor.execute(new Cliente(cajas));
	}
}

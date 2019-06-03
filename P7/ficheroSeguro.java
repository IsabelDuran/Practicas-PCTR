import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ficheroSeguro extends Thread{
	public static RandomAccessFile fichero;
	public static Executor executor;
	
	@Override
	public void run() {
		while(true) 
			write(this.getName());
	}

	public static synchronized void write(String nombreHilo) {
		try {
			fichero.writeChars("Soy el hilo " + nombreHilo + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		int nHilos = (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.5)); //Cb = 0.5
		executor = Executors.newFixedThreadPool(nHilos);
		
		fichero = new RandomAccessFile("fichero.txt", "rwd");
		for(int i = 0; i < nHilos; i++)
			executor.execute(new ficheroSeguro());
		
	}
}

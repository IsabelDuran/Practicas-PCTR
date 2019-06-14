/**
 * @author isa
 *
 */
public class pruebaMonitor extends Thread {
	public static int contador = 0; // Lo usaremos como recurso compartido
	public static monitorSem semaforo = new monitorSem();
	int inicio, fin;
	
	/**
	 * @param i
	 * @param f
	 */
	public pruebaMonitor(int i, int f) {
		inicio = i;
		fin = f;
	}
	
	/**
	 *
	 */
	@Override
	public void run() {
		for (int i = inicio; i < fin; i++) {
			try {
				semaforo.waitSem();
				contador++;
				semaforo.signalSem();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		pruebaMonitor hilo1 = new pruebaMonitor(0, 50);
		pruebaMonitor hilo2 = new pruebaMonitor(51, 101);

		hilo1.start();
		hilo2.start();
		hilo1.join();
		hilo2.join();

		System.out.println("El valor del contador debería ser 100, y es: " + contador);
	}
}

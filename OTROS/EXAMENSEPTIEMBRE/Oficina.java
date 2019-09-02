
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Oficina {
	private final int nMesas = 10;
	private int nMesasLibres = nMesas;
	private int nMesasLimpias = nMesas;
	private ReentrantLock cerrojo = new ReentrantLock();
	private Condition mesasNoDisponibles = cerrojo.newCondition();
	private Condition mesasNoSucias = cerrojo.newCondition();

	public Oficina() {
	}

	public void ocuparMesa(int id) throws InterruptedException {
		cerrojo.lock();
		try {
			while (nMesasLibres <= 0 || nMesasLimpias <= 0) {
				System.out.println("No hay mesas disponibles");
				mesasNoDisponibles.await();

			}
			System.out.println("El empleado " + id + " va a coger una mesa ");
			nMesasLibres--;
		} finally {
			cerrojo.unlock();
		}
	}

	public void dejarMesa(int id) {
		cerrojo.lock();
		try {
			System.out.println("El empleado " + id + " va a dejar una mesa ");
			nMesasLibres++;
			nMesasLimpias--;
			mesasNoSucias.notify();
		} finally {
			cerrojo.unlock();
		}
	}

	public void limpiarMesa(int id) throws InterruptedException {
		cerrojo.lock();
		try {
			while (nMesasLibres <= 0 || nMesasLimpias == 10) {
				System.out.println("No hay mesas sucias disponibles");
				mesasNoSucias.await();
			}
			System.out.println("El limpiador " + id + " va a limpiar una mesa.");
		} finally {
			cerrojo.unlock();
		}
	}

	public void dejarMesaLimpia(int id) {
		cerrojo.lock();
		try {
			System.out.println("El limpiador " + id + " va a dejar la mesa ");
			nMesasLimpias--;
			mesasNoDisponibles.notify();
		} finally {
			cerrojo.unlock();
		}
	}

}

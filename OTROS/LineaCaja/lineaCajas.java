import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
public class lineaCajas {
	private final Lock cerrojo = new ReentrantLock();
	final Condition cola = cerrojo.newCondition();
	List<Integer> puestosAtencion = new ArrayList<>(10);

	/**
	 * 
	 */
	public lineaCajas() {
		puestosAtencion = Arrays.asList(0, 0, 0, 0); 
	}

	/**
	 * 
	 */
	public void entrarCaja() {
		cerrojo.lock();
		try {
			while (puestoLibre() < 0) {
				System.out.println("El cliente " + Thread.currentThread().getName()
						+ " debe esperar pues no hay cajas disponibles");
				cola.await();
			}
			System.out
					.println("El cliente " + Thread.currentThread().getName() + " pasa por la línea " + puestoLibre());
			puestosAtencion.set(puestoLibre(), 1);
		} catch (InterruptedException e) {
			System.out.println("¡Error!");
		} finally {
			cerrojo.unlock();
		}
	}

	/**
	 * 
	 */
	public void dejarCaja() {
		cerrojo.lock();
		try {
			System.out.println("El cliente " + Thread.currentThread().getName() + " va a abandonar la línea de caja.");
			puestosAtencion.set(puestoOcupado(), 0);
			cola.signal();
		} finally {
			cerrojo.unlock();
		}

	}

	/**
	 * @return
	 */
	private int puestoLibre() {
		cerrojo.lock();
		try {
			return puestosAtencion.indexOf(0);
		} finally {
			cerrojo.unlock();
		}
	}

	/**
	 * @return
	 */
	private int puestoOcupado() {
		cerrojo.lock();
		try {
			return puestosAtencion.indexOf(1);
		} finally {
			cerrojo.unlock();
		}
	}

}

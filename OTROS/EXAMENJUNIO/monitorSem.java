import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
public class monitorSem {
	private final ReentrantLock cerrojo = new ReentrantLock();
	public Condition cola = cerrojo.newCondition();
	
	public int valInicialSem = 1;
	
	
	/**
	 * @throws InterruptedException
	 */
	public void waitSem() throws InterruptedException {
		cerrojo.lock();
		try {
			while(valInicialSem == 0)
				cola.await();
			valInicialSem -= 1;
		} finally {
			cerrojo.unlock();
		}
	}
	
	/**
	 * 
	 */
	public void signalSem() {
		cerrojo.lock();
		try {
			valInicialSem += 1;			
		} finally {
			cerrojo.unlock();
		}
	}
}

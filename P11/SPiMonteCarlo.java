import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;

public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
	private int puntos = 0;
	private double aprox = 0;
	private int cont = 0;
	static ReentrantLock cerrojo = new ReentrantLock();

	protected SPiMonteCarlo() throws RemoteException {
		super();
	}

	@Override
	public void reset() throws RemoteException {
		cerrojo.lock();
		try {
			puntos = 0;
			aprox = 0;
			cont = 0;
		} finally {
			cerrojo.unlock();
		}

	}

	@Override
	public void masPuntos(int n) throws RemoteException {
		double x, y;
		cerrojo.lock();
		try {
			puntos = puntos + n;
			for(int i = 0; i < puntos; ++i) {
				x = Math.random();
				y = Math.random();
				if ((y * y) < (1 - (x * x))) {
					cont++;
				}
			}
			aprox=(double) 4 * cont / puntos;
			System.out.println("Aproximacion: " + aprox + " Para " + puntos + " puntos.");
		} finally {
			cerrojo.unlock();
		}

	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
		SPiMonteCarlo ORemoto = new SPiMonteCarlo();
		Naming.bind("Servidor", ORemoto); // TODO Auto-generated constructor stub
		System.out.println("Servidor Preparado");
	}
}

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
class primesServer extends UnicastRemoteObject implements primesInterface {
	ReentrantLock cerrojo = new ReentrantLock();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @throws RemoteException
	 */
	protected primesServer() throws RemoteException {
		super();
	}

	/**
	 *
	 */
	@Override
	public int primesInRange(int beginRange, int endRange) throws RemoteException {
		cerrojo.lock();
		try {
			int nPrimos = 0;
			for (int i = beginRange; i <= endRange; i++) {
				if(esPrimo(i))
					nPrimos++;
			}
			return nPrimos;
		} finally {
			cerrojo.unlock();
		}

	}

	/**
	 * @param n
	 * @return
	 */
	private boolean esPrimo(int n) {
		if(n == 1)
			return false;
	    if(n == 2)
	        return true;
	    else if(n % 2 == 0)
	        return false;
	    else{
	        for(int i = 3; i < Math.sqrt(n); i += 2){
	            if(n % i == 0)
	                return false;
	        }
	    }

	    return true;
	}

	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
		primesServer ps = new primesServer();
		Naming.bind("//localhost/servidor", ps);
		System.out.println("Servidor listo");
	}
}
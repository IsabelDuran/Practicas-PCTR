import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author isa
 *
 */
public interface primesInterface extends Remote {
	/**
	 * @param beginRange
	 * @param endRange
	 * @return
	 * @throws RemoteException
	 */
	int primesInRange(int beginRange, int endRange) throws RemoteException;
}

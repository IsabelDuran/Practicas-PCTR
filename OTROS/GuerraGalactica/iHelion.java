
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author isa
 *
 */
public interface iHelion extends Remote {
	
	/**
	 * @param x
	 * @param y
	 * @return
	 * @throws RemoteException
	 */
	public int lanzarOjivaNuclear(int x, int y) throws RemoteException;
	public int estadoActual() throws RemoteException;
}

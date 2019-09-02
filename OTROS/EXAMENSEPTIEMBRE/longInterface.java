import java.rmi.Remote;
import java.rmi.RemoteException;

public interface longInterface extends Remote {
	float lonSubSegmento(int n, punto[] datos) throws RemoteException;
}

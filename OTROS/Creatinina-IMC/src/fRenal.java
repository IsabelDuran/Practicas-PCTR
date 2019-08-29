import java.rmi.Remote;
import java.rmi.RemoteException;

public interface fRenal extends Remote {
	public float aclarCreat(Datos t) throws RemoteException;

	public float creatininaMedia() throws RemoteException;

	public float indMasaCorporal(float peso, float altura) throws RemoteException;
}
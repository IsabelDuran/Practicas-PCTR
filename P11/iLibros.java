import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iLibros extends Remote{
	public void anadirLibro(Libro libro) throws RemoteException;
	public void extraerLibro(String referencia) throws RemoteException;
	public void consultarLibro(String referencia) throws RemoteException;
}

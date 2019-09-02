import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class longServer extends UnicastRemoteObject implements longInterface {

	protected longServer() throws RemoteException {
		super();
	}
	
	@Override
	public float lonSubSegmento(int n, punto[] datos) throws RemoteException {
		float lon = 0;
		for(int i = 0; i < n - 1; i++) {
			lon += Math.sqrt(Math.pow(datos[i].getX() - datos[i + 1].getX(), 2) + Math.pow(datos[i].getY() - datos[i + 1].getY(), 2));
		}
		return lon;
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		longServer longserver = new longServer();
		Naming.bind("//localhost/servidor", longserver);
		System.out.println("Servidor listo");
	}

}
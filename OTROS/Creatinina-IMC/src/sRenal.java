import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class sRenal extends UnicastRemoteObject implements fRenal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Double> creatininasAlmacenadas = new ArrayList<>();

	protected sRenal() throws RemoteException {
		super();

	}

	@Override
	public float aclarCreat(Datos t) throws RemoteException {
		float aclaramiento = (float) (((140 - t.getEdad()) * t.getPeso()) / (72 * t.getCreatinina()));
		if (t.getSexo() == 'm')
			aclaramiento *= 0.85;
		
		creatininasAlmacenadas.add(t.getCreatinina());
		
		return aclaramiento;
	}

	@Override
	public float creatininaMedia() throws RemoteException {
		float suma = 0;
		for (int i = 0; i < creatininasAlmacenadas.size(); i++) {
			suma += creatininasAlmacenadas.get(i);
		}
		return (suma / creatininasAlmacenadas.size());
	}

	@Override
	public float indMasaCorporal(float peso, float altura) throws RemoteException {
		return (float) (peso / Math.pow(altura, 2));
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		sRenal srenal = new sRenal();
		Naming.bind("//localhost:1066/servidor", srenal);
		System.out.println("Servidor listibiris");

	}

}

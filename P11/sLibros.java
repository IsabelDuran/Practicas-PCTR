import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class sLibros extends UnicastRemoteObject implements iLibros {
	protected sLibros() throws RemoteException {
	}

	@Override
	public void anadirLibro(Libro libro) throws RemoteException {
		Libro.addLibro(libro);
		System.out.println("Libro añadido");
	}

	@Override
	public void extraerLibro(String referencia) throws RemoteException {
		Scanner sc = new Scanner(System.in);
		boolean encontrado = false;
		for (Libro libr : Libro.getLibros()) {
			if (libr.getReferencia().equals(referencia)) {
				Libro.eliminarLibro(referencia);
				System.out.println("Libro eliminado");
				encontrado = true;
			}
		}

		if (!encontrado)
			System.out.println("Ningún libro posee esa referencia");

	}

	@Override
	public void consultarLibro(String referencia) throws RemoteException {
		boolean encontrado = false;
		for (Libro libr : Libro.getLibros()) {
			if (libr.getReferencia().equals(referencia)) {
				System.out.println("Referencia " + libr.getReferencia());
				System.out.println("Autor/es " + libr.getAutores());
				System.out.println("Año publicación " + libr.getAnno_publicacion());
				System.out.println("Editorial " + libr.getEditorial());
				encontrado = true;

			}
		}

		if (!encontrado)
			System.out.println("Ningún libro posee esa referencia");

	}

	public static void main(String[] args) throws Exception {
		sLibros oRemoto = new sLibros();

		Naming.bind("servidor", oRemoto);
		System.out.println("Servidor preparado");
	}

}

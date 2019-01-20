import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class cLibros {
	public static void main(String[] args) {
		try {
			iLibros ReferenciaObjetoRemoto = (iLibros)Naming.lookup("//localhost/servidor");
			
			String autores;
			String referencia;
			String anno_publicacion;
			String editorial;

			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Introduce el nombre del autor");
			autores = scanner.nextLine();
			System.out.println("Introduce la referencia");
			referencia = scanner.nextLine();
			System.out.println("Introduce el año de publicación");
			anno_publicacion = scanner.nextLine();
			System.out.println("Introduce la editorial");
			editorial = scanner.nextLine();
			
			ReferenciaObjetoRemoto.anadirLibro(new Libro(autores, referencia, anno_publicacion, editorial));
			ReferenciaObjetoRemoto.consultarLibro(referencia);
			ReferenciaObjetoRemoto.extraerLibro(referencia);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
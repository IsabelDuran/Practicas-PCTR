import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CPiMonteCarlo {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			iPiMonteCarlo RefORemoto = (iPiMonteCarlo) Naming.lookup("//localhost/Servidor");
			int op;
			do {
				System.out.println("Elije que acción desea realizar");
				System.out.println("1.Añadir más puntos");
				System.out.println("2.Resetear servidor");
				System.out.println("3.Salir");
				op = sc.nextInt();
				
				switch (op) {
				case 1: System.out.println("¿Cuantos puntos desea meter?"); 
						int i = sc.nextInt();
						RefORemoto.masPuntos(i);
						break;
				case 2: RefORemoto.reset();
				}
				
			}while(op != 3);
			System.out.println("¡Hasta otra!");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

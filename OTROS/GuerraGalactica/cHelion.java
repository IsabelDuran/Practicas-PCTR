import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class cHelion {
	private static boolean victoria(int resultadoAtaque) {
		if(resultadoAtaque > 1 && resultadoAtaque < 5)
			return true;
		else
			return false;
	}
	
	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		Scanner sc = new Scanner(System.in);
		int x, y, opcion;
		iHelion oRemoto = (iHelion) Naming.lookup("//localhost:2020/servidor");
		System.out.println("Saludo, Space Ranger, bienvenido al sistema de lanzamiento de Ovijas Nucleares. ¡Comencemos el ataque!");
		int resultadoAtaque = 0;
		do {
			System.out.println("¿Desea saber cuantas ciudades han sido ya destruidas? [1/0]");
			opcion = sc.nextInt();
			if(opcion == 1)
				System.out.println("Hemos destruido " + oRemoto.estadoActual() + " ciudades. ¡A por todas!");
			
			System.out.println("Space Ranger, introduce la coordenada x: ");
			x = sc.nextInt();
			System.out.println("Space Ranger, introduce la coordenada y: ");
			y = sc.nextInt();
			resultadoAtaque = oRemoto.lanzarOjivaNuclear(x, y);
			
			switch (resultadoAtaque) {
			case 0:		
				System.out.println("Lamentamos informarle que el misil ha caido en saco roto. Pruebe otra vez.");
				break;
			case 1:		
				System.out.println("¡Magnifico! Esa Ovija ha dado de lleno contra una de sus ciudades, aún nos queda mucho que destruir, por favor, continue.");
				break;
			case 2:
				System.out.println("¡De lleno! Podemos escuchar el llanto de los aliens desde aquí pidiendo clemencia. Finalizamos ataque.");
				break;
				
			case 3:
				System.out.println("¡Agur! No queda ni rastro de ese asqueroso planeta ni de sus habitantes.");
				break;

			case 4:
				System.out.println("¡Pero chaval! Has dado de lleno en el agujero negro. La guerra ha finalizado.");
				break;
			case 5:
				System.out.println("¡Eres mas tonto que un arao! La x máxima es 199, la mínima es 0. La y máxima es 99 y la mínima 0. ¡Apunta bien!");
				break;
			}
		} while (!victoria(resultadoAtaque));
	}
}

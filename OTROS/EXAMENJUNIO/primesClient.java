import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class primesClient {
	/**
	 * @return
	 */
	private static int leerTeclado() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		int rBeginRange, rEndRange, m;
		int totalPrimos = 0;
		primesInterface oRemoto = (primesInterface) Naming.lookup("//localhost/servidor");
		System.out.println(
				"Introduce el rango de búsqueda para los números primos, un rango tiene un valor de inicio y un valor de fin.");
		System.out.println("Valor de inicio: ");
		rBeginRange = leerTeclado();
		System.out.println("Valor de fin: ");
		rEndRange = leerTeclado();

		while (rEndRange < rBeginRange) {
			System.out.println("El fin debe ser mayor que el inicio");
			rEndRange = leerTeclado();
		}

		System.out.println("Introduce el número de rangos");
		m = leerTeclado();

		int totalNumerosRango = rEndRange - rBeginRange + 1;
		int nTareas = totalNumerosRango / m;
		System.out.println(nTareas + " total tareas ");
		int inicio = rBeginRange;
		int contador = 0;

		while (inicio <= rEndRange) {
			if (inicio < rEndRange) {
				System.out.println("En el rango del " + inicio + " al " + (inicio + nTareas) + " hay "
						+ oRemoto.primesInRange(inicio, inicio + nTareas) + " primos.");
				totalPrimos += oRemoto.primesInRange(inicio, inicio + nTareas);
				contador = inicio + nTareas;
			}
			inicio += nTareas + 1;
		}

		if (contador != rEndRange){
			System.out.println("En el rango del " + rEndRange + " al " + (rEndRange) + " hay "
					+ oRemoto.primesInRange(rEndRange, rEndRange) + " primos.");
			totalPrimos += oRemoto.primesInRange(rEndRange, rEndRange);
		}

		System.out.println("El total de primos es " + totalPrimos);
	}
}

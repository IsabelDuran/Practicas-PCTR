import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class longClient {
	public static final int nPuntosNube = 20;
	public static Scanner sc = new Scanner(System.in);
	public static punto[] puntos = new punto[20];
	public static float[] longitudes;
	public static int m; // Numero de subrangos

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		longInterface oRemoto = (longInterface) Naming.lookup("//localhost/servidor");

		for (int i = 0; i < nPuntosNube; i++) {
			float x, y;
			System.out.println("Introduzca un punto [0,19]");
			System.out.println("Introduzca su coordenada x");
			x = Float.parseFloat(sc.nextLine());
			System.out.println("Introduzca su coordenada y");
			y = Float.parseFloat(sc.nextLine());
			puntos[i] = new punto(x, y);

		}

		do {
			System.out.println("Introduzca el número de subrangos");
			m = sc.nextInt();
			if (m > nPuntosNube)
				System.out.println("El limite para el número de subrangos es 20");
		} while (m > nPuntosNube);

		longitudes = new float[m];

		int inicio = 0;
		int nPasos = nPuntosNube / m;

		for (int i = 0; i < m - 2; i++) {
			punto[] matAuxPuntos = new punto[nPasos];
			for (int j = 0; j < nPasos; j++) {
				matAuxPuntos[j] = puntos[j];
			}
			longitudes[i] = oRemoto.lonSubSegmento(nPasos, matAuxPuntos);
			inicio += nPasos;
		}

		punto[] matAuxPuntos = new punto[(nPuntosNube - inicio)];
		for (int j = 0; j < (nPuntosNube - inicio); j++)
			matAuxPuntos[j] = puntos[j];

		longitudes[m - 1] = oRemoto.lonSubSegmento((nPuntosNube - inicio), matAuxPuntos);

		float total = 0;
		for (int i = 0; i < longitudes.length; i++)
			total += longitudes[i];
		System.out.println("La longitid es " + total);
	}
}

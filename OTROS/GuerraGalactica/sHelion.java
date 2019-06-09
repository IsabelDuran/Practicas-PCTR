import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author isa
 *
 */
class sHelion extends UnicastRemoteObject implements iHelion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int[][] helion = new int[200][100];
	public static int nCiudadesDestruidas = 0;
	static ReentrantLock cerrojo = new ReentrantLock();

	/**
	 * @throws RemoteException
	 */
	protected sHelion() throws RemoteException {
		super();
		inicializaHelion();
	}

	/**
	 * 
	 */
	private void inicializaHelion() {
		int x, y;
		Random rd = new Random();
		// Inicializamos el planeta sin ninguna ciudad (No hay ciudad = 0)
		for (int i = 0; i < 200; i++)
			for (int j = 0; j < 100; j++)
				helion[i][j] = 0;

		// Aleatoriamente, introduciremos las megaciudades en Helion III
		for (int i = 0; i < 2500; i++) {
			x = rd.nextInt(200);
			y = rd.nextInt(100);

			if (helion[x][y] != 1)
				helion[x][y] = 1;
			else
				i--;
		}

		// Y a parte crearemos el agujero negro del cual obtienen los enemigos su
		// energía, también de forma aleatoria
		boolean estaColocado = false;
		while (!estaColocado) {
			x = rd.nextInt(200);
			y = rd.nextInt(100);

			if (helion[x][y] == 0) {
				helion[x][y] = 2;
				estaColocado = true;
			}
		}
	}

	/**
	 *
	 */
	@Override
	public int lanzarOjivaNuclear(int x, int y) throws RemoteException {
		cerrojo.lock();
		try {
			int valorMapa, valRetorno = 0;

			if (x > 199 || x < 0 || y > 99 || y < 0)
				valorMapa = 3;
			else
				valorMapa = helion[x][y];

			switch (valorMapa) {
			case 0:
				valRetorno = 0;
				break;

			case 1:
				helion[x][y] = 0;
				nCiudadesDestruidas++;
				if (nCiudadesDestruidas == 2400)
					valRetorno = 2;
				else if (nCiudadesDestruidas == 2500)
					valRetorno = 3;
				else
					valRetorno = 1;

				break;

			case 2:
				helion[x][y] = 0;
				valRetorno = 4;
				break;
			case 3:
				valRetorno = 5;
				break;
			}

			return valRetorno;

		} finally {
			cerrojo.unlock();
		}

	}

	/**
	 * @throws RemoteException
	 */
	@Override
	public int estadoActual() throws RemoteException {
		cerrojo.lock();
		try {
			return nCiudadesDestruidas;
		} finally {
			cerrojo.unlock();
		}
	}

	/**
	 * @param args
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		sHelion sh = new sHelion();
		Naming.bind("//localhost:2020/servidor", sh);
		System.out.println("Servidor listo para el ataque");
	}

}
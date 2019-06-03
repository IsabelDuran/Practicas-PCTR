
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author isa
 *
 */
public class celLife {
	public static int[][] mundo;
	public static int tam;
	public static int celulasVivas;

	/**
	 * @param nCelulasVivas
	 */
	public celLife(int nCelulasVivas) {
		Random ranNumbers = new Random();
		int columna, fila;
		int nCeldas = 0;
		celulasVivas = nCelulasVivas;

		tam = 10;

		// Inicializamos la matriz a 0, dónde todas las células estarán muertas.
		mundo = new int[10][10];
		for (int i = 0; i < mundo.length; i++)
			for (int j = 0; j < mundo.length; j++)
				mundo[i][j] = 0;

		// Ahora asignamos las células vivas
		if (nCelulasVivas < (mundo.length * mundo.length)) {
			while (nCeldas < nCelulasVivas) {
				columna = ranNumbers.nextInt(10);
				fila = ranNumbers.nextInt(10);
				if (mundo[fila][columna] != 1) {
					mundo[fila][columna] = 1;
					nCeldas++;
				}
			}
		}

	}

	/**
	 * 
	 */
	public static void imprimirMundo() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				System.out.print(mundo[i][j]);
			System.out.println();
		}
	}

	public static List<Integer> celdasAdyacentes(int fila, int columna) {
		List<Integer> valoresCeldasAdyacentes = new ArrayList<>();

		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) 
				if ((fila + i) >= 0 && (fila + i) < 10 && (columna + j) >= 0 && (columna + j) < 10)
					valoresCeldasAdyacentes.add(mundo[fila + i][columna + j]);
			

		return valoresCeldasAdyacentes;
	}
}

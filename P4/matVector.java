package matvector;

import java.util.Scanner;

/**
 * @author isa
 *
 */
public class matVector {
	static int n;
	static double[][] matriz;
	static double[] vectorIni, vectorFinal;

	/**
	 * 
	 */
	static void setData() {
		Scanner sc = new Scanner(System.in);
		double valor;
		System.out.println("Introduce un tamaño:");
		n = sc.nextInt();

		matriz = new double[n][n];
		vectorIni = new double[n];

		System.out.println("Introduce los datos de la Matriz:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				valor = sc.nextDouble();
				matriz[i][j] = valor;
			}
		}

		System.out.println("Introduce los datos del VectorIni:");
		for (int i = 0; i < n; i++) {
			vectorIni[i] = sc.nextDouble();
		}
	}

	/**
	 * 
	 */
	static void randomData() {
		n = (int) (Math.random() * 100);

		matriz = new double[n][n];
		vectorIni = new double[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = (Math.random() * 100);
			}
		}

		for (int i = 0; i < n; i++) {
			vectorIni[i] = (Math.random() * 100);
		}
	}

	/**
	 * 
	 */
	static void multiplicar() {
		vectorFinal = new double[n];

		for (int i = 0; i < n; i++) {
			double suma = 0;
			for (int j = 0; j < n; j++) {
				suma += (matriz[i][j] * vectorIni[j]);
			}

			vectorFinal[i] = suma;
		}
	}

	/**
	 * 
	 */
	public static void getData() {
		for (int i = 0; i < n; i++)
			System.out.print(vectorFinal[i] + " ");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		matVector mV = new matVector();

		System.out.println("1.Introducir datos");
		System.out.println("2.Generar datos aleatoriamente");
		int op = sc.nextInt();

		switch (op) {
		case 1:
			setData();
			break;
		case 2:
			randomData();
			break;
		}

		multiplicar();
		getData();
	}
}

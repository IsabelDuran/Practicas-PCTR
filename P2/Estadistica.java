package estadistica;

import java.util.Scanner;

/**
 * @author isa
 *
 */
public class Estadistica {
	/**
	 * @param datos
	 * @return
	 */
	public static double media(String[] datos) {
		int n = datos.length;
		double media = 0;

		for (int i = 0; i < n; i++) {
			media = Double.valueOf(datos[i]).doubleValue() + media;
		}

		media = media / n;

		return media;
	}

	/**
	 * @param datos
	 * @return
	 */
	public static double moda(String[] datos) {
		int n = datos.length, coincidenciasA, coincidenciasB = 0;
		double moda = Double.valueOf(datos[0]).doubleValue();

		for (int i = 0; i < n; i++) {
			coincidenciasA = 0;
			for (int j = 0; j < n; j++) {
				if (Double.valueOf(datos[i]).doubleValue() == Double.valueOf(datos[j]).doubleValue())
					coincidenciasA++;

			}
			if (coincidenciasA > coincidenciasB) {
				coincidenciasB = coincidenciasA;
				moda = Double.valueOf(datos[i]).doubleValue();
			}

		}

		return moda;
	}

	/**
	 * @param datos
	 * @return
	 */
	public static double varianza(String[] datos) {
		int n = datos.length;
		double varianza = 0;

		for (int i = 0; i < n; i++) {
			varianza = varianza + Math.pow(Double.valueOf(datos[i]).doubleValue() - media(datos), 2);
		}

		varianza = (varianza / (n - 1));

		return varianza;
	}

	/**
	 * @param datos
	 * @return
	 */
	public static double desviacionTipica(String[] datos) {
		return Math.sqrt(varianza(datos));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int op;

		System.out.println("1.Media");
		System.out.println("2.Moda");
		System.out.println("3.Varianza");
		System.out.println("4.Desviacion Tipica");

		op = sc.nextInt();

		switch (op) {
		case 1:
			System.out.println("Media: " + media(args));
			break;
		case 2:
			System.out.println("Moda: " + moda(args));
			break;
		case 3:
			System.out.println("Varianza: " + varianza(args));
			break;
		case 4:
			System.out.println("DesviacionTipica: " + desviacionTipica(args));
			break;
		}

	}
}

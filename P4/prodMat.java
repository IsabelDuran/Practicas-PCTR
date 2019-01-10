package matvector;

import java.util.Scanner;

/**
 * @author isa
 *
 */
public class prodMat {
	int tamMatriz;
	double[][] matrizA, matrizB, matrizResultado;

	/**
	 * 
	 */
	public void imprimeMatriz() {
		for (int i = 0; i < tamMatriz; i++) {
			System.out.println("");
			for (int j = 0; j < tamMatriz; j++)
				System.out.print(matrizResultado[i][j] + " ");
		}
	}
	
	/**
	 * 
	 */
	public void setData()
	{
		Scanner sc = new Scanner(System.in);
		double valor;
		System.out.println("Introduce un tamaño:");
		tamMatriz = sc.nextInt();

		matrizA = new double[tamMatriz][tamMatriz];
		matrizB = new double[tamMatriz][tamMatriz];

		System.out.println("Introduce los datos de la Matriz A:");
		for (int i = 0; i < tamMatriz; i++) {
			for (int j = 0; j < tamMatriz; j++) {
				valor = sc.nextDouble();
				matrizA[i][j] = valor;
			}
		}

		System.out.println("Introduce los datos de la Matriz B:");
		for (int i = 0; i < tamMatriz; i++) {
			for (int j = 0; j < tamMatriz; j++) {
				valor = sc.nextDouble();
				matrizA[i][j] = valor;
			}
		}
	}

	/**
	 * 
	 */
	public void multiplicar() {
		matrizResultado = new double[tamMatriz][tamMatriz];

		for (int i = 0; i < matrizResultado.length; i++) 
			for (int j = 0; j < matrizResultado.length; j++) 
				for (int k = 0; k < matrizResultado[0].length; k++)
					matrizResultado[i][j] = matrizA[i][k] * matrizB[k][j];
	}
}

import java.util.Scanner;

/**
 * @author isa
 *
 */
public class escalaVector {
	private static int[] vector = new int[100000000];
	
	/**
	 * 
	 */
	public escalaVector() {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = i;
		}
	}
	
	/**
	 * @param n
	 */
	public static void escalar(double n) {
		for (int i = 0; i < vector.length; i++) {
			vector[i] *= n;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double n;

		System.out.println("Introduce un factor de escala:");
		n = sc.nextDouble();
		sc.close();
		
		escalar(n);
		System.out.println("Finalizado");
	}

}

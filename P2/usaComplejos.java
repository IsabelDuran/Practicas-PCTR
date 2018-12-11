package complejos;

import java.util.*;

/**
 * @author isa
 *
 */
class usaComplejos {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Complejos c1 = new Complejos(3.2, 5.6);
		Complejos c2 = new Complejos(1, 2.5);
		int op;
		Scanner sc = new Scanner(System.in);

		System.out.println("1.Suma");
		System.out.println("2.Resta");
		System.out.println("3.Producto");
		System.out.println("4.Modulo");
		System.out.println("5.Cociente");

		op = sc.nextInt();

		switch (op) {
		case 1:
			c1.suma(c2);
			System.out.println("Suma: (" + c1.getReal() + "," + c1.getImag() + "i)");
			break;
		case 2:
			c1.resta(c2);
			System.out.println("Resta: (" + c1.getReal() + "," + c1.getImag() + "i)");
			break;
		case 3:
			c1.producto(c2);
			System.out.println("Producto: (" + c1.getReal() + "," + c1.getImag() + "i)");
			break;
		case 4:
			System.out.println("Modulo:" + c1.modulo());
			break;
		case 5:
			c1.cociente(c2);
			System.out.println("Cociente: (" + c1.getReal() + "," + c1.getImag() + "i)");
			break;
		}

	}
}
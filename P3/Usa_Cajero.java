/**
 * @author isa
 *
 */
public class Usa_Cajero {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Cuenta_Banca cuenta = new Cuenta_Banca(1, 500);

		Thread hilo1 = new Thread(new Cajero(cuenta, true, 200));
		Thread hilo2 = new Thread(new Cajero(cuenta, false, 200));

		hilo1.start();
		hilo2.start();
		hilo1.join();
		hilo2.join();
		
		System.out.println("El saldo es: " + cuenta.Saldo());
	}
}

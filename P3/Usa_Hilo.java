/**
 * @author isa
 *
 */
public class Usa_Hilo {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Hilo h1 = new Hilo(1);
		Hilo h2 = new Hilo(0);
		h1.start();
		h2.start();
		h1.join();
		h2.join();
		System.out.println(Hilo.getN());
	}
}

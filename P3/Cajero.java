/**
 * @author isa
 *
 */
public class Cajero implements Runnable {

	private static boolean operacion;
	private static Cuenta_Banca cuenta;
	private int depositoReintegro;

	/**
	 * @param cuenta
	 * @param operacion
	 * @param depositoReintegro
	 */
	public Cajero(Cuenta_Banca cuenta, boolean operacion, int depositoReintegro) {
		Cajero.cuenta = cuenta;
		Cajero.operacion = operacion;
		this.depositoReintegro = depositoReintegro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		if (operacion)
			for (int i = 0; i < this.depositoReintegro; i++)
				cuenta.Deposito(1);
		else
			for (int i = 0; i < this.depositoReintegro; i++)
				cuenta.Reintegro(1);
	}

}


public class Cliente implements Runnable{
	lineaCajas cajas;
	/**
	 * 
	 */
	public Cliente(lineaCajas cajas) {
		this.cajas = cajas;
	}

	/**
	 *
	 */
	@Override
	public void run() {
		while(true) {
			cajas.entrarCaja();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cajas.dejarCaja();
		}
	}

}

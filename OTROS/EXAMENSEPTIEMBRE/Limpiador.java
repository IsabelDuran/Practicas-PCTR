

public class Limpiador implements Runnable {
	int id;
	Oficina oficina;

	public Limpiador(int id, Oficina oficina) {
		super();
		this.id = id;
		this.oficina = oficina;
	}

	@Override
	public void run() {
		try {
			oficina.limpiarMesa(id);
			Thread.sleep(100);
			oficina.dejarMesaLimpia(id);
		} catch (Exception e) {
			System.out.print(" ");
		}

	}

}

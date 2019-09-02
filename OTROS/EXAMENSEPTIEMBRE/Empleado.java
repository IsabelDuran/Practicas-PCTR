

public class Empleado implements Runnable {
	int id;
	Oficina oficina;
	
	
	public Empleado(int id, Oficina oficina) {
		super();
		this.id = id;
		this.oficina = oficina;
	}


	@Override
	public void run() {
		try {
			oficina.ocuparMesa(id);
			Thread.sleep(10000);
			oficina.dejarMesa(id);
		} catch (Exception e) {
			System.out.print(" ");
		}
		
	}

}

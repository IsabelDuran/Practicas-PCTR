import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHiloSinPool extends Thread {
	Socket enchufe;
	public ServidorHiloSinPool(Socket s) {
		enchufe = s;
		this.start();
	}

	public void run() {
		long inicio = System.currentTimeMillis();
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
			String datos = entrada.readLine();
			int j;
			int i = Integer.valueOf(datos).intValue();
			for (j = 1; j <= 20; j++) {
				System.out.println("El hilo " + this.getName() + " escribiendo el dato " + i);
				sleep(1000);
			}
			enchufe.close();
			System.out.println("El hilo " + this.getName() + " cierra su conexion...");
		} catch (Exception e) {
			System.out.println("Error...");
		}
		long fin = System.currentTimeMillis();
		long tiempo = fin - inicio;
		System.out.println("El hilo " + this.getName() + " ha tardado " + tiempo + " segundos");
	}// run

	public static void main (String[] args)
	{
	    int i;
	    int puerto = 2001;
	        try{
	            ServerSocket chuff = new ServerSocket (puerto, 3000);

	            while (true){
	                System.out.println("Esperando solicitud de conexion...");
	                Socket cable = chuff.accept();
	                System.out.println("Recibida solicitud de conexion...");
	                new ServidorHiloSinPool(cable);
	        }//while
	      } catch (Exception e)
	        {System.out.println("Error en sockets...");}
	}//main
}

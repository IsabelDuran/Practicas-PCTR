import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteMultiple {
	public static void main(String[] args) {
		int nPeticiones = 1;
		for (int j = nPeticiones; j < 10; j++) {
			int i = (int) (Math.random() * 10);
			int puerto = 2001;
			try {

				System.out.println("Realizando conexion...");
				Socket cable = new Socket("localhost", puerto);
				System.out.println("Realizada conexion a " + cable);
				PrintWriter salida = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
				salida.println(i);
				salida.flush();
				System.out.println("Cerrando conexion...");
				cable.close();

			} // try
			catch (Exception e) {
				System.out.println("Error en sockets...");
			}
		}
	}
}

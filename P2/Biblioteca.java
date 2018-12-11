package paciente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author isa
 *
 */
public class Biblioteca {

	private static List<Paciente> pacientes = new ArrayList<>();

	public static void anadirPaciente(String nombre, String dni, String direccion, String telefono, String compania) {
		pacientes.add(new Paciente(nombre, dni, direccion, telefono, compania));
	}

	/**
	 * @param nombre
	 * @return
	 */
	public static List<Paciente> buscarPacientesNombre(String nombre) {
		List<Paciente> pacientesEncontrados = new ArrayList<>();

		for (Paciente paciente : pacientes)
			if (paciente.getNombre() == nombre)
				pacientesEncontrados.add(paciente);

		return pacientes;
	}

	/**
	 * @param compania
	 * @return
	 */
	public static List<Paciente> buscarPacientesCompaniaSeguros(String compania) {
		List<Paciente> pacientesEncontrados = new ArrayList<>();

		for (Paciente paciente : pacientes)
			if (paciente.getCompaniaSeguros() == compania)
				pacientesEncontrados.add(paciente);

		return pacientes;
	}

	/**
	 * @param direccion
	 * @return
	 */
	public static List<Paciente> buscarPacientesDireccion(String direccion) {
		List<Paciente> pacientesEncontrados = new ArrayList<>();

		for (Paciente paciente : pacientes)
			if (paciente.getDireccion() == direccion)
				pacientesEncontrados.add(paciente);

		return pacientes;
	}

	/**
	 * @param DNI
	 * @return
	 */
	public static Paciente buscarPacienteDNI(String DNI) {
		for (Paciente paciente : pacientes)
			if (paciente.getDni() == DNI)
				return paciente;
		return null;
	}

	/**
	 * @param telefono
	 * @return
	 */
	public static Paciente buscarPacienteTelefono(String telefono) {
		for (Paciente paciente : pacientes)
			if (paciente.getTelefono() == telefono)
				return paciente;
		return null;
	}

	/**
	 * @param dni
	 */
	public static void borrarPaciente(String dni) {
		for (Paciente paciente : pacientes)
			if (paciente.getDni() == dni)
				pacientes.remove(paciente);
	}
	
	/**
	 * @param pacientesEncontrados
	 */
	public static void listarPacientes(List<Paciente> pacientesEncontrados) {
		for (Paciente paciente : pacientesEncontrados)
			paciente.toString();
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int op;
		System.out.println("Bienvenido, ¿Qué operación desea realizar?");
		System.out.println("1.Consultar cliente por DNI");
		System.out.println("2.Consultar clientes por nombre");
		System.out.println("3.Consultar clientes por dirección");
		System.out.println("4.Consultar clientes por compañía de seguros");
		System.out.println("5.Consultar cliente por telefono");
		System.out.println("6.Añadir cliente");
		System.out.println("7.Borrar cliente");
		op = sc.nextInt();

		switch (op) {
		case 1:
			System.out.println("Introduce el dni del cliente que desea buscar");
			String dniBuscar = dato.readLine();
			buscarPacienteDNI(dniBuscar).toString();
			break;
		case 2:
			System.out.println("Introduce el nombre de los clientes que desea buscar");
			String nombreBuscar = dato.readLine();
			listarPacientes(buscarPacientesNombre(nombreBuscar));
			break;
			
		case 3:
			System.out.println("Introduce la direccion de los clientes que desea buscar");
			String direccionBuscar = dato.readLine();
			listarPacientes(buscarPacientesNombre(direccionBuscar));
			break;
			
		case 4:
			System.out.println("Introduce la compañia de seguros de los clientes que desea buscar");
			String companiaBuscar = dato.readLine();
			listarPacientes(buscarPacientesNombre(companiaBuscar));
			break;
		case 5:
			System.out.println("Introduce el telefono del cliente que desea buscar");
			String telefonoBuscar = dato.readLine();
			buscarPacienteTelefono(telefonoBuscar).toString();
			break;
		case 6:
			System.out.println("Introduce el DNI del cliente que desea añadir");
			String dni = dato.readLine();
			System.out.println("Introduce el nombre del cliente que desea añadir");
			String nombre = dato.readLine();
			System.out.println("Introduce la dirección del cliente que desea añadir");
			String direccion = dato.readLine();
			System.out.println("Introduce la compañía de seguros del cliente que desea añadir");
			String compania = dato.readLine();
			System.out.println("Introduce el telefono del cliente que desea añadir");
			String telefono = dato.readLine();
			anadirPaciente(nombre, dni, direccion, telefono, compania);
			break;
		case 7:
			System.out.println("Introduce el DNI del cliente que desea borrar");
			String dni1 = dato.readLine();
			borrarPaciente(dni1);
			break;
		}
	}

}

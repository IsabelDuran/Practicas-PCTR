package paciente;

/**
 * @author isa
 *
 */
public class Paciente {

	private String nombre;
	private String dni;
	private String direccion;
	private String telefono;
	private String companiaSeguros;

	/**
	 * @param nombre
	 * @param dni
	 * @param direccion
	 * @param telefono
	 * @param companiaSeguros
	 */
	public Paciente(String nombre, String dni, String direccion, String telefono, String companiaSeguros) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.companiaSeguros = companiaSeguros;
	}
	
	/**
	 * 
	 */
	public Paciente() {}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return
	 */
	public String getCompaniaSeguros() {
		return companiaSeguros;
	}

	/**
	 * @param companiaSeguros
	 */
	public void setCompaniaSeguros(String companiaSeguros) {
		this.companiaSeguros = companiaSeguros;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "El paciente: " + nombre + " con DNI: " + dni + " vive en " + direccion
				+ " su telefono es " + telefono + " y su seguro es " + companiaSeguros;
	}
	

}
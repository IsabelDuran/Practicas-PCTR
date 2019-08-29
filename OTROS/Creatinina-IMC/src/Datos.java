import java.io.Serializable;

public class Datos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int edad;
	private double peso;
	private double creatinina;
	private int sexo;

	public Datos(int edad, double peso, double creatinina, int sexo) {
		super();
		this.edad = edad;
		this.peso = peso;
		this.creatinina = creatinina;
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getCreatinina() {
		return creatinina;
	}

	public void setCreatinina(double creatinina) {
		this.creatinina = creatinina;
	}

	public int getSexo() {
		return sexo;
	}



}

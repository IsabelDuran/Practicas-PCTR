/**
 * Clase que modela personas.
 *
 * @author Natalia Partera
 * @version 2.0
 */

public class Persona {
  protected String nombre, apellidos;
  protected int añoNacimiento;
  private int dni;
  private String ciudadR;
  protected String paisR;


  public Persona() {}

  public Persona(String nom, String ape, int año, String pais) {
    nombre = nom;
    apellidos = ape;
    añoNacimiento = año;
    paisR = pais;
  }

  public Persona(String nom, String ape, int año, int dni, String ciudad, String pais) {
    nombre = nom;
    apellidos = ape;
    añoNacimiento = año;
    this.dni = dni;
    ciudadR = ciudad;
    paisR = pais;
  }

  protected String Nombre() {
    return nombre;
  }

  protected String Apellidos() {
    return apellidos;
  }

  protected int AñoNacimiento() {
    return añoNacimiento;
  }

  private int Dni() {
    return dni;
  }

  private String Ciudad() {
    return ciudadR;
  }

  protected String Pais() {
    return paisR;
  }

  protected void Ciudad(String ciudad) {
    ciudadR = ciudad;
  }

  protected void Pais(String pais) {
    paisR = pais;
  }
}

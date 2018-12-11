/**
 * Clase que modela personas.
 *
 * nombre, apellidos, año de nacimiento, DNI, ciudad de residencia, país de residencia y estudios
 * @author Natalia Partera
 * @version 1.0
 */

public class Persona {
  protected String nombre, apellidos;
  protected int añoNacimiento;
  private int dni;
  private String ciudadR;
  protected String paisR;
  private String estudios; 


  public Persona() {}

  protected Persona(String nom, String ape, int año, String pais) {
    nombre = nom;
    apellidos = ape;
    añoNacimiento = año;
    paisR = pais;
  }

  public Persona(String nom, String ape, int año, int dni, String ciudad, String pais, String est) {
    nombre = nom;
    apellidos = ape;
    añoNacimiento = año;
    this.dni = dni;
    ciudadR = ciudad;
    paisR = pais;
    estudios = est;
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

  private String Estudios() {
    return estudios;
  }

  public void Ciudad(String ciudad) {
    ciudadR = ciudad;
  }

  public void Pais(String pais) {
    paisR = pais;
  }

  public void Estudios(String est) {
    estudios = est;
  }

  public void VerDatos() {
    System.out.println(Nombre() + " " + Apellidos() + ", con DNI: " + Dni() + " nació en " + AñoNacimiento());
    System.out.println("Vive en " + Ciudad() + ", " + Pais() + ". Tiene estudios de " + Estudios());
  }
}

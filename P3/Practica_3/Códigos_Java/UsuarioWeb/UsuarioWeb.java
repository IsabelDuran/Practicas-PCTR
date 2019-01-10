/**
 * Clase que modela los datos almacenados de los usuarios de una web.
 *
 * @author Natalia Partera
 * @version 2.0
 */

public class UsuarioWeb extends Persona {
  private String nombreUsuario, contraseña;

  public UsuarioWeb() {}

  public UsuarioWeb(String alias, String contras, String nom, String ape, int añoNac, String pais) {
    super(nom, ape, añoNac, pais);
    nombreUsuario = alias;
    contraseña = contras;
  }

  public UsuarioWeb(String alias, String contras, String nom, String ape, int añoNac, int dni, String ciudad, String pais) {
    super(nom, ape, añoNac, dni, ciudad, pais);
    nombreUsuario = alias;
    contraseña = contras;
  }

  public String Alias() {
    return nombreUsuario;
  }

  protected String Contraseña() {
    return contraseña;
  }

  protected void Contraseña(String contras) {
    contraseña = contras;
  }

  public boolean ValidarContraseña(String contras) {
    if(contraseña == contras)
      return true;
    else
      return false;
  }
}

/**
 * Clase que modela los datos almacenados de los usuarios de una web.
 *
 * el alias del usuario, contraseña, nombre real, año de nacimiento y el país de residencia.
 * @author Natalia Partera
 * @version 1.0
 */

public class UsuarioWeb extends Persona {
  private String nombreUsuario, contraseña;

  public UsuarioWeb() {}

  public UsuarioWeb(String alias, String contras, String nom, String ape, int añoNac, String pais) {
    super(nom, ape, añoNac, pais);
    nombreUsuario = alias;
    contraseña = contras;
  }

  public String Alias() {
    return nombreUsuario;
  }

  public String Contraseña() {
    return contraseña;
  }

  public void Contraseña(String contras) {
    contraseña = contras;
  }

  public boolean ValidarContraseña(String contras) {
    if(contraseña == contras)
      return true;
    else
      return false;
  }
}

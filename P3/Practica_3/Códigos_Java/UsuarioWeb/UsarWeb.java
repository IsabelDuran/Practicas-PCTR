/**
 * Interfaz que modela el uso de una página web.
 *
 * @author Natalia Partera
 * @version 1.0
 */


public interface UsarWeb{
  boolean Conectarse(String contraseña) {}
  boolean Desconectarse() {}
  void AvisarActualizacion() {}
}

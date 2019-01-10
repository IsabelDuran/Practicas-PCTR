/**
 * Programa que simula el guardado de datos de los usuarios de una web.
 *
 * @author Natalia Partera
 * @version 1.0
 */

import java.util.*;

public class UsaUsuarioWeb
{
  public static void main (String args[]) 
  {
    Persona p1;
    UsuarioWeb user2;

    p1 = new Persona("Pepito", "de los Palotes", 1923, 12345678, "Salamanca", "España", "Master en decir tonterías");
    user2 = new UsuarioWeb("mBombo", "palillos11", "Manolo", "el del Bombo", 1900, "España");

    System.out.println("Persona p1:");
    System.out.println("Nombre: " + p1.Nombre());    //protected
    System.out.println("Apellidos: " + p1.Apellidos());    //protected
    System.out.println("Año de nacimiento: " + p1.AñoNacimiento());    //protected
//    System.out.println("DNI: " + p1.Dni());    //private
//    System.out.println("Ciudad: " + p1.Ciudad());    //private
    System.out.println("Pais: " + p1.Pais());    //protected
//    System.out.println("Estudios: " + p1.Estudios());    //private
    p1.Ciudad("Lisboa");    //public
    p1.Pais("Portugal");    //public
    p1.Estudios("Master en tocar los palillos");    //public
    p1.VerDatos();    //public

    System.out.println("User user2:");
    System.out.println("Alias: " + user2.Alias());    //public
    System.out.println("Contraseña: " + user2.Contraseña());    //public
    System.out.println("Nombre: " + user2.Nombre());    //protected
    System.out.println("Apellidos: " + user2.Apellidos());    //protected
    System.out.println("Año de nacimiento: " + user2.AñoNacimiento());    //protected
//    System.out.println("Ciudad: " + user2.Ciudad());    //private
    System.out.println("Pais: " + user2.Pais());    //protected
  }
}

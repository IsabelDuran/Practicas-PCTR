/**
 * Programa que simula el guardado de datos de los usuarios de una web.
 *
 * @author Natalia Partera
 * @version 2.0
 */

import java.io.*;
import java.util.*;

public class UsaUsuarioWeb
{
  public static void main (String args[]) 
  {
    UsaUsuarioWeb web = new UsaUsuarioWeb();
    LinkedList<UsuarioWeb> usuarios = new LinkedList();
    boolean salir = false;
    int uId, año;
    UsuarioWeb user = new UsuarioWeb();
    String alias, contraseña, nombre, apellidos, pais;
    BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

    try {
      do {
        int opc = web.Menu();
        switch (opc) {
          case 1:
            System.out.println("Introduzca su nombre de usuario: ");
            alias = br.readLine();
            System.out.println("Introduzca su contraseña: ");
            contraseña = br.readLine();
            System.out.println("Introduzca su nombre real: ");
            nombre = br.readLine();
            System.out.println("Introduzca sus apellidos reales: ");
            apellidos = br.readLine();
            System.out.println("Introduzca el año en que nació: ");
            año = Integer.parseInt(br.readLine());
            System.out.println("Introduzca el país en el que reside: ");
            pais = br.readLine();
            user = new UsuarioWeb(alias, contraseña, nombre, apellidos, año, pais);
            usuarios.add(user);
          break;
          case 2:
            if(usuarios.size() > 0) {
              for (int i = 0; i < usuarios.size(); ++i) {
                user = usuarios.get(i);
                System.out.println((i + 1) + ") " + user.Alias());
              }
              do {
                System.out.print("Indique el número del usuario que desea borrar: ");
                uId = Integer.parseInt(br.readLine());
              } while (uId < 1 || uId > (usuarios.size() + 1));
              --uId;
              usuarios.remove(uId);
            }
            else {
              System.out.println("No hay ningún usuario.");
            }
          break;
          case 3:
            if(usuarios.size() > 0) {
              for (int i = 0; i < usuarios.size(); ++i) {
                user = usuarios.get(i);
                System.out.println((i + 1) + ") " + user.Alias());
              }
              do {
                System.out.print("Indique el número del usuario que desea editar: ");
                uId = Integer.parseInt(br.readLine());
              } while (uId < 1 || uId > (usuarios.size() + 1));
              --uId;
              user = usuarios.get(uId);
              System.out.println("El usuario " + user.Alias() + " con contraseña " + user.Contraseña() + " se llama en realidad " + user.Nombre() + " " + user.Apellidos() + ". Nació en " + user.AñoNacimiento() + " y reside en " + user.Pais() + ".");
              System.out.println("Editando país. Introduzca país actual: ");
              pais = br.readLine();
              user.Pais(pais);
              System.out.println("Editando contraseña. Introduzca nueva contraseña: ");
              contraseña = br.readLine();
              user.Contraseña(contraseña);
              usuarios.set(uId, user);
            }
            else {
              System.out.println("No hay ningún usuario.");
            }
          break;
          case 4:
            for (int i = 0; i < usuarios.size(); ++i) {
              user = usuarios.get(i);
              System.out.println((i + 1) + ") " + user.Alias());
            }
          break;
          case 5:
          salir = true;
          break;
        }
      } while(!salir); 
    }
    catch(IOException exc) {
      System.err.println("¡Ups! Ha tenido lugar un error de E/S");
      exc.printStackTrace();
      System.exit(-1);
    }
  }


  int Menu() {
    Scanner teclado = new Scanner(System.in);
    int opc;

    System.out.println("Lista de opciones:");
    System.out.println("1) Añadir nuevo usuario.");
    System.out.println("2) Borrar usuario.");
    System.out.println("3) Editar usuario.");
    System.out.println("4) Ver todos los usuarios.");
    System.out.println("5) Salir.");
    do {    
      System.out.print("¿Qué acción desea realizar? ");
      opc = teclado.nextInt();
    } while(opc < 1 || opc > 5);

    return opc;
  }
}

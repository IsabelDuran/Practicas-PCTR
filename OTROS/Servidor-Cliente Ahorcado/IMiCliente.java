/**
*	Interfaz que muestra los métodos remotos del cliente.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.rmi.*;

public interface IMiCliente extends Remote{
	
	/**
	* Método remoto que muestra mediante un mensaje el ganador del juego.
	*@param nombre Nombre del ganador.
	*@return
	*@throws RemoteException
	*/
	public void ganador(String nombre) throws RemoteException;

	/**
	* Método remoto que actualiza el turno del jugador y el progreso de la palabra en juego.
	*@param turn  Nombre del jugador que ocupa el turno .
	*@param averiguado Cadena de caracteres que muestra el progreso realizado.
	*@return
	*@throws RemoteException
	*/
	public void actualizar(String turn,String averiguado)throws RemoteException;
}
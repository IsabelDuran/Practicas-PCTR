/**
*	Interfaz que define los métodos del servidor MiAhorcadoServidor.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/


import java.rmi.*;

public interface IMiAhorcado extends Remote{
	/**
	*	Envia una letra al servidor
	*	@param letra
	*	@return 	
	*/
	public void enviarLetra(char letra) throws RemoteException; 

	/**
	*	Envia una palabra como solución y el indice que ocupa el jugador en el servidor.
	*	@param palabra Palabra que envía como posible solución.
	*	@param i Indice entero que ocupa el jugador en el servidor.
	*	@return
	*	@throws RemoteException
	*/
	public void enviarSolucion(String palabra,int i) throws RemoteException;


	/**
	*	Registra un jugador en el servidor.
	*	@param jug Objeto remoto del cliente que ejecuta el método.
	*	@param  nombre Nombre del jugador.
	*	@return Devuelve el indice que ocupa el jugador en el servidor.
	*	@throws RemoteException 
	*/
	public int registrarJugador(IMiCliente jug, String nombre) throws RemoteException;

	/**
	*	Elimina un jugador del servidor.
	*	@param jug Objeto remoto del cliente que vamos a eliminar.
	*	@return
	*	@throws RemoteException
	*/
	public void eliminarJugador(IMiCliente jug) throws RemoteException;

	/**
	*	Devuelve el número de jugadores conectados al servidor en ese momento.
	*	@return Devuelve el número de jugadores(entero).
	*	@throws RemoteException
	*
	*/
	public int nJugadores()throws RemoteException;
	
	/**
	*	Devuelve el id del jugador al que le corresponde el turno
	*	@return entero que representa el id del jugador al que le toca jugar.
	*	@throws RemoteException
	*/
	public int turno() throws RemoteException;


	/**
	*	Actualiza los valores del turno y de las letras encontradas de la palabra.
	*	@return
	*	@throws RemoteException
	*/
	public void actualizar() throws RemoteException;
}

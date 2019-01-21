/**
*	Servidor que se encarga de llevar a cabo el juego del ahorcador	
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.rmi.registry.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.Arrays;

public class MiAhorcadoServidor extends UnicastRemoteObject implements IMiAhorcado{
	ReentrantLock c = new ReentrantLock();
	String[] diccionario = {"Programacion","Concurrencia","Semaforo","Monitor","Bishuela","Discreta","Metodologia","Embebida","Transaccion"};
	String solucion;
	char[] averiguado;
	List<IMiCliente> jugadores;
	String[]	nombres;
	int turno;
	int n;

	public MiAhorcadoServidor() throws RemoteException{
		reset();
	}
	
	/**
	*	Actualiza los valores del turno y de las letras encontradas de la palabra.
	*	@return
	*	@throws RemoteException
	*/
	public void actualizar() throws RemoteException{
		for(int j=0;j<jugadores.size();j++){
			IMiCliente aux=jugadores.get(j);
			aux.actualizar(nombres[turno],new String(averiguado));
		}
	}

	/**
	*	Registra un jugador en el servidor.
	*	@param jug Objeto remoto del cliente que ejecuta el método.
	*	@param  nombre Nombre del jugador.
	*	@return Devuelve el indice que ocupa el jugador en el servidor.
	*	@throws RemoteException 
	*/
	public int registrarJugador(IMiCliente jug,String nombre) throws RemoteException{
		c.lock();
		jugadores.add(n,jug);
		nombres[n]=nombre;
		n++;
		c.unlock();
		return (n-1);
	}

	/**
	*	Devuelve el número de jugadores conectados al servidor en ese momento.
	*	@return Devuelve el número de jugadores(entero).
	*	@throws RemoteException
	*
	*/
	public int nJugadores()throws RemoteException{
		return n;
	}

	/**
	*	Elimina un jugador del servidor.
	*	@param jug Objeto remoto del cliente que vamos a eliminar.
	*	@return
	*	@throws RemoteException
	*/
	public void eliminarJugador(IMiCliente jug)throws RemoteException{
		c.lock();
		if(jugadores.remove(jug)){
			System.out.println("Jugador eliminado.");
			n--;
			if(jugadores.size()==0){
				System.out.println("No quedan jugadores");
				System.out.println("Reiniciando Servidor...");
				reset();
			}
		}
		else{
			System.out.println("Jugador eliminado.");
		}
		c.unlock();

	}
	
	/**
	*	Devuelve el id del jugador al que le corresponde el turno
	*	@return entero que representa el id del jugador al que le toca jugar.
	*	@throws RemoteException
	*/
	public int turno(){
		return turno;
	}

	/**
	*	Envia una letra al servidor
	*	@param letra
	*	@return 	
	*/
	public void enviarLetra(char letra) throws RemoteException{
		int i=solucion.indexOf(new Character(letra).toString().toLowerCase(),0);
		while(i!=-1){
			averiguado[i]=letra;
			i=solucion.indexOf(new Character(letra).toString().toLowerCase(),i+1);		
		}
		turno=(turno+1)%n;
		for(int j=0;j<jugadores.size();j++){
			IMiCliente aux=jugadores.get(j);
			aux.actualizar(nombres[turno],new String(averiguado));
		}
		
	}

	/**
	*	Envia una palabra como solución y el indice que ocupa el jugador en el servidor.
	*	@param palabra Palabra que envía como posible solución.
	*	@param i Indice entero que ocupa el jugador en el servidor.
	*	@return
	*	@throws RemoteException
	*/
	public void enviarSolucion(String palabra,int i) throws RemoteException{
		boolean val=solucion.equals(palabra.toLowerCase());
		turno=(turno+1)%n;
		for(int j=0;j<jugadores.size();j++){
			IMiCliente aux=jugadores.get(j);
			aux.actualizar(nombres[turno],new String(averiguado));
		}
		if(val){
			averiguado=solucion.toCharArray();
			c.lock();
			try{
				for(int j=0;j<jugadores.size();j++){
					IMiCliente aux=jugadores.get(j);
					System.out.println(jugadores.size());
					aux.ganador(nombres[i]);
				}
			}finally{
				c.unlock();
			}
		}
	}
	
	/**
	*	Método privado que sirve para reiniciar todos los parámetros del servidor.
	*	@return
	*/
	private  void reset(){
		turno=0;
		n=0;
		solucion = diccionario[(int)Math.floor(Math.random()*9)];
		jugadores = new ArrayList<IMiCliente>();
		averiguado = new char[solucion.length()];
		Arrays.fill(averiguado,'_');
		nombres = new String[100];
		this.solucion=solucion.toLowerCase();
	}
	
	public static void main(String[] args){
		try{
			LocateRegistry.createRegistry(1099);
			//System.setProperty("java.rmi.server.hostname", "192.168.0.168"); 
			IMiAhorcado oRemoto  = new MiAhorcadoServidor();
			Naming.rebind("rmi://localhost:1099/MiAhorcado",oRemoto);
		}catch(RemoteException f){
			System.out.println("Error servidor.");
		}
		catch(MalformedURLException e){
			System.out.println("Dominio malformado.");
		}
		System.out.println("Sin problemas");

	}
}

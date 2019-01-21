
/**
*	Cliente del juego del Ahorcado, que se conecta a MiAhorcadoServidor, con gui propia,callback de cliente y polling para realizar un conteo de los jugadores.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.net.*;
import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiAhorcadoCliente extends UnicastRemoteObject implements IMiCliente{
	private  static String entrada;
	private static IMiCliente cliente;
	private static IMiAhorcado oRemoto;
	private static VentanaCliente gui;
	private static JLabel solucion;
	private static JTextField edittext;
	private static JLabel turno;
	private static boolean ganador;
	private static boolean fin;
	private static boolean block;
	private static int id;

	//Constructor de la clase
	public MiAhorcadoCliente() throws RemoteException{}


	/**
	* Método remoto que muestra mediante un mensaje el ganador del juego.
	*@param nombre Nombre del ganador.
	*@return
	*@throws RemoteException
	*/
	public void ganador(String nombre) throws RemoteException{
		oRemoto.actualizar();
		fin=true;
		block=false;
		synchronized(gui){
			gui.notifyAll();
		}
		JOptionPane.showMessageDialog(null,"El ganador ha sido: "+nombre,"GANADOR",JOptionPane.PLAIN_MESSAGE);
	}


	/**
	* Método que se encarga de inicializar el GUI del cliente.
	* @param 
	* @return
	*/
	private static void guicliente(){
		gui = new VentanaCliente("AhorcadoCliente v1.0","Introduce palabra o letra");
		edittext = new JTextField(20);
		turno = new JLabel();
		edittext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				entrada=e.getActionCommand();
				block=false;
				synchronized(gui){gui.notifyAll();}
			}
		});
		solucion = new JLabel();
		gui.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent w){
				try{
					oRemoto.eliminarJugador(cliente);
				}catch(Exception e){}
				System.exit(0);
				
			}
		});
		solucion.setForeground(Color.RED);
		solucion.setFont(new Font("Arial",Font.BOLD,16));
		JLabel title = new JLabel("Solucion:");
		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("cliente.png"));
		edittext.setEditable(false);
		gui.add(edittext);
		gui.add(title);
		gui.add(solucion);
		gui.add(turno);
		gui.add(imagen);
		gui.setSize(300,400);
		gui.setVisible(true);
	}

	/**
	* Método remoto que actualiza el turno del jugador y el progreso de la palabra en juego.
	*@param turn  Nombre del jugador que ocupa el turno .
	*@param averiguado Cadena de caracteres que muestra el progreso realizado.
	*@return
	*@throws RemoteException
	*/
	public void actualizar(String turn,String averiguado) throws RemoteException{
		turno.setText("Turno de: "+turn);
		solucion.setText(averiguado);
		if(oRemoto.turno()==id) {
			edittext.setEditable(true);
		}
	}

	/**
	* Método main que conecta al servidor y se encarga de realizar las operaciones para gestionar el juego.
	*
	* @param
	* @return
	*/
	public static void main(String[] args){
		block=true;
		fin = false;
		try{
			oRemoto = (IMiAhorcado)Naming.lookup("rmi://localhost:1099/MiAhorcado");
			cliente = new MiAhorcadoCliente();
			String nombre = JOptionPane.showInputDialog(null,"Introduce tu nombre:","Registro",JOptionPane.PLAIN_MESSAGE);
			id=oRemoto.registrarJugador(cliente,nombre);
			VentanaCarga loadingscreen = new VentanaCarga("Cargando...","Esperando jugadores...");
			while(oRemoto.nJugadores()<2);
			guicliente();
			loadingscreen.setVisible(false);
			loadingscreen.dispose();
			oRemoto.actualizar();
			do{
				while(block==true){
					synchronized(gui){
						try{
							gui.wait();
						}catch(InterruptedException e){}
					}
				}
				if(!fin && entrada.length()==1){
					oRemoto.enviarLetra(entrada.charAt(0));

				}
				else if(!fin){
					oRemoto.enviarSolucion(entrada,id);
				}
				block=true;
				edittext.setEditable(false);
			}while(!fin);
			oRemoto.eliminarJugador(cliente);
		}catch(RemoteException | NotBoundException | MalformedURLException e){}
		System.exit(0);
	}
}

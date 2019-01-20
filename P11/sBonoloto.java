//package bonoloto;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sBonoloto extends UnicastRemoteObject implements iBonoloto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] combinacionGanadora = new int[6];
	
	private void printNumbers() {
		for(int i = 0; i < combinacionGanadora.length; i++) {
			System.out.println(combinacionGanadora[i]);
		}
	}


	@Override
	public boolean compApuesta(int[] apuesta) throws RemoteException {
		boolean falso = true;
		for (int i = 0; i < apuesta.length; i++) {
			if(apuesta[i] != combinacionGanadora[i])
				falso = false;
			
		}
	
		return falso;
	}
	
	public sBonoloto() throws RemoteException {
		Random ranNumbers = new Random();
		for(int i = 0; i < combinacionGanadora.length; i++) {
			combinacionGanadora[i] = ranNumbers.nextInt(49);
		}
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		sBonoloto ORemoto =  new sBonoloto();
		
		//ORemoto.printNumbers();
		Naming.bind("Servidor", ORemoto);
		  
 		System.out.println("Servidor Remoto Preparado");
	}

}


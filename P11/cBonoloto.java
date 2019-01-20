//package bonoloto;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class cBonoloto {
	
	private static int[] combinacionPrueba = new int[6];
	
	public static void rellenarApuesta() {
		System.out.println("Introduce seis números");
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < combinacionPrueba.length; i++) 
			combinacionPrueba[i] = sc.nextInt();
		
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		iBonoloto RefObRemoto = 
				  (iBonoloto)Naming.lookup("//localhost/Servidor");
		
		rellenarApuesta();
		if(RefObRemoto.compApuesta(combinacionPrueba))
			System.out.println("¡Menudo acierto, has ganado!");
		else
			System.out.println("¡Más suerte la próxima! :(");
		
	}
}

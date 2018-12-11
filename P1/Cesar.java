package codificacionCesar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Cesar {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Introduce la cadena:");
		BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
		String cadena = dato.readLine();
		System.out.println("Introduce n: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
				

		System.out.println(codificar(cadena, n));
	}
	
	public static String codificar(String cadena, int n)
	{
		int lonCadena = cadena.length();
		char[] aux = new char[lonCadena];
		
		for(int i = 0; i < lonCadena; i++)
		{
			aux[i] = codificadorCaracter(i, cadena.charAt(i), n);
		}
		String cadCodificada = new String(aux);
		return cadCodificada;
	}
	
	private static char codificadorCaracter(int i, char caracterInicial, int n)
	{
		if(caracterInicial != 32)
			if(caracterInicial > 90){
				caracterInicial = (char) ((((caracterInicial - 97) + n) % 26) + 97);
			}
			else{
				caracterInicial = (char) ((((caracterInicial - 65) + n) % 26) + 65);
			}
		return caracterInicial;
	}
}

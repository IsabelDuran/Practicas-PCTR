package codificacionCesar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class desCesar {
	
public static void main(String[] args) throws Exception {
		
		System.out.println("Introduce la cadena a descodificar:");
		BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
		String cadena = dato.readLine();
		System.out.println("Introduce n: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
				

		System.out.println(descodificar(cadena, n));
	}
	
	public static String descodificar(String cadena, int n)
	{
		int lonCadena = cadena.length();
		char[] aux = new char[lonCadena];
		
		for(int i = 0; i < lonCadena; i++)
		{
			aux[i] = descodificadorCaracter(i, cadena.charAt(i), n);
		}
		String cadCodificada = new String(aux);
		return cadCodificada;
	}
	
	private static char descodificadorCaracter(int i, char caracterInicial, int n)
	{
		if(caracterInicial != 32)
			if(caracterInicial > 90)
				{
					if(caracterInicial == 97)
						caracterInicial = 122;
					else
						caracterInicial = (char) ((((caracterInicial - 97) - n) % 26) + 97);
				}
			else
				{
					if(caracterInicial == 65)
						caracterInicial = 90;
					else
						caracterInicial = (char) ((((caracterInicial - 65) - n) % 26) + 65);
				}
			
		return caracterInicial;
	}
}

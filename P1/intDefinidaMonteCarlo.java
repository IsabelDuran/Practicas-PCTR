package intDefinidaMonteCarlo;
import java.util.Scanner;
	
public class intDefinidaMonteCarlo{
	
	public static void main(String[] args) {
		System.out.println("Introduce un número de puntos a lanzar:");
		int n, numPuntos = 0;
		double numeroX, numeroY;
		Scanner sc = new Scanner(System.in);	
		n = sc.nextInt();
		
		System.out.println("La aproximación para f(x) = sen(x)");
		for(int i = 0; i < n; i++)
		{
			numeroX = Math.random();
			numeroY = Math.random();
			
			if(numeroY < Math.sin(numeroX))
				numPuntos++;
		}
		System.out.println(numPuntos /(double) n);
		
		numPuntos = 0;
		System.out.println("Para f(x) = x");
		for(int i = 0; i < n; i++)
		{
			numeroX = Math.random();
			numeroY = Math.random();
			if(numeroY < numeroX)
				numPuntos++;
		}
		System.out.println(numPuntos /(double) n);
		
		
	}

}

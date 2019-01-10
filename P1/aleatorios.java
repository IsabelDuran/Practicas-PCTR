package aleatorios;

import java.util.Scanner;

public class aleatorios {
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n;

		System.out.println("Cuantos números desea generar?");
		n = sc.nextInt();

		for(int i = 0; i < n; i++)
		{
			System.out.print(Math.random() + " ");
		}

	}	

}

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class cRenal {
	public static void main(String[] args) throws NotBoundException, IOException {
		Scanner sc = new Scanner(System.in);
		fRenal oRemoto = (fRenal) Naming.lookup("//localhost:1066/servidor");
		int op = 0;
		do {
			imprirMenu();
			op = sc.nextInt();

			switch (op) {
			case 1:
				System.out.println("Introduzca su edad");
				int edad = sc.nextInt();
				System.out.println("Introduzca su peso");
				double Peso = sc.nextDouble();
				System.out.println("Introduzca su creatinina");
				double creatinina = sc.nextDouble();
				System.out.println("¿Es un hombre, o una mujer? 0 -> hombre /1 -> mujer");
				int sexo = sc.nextInt();
				Datos dato = new Datos(edad, Peso, creatinina, sexo);
				
				float valorAclaramiento = oRemoto.aclarCreat(dato);
				System.out.println("Su valor de aclaramiento es " + valorAclaramiento);
				if(valorAclaramiento >= 90)
					System.out.println("Función normal");
				else if(valorAclaramiento >= 60 && valorAclaramiento < 90)
					System.out.println("Daño renal leve");
				else if(valorAclaramiento >= 30 && valorAclaramiento < 60)
					System.out.println("Daño renal moderado");
				else if(valorAclaramiento >= 15 && valorAclaramiento < 30)
					System.out.println("Daño renal grave");
				else if(valorAclaramiento < 15)
					System.out.println("FALLO RENAL");
				break;

			case 2:
				System.out.println("Introduzca su peso");
				float peso = (float) sc.nextDouble();
				System.out.println("Introduzca su estatura (m)");
				float altura = (float) sc.nextDouble();
				float imc = oRemoto.indMasaCorporal(peso, altura);
				System.out.println("Su IMC es de " + imc);

				if (imc < 16)
					System.out.println("Usted tiene una delgadez severa");
				else if (imc >= 16 && imc < 17)
					System.out.println("Usted tiene una delgadez moderada");
				else if (imc >= 17 && imc < 18.50)
					System.out.println("Usted tiene una delgadez Aceptable");
				else if (imc >= 18.50 && imc < 25)
					System.out.println("Usted tiene un peso normal");
				else if (imc >= 25 && imc < 30)
					System.out.println("Usted tiene sobrepeso");
				else if (imc >= 30 && imc < 35)
					System.out.println("Usted tiene Obesidad tipo I");
				else if (imc >= 35 && imc < 40)
					System.out.println("Usted tiene Obesidad tipo II");
				else if (imc >= 40)
					System.out.println("Usted tiene Obesidad tipo III");
			case 3:
				System.out.println("La media de creatinina almacenada es " + oRemoto.creatininaMedia());

				break;

			}
		} while (op != 4);
	}

	private static void imprirMenu() {
		System.out.println("¿Qué desea hacer?");
		System.out.println("1.Calcular aclaramiento de creatinina");
		System.out.println("2.Calcular IMC");
		System.out.println("3.Mostrar media de la creatinina");
		System.out.println("4.Salir");
	}
}

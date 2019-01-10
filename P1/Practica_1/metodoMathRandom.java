public class metodoMathRandom {

	public static void main(String[] args) {

		int num1=50;
		int num2=120;

		System.out.println("Numeros generados entre 0 y 20, con decimales (sin incluir el 0 y el 20)");
		for (int i=0;i<10;i++){
			double numAleatorio=Math.random()*20;
			System.out.println(numAleatorio);
		}

		System.out.println("Numeros generados entre 5 y 20, con decimales (sin incluir el 5 y el 20)");
		for (int i=0;i<10;i++){
			double numAleatorio=Math.random()*(20-5)+5;
			System.out.println(numAleatorio);
		}

		System.out.println("Numeros generados entre 50 y 120, sin decimales (sin incluir el 50 y el 120)");
		for (int i=0;i<10;i++){
			int numAleatorio=(int)Math.floor(Math.random()*(num1-num2)+num2);
			System.out.println(numAleatorio);
		}

                System.out.println("Numeros generados entre 50 y 120, sin decimales (incluyendo el 50 y el 120)");
		for (int i=0;i<10;i++){
			int numAleatorio=(int)Math.floor(Math.random()*(num1-(num2+1))+(num2));
			System.out.println(numAleatorio);
		}
	}

}
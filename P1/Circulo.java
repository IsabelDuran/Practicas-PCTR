public class Circulo {
	final double π = 3.14;
	double diametro = 14.2, altura = 20;
	
	double volumen()
	{
		double volumen = ((1.0/3.0) * π * (diametro / 2) * (diametro / 2) * altura);
		return volumen;
	}

	public static void main(String[] args)
	{
		Circulo cono = new Circulo();

		System.out.println("El volumen es: " + cono.volumen());	
	}

}

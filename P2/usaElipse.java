/**
 * @author isa
 *
 */
public class usaElipse
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Elipse e = new Elipse(2.3, 7.5, 6, 3);
		System.out.println("FOCO 1: " + e.getF1());
		System.out.println("FOCO 2: " + e.getF2());
		System.out.println("Semieje Mayor:" + e.getSemiejeMayor());
		System.out.println("Semieje Menor:" + e.getSemiejeMenor());
		System.out.println("Centro: " + e.getCentro());

		e.setF1(3.4);
		System.out.println("FOCO 1: " + e.getF1());
		System.out.println("FOCO 2: " + e.getF2());
		System.out.println("Centro: " + e.getCentro());
		e.setF2(4.4);
		System.out.println("FOCO 1: " + e.getF1());
		System.out.println("FOCO 2: " + e.getF2());
		System.out.println("Centro: " + e.getCentro());

		e.setSemiejeMenor(8);
		e.setSemiejeMayor(5);

		System.out.println("FOCO 1: " + e.getF1());
		System.out.println("FOCO 2: " + e.getF2());
		System.out.println("Semieje Mayor:" + e.getSemiejeMayor());
		System.out.println("Semieje Menor:" + e.getSemiejeMenor());
		System.out.println("Centro: " + e.getCentro());

		if(e.perteneceElipse(3, 6))
			System.out.println("pertenece");
		else
			System.out.println("No pertenece");

	}	
}
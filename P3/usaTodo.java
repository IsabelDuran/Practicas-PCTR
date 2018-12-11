/**
 * @author isa
 *
 */
public class usaTodo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Triangulo t = new Triangulo(new Punto(3.4, 5.6), new Punto(4.4, 7.6), new Punto(9, 6));
		System.out.println(t.perimetro());
		System.out.println(t.area());

		Pentagono p = new Pentagono(new Punto(3.4, 5.6), new Punto(4.4, 7.6), new Punto(9, 6), new Punto(8, 9), new Punto(10,13));
		System.out.println(p.perimetro());
		System.out.println(p.area());
		
		Cuadrado c = new Cuadrado(new Punto(3.4, 5.6), new Punto(4.4, 7.6), new Punto(9, 6), new Punto(8, 9));
		System.out.println(c.perimetro());
		System.out.println(c.area());
		
		Hexagono h = new Hexagono(new Punto(3.4, 5.6), new Punto(4.4, 7.6), new Punto(9, 6), new Punto(8,9), new Punto(8,10), new Punto(8,18) );
		System.out.println(h.perimetro());
		System.out.println(h.area());
		
	}

}

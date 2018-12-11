/**
 * @author isa
 *
 */
public class Cuadrado extends Poligono {

	/**
	 * @param vertice1
	 * @param vertice2
	 * @param vertice3
	 * @param vertice4
	 */
	public Cuadrado(Punto vertice1, Punto vertice2, Punto vertice3, Punto vertice4) {
		super();
		puntos.add(vertice1);
		puntos.add(vertice2);
		puntos.add(vertice3);
		puntos.add(vertice4);
	}
	
	public float area() {
		float lado = distancia(puntos.get(0), puntos.get(1));
		return lado * lado;
	}

}

/**
 * @author isa
 *
 */
public class Pentagono extends Poligono {
	/**
	 * @param vertice1
	 * @param vertice2
	 * @param vertice3
	 * @param vertice4
	 * @param vertice5
	 */
	public Pentagono(Punto vertice1, Punto vertice2, Punto vertice3, Punto vertice4, Punto vertice5) {
		super();
		puntos.add(vertice1);
		puntos.add(vertice2);
		puntos.add(vertice3);
		puntos.add(vertice4);
		puntos.add(vertice5);
	}

	/**
	 * @return
	 */
	public float area() {
		float perimetro, apotema;
		double x, y;

		x = (puntos.get(0).getX() + puntos.get(3).getX()) / 2;
		y = (puntos.get(0).getY() + puntos.get(3).getY()) / 2;

		Punto centroPentagono = new Punto(x, y);

		x = (puntos.get(0).getX() + puntos.get(1).getX()) / 2;
		y = (puntos.get(0).getY() + puntos.get(1).getY()) / 2;

		Punto centroBase = new Punto(x, y);

		apotema = this.distancia(centroPentagono, centroBase);
		perimetro = this.perimetro();

		return (perimetro * apotema) / 2;
	}

}

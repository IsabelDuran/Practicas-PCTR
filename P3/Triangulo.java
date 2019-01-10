/**
 * @author isa
 *
 */
public class Triangulo extends Poligono {

	/**
	 * @param vertice1
	 * @param vertice2
	 * @param vertice3
	 */
	public Triangulo(Punto vertice1, Punto vertice2, Punto vertice3) {
		super();
		puntos.add(vertice1);
		puntos.add(vertice2);
		puntos.add(vertice3);
	}
	
	/**
	 * @return
	 */
	public float area() {
		float base, altura;
		Punto puntoMedio = new Punto((this.getPuntos().get(0).getX() + 
									  this.getPuntos().get(0).getX() / 2),
									 (this.getPuntos().get(1).getY() + 
									  this.getPuntos().get(1).getY() / 2));
		base = distancia(this.getPuntos().get(0), this.getPuntos().get(1));
		altura = distancia(puntoMedio, this.getPuntos().get(2));
		
		return ((base * altura) / 2);
	}

}

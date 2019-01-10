import java.util.ArrayList;
import java.util.List;

/**
 * @author isa
 *
 */
public class Poligono {
	
	protected List<Punto> puntos;
	
	/**
	 * @return
	 */
	public List<Punto> getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos
	 */
	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}

	/**
	 * @param puntos
	 */
	public Poligono(List<Punto> puntos) {
		this.puntos = new ArrayList<>(puntos);
	}
	
	/**
	 * 
	 */
	public Poligono() {
		this.puntos = new ArrayList<>();
	}

	/**
	 * @return
	 */
	public int nLados() {
		return puntos.size();
	}
	
	/**
	 * @return
	 */
	public float perimetro() {
		float total = 0;
		for(int i = 0; i < puntos.size() - 1; i++) {
			total += puntos.get(i).distancia(puntos.get(i + 1));
		}
		return total;
	}
	
	/**
	 * @param z
	 */
	public void escalado(float z) {
		for(Punto punto : puntos) {
			punto.setX(punto.getX() * z);
			punto.setY(punto.getY() * z);
		}
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @return
	 */
	protected float distancia(Punto p1, Punto p2)
	{
		return (float) Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

}

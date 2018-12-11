/**
 * @author isa
 *
 */
public class Punto {
	private double x, y; // Variables de instancia

	/**
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Punto(double x, double y) { // El constructor
		this.x = x;
		this.y = y;
	}

	/**
	 * @param dx
	 * @param dy
	 */
	public void moverEn(double dx, double dy) { // Un método
		this.x += dx;
		this.y += dy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() { // Otro método
		return "(" + this.x + "," + this.y + ")";
	}
	
	/**
	 * @param p
	 * @return
	 */
	public double distancia(Punto p) {
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
	}
	
	/**
	 * @return
	 */
	public double distanciaOrigen() {
		Punto origen = new Punto(0, 0);
		return distancia(origen);
	}
	
	/**
	 * @return
	 */
	public int cuadrante() {
		if(x > 0 && y > 0)
			return 2;
		else
			if(x < 0 && y > 0)
				return 1;
			else
				if(x < 0 && y < 0)
					return 3;
				else
					if(x > 0 && y < 0)
						return 4;
		return 0;
	}
}

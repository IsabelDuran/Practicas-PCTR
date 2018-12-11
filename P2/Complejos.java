package complejos;

/**
 * @author isa
 *
 */
public class Complejos {
	private double[] complejo = new double[2];

	/**
	 * @param r
	 * @param i
	 */
	public Complejos(double r, double i) {
		complejo[0] = r;
		complejo[1] = i;
	}

	/**
	 * @return
	 */
	public double getReal() {
		return this.complejo[0];
	}

	/**
	 * @return
	 */
	public double getImag() {
		return this.complejo[1];
	}

	/**
	 * @param r
	 */
	public void setReal(double r) {
		complejo[0] = r;
	}

	/**
	 * @param i
	 */
	public void setImag(double i) {
		complejo[1] = i;
	}

	/**
	 * @param c
	 * @return
	 */
	public Complejos suma(Complejos c) {
		this.complejo[0] += c.complejo[0];
		this.complejo[1] += c.complejo[1];
		return this;
	}

	/**
	 * @param c
	 * @return
	 */
	public Complejos resta(Complejos c) {
		this.complejo[0] -= c.complejo[0];
		this.complejo[1] -= c.complejo[1];
		return this;
	}

	/**
	 * @return
	 */
	public double modulo() {
		return Math.sqrt(Math.pow(this.complejo[0], 2) + Math.pow(this.complejo[0], 2));
	}

	/**
	 * @param c
	 * @return
	 */
	public Complejos producto(Complejos c) {
		this.complejo[0] = ((this.complejo[0] * c.complejo[0]) - (this.complejo[1] * c.complejo[1]));
		this.complejo[1] = ((this.complejo[0] * c.complejo[1]) + (this.complejo[1] * c.complejo[0]));
		return this;
	}

	/**
	 * @param c
	 * @return
	 */
	public Complejos cociente(Complejos c)
	{
		this.complejo[0] = ((this.complejo[0] * c.complejo[0]) - (this.complejo[1] * c.complejo[1])) / (Math.pow(c.complejo[1], 2) + Math.pow(c.complejo[1], 2));
		this.complejo[1] = ((this.complejo[0] * c.complejo[1]) + (this.complejo[1] * c.complejo[0])) / (Math.pow(c.complejo[1], 2) + Math.pow(c.complejo[1], 2));
		return this;
	}

}
/**
 * @author isa
 *
 */
public class Elipse {
	private double f1, f2;
	private double centro, semiejeMayor, semiejeMenor;

	/**
	 * @param f1
	 * @param f2
	 * @param semiejeMayor
	 * @param semiejeMenor
	 */
	public Elipse(double f1, double f2, double semiejeMayor, double semiejeMenor) {
		this.f1 = f1;
		this.f2 = f2;
		this.centro = ((f1 + f2) / 2);
		this.semiejeMayor = semiejeMayor;
		this.semiejeMenor = semiejeMenor;
	}

	/**
	 * @return
	 */
	public double getF1() {
		return this.f1;
	}

	/**
	 * @return
	 */
	public double getF2() {
		return this.f2;
	}

	/**
	 * @return
	 */
	public double getSemiejeMayor() {
		return this.semiejeMayor;
	}

	/**
	 * @return
	 */
	public double getSemiejeMenor() {
		return this.semiejeMenor;
	}

	/**
	 * @return
	 */
	public double getCentro() {
		return this.centro;
	}

	/**
	 * @param f1
	 */
	public void setF1(double f1) {
		this.f1 = f1;
		this.centro = ((f1 + f2) / 2);
	}

	/**
	 * @param f2
	 */
	public void setF2(double f2) {
		this.f2 = f2;
		this.centro = ((f1 + f2) / 2);
	}

	/**
	 * @param s
	 */
	public void setSemiejeMayor(double s) {
		this.semiejeMayor = s;
	}

	/**
	 * @param s
	 */
	public void setSemiejeMenor(double s) {
		this.semiejeMenor = s;
	}

	/**
	 * @param puntoX
	 * @param puntoY
	 * @return
	 */
	public boolean perteneceElipse(double puntoX, double puntoY) {
		double punto = (Math.pow(puntoX, 2) / Math.pow(semiejeMayor, 2))
				+ (Math.pow(puntoY, 2) / Math.pow(semiejeMenor, 2));
		if (punto == 1)
			return true;
		else
			return false;
	}
}
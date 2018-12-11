/**
 * @author isa
 *
 */
public class Hilo extends Thread {
	private static int n = 0;
	private int turno;
	
	/**
	 * @param turno
	 */
	public Hilo(int turno) {
		this.turno = turno;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if(turno == 1) {
			for (int i = 0; i < 1000; i++)
				n++;
		}
		else
			if(turno == 0) {
				for (int i = 0; i < 1000; i++)
					n--;
			}
	}
	
	/**
	 * @return
	 */
	public static int getN() {
		return n;
	}


}

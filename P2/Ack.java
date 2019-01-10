import java.util.Scanner;

/**
 * @author isa
 *
 */
public class Ack {	
	/**
	 * @param m
	 * @param n
	 * @return
	 */
	public static int ack(int m, int n) {
		if(m == 0)
			return n + 1;
		else
			if(m > 0 && n == 0)
				return ack(m - 1, 1);
			else
				if(m > 0 && n >  0)
					return ack(m - 1, ack(m, n - 1));
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m, n;
		System.out.println("Introduce una m:");
		m = sc.nextInt();
		System.out.println("Introduce una n:");
		n = sc.nextInt();
		
		System.out.println(ack(m, n));
		
	}

}

import java.util.Scanner;

public class NewtonRaphson {
	
	public static int n;
	public static double x0;
	public static void main(String[] args) {
		Scanner scx0 = new Scanner(System.in);
		Scanner scn = new Scanner(System.in);
		System.out.println("Introduce el número de iteraciones:");
		n = scn.nextInt();
		System.out.println("Introduce una aproximación inicial:");
		x0 = scx0.nextDouble();
		
		System.out.println("La aproximación es (para cos(x) + x³): " + aproxNewtonRaphsonFun1(x0, n));
		System.out.println("La aproximación es (para x² + 5): " + aproxNewtonRaphsonFun2(x0, n));
	}
	
	public static double aproxNewtonRaphsonFun1(double x0, int n){
		double xn = x0, xn1;
		for(int i = 0; i < n; i++)
		{
			xn1 = xn - fun1(xn)/ derFun1(xn);
			xn = xn1;
		}
		return xn;
	}
	
	public static double aproxNewtonRaphsonFun2(double x0, int n){
		double xn = x0, xn1;
		for(int i = 0; i < n; i++)
		{
			xn1 = xn - fun2(xn)/ derFun2(xn);
			xn = xn1;
		}
		return xn;
	}
	
	private static double fun2(double xn) {
		return Math.pow(xn, 2) + 5;

	}
	
	private static double derFun2(double xn) {
		return 2 * xn;

	}
	
	
	private static double fun1(double xn) {
		return Math.cos(xn) - Math.pow(xn, 3);
	}
	
	private static double derFun1(double xn) {
		return Math.sin(xn) * -1 - 3 * Math.pow(xn, 2);
	}

}

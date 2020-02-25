import java.util.Scanner;

public class NumericalIntegration {
	
	public static void main(String[] args) {
		double a, b, n, A, B, C, D, E, F;
		double MP, T, S, FTC;
		double mpErr, tErr, sErr;
		Scanner in = new Scanner(System.in);
		
		// Lower limit of integration
		System.out.println("Please enter the lower limit of integration: ");// Lower limit of integration
		a = in.nextDouble();
		
		// Upper limit of integration
		System.out.println("Please enter the upper limit of integration: ");
		b = in.nextDouble();
		
		// Number of rectangles
		System.out.println("Please enter the whole number of subintervals (n): ");
		n = in.nextInt();
		
		// 5th degree polynomial
		System.out.println("Please enter the coefficients for a 5th degree polynomial (if none, enter 0):");
		A = in.nextDouble();
		B = in.nextDouble();
		C = in.nextDouble();
		D = in.nextDouble();
		E = in.nextDouble();
		F = in.nextDouble();
		
		in.close();
		
		MP = Midpoint(a, b, n, A, B, C, D, E, F);       // returned value from Midpoint method
		T = Trapezoidal(a, b, n, A, B, C, D, E, F);     // returned value from Trapezoidal method
		S = Simpsons(a, b, n, A, B, C, D, E, F);        // returned value from Simpson's method
		FTC = Fundamental(a, b, A, B, C, D, E, F);      // returned value from Fundamental method
		
		// Error
		mpErr = ((FTC - MP)/(FTC + (MP/2.0))) * 100.0;  // midpoint error
		tErr = ((FTC - T)/(FTC + (T/2.0))) * 100.0;     // trapezoidal error
		sErr = ((FTC - S)/(FTC + (S/2.0))) * 100.0;     // simpson's error
		
		System.out.printf("%nMidpoint Rule: %.8f%n", MP);
		System.out.printf("Percent Error: %.8f%n%n", mpErr);
		
		System.out.printf("Trapezoidal Rule: %.8f%n", T);
		System.out.printf("Percent Error: %.8f%n%n", tErr);
		
		System.out.printf("Simpson's Rule: %.8f%n", S);
		System.out.printf("Percent Error: %.8f%n%n", sErr);
		
		System.out.printf("Fundamental Theorem of Calculus: %.8f%n", FTC);
	}
	
	// Arbitrary 5th degree function
	public static double f(double a, double b, double c, double d, double e, double f, double x) {
		return ((a * Math.pow(x, 5)) + (b * Math.pow(x, 4)) + (c * Math.pow(x, 3)) + (d * Math.pow(x, 2)) + (e * Math.pow(x, 1)) + f);
	}
	
	
	// Midpoint Rule
	public static double Midpoint(double a, double b, double n, double A, double B, double C, double D, double E, double F) {
		double deltaX = (b - a)/n;
		double sum = 0;
		
		for (double i = 1; i <= n; i++) {
			double x = (a + deltaX * i) - (deltaX * 0.5);
			sum += f(A, B, C, D, E, F, x);
		}
		
		double s = sum * deltaX;
		return s;
	}
	
	
	// Trapezoidal Rule
	public static double Trapezoidal(double a, double b, double n, double A, double B, double C, double D, double E, double F) {
		double deltaX = (b - a)/n;
		double sum = 0.5 * (f(A, B, C, D, E, F, a) + f(A, B, C, D, E, F, b));
		
		for (int i = 1; i < n; i++) {
			double x = a + i * deltaX;
			sum += f(A, B, C, D, E, F, x);
		}
		
		return sum * deltaX;
	}
	
	
	// Simpson's Rule
	public static double Simpsons(double a, double b, double n, double A, double B, double C, double D, double E, double F) {
		double deltaX = (b - a)/n;
		double sum, sum4 = 0, sum2 = 0, x;
		
		for (int i = 1; i < n; i += 2) {
			x = a + i * deltaX;
			sum4 += f(A, B, C, D, E, F, x);
		}
		
		for (int i = 2; i < n - 1; i += 2) {
			x = a + i * deltaX;
			sum2 += f(A, B, C, D, E, F, x);
		}
		
		sum = f(A, B, C, D, E, F, a) + f(A, B, C, D, E, F, b);
		sum = (deltaX/3) * (sum + (sum4 * 4) + (sum2 * 2));
		
		return sum;
	}
	
	// Fundamental Theorem of Calculus
	public static double Fundamental(double a, double b, double A, double B, double C, double D, double E, double F) {
		double start = ((A * Math.pow(a, 6)/6.0) + (B * Math.pow(a, 5)/5.0) + (C * Math.pow(a, 4)/4.0) + (D * Math.pow(a, 3)/3.0) + (E * Math.pow(a, 2)/2.0) + (F * a));
		double end = ((A * Math.pow(b, 6)/6.0) + (B * Math.pow(b, 5)/5.0) + (C * Math.pow(b, 4)/4.0) + (D * Math.pow(b, 3)/3.0) + (E * Math.pow(b, 2)/2.0) + (F * b));
		
		return end - start;
	}
}
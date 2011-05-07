/**
 * Greplin challenge #2: Given a number, find the smallest fibonacci number smaller than it, and return the sum of 
 * 						 the prime divisors.
 * 
 * @author michaelvo
 *
 */


public class Fibonacci {

	/**
	 * @param min 
	 */
	public Fibonacci(int min) {
		int a = 1;
		int b = 1;
		while ( b < min ) {
			int temp = b;
			b = b + a;
			a = temp;
		}
		while ( !isPrime(b) ) {
			int temp = b;
			b = b + a;
			a = temp;
		}
		int c = b + 1;
		int answer = sumPrimeDivisors(c);
		System.out.println("answer is " + answer );
	}
	
	/**
	 * @param a
	 * @return if a=x1^i1 * x2^i2 * ... * xn^in where x1, x2, ..., xn are prime divisors then return x1+...+xn 
	 */
	private int sumPrimeDivisors( int a ) {
		int sum = 0;
		if( a % 2 == 0 ) {
			sum += 2;
		}
		for( int i = 2; i < (int)Math.sqrt(a); i++ ) {
			
			if ( (i % 2) != 0 && (a % i) == 0 ) {
				if( isPrime(i)) {
					sum += i;
				}
			}
		}
		return sum;
	}
	
	/**
	 * @param a
	 * @return true iff a is prime
	 */
	private boolean isPrime( int a ) {
		for( int i = 2; i <= (int)Math.sqrt(a); i++ ) {
			if ( a % i == 0 ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 *  227000 is the integer supplied by greplin. Change this parameter.
	 */
	public static void main(String[] args) {
		Fibonacci fib = new Fibonacci(227000); 
	}
}

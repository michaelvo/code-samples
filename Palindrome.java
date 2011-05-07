/**
 *  Greplin challenge #1: Given a string, find the longest palindrome embedded within
 * 
 *  Solution: all palindromes have the property that the middle either looks like xx or xyx. We search for all occurrences
 *  of these then check the adjoining letters for the length. 
 *  
 *  usage: the string is located in a text file called "file.txt". 
 *  
 *  runtime: the worst case string for this algorithm is "xxx...xxx" which yields a runtime of O(n^2) as each position i 
 *           in the string takes i-loops
 *  
 *  @author Michael Vo
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class Palindrome {

	/**
	 *  Palindrome: store the locations of xx and xyx in the string and then search for the longest palindrome
	 */
	public Palindrome() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("file.txt"));
			String file = in.readLine();
			file = file.toLowerCase();
			ArrayList<Integer> doubleLetters = new ArrayList<Integer>(); // xx
			ArrayList<Integer> oddLetters = new ArrayList<Integer>();    // xyx
			for( int i = 0; i < file.length(); i ++ ) {
				if ( (i+1) < file.length() && file.charAt(i) == file.charAt(i + 1) ) {
					doubleLetters.add( new Integer( i) );
					
				}
				if ( (i+2) < file.length() && file.charAt(i) == file.charAt(i+2) ) {
					oddLetters.add( new Integer( i + 1));
				}
			}
			
			// xx-palindromes
			int maxLength = 0;
			int index = 0; 
			for( int i = 0; i < doubleLetters.size(); i++ ) {
				int startingLoc = doubleLetters.get(i);
				int a,b;
				a = startingLoc;
				b = startingLoc + 1;
				while( a >= 0 && b < file.length() && file.charAt(a) == file.charAt(b)) {
					a--;
					b++;
				}
				int temp = maxLength;
				maxLength = Math.max(maxLength, b - a + 1);
				if ( maxLength > temp ) {
					index = a;
				}
			}
			
			// xyx-palindromes
			int maxLength2 = 0;
			int index2 = 0;
			for( int i = 0; i < oddLetters.size(); i++ ) {
				int startingLoc = oddLetters.get(i);
				int a,b;
				a = startingLoc - 1;
				b = startingLoc + 1;
				while( a >= 0 && b < file.length() && file.charAt(a) == file.charAt(b)) {
					a--;
					b++;
				}
				int temp = maxLength2;
				maxLength2 = Math.max(maxLength2, b - a -1);
				if ( maxLength2 > temp) {
					index2 = a+1;
				}
			}
			
			if ( maxLength > maxLength2 ) {
				System.out.println( file.substring( index, index + maxLength));	
			}		
			else {
				System.out.println( file.substring( index2, index2 + maxLength2));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Palindrome pal = new Palindrome();
	}
}

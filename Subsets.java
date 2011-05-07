/**
 * Greplin challenge #3
 * Given a list of integers array List, find the number of subsets of cardinality >= 3 where the largest 
 * element is the sum of the others.
 * For example given {1, 2, 3} return 1 as 1 + 2 = 3 is the only subset 
 * 
 * Solution: use dynamic programming method to solve this problem
 * Running time: if m is the largest integer and we have n integers then the running time is O(mn)
 * 
 * @author michaelvo
 *
 */
public class Subsets {

	private final int SECOND_ELEMENT = 2;
	
	/**
	 * Calculate the number of subsets 
	 * @param aList The list of integers
	 */
	public Subsets(int[] aList) {
		int[] list = aList;
		
		/**
		 *  ways[i] holds the number of ways to make sum i using the first x_i - 1 elements
		 *  tempWays[i] holds the number of ways to make sum i using the first x_i elements
		 */
		int[] ways = new int[list[list.length-1]+1]; 
		int[] tempWays = new int[list[list.length-1]+1]; 
		for( int i = 0; i < ways.length; i++ ) {
			ways[i] = 0;
			tempWays[i] = 0;
			ways[list[0]] = 1;
			tempWays[list[0]] = 1;
		}
		
		/** Q stores true iff it is possible to make sum column_i with the first row_i elements
		 *  i.e Q[3][9] true means that we can make the sum 9 using the first 3 elements. 
		 */
		boolean[][] Q = new boolean[list.length+1][list[list.length-1]+1]; 
		
		// initialize base case; first two rows of Q
		for( int i = 0; i < list[list.length-1]; i++ ) {
			Q[0][i] = false;
			Q[1][i] = false;
		}
		Q[0][0] = true;
		Q[1][list[0]] = true;
		Q[1][0] = true;
		
		int counter = 0;
		for( int i = SECOND_ELEMENT; i < Q.length; i++ ) {
			for ( int j = 0; j < Q[i].length; j++ ) {
				/** Update Q: either we can make the sum j with the newest element, or we already could make the sum j
			     *    with the previous elements, or we can't make the sum j.
			     */
				if ( (j - list[i-1]) >=0 && Q[i-1][j - list[i-1]] ) {
					// Using the previous elements gives us the sum j-x_i. We can add x_i to get the sum j
					Q[i][j] = true;			
					if( list[i-1] != j) {
						//update the number of ways to make the sum j.			
						tempWays[j]+= ways[j - list[i-1]];
					}						
					if( contains(j, list) && (j-list[i-1])!= 0) {
						int inc = ways[j - list[i-1]];
						counter+= inc;
					}		
				}
				else if( Q[i-1][j] ) {
					// Assert: we can make the sum j using previous elements x_1 to x_i-1
					Q[i][j] = true;
				}
				else {
					Q[i][j] = false;
				}
			}	
			tempWays[list[i-1]]++; // make the sum x_i using the singleton element x_i
			copy(ways, tempWays);
		}
		System.out.println("number of ways is " + counter);
	}
	
	/**
	 * Make a deep copy of b into a
	 * @param a The array holding the new copy
	 * @param b The array to copy
	 */
	private void copy(int[] a, int[] b) {
		if( a.length != b.length) {
			System.out.println("ERROR. array b must be of the same size as array a: " + a.length + " " + b.length);
		}
		for( int i = 0; i < a.length; i++ ) {
			a[i] = b[i];
		}
	}
	
	/**
	 * @return true iff array Q contains integer j
	 */
	private boolean contains( int j, int[] Q) {
		for( int i = 0; i < Q.length; i++) {
			if( Q[i] == j) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Subsets subsets = new Subsets( new int[]{3, 4, 9, 14, 15, 19, 28, 37, 47, 50, 54, 56, 59, 61, 70, 73, 78, 81, 92, 95, 97, 99} );
	}
}

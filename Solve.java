/**
 * Problem: Given a 5x5 grid, how many ways are there to move from one corner of the grid to the opposite corner
 * of the grid while visiting each square exactly once. 
 * 
 * Solution: recursively count the paths
 * @author michael vo
 *
 */
public class Solve {

	private int numWays = 0;
	private static final int FIRST_ROW = 0;
	private static final int FIRST_COLUMN = 0;

	private static final int ROW_LENGTH = 5;
	private static final int COLUMN_LENGTH = 5; 
	private static final int LAST_ROW = ROW_LENGTH - 1;
	private static final int LAST_COLUMN = COLUMN_LENGTH - 1;
	private int[][] matrix = new int[ROW_LENGTH][COLUMN_LENGTH];
	
	public Solve() {
		recurseSolve( FIRST_ROW, FIRST_COLUMN, matrix );
	}
	
	/**
	 * 
	 * @param row the current row location
	 * @param column the current column location
	 * @param aMatrix an indicator matrix of the squares visited (=1) and not visited (=0)
	 */
	private void recurseSolve( int row, int column, int[][] aMatrix) {
		aMatrix[row][column] = 1; 
		
		// base case
		if ( hasCompletedPath( aMatrix) && row == LAST_ROW && column == LAST_COLUMN ) {
			numWays++;
		}
		
		else {
			// recurse right
			if ( (column + 1) < COLUMN_LENGTH && aMatrix[row][column + 1] == 0 ) {
				int[][] bMatrix;
				bMatrix = copyMatrix( aMatrix);
				recurseSolve( row, column + 1, bMatrix);
			}
			// recurse down
			if ( (row + 1) < ROW_LENGTH && aMatrix[row + 1][column] == 0 ) {
				int[][] bMatrix;
				bMatrix = copyMatrix( aMatrix);
				recurseSolve( row + 1, column, bMatrix);
			}
			// recurse left
			if ( (column - 1) >= 0 && aMatrix[row][column - 1] == 0 ) {
				int[][] bMatrix;
				bMatrix = copyMatrix( aMatrix);
				recurseSolve( row, column - 1, bMatrix);
			}
			// recurse up
			if ( (row - 1) >= 0 && aMatrix[row - 1][column] == 0 ) {
				int[][] bMatrix;
				bMatrix = copyMatrix( aMatrix);
				recurseSolve( row - 1, column, bMatrix);
			}
		}
		
	}
	
	/**
	 * Make a deep copy of the indicator matrix
	 * @param aMatrix the matrix to copy
	 * @return a deep copy of aMatrix
	 */
	private int[][] copyMatrix( int[][] aMatrix) {
		int[][] bMatrix = new int[ROW_LENGTH][COLUMN_LENGTH];
		for( int i =0 ; i < ROW_LENGTH; i++ ) {
			for( int j = 0; j < COLUMN_LENGTH; j++ ) {
				bMatrix[i][j] = aMatrix[i][j];
			}
		}
		return bMatrix;
	}
	
	/**
	 * Checks that every entry in aMatrix is equal to 1, i.e. a complete tour of the grid
	 * @param aMatrix
	 * @return true iff aMatrix[i][j] == 1 for all i, j in {0, 1, ..., 4}
	 */
	private boolean hasCompletedPath( int[][] aMatrix ) {
		for( int i = 0; i < ROW_LENGTH; i++ ) {
			for( int j = 0; j < COLUMN_LENGTH; j++ ) {
				if( aMatrix[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Solve foo = new Solve();
		System.out.println( "The number of different paths is " + foo.numWays);
	}
	
	
}

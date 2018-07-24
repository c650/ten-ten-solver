package com.palmercodingclub.tentensolver;
import java.util.ArrayList;

// Javadoc added by Cassandra

public class Board {
	private boolean[][] board;
	private final boolean DEBUG;

	/**
	* Default constructor for Board objects.
	* Always creates a board with debug turned off.
	*/
	public Board() {
		this(false);
	}
	
	/**
	* Constructor that specifies debug flag.
	* @param dbg A boolean, dictates whether to print debugging messages or not.
	*/
	public Board(boolean dbg) {
		/* board will always be a square. */
		board = new boolean[10][10];
		DEBUG = dbg;
	}

	/**
	* Clears the board.
	*/
	public void clear() {
		for (int i = 0, j = 0; i < board.length; i++)
			for (j = 0; j < board[i].length; j++)
				board[i][j] = false;
	}

	/**
	* Scans the board for completed rows and clears them.
	* @return the number of rows cleared
	*/
	public int scanAndClear() {
		int cleared = 0;
		boolean clearableRows[], clearableCols[];
		clearableRows = new boolean[10];
		clearableCols = new boolean[10];

		/* Loop thru each row and validate.	*/
		for (int i = 0, j = 0 ; i < board.length ; i++) {
			clearableRows[i] = clearableCols[i]  = true;

			for (j = 0; j < board[i].length ; j++) {
				clearableRows[i] = clearableRows[i] && board[i][j];
				clearableCols[i] = clearableCols[i] && board[j][i];
			}
			if (clearableRows[i]) cleared++;
			if (clearableCols[i]) cleared++;
		}

		/* CLEAR all clearable rows */
		for (int i = 0, j = 0 ; i < clearableRows.length ; i++)
			if (clearableRows[i]) {
				for (j = 0 ; j < board[i].length ; j++)
					board[i][j] = false;
				debug("Clearing Row #" + (i+1));
			}

		/* CLEAR all clearable columns. */
		for (int i = 0, j = 0 ; i < clearableCols.length ; i++)
			if (clearableCols[i]) {
				for (j = 0 ; j < board.length ; j++)
					board[j][i] = false;

				debug("Clearing Col #" + (i+1));
			}

		return cleared;
		/* TODO: ensure that intersecting fields do not affect score
			in the wrong whey. */
	}
	
	/**
	* Finds all the locations where a piece can fit.
	* @param  p A `Piece` object 
	* @return   An `ArrayList` of `Coordinate` containing all the locations on *this* `Board` the `Piece` "p" can be placed.
	* @see      Piece
	* @see      Coordinate
	*/
	// replaced all references of a boolean array to the Piece object - cass
	public ArrayList<Coordinate> getAvailableSpots(Piece p) {

		ArrayList<Coordinate> results = new ArrayList<>();

		for ( int i = 0 ; i < board.length - p.getRaw().length + 1; i++  ) {
			for ( int j = 0 ; j < board[i].length - p.getRaw()[0].length + 1; j++) {
				/* so now we can work with each possible upper left corner...*/

				boolean works = true;
				
				// made some changes to improve run time. Checking value of a boolean is mildly faster than comparing a value of integers - Cass
				for (int k = 0 ; works && k <  p.getRaw().length; k++ ) {
					for (int l = 0 ; works && l <  p.getRaw()[k].length; l++) {
						/* check if there is a block at this cell in piece
							AND the corresp. block in board is empty, OR the cell
							in the piece is empty... */
						works = (p.getRaw()[k][l] && !board[i+k][j+l]) || !p.getRaw()[k][l];
					}
				}

				if (works)
					results.add(new Coordinate( i , j ) );

			}
		}
		return results;
	}
	
	/**
	* Places a `Piece` "p" on *this* `Board` at `Coordinate` "c"
	* @param p The `Piece` to be played
	* @param c The `Coordinate` to play at
	*/
	/* assumes that the piece is being inserted in a valid place.*/
	// replaced all references of a boolean array to the Piece object - Cass
	public void placePiece(Piece p, Coordinate c) {
		for (int i = 0; i < p.getRaw().length; i++) {
			for (int j = 0; j < p.getRaw()[0].length; j++) {
				board[c.row+i][c.col+j] = board[c.row+i][c.col+j] || p.getRaw()[i][j];
			}
		}
	}

	/**
	* Returns a `String` representation of *this* `Board`
	* Overriden from parent *class* `Object`
	* @return a `String` representation of *this* `Board`
	*/
	public String toString() {

		String ret = "";
		for (boolean[] arr : board) {
			ret += "| ";
			for (boolean cell : arr)
				ret += (cell ? "O" : "-") + " ";
			ret += "|\n";
		}
		return ret;
	}
	
	/**
	* Passes the 2D boolean array of the board by reference
	* @return the 2D boolean array of the board
	*/
	public boolean[][] getBoard() {
		return board;
	}

	/**
	* Prints a string if debug is turned on
	* @param s The string to be printed
	*/
	private void debug(String s) {
		if (DEBUG)
			System.out.println(s);
	}
	
	public Board copyBoard() {
		Board result=new Board();
		for(int i=0;i<board.length;i++) {
			System.arraycopy(board[i], 0, result.board[i], 0, board[i].length);
		}
		return result;
	}
}

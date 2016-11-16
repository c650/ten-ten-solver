import java.util.ArrayList;

public class Board {
	private boolean[][] board;

	public Board() {
		/* board will always be a square. */
		board = new boolean[10][10];
	}

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
			if (clearableRows[i])
				for (j = 0 ; j < board[i].length ; i++)
					board[i][j] = false;

		/* CLEAR all clearable columns. */
		for (int i = 0, j = 0 ; j < clearableCols.length ; i++)
			if (clearableCols[i])
				for (j = 0 ; j < board.length ; j++)
					board[j][i] = false;

		return cleared;
		/* TODO: ensure that intersecting fields do not affect score
			in the wrong whey. */
	}
	public ArrayList<Coordinate> getAvailableSpots(boolean[][] piece) {

		ArrayList<Coordinate> results = new ArrayList<>();

		for ( int i = 0 ; i < board.length - piece.length + 1; i++  ) {
			for ( int j = 0 ; j < board[i].length - piece[0].length + 1; j++) {
				/* so now we can work with each possible upper left corner...*/

				boolean works = true;
				for (int k = 0 ; k < piece.length && works ; k++ ) {
					for (int l = 0 ; l < piece[k].length && works; l++) {
						/* check if there is a block at this cell in piece
							AND the corresp. block in board is empty, OR the cell
							in the piece is empty... */
						works = (piece[k][l] && !board[i+k][j+l]) || !piece[k][l];
					}
				}

				if (works)
					results.add(new Coordinate( i , j ) );
				
			}
		}
		return results;
	}

	/* assumes that the piece is being inserted in a valid place.*/
	public void placePiece(boolean[][] piece, Coordinate c) {
		for (int i = 0; i < piece.length; i++) {
			for (int j = 0; j < piece[0].length; j++) {
				board[c.row+i][c.col+j] = board[c.row+i][c.col+j] || piece[i][j];	
			}
		}
	}

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

	public void placePiece(Piece p, Coordinate c) {
		placePiece(p.raw , c);
	}

	public ArrayList<Coordinate> getAvailableSpots(Piece p) {
		return getAvailableSpots(p.raw);
	}















}
